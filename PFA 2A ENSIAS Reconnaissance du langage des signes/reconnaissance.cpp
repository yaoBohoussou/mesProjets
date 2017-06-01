#include"version4.hpp"
const float RATIO = 0.8f;
const float inlier_threshold = 100.0f;
#define PI 3.14

/*vector<Mat> list_images(){

   string chemin = "alphabet";
   
   DIR *rep = opendir("alphabet"); 
   vector<Mat> list_img;

   if (rep != NULL) {
 
        struct dirent * ent; 
        
        while ((ent = readdir(rep)) != NULL) { 
       		if( (strcmp(ent->d_name,".") !=0) &&  (strcmp(ent->d_name,"..")!=0) ){
			
			 Mat img = imread(chemin+"/"+ent->d_name,0);
			 list_img.push_back(img);	
			 //cout << chemin+"/"+ent->d_name << endl; 
		}
        } 
  
        closedir(rep); 
    }
    
    return list_img; 
}*/

vector<Mat> list_images(string cheminAlphabet)
{
	vector<Mat> alphabet;
	string nomIm;
string noms[13] ={"A.png","B.png","S.png","K.png","D.png","V.png","i1.png","i2.png","i3.png","i4.png","i5.png","iB.png","ip.png"};
//	string noms[7] = {"A1.png","B1.png","S1.png","K1.png","D1.png","U1.png","V1.png"};		
	for(int i=0;i<13;i++)
	{
		nomIm = cheminAlphabet+noms[i];
		Mat img = imread(nomIm,CV_LOAD_IMAGE_GRAYSCALE);
		//imshow("imgsimi",img);
		//waitKey(1000);	
		alphabet.push_back(img);
	}
	return alphabet;
}



int calcul_nombre_doigts(Mat& img){

	vector<vector<Point> > contours;
  	vector<Vec4i> hierarchy;
	int nombre_doigts = 0;

	findContours( img,contours, hierarchy,RETR_TREE, CHAIN_APPROX_SIMPLE, Point(0, 0) );
	vector<vector<Point> > hull(contours.size());
	vector<vector<int> > hullI(contours.size()); 

	vector<vector<Vec4i> > defects(contours.size());


	convexHull( Mat(contours[0]), hull[0], false );
	convexHull( Mat(contours[0]), hullI[0], false );
    	convexityDefects(contours[0], hullI[0], defects[0]);
     
	//drawContours( drawing, contours, 0, Scalar(255,0,0), 1, 8, vector<Vec4i>(), 0, Point() );
	//drawContours( drawing, hull, 0, Scalar(255,255,255), 1, 8, vector<Vec4i>(), 0, Point() );

	for(int j=0;j<defects[0].size();j++){

		const Vec4i& v = defects[0][j];
         	float depth = v[3]/256;
		
         	if (depth > 20 && depth < 90 ){

       			int startidx = v[0]; Point ptStart(contours[0][startidx]);
                  	int endidx = v[1]; Point ptEnd(contours[0][endidx]);
                    	int faridx = v[2]; Point ptFar(contours[0][faridx]);

			/*
                   	line(drawing, ptStart, ptEnd, Scalar(0, 255, 0), 1);
                    	line(drawing, ptStart, ptFar, Scalar(0, 255, 0), 1);
                   	line(drawing, ptEnd, ptFar, Scalar(0, 255, 0), 1);
                   	circle(drawing, ptFar, 4, Scalar(0, 255, 0), 2);
			circle(drawing, ptEnd, 4, Scalar(0, 0,255), 2);
		         circle(drawing, ptStart, 4, Scalar(255, 255,255), 2);
			*/

			nombre_doigts++;
                }
	}

	return nombre_doigts;

}

Point high_point(vector<Point> contour){

	Point point = contour[0];

	for(int i=1;i< contour.size();i++){
		
		if(contour[i].y< point.y)
			point = contour[i];
	}
	
	return point;
}

