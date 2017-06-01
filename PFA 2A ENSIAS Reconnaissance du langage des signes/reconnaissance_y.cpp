#include "version4.hpp"

vector<double> calcHuMoments (vector<Point> contour)
{
	vector<double> hu_moments;
	Moments moment = moments( contour );
	HuMoments( moment , hu_moments );
	return hu_moments;
}


double calc_Pochhammer( double a, int v)
{
	double av = a;
	for(int i =1; i<v; i++)
	{
		av = av * (av+(double)i);
	}
	return av;
}

double calc_factoriel(int a)
{
	double fact = (double)a;
	for(int i = a-1; i>0; i--)
	{
		fact = fact * (double)i;
	}
	return fact;
}

double calc_hyper_geometric(double a1, double a2, double a3, double b1, double b2, int z, int n)
{
	double hypGeo = 0;
	for(int v=0; v<=n; v++)
	{
		hypGeo = hypGeo + ( (calc_Pochhammer(a1,v) *calc_Pochhammer(a2,v) *calc_Pochhammer(a3,v) * pow (z,v))/(calc_Pochhammer(a1,v) *calc_Pochhammer(a2,v) * calc_factoriel(v)) );
	}
	return hypGeo;
}

double calc_discrete_Tchebichef_polynomials(int p, int N, double x)
{
	double Tx;
	
	T = calc_Pochhammer( 1-N, p) *calc_hyper_geometric(-p, -x, 1+p, 1-N, 1, 1, N);

	return T;
}

double calc_Ro(int p, int N)
{
	double Ro = (double)N;
	for(int i=1; i<=p ; i++)
	{
		Ro =Ro * (1-pow(i,2)/pow(N,2));
	}
	
	Ro = Ro / (double)(2*p+1)
	
	return Ro;
}

double calc_t_prime(int p, int N, double x)
{

	double t_prime;
	t_prime = calc_discrete_Tchebichef_polynomials(p,N,x)/sqrt(calc_Ro(p,N));
	return t_prime;
}

double calc_discrete_Tchebichef_moments(Mat image, int p)
{
	double T = 0;

	for(int i=0;i<image.rows;i++)
	{
		for(int j=0;j<image.cols;j++)
		{
			uchar pixel = image.at<uchar>(i,j);
			T = T + calc_t_prime(p, image.rows,image.cols) * calc_t_prime(p, image.rows,image.rows) * pixel;
		}
	}

	return T;

}








