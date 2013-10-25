
/*
* generated by Xtext
*/
lexer grammar InternalSqlLexer;


@header {
package com.jaspersoft.studio.data.ui.contentassist.antlr.lexer;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.Lexer;
}




KEYWORD_66 : ('R'|'r')('I'|'i')('G'|'g')('H'|'h')('T'|'t')' '('O'|'o')('U'|'u')('T'|'t')('E'|'e')('R'|'r')' '('J'|'j')('O'|'o')('I'|'i')('N'|'n');

KEYWORD_64 : ('F'|'f')('U'|'u')('L'|'l')('L'|'l')' '('O'|'o')('U'|'u')('T'|'t')('E'|'e')('R'|'r')' '('J'|'j')('O'|'o')('I'|'i')('N'|'n');

KEYWORD_65 : ('L'|'l')('E'|'e')('F'|'f')('T'|'t')' '('O'|'o')('U'|'u')('T'|'t')('E'|'e')('R'|'r')' '('J'|'j')('O'|'o')('I'|'i')('N'|'n');

KEYWORD_62 : ('I'|'i')('S'|'s')' '('N'|'n')('O'|'o')('T'|'t')' '('N'|'n')('U'|'u')('L'|'l')('L'|'l');

KEYWORD_63 : ('N'|'n')('O'|'o')('T'|'t')' '('B'|'b')('E'|'e')('T'|'t')('W'|'w')('E'|'e')('E'|'e')('N'|'n');

KEYWORD_60 : ('C'|'c')('R'|'r')('O'|'o')('S'|'s')('S'|'s')' '('J'|'j')('O'|'o')('I'|'i')('N'|'n');

KEYWORD_61 : ('I'|'i')('N'|'n')('N'|'n')('E'|'e')('R'|'r')' '('J'|'j')('O'|'o')('I'|'i')('N'|'n');

KEYWORD_58 : ('I'|'i')('N'|'n')('T'|'t')('E'|'e')('R'|'r')('S'|'s')('E'|'e')('C'|'c')('T'|'t');

KEYWORD_59 : '['('B'|'b')('E'|'e')('T'|'t')('W'|'w')('E'|'e')('E'|'e')('N'|'n')']';

KEYWORD_50 : ('B'|'b')('E'|'e')('T'|'t')('W'|'w')('E'|'e')('E'|'e')('N'|'n')']';

KEYWORD_51 : ('D'|'d')('I'|'i')('S'|'s')('T'|'t')('I'|'i')('N'|'n')('C'|'c')('T'|'t');

KEYWORD_52 : ('G'|'g')('R'|'r')('O'|'o')('U'|'u')('P'|'p')' '('B'|'b')('Y'|'y');

KEYWORD_53 : ('N'|'n')('O'|'o')('T'|'t')' '('L'|'l')('I'|'i')('K'|'k')('E'|'e');

KEYWORD_54 : ('N'|'n')('O'|'o')('T'|'t')('E'|'e')('Q'|'q')('U'|'u')('A'|'a')('L'|'l');

KEYWORD_55 : ('O'|'o')('R'|'r')('D'|'d')('E'|'e')('R'|'r')' '('B'|'b')('Y'|'y');

KEYWORD_56 : '['('B'|'b')('E'|'e')('T'|'t')('W'|'w')('E'|'e')('E'|'e')('N'|'n');

KEYWORD_57 : '['('G'|'g')('R'|'r')('E'|'e')('A'|'a')('T'|'t')('E'|'e')('R'|'r');

KEYWORD_47 : ('B'|'b')('E'|'e')('T'|'t')('W'|'w')('E'|'e')('E'|'e')('N'|'n');

KEYWORD_48 : ('G'|'g')('R'|'r')('E'|'e')('A'|'a')('T'|'t')('E'|'e')('R'|'r');

KEYWORD_49 : ('I'|'i')('S'|'s')' '('N'|'n')('U'|'u')('L'|'l')('L'|'l');

KEYWORD_43 : ('E'|'e')('X'|'x')('C'|'c')('E'|'e')('P'|'p')('T'|'t');

KEYWORD_44 : ('H'|'h')('A'|'a')('V'|'v')('I'|'i')('N'|'n')('G'|'g');

KEYWORD_45 : ('N'|'n')('O'|'o')('T'|'t')' '('I'|'i')('N'|'n');

KEYWORD_46 : ('S'|'s')('E'|'e')('L'|'l')('E'|'e')('C'|'c')('T'|'t');

KEYWORD_37 : ('E'|'e')('Q'|'q')('U'|'u')('A'|'a')('L'|'l');

KEYWORD_38 : ('L'|'l')('E'|'e')('S'|'s')('S'|'s')']';

