version4 : version4.hpp main.o contours.o head.o mvt.o peau.o freeman.o selection.o reconnaissance.o
	g++ -o version4 main.o contours.o head.o mvt.o peau.o freeman.o selection.o reconnaissance.o `pkg-config --cflags opencv` `pkg-config --libs opencv`

main.o : version4.hpp main.cpp
	g++ -c main.cpp `pkg-config --cflags opencv` `pkg-config --libs opencv`

contours.o : version4.hpp contours.cpp
	g++ -c contours.cpp `pkg-config --cflags opencv` `pkg-config --libs opencv`

head.o : version4.hpp head.cpp
	g++ -c head.cpp `pkg-config --cflags opencv` `pkg-config --libs opencv`

mvt.o : version4.hpp mvt.cpp
	g++ -c mvt.cpp `pkg-config --cflags opencv` `pkg-config --libs opencv`

peau.o : version4.hpp peau.cpp
	g++ -c peau.cpp `pkg-config --cflags opencv` `pkg-config --libs opencv`

freeman.o : version4.hpp freeman.cpp
	g++ -c freeman.cpp `pkg-config --cflags opencv` `pkg-config --libs opencv`

selection.o : version4.hpp selection.cpp
	g++ -c selection.cpp `pkg-config --cflags opencv` `pkg-config --libs opencv`

reconnaissance.o : version4.hpp reconnaissance.cpp
	g++ -c reconnaissance.cpp `pkg-config --cflags opencv` `pkg-config --libs opencv`
