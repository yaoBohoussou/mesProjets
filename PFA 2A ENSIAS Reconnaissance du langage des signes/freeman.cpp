#include "version4.hpp"

vector<int> getFreemanCodage (vector<Point> contour)
{
	vector<int> freemanCode;
	Point prec,pt;
	int pt_x, pt_y, prec_x, prec_y;
	int i = 0;
	for (vector<Point>::iterator it = contour.begin() ; it != contour.end(); ++it)
	{
		if(i == 0){prec = *it;i+=1;}
		else
		{			
			pt = *it;
			pt_x = pt.x;
			pt_y = pt.y;
			prec_x = prec.x;
			prec_y = prec.y;
			if(pt_x == prec_x+1 && pt_y == prec_y)
				freemanCode.push_back(1);
			else if(pt_x == prec_x+1 && pt_y == prec_y+1)
				freemanCode.push_back(2);
			else if(pt_x == prec_x && pt_y == prec_y+1)
				freemanCode.push_back(3);
			else if(pt_x == prec_x-1 && pt_y == prec_y+1)
				freemanCode.push_back(4);
			else if(pt_x == prec_x-1 && pt_y == prec_y)
				freemanCode.push_back(5);
			else if(pt_x == prec_x-1 && pt_y == prec_y-1)
				freemanCode.push_back(6);
			else if(pt_x == prec_x && pt_y == prec_y-1)
				freemanCode.push_back(7);
			else if(pt_x == prec_x+1 && pt_y == prec_y-1)
				freemanCode.push_back(8);
			
			prec = pt;
		}
	}
	return freemanCode;
}

void writeFreeman(vector<vector<Point> > contours)
{
	vector<int> freeman;
	for (vector<vector<Point> >::iterator it = contours.begin() ; it != contours.end(); ++it)
	{
		freeman = getFreemanCodage (*it);
		for (vector<int>::iterator it_int = freeman.begin() ; it_int != freeman.end(); ++it_int)
		{
			cout << *it_int << " ";	
		}
		cout << "fin" << endl;
	}
}

vector<vector<int > > getFreemans(vector<vector<Point> > contours)
{
	vector<vector<int > > freemans;
	for (vector<vector<Point> >::iterator it = contours.begin() ; it != contours.end(); ++it)
	{
		vector<int> freeman;
		freeman = getFreemanCodage (*it);
		freemans.push_back(freeman);
	}
	return freemans;
}

int calcFreemanZigZag(vector<int> freeman)
{
	int zigzag=0;
	int prec = -1;
	for (vector<int>::iterator it_int = freeman.begin() ; it_int != freeman.end(); ++it_int)
	{
		if(prec == -1)
		{
			prec = *it_int;
		}
		else
		{
			if(abs(*it_int - prec) >= 2)
				zigzag+=1;
			prec = *it_int;
		}
	}

	return zigzag;
}

int calcFreemanLines(vector<int> freeman)
{
	int line=0;
	int prec = -1;
	for (vector<int>::iterator it_int = freeman.begin() ; it_int != freeman.end(); ++it_int)
	{
		if(prec == -1)
		{
			prec = *it_int;
		}
		else
		{
			if(abs(*it_int - prec) == 0)
				line+=1;
			prec = *it_int;
		}
	}

	return line;
}

int getMoreCloseToHandFreeman(vector<int> freeman1, int idxFreeman1, vector<int> freeman2, int idxFreeman2)
{
	//if(calcFreemanZigZag(freeman1) > calcFreemanZigZag(freeman2))
	if(calcFreemanLines(freeman1) > calcFreemanLines(freeman2))
		return idxFreeman1;
	else
		return idxFreeman2;
}
