%{
    #include <stdio.h>
    int yylex(void);
    void yyerror(char *);
%}

%token SELECT FROM IDENTIFIER WHERE AND OR; 

%%

print: 
     print select item using condition '\n' { printf("Syntax correct\n"); }
     |
     ;

select: SELECT
      ;

item: '*' 
    | identifier
    ;

identifier: IDENTIFIER
          | IDENTIFIER ',' identifier
          ;

using: FROM IDENTIFIER WHERE
     ;  

condition: IDENTIFIER '=' IDENTIFIER 
         | IDENTIFIER '=' IDENTIFIER AND condition
         | IDENTIFIER '=' IDENTIFIER OR condition
         ;

%%

void yyerror(char *s) {
    fprintf(stderr, "%s\n", s);
}

int main(void) {
    yyparse();
    return 0;
}