KEYWORD_39 : ('M'|'m')('I'|'i')('N'|'n')('U'|'u')('S'|'s');

KEYWORD_40 : ('N'|'n')('O'|'o')('T'|'t')('I'|'i')('N'|'n');

KEYWORD_41 : ('U'|'u')('N'|'n')('I'|'i')('O'|'o')('N'|'n');

KEYWORD_42 : ('W'|'w')('H'|'h')('E'|'e')('R'|'r')('E'|'e');

KEYWORD_28 : ('C'|'c')('A'|'a')('S'|'s')('E'|'e');

KEYWORD_29 : ('D'|'d')('E'|'e')('S'|'s')('C'|'c');

KEYWORD_30 : ('E'|'e')('L'|'l')('S'|'s')('E'|'e');

KEYWORD_31 : ('F'|'f')('R'|'r')('O'|'o')('M'|'m');

KEYWORD_32 : ('L'|'l')('E'|'e')('S'|'s')('S'|'s');

KEYWORD_33 : ('L'|'l')('I'|'i')('K'|'k')('E'|'e');

KEYWORD_34 : ('S'|'s')('O'|'o')('M'|'m')('E'|'e');

KEYWORD_35 : ('T'|'t')('H'|'h')('E'|'e')('N'|'n');

KEYWORD_36 : ('W'|'w')('H'|'h')('E'|'e')('N'|'n');

KEYWORD_23 : ('A'|'a')('L'|'l')('L'|'l');

KEYWORD_24 : ('A'|'a')('N'|'n')('D'|'d');

KEYWORD_25 : ('A'|'a')('N'|'n')('Y'|'y');

KEYWORD_26 : ('A'|'a')('S'|'s')('C'|'c');

KEYWORD_27 : ('E'|'e')('N'|'n')('D'|'d');

KEYWORD_14 : '$'('X'|'x');

KEYWORD_15 : '<''=';

KEYWORD_16 : '<''>';

KEYWORD_17 : '>''=';

KEYWORD_18 : ('A'|'a')('S'|'s');

KEYWORD_19 : ('I'|'i')('N'|'n');

KEYWORD_20 : ('O'|'o')('N'|'n');

KEYWORD_21 : ('O'|'o')('R'|'r');

KEYWORD_22 : '|''|';

KEYWORD_1 : '(';

KEYWORD_2 : ')';

KEYWORD_3 : '+';

KEYWORD_4 : ',';

KEYWORD_5 : '-';

KEYWORD_6 : '.';

KEYWORD_7 : '/';

KEYWORD_8 : '<';

KEYWORD_9 : '=';

KEYWORD_10 : '>';

KEYWORD_11 : '{';

KEYWORD_12 : '|';

KEYWORD_13 : '}';



RULE_JRPARAM : '$P{' ( options {greedy=false;} : . )*'}';

RULE_JRNPARAM : '$P!{' ( options {greedy=false;} : . )*'}';

RULE_STAR : '*';

RULE_INT : '-'? ('0'..'9')+;

RULE_TIMESTAMP : RULE_DATE ' ' RULE_TIME;

RULE_DATE : '\'' '0'..'9' '0'..'9' '0'..'9' '0'..'9' '-' '0'..'1' '0'..'9' '-' '0'..'3' '0'..'9' '\'';

RULE_TIME : '\'' '0'..'9' '0'..'9' ':' '0'..'9' '0'..'9' ':' '0'..'1' '0'..'9' '.' '0'..'9' '0'..'9' '0'..'9' '\'';

RULE_SIGNED_DOUBLE : '-'? ('0'..'9')+ ('.' ('0'..'9')+)?;

RULE_STRING_ : '\'' ('\\' ('b'|'t'|'n'|'f'|'r'|'u'|'"'|'\''|'\\')|~(('\\'|'\'')))* '\'';

RULE_STRING : '"' ('\\' ('b'|'t'|'n'|'f'|'r'|'u'|'"'|'\''|'\\')|~(('\\'|'"')))* '"';

RULE_DBNAME : ('`' ('\\' ('b'|'t'|'n'|'f'|'r'|'u'|'"'|'\''|'\\')|~(('\\'|'`')))* '`'|'[' ('\\' ('b'|'t'|'n'|'f'|'r'|'u'|'"'|'\''|'\\')|~(('\\'|']')))* ']');

RULE_ID : ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

RULE_SL_COMMENT : ('--'|'#'|'//') ~(('\n'|'\r'))* ('\r'? '\n')?;

RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

RULE_WS : (' '|'\t'|'\r'|'\n')+;

RULE_ANY_OTHER : .;