bool sans_doigts(vector<Point> contour,vector<Point> hull){

	double dc = contourArea(contour);
	double dh = contourArea(hull);

	/*printf("dh = %f\n",dh);
	printf("dc = %f\n",dc);
	printf("diff = %f\n",dh-dc);*/

	if(dh - dc < 1000)
		return true; 
	return false;
}

Point low_point(vector<Point> contour){

	Point point = contour[0];

	for(int i=1;i< contour.size();i++){
		
		if(contour[i].y > point.y)
			point = contour[i];
	}
	
	return point;
}

double distanceP2P(Point a, Point b){
	double d= sqrt(fabs( pow(a.x-b.x,2) + pow(a.y-b.y,2) )) ;  
	return d;
}

double getAngle(Point s, Point f, Point e){

	double l1 = distanceP2P(f,s);
	double l2 = distanceP2P(f,e);
	double dot=(s.x-f.x)*(e.x-f.x) + (s.y-f.y)*(e.y-f.y);
	double angle = acos(dot/(l1*l2));
	angle=angle*180/PI;
	return angle;
}


bool appartient_convexe(Point p,vector<Point> hull){

	for(int i=0;i<hull.size();i++){

		if(hull[i].x == p.x && hull[i].y == p.y)
			return true;
	
	}
	return false;
}

int calcul_nombre_doigts2(Mat& img,Mat& drawing){

	vector<vector<Point> > contours;
  	vector<Vec4i> hierarchy;
	int nombre_interstices = 0;

	findContours( img,contours, hierarchy,RETR_TREE, CHAIN_APPROX_SIMPLE, Point(0, 0) );
	vector<vector<Point> > hull(contours.size());
	vector<vector<int> > hullI(contours.size()); 

	vector<vector<Vec4i> > defects(contours.size());

	//for(int i=0;i<contours.size();i++)
	convexHull( Mat(contours[0]), hull[0], false );
	convexHull( Mat(contours[0]), hullI[0], false );
    	convexityDefects(contours[0], hullI[0], defects[0]);

	//points 
	Point h_point = high_point(contours[0]);
	Point l_point = low_point(contours[0]);
	Point project_l_point;
	project_l_point.x = l_point.x,
	project_l_point.y = h_point.y;
	double height = distanceP2P(l_point,project_l_point);

	//circle(drawing, h_point, 4, Scalar(255,0,0), 2);
     	//circle(drawing, l_point, 4, Scalar(255,0,0), 2);

	drawContours( drawing, contours, 0, Scalar(255,0,0), 1, 8, vector<Vec4i>(), 0, Point() );
	drawContours( drawing, hull, 0, Scalar(255,255,255), 1, 8, vector<Vec4i>(), 0, Point() );

	//distance et angle de tolerance
	int tolerance =  height/5;
	double angleTol=95;

	for(int j=0;j<defects[0].size();j++){

		const Vec4i& v = defects[0][j];
         	float depth = v[3]/256;
		int startidx = v[0]; Point ptStart(contours[0][startidx]);
                  int endidx = v[1]; Point ptEnd(contours[0][endidx]);
                 	int faridx = v[2]; Point ptFar(contours[0][faridx]);

		Point project;
		project.x = ptFar.x;
		project.y = ptEnd.y;

		double angle = getAngle(ptStart,ptFar,ptEnd);


         	if(distanceP2P(ptStart, ptFar) > tolerance && distanceP2P(ptEnd, ptFar) > tolerance &&  angle < angleTol ){
			
			/*if( ptEnd.y > (project_l_point.y + height - height/4 ) ){
			}else if( ptStart.y > (project_l_point.y + height - height/4 ) ){
			}else {*/

				if(!appartient_convexe(ptFar,hull[0])){
			
					//printf("angle %i = %f\n",j,angle);			
	                   		line(drawing, ptStart, ptEnd, Scalar(0,0,255), 1);
	                    		line(drawing, ptStart, ptFar, Scalar(0,0, 255), 1);
	                   		line(drawing, ptEnd, ptFar, Scalar(0,0, 255), 1);
					//line(drawing, project, ptFar, Scalar(0,0, 255), 1);
					//line(drawing, project, ptEnd, Scalar(0,0, 255), 1);
	
	                   		circle(drawing, ptFar, 4, Scalar(0, 255, 0), 2);
					circle(drawing, ptEnd, 4, Scalar(0, 0,255), 2);
			        		//circle(drawing, project, 4, Scalar(255, 255,255), 2);
					circle(drawing, ptStart, 4, Scalar(255, 0,0), 2);
					nombre_interstices++; 
				}
			//}			
		}
        }
	//On compte le nombre de doigts
	if(nombre_interstices == 0){
		if(!sans_doigts(contours[0],hull[0]))
			nombre_interstices++;
	}
	else{
		nombre_interstices++;
	}

	return nombre_interstices;
}

