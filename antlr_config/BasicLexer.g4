lexer grammar BasicLexer;

//operators
PLUS: '+' ;
MINUS: '-' ;
MULT: '*' ;
//brackets
OPEN_PARENTHESES: '(' ;
CLOSE_PARENTHESES: ')' ;

//numbers
fragment DIGIT: '0'..'9' ; 

INTEGER: DIGIT+ ;

//whitespace
EOL: '\n' ;




 
