//opencv import
#include <opencv2/objdetect/objdetect.hpp>
#include <opencv2/highgui/highgui.hpp>
#include <opencv2/imgproc/imgproc.hpp>
#include <opencv2/video.hpp>
#include<opencv2/xfeatures2d.hpp>
#include "opencv2/core/core.hpp"


//C++ import
#include <iostream>
#include <vector>
#include<math.h>
#include<string.h>
#include <dirent.h>


using namespace std;
using namespace cv;

//contours.cpp
vector<vector<Point> > getContours (Mat frame); //Récupération des contours dans une image de type Mat
Mat dessinerContours (vector<vector<Point> > contours, Size frame_size ); //permet de dessiner les contours. frame_size doit être égale
									//  au size de l'image dont on a récupéré les contours

Mat dessinerContoursV2 (vector<vector<Point> > contours, Size frame_size);
Mat getContours_dessinerContours (Mat frame);
vector<int> biggest2Contour(vector<vector<Point> > contours);
Mat dessinerContoursV3(vector<vector<Point> > contours, Size frame_size);
vector<Point> getHandContoursV1(vector<vector<Point> > contours, Size frame_size);
Rect zoneContour(vector<Point> contour);
Mat dessinerContoursV4(vector<vector<Point> > contours, Size frame_size,Rect head);


//head.cpp
Mat deleteHead (Rect head, Mat image); //image == binary image with threshold
vector<Rect> getVisage(Mat frame,String face_cascade_name ,CascadeClassifier face_cascade);
Rect getHead (Rect face);
vector<Rect> getAllHead( vector<Rect> faces);
Rect getHeadRect(vector<Rect> faces);
int getDiferentToHeadRect(Rect rect1, Rect rect2, Rect rectComp);

//mvt.cpp
Mat deleteBackground(Mat frame,Ptr<BackgroundSubtractor> bg);

//peau.cpp
vector<Scalar> getColor(Mat image);
vector<vector<Scalar> > calibrage(VideoCapture cap,string windowsName,int code);
vector<vector<Scalar> > calibrageV2(VideoCapture cap,string windowsName,int code);
Mat processColor(Mat image, vector<vector<Scalar> > interColor);



//freeman.cpp
//vector<int> getFreemanCodage (vector<Point> contour)
vector<int> getFreemanCodage (vector<Point> contour);
void writeFreeman(vector<vector<Point> > contours);
vector<vector<int > > getFreemans(vector<vector<Point> > contours);
int calcFreemanZigZag(vector<int> freeman);
int getMoreCloseToHandFreeman(vector<int> freeman1, int idxFreeman1, vector<int> freeman2, int idxFreeman2);
int calcFreemanLines(vector<int> freeman);


//selection.cpp
Rect selection(Mat image,Rect last);

//reconnaissance.cpp
//vector<Mat> list_images();
vector<Mat> list_images(string cheminAlphabet);
int calcul_nombre_doigts(Mat& img);
void image_similaire(Mat& img,vector<Mat> list_img_preselect,Mat& img_sim);

Point high_point(vector<Point> contour);
bool sans_doigts(vector<Point> contour,vector<Point> hull);
Point low_point(vector<Point> contour);
double distanceP2P(Point a, Point b);
double getAngle(Point s, Point f, Point e);
bool appartient_convexe(Point p,vector<Point> hull);
int calcul_nombre_doigts2(Mat& img,Mat& drawing);
double ratio(Mat& img_1,Mat& img_2);
int image_similaire_sift(Mat& img_1,vector<Mat> list_img);

int image_similaire_freeman(Mat& img,vector<Mat> list_img);
double distanceF2F(vector<int> f1,vector<int> f2);


//reconnaissance_y.cpp
vector<double> calcHuMoments (vector<Point> contour);

