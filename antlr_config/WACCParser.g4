parser grammar WACCParser;

options {
  tokenVocab=WACCLexer;
}


// EOF indicates that the program must consume to the end of the input.
program: BEGIN func* stat END EOF
;

func: type IDENT OPEN_PARENTHESES (param_list)? CLOSE_PARENTHESES IS stat END
;

param_list: param (COMMA param)*
;

param: type IDENT
;

stat: SKIP2
| type IDENT ASSIGN assign_rhs
| assign_lhs ASSIGN assign_rhs
| READ assign_lhs
| FREE expr
| RETURN expr
| EXIT expr
| PRINT expr
| PRINTLN expr
| IF expr THEN stat ELSE stat FI
| WHILE expr DO stat DONE
| BEGIN stat END
| stat SEMICOLON stat
;

assign_lhs: IDENT
| array_elem
| pair_elem
;

assign_rhs: expr
| array_liter
| NEW_PAIR OPEN_PARENTHESES expr COMMA expr CLOSE_PARENTHESES
| pair_elem
| CALL IDENT OPEN_PARENTHESES (arg_list)? CLOSE_PARENTHESES
;

arg_list: expr (COMMA expr)*;

pair_elem: FST expr
| SND expr
;

type: base_type
| type OPEN_SQUARE CLOSE_SQUARE
| pair_type
;

base_type: INT | BOOL | CHAR | STRING;

pair_type: PAIR OPEN_PARENTHESES pair_element_type COMMA pair_element_type CLOSE_PARENTHESES;

pair_element_type: base_type
| type OPEN_SQUARE CLOSE_SQUARE
| PAIR
;

expr: int_liter2
| BOOL_LITER
| CHAR_LITER
| STRING_LITER
| pair_liter
| IDENT
| array_elem
| unary_oper expr
| expr binary_oper expr
| OPEN_PARENTHESES expr CLOSE_PARENTHESES
;

unary_oper: NOT | MINUS | LEN | ORD | CHR;

binary_oper: PLUS | MINUS | MULT | DIV | MOD | GT | GTE |  LT | LTE | EQUAL | NOT_EQUAL | AND | OR;

array_elem: IDENT (OPEN_SQUARE expr CLOSE_SQUARE)+;

array_liter: OPEN_SQUARE (expr (COMMA expr)*)? CLOSE_SQUARE;

int_liter2: (PLUS | MINUS)? INT_LITER;

pair_liter: NULL;