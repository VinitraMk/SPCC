bison -y -d sql.y
flex sql.l
gcc -c y.tab.c lex.yy.c
gcc y.tab.o lex.yy.o -o sql.exe
./sql.exe
