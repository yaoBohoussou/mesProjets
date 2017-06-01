#include "version4.hpp"


vector<vector<Point> > getContours (Mat frame)
{
	vector<vector<Point> > contours;
	vector<Vec4i> hierarchy;
	Mat contours1;
	
	Canny( frame, contours1, 100, 255, 3 );
	dilate( contours1, contours1, Mat(), Point(-1,-1),1, BORDER_CONSTANT, morphologyDefaultBorderValue() );
	findContours( contours1, contours, hierarchy, RETR_EXTERNAL, CHAIN_APPROX_SIMPLE, Point(0, 0) );

	return contours;
}

Mat getContours_dessinerContours (Mat frame)
{
	Mat cont1,cont2;
	vector<vector<Point> > contours;
	vector<Vec4i> hierarchy;
	Scalar color = Scalar( 255, 255, 255 );
	
	Canny( frame, cont1, 100, 255, 3 );
	dilate( cont1, cont1, Mat(), Point(-1,-1),1, BORDER_CONSTANT, morphologyDefaultBorderValue() );
	findContours( cont1, contours, hierarchy, RETR_EXTERNAL, CHAIN_APPROX_SIMPLE, Point(0, 0) );
	cont2 = Mat::zeros( frame.size(), CV_8UC3 );
  	for( size_t i = 0; i< contours.size(); i++ )
     	{
       		drawContours( cont2, contours, (int)i, color, CV_FILLED, 8, hierarchy, 0, Point() );	
     	}
	return cont2;
}

int biggestContour(vector<vector<Point> > contours)
{
	int maxSize=0;
	int index=0;
	double maxarea=0;
	for( size_t i = 0; i< contours.size(); i++ )
     	{
		if(contourArea( contours[i]) > maxarea)
		{
			index = (int)i;
		}
     	}

	return index;

}

vector<int> biggest2Contour(vector<vector<Point> > contours)
{
	double maxSize=0;
	int index=0, index2=0;
	for( size_t i = 0; i< contours.size(); i++ )
     	{
		if(contourArea( contours[i]) > maxSize)
		{
			index2 = index;
			index = (int)i;
			maxSize = (int)contours[i].size();
		}
     	}
	vector<int> ls;
	ls.push_back(index);
	ls.push_back(index2);

	return ls;

}

Mat dessinerContours (vector<vector<Point> > contours, Size frame_size)//, vector<Vec4i> hierarchy )
{
	Mat im_Contours = Mat::zeros( frame_size, CV_8UC3 ) ;
	Scalar color = Scalar( 255, 255, 255 );

	int bg = biggestContour(contours);

	for( size_t i = 0; i< contours.size(); i++ )
     	{
       		drawContours( im_Contours, contours, (int)i, color, CV_FILLED, 8);//, hierarchy, 0, Point() );	
     	}

	drawContours( im_Contours, contours, bg, Scalar(10,155,220), CV_FILLED, 8);

	return im_Contours;
}

Mat dessinerContoursV2 (vector<vector<Point> > contours, Size frame_size)//, vector<Vec4i> hierarchy )
{
	Mat im_Contours = Mat::zeros( frame_size, CV_8UC3 ) ;
	Scalar color = Scalar( 255, 255, 255 );

	vector<vector<Point> > bgcontours;
	
	//writeFreeman(contours);


	//int bg = biggestContour(contours);
	vector<int> bg = biggest2Contour(contours);

	drawContours( im_Contours, contours, bg[0], Scalar(10,155,220), CV_FILLED, 8);
	//contours.erase(contours.begin()+(bg-1));
		
	//bg = biggestContour(contours);
	drawContours( im_Contours, contours, bg[1], Scalar(220,155,10), CV_FILLED, 8);

	return im_Contours;
}

Mat dessinerContoursV3(vector<vector<Point> > contours, Size frame_size)
{
	vector<int> bg = biggest2Contour(contours);
	vector<vector<Point> > bgContours;
	bgContours.push_back(contours[bg[0]]);
	bgContours.push_back(contours[bg[1]]);
	vector<vector<int > > freemans = getFreemans(bgContours);
	int idxHand = getMoreCloseToHandFreeman(freemans[0], 0, freemans[1], 1);

	Mat im_Contours = Mat::zeros( frame_size, CV_8UC3 ) ;
	Scalar color = Scalar( 255, 255, 255 );
	drawContours( im_Contours, bgContours, idxHand, Scalar(10,155,220), CV_FILLED, 8);
	return im_Contours;
}

Mat dessinerContoursV4(vector<vector<Point> > contours, Size frame_size,Rect head)
{
	Mat im_Contours = Mat::zeros( frame_size, CV_8UC3 ) ;
	Scalar color = Scalar( 255, 255, 255 );
	vector<int> bg = biggest2Contour(contours);

	Rect bgCont1,bgCont2;
	bgCont1 = zoneContour(contours[bg[0]]);
	bgCont2 = zoneContour(contours[bg[1]]);
	
	int moreClose = getDiferentToHeadRect(bgCont1,bgCont2,head);
	
	if(moreClose == 1)
		drawContours( im_Contours, contours, bg[0], Scalar(10,155,220), CV_FILLED, 8);
	else
		drawContours( im_Contours, contours, bg[1], Scalar(10,155,220), CV_FILLED, 8);

	return im_Contours;
}

vector<Point> getHandContoursV1(vector<vector<Point> > contours, Size frame_size)
{
	vector<int> bg = biggest2Contour(contours);
	vector<vector<Point> > bgContours;
	bgContours.push_back(contours[bg[0]]);
	bgContours.push_back(contours[bg[1]]);
	vector<vector<int > > freemans = getFreemans(bgContours);
	int idxHand = getMoreCloseToHandFreeman(freemans[0], 0, freemans[1], 1);

	return bgContours[idxHand];
	
}

Rect zoneContour(vector<Point> contour)
{
	Rect contourRect = Rect(0,0,1,1);
	int xBmin=1000,yBmin=1000,xBmax=0,yBmax=0;
	Point pt;
	for(vector<Point>::iterator it = contour.begin(); it!=contour.end();++it)
	{
		pt = *it;
		if(pt.x < xBmin) xBmin = pt.x;
		if(pt.y < yBmin) yBmin = pt.y;
		if(pt.x > xBmax) xBmax = pt.x;
		if(pt.y > yBmax) yBmax = pt.y;
	}
	//if(xBmin!=1000 && yBmin!=1000 && xBmax!=0 && yBmax!=0 && xBmin>8 && yBmin>8)
	//{
		contourRect = Rect(xBmin,yBmin,xBmax-xBmin,yBmax-yBmin);
		//contourRect = Rect(xBmin-8,yBmin-8,xBmax-xBmin+8,yBmax-yBmin+8);
	//}

	return contourRect;
}
