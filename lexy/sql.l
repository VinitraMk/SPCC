%{
    #include "y.tab.h"
    #include <stdlib.h>
    void yyerror(char *);
%}

%%

select  return SELECT;
from return FROM;
where return WHERE;
and return AND;
or return OR;
[*] return *yytext;
[,] return *yytext;
[=] return *yytext;
[a-zA-Z][a-zA-Z0-9]+ return IDENTIFIER;
[0-9]+ return IDENTIFIER;
\n return *yytext;
[ \t]+ ;

%%

int yywrap(void) {
    return 1;
}
