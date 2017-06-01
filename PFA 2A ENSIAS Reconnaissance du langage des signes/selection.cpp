#include "version4.hpp"

Rect selection(Mat image,Rect last)
{
	Rect select;
	int xBmin=image.cols,yBmin=image.rows,xBmax=0,yBmax=0;
	
	for(int i=0;i<image.rows;i++)
	{
		for(int j=0;j<image.cols;j++)
		{
			Vec3b pixel = image.at<Vec3b>(i,j);
			if(pixel.val[0]==255 && pixel.val[1]==255 && pixel.val[2]==255)
			{
				if(j<xBmin) xBmin = j;
				if(j>xBmax) xBmax = j;
				if(i<yBmin) yBmin = i;
				if(i>yBmax) yBmax = i;	
			}
		}
	}
	if( xBmin==image.cols || yBmin==image.rows || xBmax==0 || yBmax==0)
		select = last;
	else if(xBmax-xBmin < 0 || yBmax-yBmin < 0)
		select = Rect(0,0,1,1);
	else
		select = Rect(xBmin,yBmin,200,300);

	return select;
}

