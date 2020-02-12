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
csteE = [0-9]+
commentaire = [/]{2}.*{finDeLigne}
finDeLigne = \r|\n
espace = {finDeLigne}  | [ \t\f]

%%

"programme"            { return symbol(CodesLexicaux.PROGRAMME); }
"debut"                { return symbol(CodesLexicaux.DEBUT); }
"fin"              	   { return symbol(CodesLexicaux.FIN); }

"tantque"              { return symbol(CodesLexicaux.TANTQUE); }
"repeter"              { return symbol(CodesLexicaux.REPETER); }
"fintantque"           { return symbol(CodesLexicaux.FINTANTQUE); }

"si"                   { return symbol(CodesLexicaux.SI); }
"alors"                { return symbol(CodesLexicaux.ALORS); }
"sinon"                { return symbol(CodesLexicaux.SINON); }
"finsi"                { return symbol(CodesLexicaux.FINSI); }

"ecrire"               { return symbol(CodesLexicaux.ECRIRE); }

"entier"               { return symbol(CodesLexicaux.ENTIER); }

"lire"                 { return symbol(CodesLexicaux.LIRE); }

"non"                  { return symbol(CodesLexicaux.NON); }



"et"                   { return symbol(CodesLexicaux.ET); }
"ou"                   { return symbol(CodesLexicaux.OU); }

";"                    { return symbol(CodesLexicaux.POINTVIRGULE); }

"="                    { return symbol(CodesLexicaux.EGAL); }

"/"                    { return symbol(CodesLexicaux.DIVISE); }
"*"                    { return symbol(CodesLexicaux.MULT); }
"+"                    { return symbol(CodesLexicaux.PLUS); }
"-"                    { return symbol(CodesLexicaux.MOINS); }

">"                    { return symbol(CodesLexicaux.SUP); }
"<"                    { return symbol(CodesLexicaux.INF); }

"=="                   { return symbol(CodesLexicaux.EGALITE); }
"!="                   { return symbol(CodesLexicaux.DIFFERENCE); }

"("                    { return symbol(CodesLexicaux.PAR_OUVRANTE); }
")"                    { return symbol(CodesLexicaux.PAR_FERMANTE); }

{csteE}      	       { return symbol(CodesLexicaux.CSTENTIERE, yytext()); }

{idf}      	           { return symbol(CodesLexicaux.IDF, yytext()); }

{commentaire}          { }

{espace}               { }

.                      { throw new AnalyseLexicaleException(yyline, yycolumn, yytext()) ; }