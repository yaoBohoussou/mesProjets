#include "version4.hpp"

vector<Scalar> getColor(Mat image)
{
	Scalar scM,scm;
	vector<Scalar> listScalar;
	int B = 0,G = 0,R = 0;
	int MB=0,mB=0,MG=0,mG=0,MR=0,mR=0;
	for(int i= 2; i<9;i++)
	{
		for(int j=2; j<9; j++)
		{
			Vec3b pixel = image.at<Vec3b>(i,j);
			B = pixel.val[0];
			if(B>MB)MB=B;
			G = pixel.val[1];
			if(G>MG)MG=G;
			R = pixel.val[2];
			if(R>MR)MR=R;
		}
	}
	mB=MB;mG=MG;mR=MR;
	for(int i= 2; i<9;i++)
	{
		for(int j=2; j<9; j++)
		{
			Vec3b pixel = image.at<Vec3b>(i,j);
			B = pixel.val[0];
			if(B<mB)mB=B;
			G = pixel.val[1];
			if(G<mG)mG=G;
			R = pixel.val[2];
			if(R<mG)mR=R;
		}
	}
	
	scM = Scalar(MB,MG,MR);
	scm = Scalar(mB,mG,mR);
	listScalar.push_back (scM);
	listScalar.push_back (scm);
	return listScalar;
}

vector<vector<Scalar> > calibrage(VideoCapture cap,string windowsName,int code)
{
	vector<vector<Scalar> > intervalles;
	Rect r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,rMain;
	Scalar color = Scalar( 255, 255, 255 );
	Mat e[12],frame;//,eMain;
	r1 = Rect(80,80,10,10);
	r2 = Rect(100,100,10,10);
	r3 = Rect(60,100,10,10);
	r4 = Rect(80,120,10,10);
	r5 = Rect(60,150,10,10);
	r6 = Rect(80,170,10,10);
	r7 = Rect(40,170,10,10);
	r8 = Rect(60,190,10,10);
	r9 = Rect(100,200,10,10);
	r10 = Rect(120,220,10,10);
	r11=Rect(80,220,10,10);
	r12=Rect(100,240,10,10);
	//rMain = Rect(40,80,90,170);

	for(;;)
	{
		cap >> frame;
		GaussianBlur(frame, frame, Size(7,7), 1.5, 1.5);
		cvtColor(frame,frame,code);
		rectangle(frame, r1, color);
		rectangle(frame, r2, color);
		rectangle(frame, r3, color);
		rectangle(frame, r4, color);
		rectangle(frame, r5, color);
		rectangle(frame, r6, color);
		rectangle(frame, r7, color);
		rectangle(frame, r8, color);
		rectangle(frame, r9, color);
		rectangle(frame, r10, color);
		rectangle(frame, r11, color);
		rectangle(frame, r12, color);
		//rectangle(frame, rMain, color);
		imshow(windowsName,frame);
		if(waitKey(30)>0)
		{
			e[0]=Mat(frame, r1);
			e[1]=Mat(frame, r2);
			e[2]=Mat(frame, r3);
			e[3]=Mat(frame, r4);
			e[4]=Mat(frame, r5);
			e[5]=Mat(frame, r6);
			e[6]=Mat(frame, r7);
			e[7]=Mat(frame, r8);
			e[8]=Mat(frame, r9);
			e[9]=Mat(frame, r10);
			e[10]=Mat(frame, r11);
			e[11]=Mat(frame, r12);
			//eMain = Mat(frame,rMain);
			break;
		}
	
	}
	
	for(int i=0; i< 12; i++)
	{
		intervalles.push_back(getColor(e[i]));
	}

	return intervalles;
}



vector<vector<Scalar> > calibrageV2(VideoCapture cap,string windowsName,int code)
{
	vector<vector<Scalar> > intervalles;
	Scalar color = Scalar( 255, 255, 255 );
	Mat e[104],frame;
	Rect r[104],rMain;
	int xdep = 40, ydep = 80, Gwidth = 70, Gheight = 170, mWidth = 10, mHeight = 10, xActual, yActual, nbreEchantillon = 56;
	
	xActual = xdep;
	yActual = ydep;

	for(int i=0; i<nbreEchantillon; i++)
	{
		//cout << "xActual :" << xActual << endl;
		//cout << "yActual :" << yActual << endl;
		
		
		Rect rc = Rect(xActual,yActual,mWidth,mHeight);
		r[i] = rc;

		if(xActual == xdep+Gwidth)
		{
			xActual = xdep;
			yActual = yActual + mHeight;
		}
		else
		{
			xActual = xActual + mWidth;
		}
	}
	

	for(;;)
	{
		cap >> frame;
		GaussianBlur(frame, frame, Size(7,7), 1.5, 1.5);
		cvtColor(frame,frame,code);

		for(int i=0; i<nbreEchantillon; i++)
		{
			rectangle(frame, r[i], color);
		}
		
		imshow(windowsName,frame);
		if(waitKey(30)>0)
		{
			for(int i=0; i<nbreEchantillon; i++)
			{
				e[i]=Mat(frame, r[i]);
			}
			break;
		}
	}

	for(int i=0; i< nbreEchantillon; i++)
	{
		intervalles.push_back(getColor(e[i]));
	}

	return intervalles;
}





Mat processColor(Mat image, vector<vector<Scalar> > interColor)
{
	Mat resultat;
	int nbrIntervalles = (int)interColor.size();
	Mat *reslt = new Mat [ nbrIntervalles ];
	vector<Scalar> color;
	int i = 0;//itérateur pour le tableau de Mat --reslt--
	for (vector<vector<Scalar> >::iterator it = interColor.begin() ; it != interColor.end(); ++it)
	{
		color = *it;
		inRange(image,color[1],color[0],reslt[i]);
		i++;
	}
	
	i=0;
	resultat = reslt[0];
	for(i=1;i<nbrIntervalles;i++)
	{
		resultat = resultat + reslt[i];
	}
		
	return resultat;
}
