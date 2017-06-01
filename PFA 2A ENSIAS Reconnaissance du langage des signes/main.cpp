#include "version4.hpp"

int main()
{
	Mat frame_courante, frame_courante_Blur;
	vector<vector<Point> > contours;
	
	VideoCapture cap(0);
	if(!cap.isOpened()) 
		return -1;
	namedWindow( "Video", CV_WINDOW_AUTOSIZE );
	namedWindow( "imgsimi", CV_WINDOW_AUTOSIZE );
	//namedWindow( "imgsimi1", CV_WINDOW_AUTOSIZE );
	//namedWindow( "detection", CV_WINDOW_AUTOSIZE );


//------------------------
Mat frame_contours, frameHSV, framePeauHSV, framePeauHSVreverse, frameY, framePeauY, framePeauYreverse, frameHelp ;
//Mat laMain, laMainContours;
int key;

vector<vector<Scalar> > intervallesHSV = calibrage(cap,"Video",COLOR_BGR2HSV);
vector<vector<Scalar> > intervallesHSVreverse = calibrage(cap,"Video",COLOR_BGR2HSV);
vector<vector<Scalar> > intervallesY = calibrage(cap,"Video",COLOR_BGR2YCrCb);
vector<vector<Scalar> > intervallesYreverse = calibrage(cap,"Video",COLOR_BGR2YCrCb);
vector<Point> handContour;
Rect hand,head_;

Rect roiRoi1 = Rect(0,0,200,200);

//-----------------------------------------------
vector<Rect> faces;
String face_cascade_name = "/usr/local/share/OpenCV/haarcascades/haarcascade_frontalface_alt.xml";
CascadeClassifier face_cascade;
face_cascade.load( face_cascade_name );
Rect head;
Mat imageHellp;



///---------rec
string cheminAlphabet = "/home/yannbohoussou/pfa/projet_1/version4/alphabet/";
vector<Mat> images = list_images(cheminAlphabet);//(cheminAlphabet);

cout << " size = " << images.size() << endl << endl ;
vector<Mat> list_img_preselect;
int nombredoigtsFrameCour, nombredoigtsAlphabet;
Mat img_sim, signe ;


	for(;;)
	{
		cap >> frame_courante;

		//faces = getVisage(frame_courante, face_cascade_name , face_cascade);

		face_cascade.detectMultiScale( frame_courante, faces, 1.1, 2, 0|CASCADE_SCALE_IMAGE, Size(30, 30) );
		head = getHeadRect(faces);

	

		//
		GaussianBlur(frame_courante, frameHSV, Size(7,7), 1.5, 1.5);
		cvtColor(frameHSV,frameHSV,COLOR_BGR2HSV);
		framePeauHSV =  processColor(frameHSV, intervallesHSV);
		framePeauHSVreverse =  processColor(frameHSV, intervallesHSVreverse);
		dilate( framePeauHSV, framePeauHSV, Mat(), Point(-1,-1),1, BORDER_CONSTANT, morphologyDefaultBorderValue() );

		GaussianBlur(frame_courante, frameY, Size(7,7), 1.5, 1.5);
		cvtColor(frameY,frameY,COLOR_BGR2YCrCb);
		framePeauY =  processColor(frameY, intervallesY);
		framePeauYreverse = processColor(frameHSV, intervallesYreverse);
		dilate( framePeauY, framePeauY, Mat(), Point(-1,-1),1, BORDER_CONSTANT, morphologyDefaultBorderValue() );

		
		contours = getContours (framePeauY);
		frame_contours = dessinerContoursV2 (contours, frame_courante.size());

		contours = getContours (framePeauYreverse);	
		frame_contours = frame_contours + dessinerContoursV2 (contours, frame_courante.size());	

		contours = getContours (framePeauHSV);	
		frame_contours = frame_contours + dessinerContoursV2 (contours, frame_courante.size());	

		contours = getContours (framePeauHSVreverse);	
		frame_contours = frame_contours + dessinerContoursV2 (contours, frame_courante.size());	
		frameHelp = frame_contours.clone();


		contours = getContours (frame_contours);
		frame_contours = dessinerContoursV3 (contours, frame_courante.size());
		//frame_contours = dessinerContoursV4 (contours, frame_courante.size(),head);

		contours = getContours (frame_contours);
				//writeFreeman(contours);
		handContour= getHandContoursV1(contours, frame_courante.size());
		hand = zoneContour(handContour);	
		frame_contours = getContours_dessinerContours (frame_contours);
		cvtColor(frame_contours, frame_contours, COLOR_BGR2GRAY);

		//new
			//laMain = Mat( frame_courante, hand);
			//laMainContours = getContours_dessinerContours (laMain);

		//end new

		rectangle( frame_courante, hand, Scalar(0,0,0) );
		//rectangle( frame_courante, head, Scalar(10,150,255) );


		imshow("Video",frame_courante);
		//imshow("detection",frame_contours);

		if(waitKey(30) >0) 
		{
			/*vector<int> compression_params;
			compression_params.push_back(CV_IMWRITE_PNG_COMPRESSION);
			imwrite( "frameRes0.png", frameHelp, compression_params);
			imwrite( "frameRes1.png", frame_courante, compression_params);
			imwrite( "frameResFin.png", frame_contours, compression_params);*/

					
			Mat imgimg = Mat::zeros( frame_contours.size(), CV_8UC1 );
			Mat imgimg1 = Mat::zeros( frame_contours.size(), CV_8UC1 );

			nombredoigtsFrameCour = calcul_nombre_doigts2(frame_contours, imgimg);
				//imshow("imgsimi1",imgimg);
			cout << " nombre de doigts requête = " <<  nombredoigtsFrameCour << endl;
			cout << " nombre Images = " <<  images.size() << endl;
			for(int i = 0;i<images.size();i++)
			{
				imageHellp = images[i].clone();
				nombredoigtsAlphabet = calcul_nombre_doigts2(imageHellp, imgimg1);
				cout << "===> i = " << i << endl;

				if(nombredoigtsAlphabet == nombredoigtsFrameCour)
				{
					list_img_preselect.push_back(images[i]);
				}
			}

			img_sim = Mat::zeros( frame_contours.size(), CV_8UC1 );

			image_similaire(frame_contours,list_img_preselect,img_sim);
			imshow("imgsimi",img_sim);



			/*//new
			int index = -1;//
			if(list_img_preselect.size() >0)
			{
				index = image_similaire_freeman(frame_contours,list_img_preselect);
			}
			
			if(index !=-1)
				imshow("imgsimi",list_img_preselect[index]);
			//new*/
						

			


			list_img_preselect.empty();

			
			//destroyAllWindows();
			//break;
		}
		
	
	}
	
	return 0;
}
