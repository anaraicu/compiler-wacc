
lexer grammar WACCLexer;

//control
BEGIN: 'begin';
END: 'end';
IS: 'is';
SKIP2: 'skip';
READ: 'read';
FREE: 'free';
RETURN: 'return';
EXIT: 'exit';
PRINT: 'print';
PRINTLN: 'println';
IF: 'if';
THEN: 'then';
ELSE: 'else';
FI: 'fi';
WHILE: 'while';
DO: 'do';
DONE: 'done';
CALL: 'call';

//punctuation
SEMICOLON: ';';
COMMA: ',';
HASH: '#';
OPEN_PARENTHESES: '(';
CLOSE_PARENTHESES: ')';
OPEN_SQUARE: '[';
CLOSE_SQUARE: ']';

//assign
ASSIGN: '=';

//base types
INT: 'int';
BOOL: 'bool';
CHAR: 'char';
STRING: 'string';

//unary operators
NOT: '!';
LEN: 'len';
ORD: 'ord';
CHR: 'chr';

//binary operators
MULT: '*';
DIV: '/';
MOD: '%';
PLUS: '+';
MINUS: '-';
GT: '>';
GTE: '>=';
LT: '<';
LTE: '<=';
EQUAL: '==';
NOT_EQUAL: '!=';
AND: '&&';
OR: '||';

//null
NULL: 'null';

//pair
PAIR: 'pair';
NEW_PAIR: 'newpair';
FST: 'fst';
SND: 'snd';

//
fragment UNDERSCORE: '_';
fragment LOWER_LETTER: 'a'..'z';
fragment UPPER_LETTER: 'A'..'Z';
fragment DIGIT: '0'..'9';
fragment ESCAPED_CHAR: ('0' | 'b' | 't' | 'n' | 'f' | 'r' | '"' | '\'' | '\\');
fragment CHARACTER: ~('\\' | '\'' | '"') | ('\\' ESCAPED_CHAR);
fragment INT_SIGN: (PLUS | MINUS);
fragment TRUE: 'true';
fragment FALSE: 'false';

//liter
INT_LITER: (DIGIT)+;
BOOL_LITER: TRUE | FALSE;
CHAR_LITER: '\'' CHARACTER '\'';
STRING_LITER: '"' (CHARACTER)* '"';

//ident
IDENT: (UNDERSCORE | LOWER_LETTER | UPPER_LETTER) (UNDERSCORE | LOWER_LETTER | UPPER_LETTER | DIGIT)*;

//whitespace
WHITESPACE: (' ' | '\n'| '\t' | '\r')+ -> skip;
EOL: ('\r' | '\n');
NON_EOL: ~('\r' | '\n');

//comment
COMMENT: HASH (NON_EOL)* EOL -> skip;
