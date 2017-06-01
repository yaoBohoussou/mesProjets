#include "version4.hpp"

Mat deleteHead (Rect head, Mat image) //image == binary image with threshold
{
	Mat resultat;
	int x_head,y_head,w_head,h_head;
	
	x_head = head.x;
	y_head = head.y;
	w_head = head.width;
	h_head = head.height;
	
	resultat = Mat(image.size(), CV_8UC1);

	for(int i = 0; i< image.rows; i++)
	{
		for(int j = 0; j< image.cols; j++)
		{
			if(i > y_head && i < y_head+h_head && j > x_head && j < x_head+w_head)
				resultat.at<uchar>(i,j) = 0;
			else
				resultat.at<uchar>(i,j) = image.at<uchar>(i,j);
		}
	}


	return resultat;
}

vector<Rect> getVisage(Mat frame,String face_cascade_name ,CascadeClassifier face_cascade)
{
	vector<Rect> faces;
	int x,y;
	if( !face_cascade.load( face_cascade_name ) ){ printf("--(!)Error loading face cascade\n"); return Mat(); };
	face_cascade.detectMultiScale( frame, faces, 1.1, 2, 0|CASCADE_SCALE_IMAGE, Size(30, 30) );
	return faces;
}

Rect getHead (Rect face)
{
	Rect head;
	int x_face,y_face,x_head,y_head,w_face,h_face,w_head,h_head;
	
	x_face = face.x;
	y_face = face.y;
	w_face = face.width;
	h_face = face.height;
	
	x_head = x_face - 15;
	y_head = y_face - 40;
	w_head = w_face + 15;
	h_head = h_face + 60;
	
	if(x_head > 0 && y_head > 0 && w_head > 0 && h_head > 0)
		head = Rect(x_head,y_head,w_head,h_head);
	else
		head = face;

	return head;
}

vector<Rect> getAllHead( vector<Rect> faces)
{
	vector<Rect> heads;
	
	for(int i = 0; i < (int)faces.size(); i++)
	{
		Rect head = getHead (faces[i]);
		heads.push_back(head);
	}

	return heads;
}

Rect getHeadRect(vector<Rect> faces)
{
	Rect head, headcour;
	int x_precdif = 1000, y_precdif = 1000;
	int i=0;
	for(vector<Rect>::iterator it = faces.begin(); it!=faces.end(); ++it)
	{
		if(i==0) head = *it;
		else
		{
			headcour = *it;
			if(head.area()< headcour.area())
				head = headcour;
		}
	}
	return head;
}

int getDiferentToHeadRect(Rect rect1, Rect rect2, Rect rectComp)
{
	Rect res;
	int x_r1Diff,y_r1Diff,x_r2Diff,y_r2Diff;
	
	x_r1Diff = abs(rect1.x-rectComp.x);
	y_r1Diff = abs(rect1.y-rectComp.y);
	x_r2Diff = abs(rect2.x-rectComp.x);
	y_r2Diff = abs(rect2.y-rectComp.y);
	
	if(x_r1Diff < x_r2Diff && y_r1Diff < y_r2Diff)
		return 2;
	else if(x_r1Diff > x_r2Diff && y_r1Diff > y_r2Diff)
		return 1;
	else
		return 1;
	
}
