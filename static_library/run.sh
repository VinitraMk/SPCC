gcc -c lib_mylib.c -o lib_mylib.o
ar rcs lib_mylib.a lib_mylib.o
gcc -c main.c 
gcc -o m main.o -L. -l_mylib
./m
