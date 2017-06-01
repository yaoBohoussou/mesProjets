#include "version4.hpp"

Mat deleteBackground(Mat frame,Ptr<BackgroundSubtractor> bg)
{
	Mat mask;
	bg->apply( frame, mask);
	return mask;
}