void image_similaire(Mat& img,vector<Mat> list_img_preselect,Mat& img_sim){
	
	Mat img_similaire = list_img_preselect[list_img_preselect.size()-1].clone();
	//int indice_img_similaire = list_img_preselect[list_img_preselect.size()-1];

	double dist = matchShapes(img,img_similaire,CV_CONTOURS_MATCH_I3,0);
	list_img_preselect.pop_back();

	for(int i = 0 ;i< list_img_preselect.size();i++){
		
		Mat img_temp = list_img_preselect[list_img_preselect.size()-1].clone();
		double dist_temp = matchShapes(img,img_temp,CV_CONTOURS_MATCH_I3,0);	
		if( dist_temp < dist){
			
			dist = dist_temp;
			img_similaire = list_img_preselect[list_img_preselect.size()-1].clone();

			//indice_img_similaire = list_img_preselect[list_img_preselect.size()-1];
		}

		list_img_preselect.pop_back();
	}

	img_sim = img_similaire.clone();
	/*vector<int> compression_params;
			compression_params.push_back(CV_IMWRITE_PNG_COMPRESSION);
			imwrite( "img_sim.png", img_similaire, compression_params);*/
	
}

/*double ratio(Mat& img_1,Mat& img_2){

	Ptr<Feature2D> f2d = xfeatures2d::SIFT::create();
	vector<KeyPoint> keypoints_1, keypoints_2;
	
	f2d->detect(img_1,keypoints_1);
	f2d->detect(img_2,keypoints_2);

	//printf("keypts1 = %d\n",(int)keypoints_1.size());
	//printf("kepts2 = %d\n",(int)keypoints_2.size());

	Mat descriptors_1, descriptors_2;
	
	f2d->compute(img_1, keypoints_1, descriptors_1);
	f2d->compute(img_2,keypoints_2,descriptors_2);
	
	BFMatcher matcher;
  	vector<vector<DMatch> > nn_matches;  
	
	matcher.knnMatch( descriptors_1, descriptors_2, nn_matches,2);
	
	vector<DMatch> good_matches,pre_good_matches;
	vector<Point2f> good_pts1, good_pts2;
	vector<KeyPoint> good_keypts1, good_keypts2;

	for(int i = 0;i<nn_matches.size();i++){

		DMatch first = nn_matches[i][0];
		float dist1 = nn_matches[i][0].distance;
		float dist2 = nn_matches[i][1].distance;

		if(dist1 < RATIO*dist2){

			good_keypts1.push_back(keypoints_1[first.queryIdx]);
			good_keypts2.push_back(keypoints_2[first.trainIdx]);
			good_pts1.push_back(keypoints_1[first.queryIdx].pt);
			good_pts2.push_back(keypoints_2[first.trainIdx].pt);

		}

	}

	Mat homography;double inlier_ratio = 0;
    	if(good_pts1.size()>=4){

		homography = findHomography(Mat(good_pts1),Mat(good_pts2),CV_RANSAC,5);

		vector<KeyPoint> inliers1, inliers2;

		for(unsigned i = 0; i < good_keypts1.size(); i++) {

      		Mat col = Mat::ones(3, 1, CV_64F);
       		col.at<double>(0) = good_keypts1[i].pt.x;
        		col.at<double>(1) = good_keypts1[i].pt.y;

     		col = homography * col;
        		col /= col.at<double>(2);
        		double dist = sqrt( pow(col.at<double>(0) - good_keypts2[i].pt.x, 2) + pow(col.at<double>(1) - good_keypts2[i].pt.y, 2));
        		if(dist < inlier_threshold) {
            		int new_i = static_cast<int>(inliers1.size());
            		inliers1.push_back(good_keypts1[i]);
          		inliers2.push_back(good_keypts2[i]);
           		good_matches.push_back(DMatch(new_i, new_i, 0));
     	   	}
    		}
	

    		Mat res;
    		//drawMatches(img_1, inliers1, img_2, inliers2, good_matches, res);
    		//imwrite("res.png", res);

    		inlier_ratio = (double) inliers1.size() / (double) good_keypts1.size();

		printf("%f\t",inlier_ratio);
		//printf("inliers = %d\n",(int)inliers1.size());
		//printf("good_keyts = %d\n",(int)good_keypts1.size());
		
		//printf("ddd = %f\n",inlier_ratio);
	}

	return inlier_ratio;
	
}

int image_similaire_sift(Mat& img_1,vector<Mat> list_img){

	vector<double> list_ratio;
	
	for(int i=0;i<list_img.size();i++){

	 	double r = ratio(list_img[i],img_1);
		list_ratio.push_back(r);
	}

	int index = list_ratio.size()-1;	
	if(index == -1)
		return index;

	double biggest_ratio = list_ratio[index];
	list_ratio.pop_back();

	while(list_ratio.size()>0){

		if(biggest_ratio < list_ratio[list_ratio.size()-1] ){

			biggest_ratio = list_ratio[list_ratio.size()-1];
			index = list_ratio.size()-1;	
		}

		list_ratio.pop_back();

	}	
	return index;

}
*/




