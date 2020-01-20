package yal.analyse ;

import java_cup.runtime.*;
import yal.exceptions.AnalyseLexicaleException;
      
%%
   
%class AnalyseurLexical
%public

%line
%column
    
%type Symbol
%eofval{
        return symbol(CodesLexicaux.EOF) ;
%eofval}

%cup

%{

  private StringBuilder chaine ;

  private Symbol symbol(int type) {
	return new Symbol(type, yyline, yycolumn) ;
  }

  private Symbol symbol(int type, Object value) {
	return new Symbol(type, yyline, yycolumn, value) ;
  }
%}

idf = [A-Za-z_][A-Za-z_0-9]*

entier = [0-9]*

csteE = [0-9]+

finDeLigne = \r|\n
espace = {finDeLigne}  | [ \t\f]

cmt = \/\/.*\n

%%

"programme"            { return symbol(CodesLexicaux.PROGRAMME); }
"debut"                { return symbol(CodesLexicaux.DEBUT); }
"fin"              	   { return symbol(CodesLexicaux.FIN); }

"ecrire"               { return symbol(CodesLexicaux.ECRIRE); }

";"                    { return symbol(CodesLexicaux.POINTVIRGULE); }

"lire"                 { return symbol(CodesLexicaux.LIRE); }

{csteE}      	       { return symbol(CodesLexicaux.CSTENTIERE, yytext()); }

{idf}      	           { return symbol(CodesLexicaux.IDF, yytext()); }

{entier}               { return symbol(CodesLexicaux.ENTIER, yytext()); }

{espace}               { }

{cmt}                  { }
.                      { throw new AnalyseLexicaleException(yyline, yycolumn, yytext()) ; }

