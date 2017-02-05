grammar Constraint;
@header {
package io.github.sgreben.rc.parser;
}

expr:   '(' expr ')'                         #parensExpr
       | '!' expr                            #notExpr
       | TRUE                                #trueExpr
       | FALSE                               #falseExpr
       | ID                                  #idExpr
       | 'if' expr 'then' expr 'else' expr   #iteExpr
       | expr '^' expr                       #powExpr
       | expr '*' expr                       #mulExpr
       | expr '/' expr                       #divExpr
       | expr '+' expr                       #addExpr
       | expr '-' expr                       #subExpr
       | expr ('='|'==') expr                #eqExpr
       | expr '!=' expr                      #neqExpr
       | expr '<=' expr                      #leqExpr
       | expr '>=' expr                      #geqExpr
       | expr '>' expr                       #gtExpr
       | expr '<' expr                       #ltExpr
       | expr '&&' expr                      #andExpr
       | expr '||' expr                      #orExpr
       | expr '=>' expr                      #impExpr
       | FLOAT                               #floatExpr
       | INT                                 #intExpr
        ;

TRUE   : 'true';
FALSE  : 'false';
INT    : '-'? [0-9]+ ;
FLOAT  : '-'? [0-9]+'.'[0-9]+;
ID     : [a-zA-Z_][a-zA-Z_0-9]*;
WS     : [ \t\r\n]+ -> skip;