//Add

vector<int> histogramme_directions(vector<int> f1){

	vector<int> directions(8,0);

	for(int i = 0;i<f1.size();i++){
		
		if(f1[i] == 1)
			directions[0]++;
		else if(f1[i] == 2)
			directions[1]++;
		else if(f1[i] == 3)
			directions[2]++;
		else if(f1[i] == 4)
			directions[3]++;
		else if(f1[i] == 5)
			directions[4]++;
		else if(f1[i] == 6)
			directions[5]++;
		else if(f1[i] == 7)
			directions[6]++;
		else 
			directions[7]++;
			
	}
	return directions;
}

double distanceF2F(vector<int> f1,vector<int> f2){
	
	double distance = 0;
	
	vector<int> directions1 = histogramme_directions(f1);
	vector<int> directions2 = histogramme_directions(f2);
	
	for(int i=0;i<directions1.size();i++){
		
		distance += pow(directions1[i] - directions2[i],2);
	}

	distance = sqrt(distance);

	//printf("distance = %f\n",distance);	
	return distance;

}

int image_similaire_freeman(Mat& img,vector<Mat> list_img){

	vector<vector<Point> > contours_img;
	vector<vector<Point> > contours;
	vector<Vec4i> hierarchy;

	cout << "yyyyyy" << endl;
	findContours( img,contours_img, hierarchy,RETR_TREE, CHAIN_APPROX_SIMPLE, Point(0, 0) );
	cout << "yyyyyy" << endl;
	vector<int> freemans_img = getFreemanCodage(contours_img[0]);

	findContours( list_img[0],contours, hierarchy,RETR_TREE, CHAIN_APPROX_SIMPLE, Point(0, 0) );
	vector<int> freemans = getFreemanCodage(contours[0]);

	double distance = distanceF2F(freemans_img,freemans);

	int index = 0;

	for(int i=1;i<list_img.size();i++){

		findContours( list_img[i],contours, hierarchy,RETR_TREE, CHAIN_APPROX_SIMPLE, Point(0, 0) );
		freemans = getFreemanCodage(contours[0]);
		double dist_temp = distanceF2F(freemans_img,freemans);
		if( dist_temp < distance){

			distance = dist_temp;
			index = i;
		}
	}

	return index;	
}

