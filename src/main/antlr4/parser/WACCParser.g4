parser grammar WACCParser;

options {
  tokenVocab=WACCLexer;
}


// EOF indicates that the program must consume to the end of the input.
program: BEGIN cls* func* stat END EOF
;

cls: CLASS IDENT BEGIN (field SEMICOLON)* method* END
;

func: type IDENT OPEN_PARENTHESES (param_list)? CLOSE_PARENTHESES IS stat END
;

method: visib_ident func
;


param_list: param (COMMA param)*
;

param: type IDENT
;

stat: SKIP2                       #skip
| BREAK                           #break
| CONTINUE                        #continue
| type IDENT ASSIGN assign_rhs    #declaration
| assign_lhs ASSIGN assign_rhs    #assign
| READ assign_lhs                 #read
| FREE expr                       #free
| RETURN expr                     #return
| EXIT expr                       #exit
| PRINT expr                      #print
| PRINTLN expr                    #println
| IF expr THEN stat ELSE stat FI  #if
| WHILE expr DO stat DONE         #while
| FOR OPEN_PARENTHESES stat SEMICOLON expr SEMICOLON stat CLOSE_PARENTHESES stat DONE    #for
| DO stat WHILE expr DONE         #doWhile
| BEGIN stat END                  #begin
| stat SEMICOLON stat             #semicolon
;

assign_lhs: IDENT
| array_elem
| pair_elem
| class_field
;

assign_rhs: expr
| array_liter
| NEW_PAIR OPEN_PARENTHESES expr COMMA expr CLOSE_PARENTHESES
| pair_elem
| CALL IDENT OPEN_PARENTHESES (arg_list)? CLOSE_PARENTHESES
| method_call
| NEW IDENT OPEN_PARENTHESES CLOSE_PARENTHESES
;

method_call: CALL class_ident=IDENT DOT method_ident=IDENT OPEN_PARENTHESES (arg_list)? CLOSE_PARENTHESES
| CALL_IN_CLASS  method_ident=IDENT OPEN_PARENTHESES (arg_list)? CLOSE_PARENTHESES
;

arg_list: expr (COMMA expr)*
;

class_field: class_ident=IDENT DOT field_ident=IDENT
;

pair_elem: FST expr
| SND expr
;

type: base_type
| type OPEN_SQUARE CLOSE_SQUARE
| pair_type
| IDENT
;

base_type: INT | BOOL | CHAR | STRING;

pair_type: PAIR OPEN_PARENTHESES fst=pair_element_type COMMA snd=pair_element_type CLOSE_PARENTHESES;

pair_element_type: base_type
| type OPEN_SQUARE CLOSE_SQUARE
| PAIR (OPEN_PARENTHESES fst=pair_element_type COMMA snd=pair_element_type CLOSE_PARENTHESES)?
;

expr: int_liter2                                        #intLiter2
| BOOL_LITER                                            #boolLiter
| CHAR_LITER                                            #charLiter
| STRING_LITER                                          #stringLiter
| pair_liter                                            #pairLiter
| IDENT                                                 #identifier
| array_elem                                            #ArrayElem
| class_field                                           #classField
| un_op=unary_oper expr                                 #unOp
| lhs=expr bin_op=(MULT | DIV | MOD) rhs=expr           #binOp
| lhs=expr bin_op=(PLUS | MINUS) rhs=expr               #binOp
| lhs=expr bin_op=(GT | GTE | LT | LTE) rhs=expr        #binOp
| lhs=expr bin_op=(EQUAL | NOT_EQUAL) rhs=expr          #binOp
| lhs=expr bin_op=BITWISE_AND rhs=expr                  #binOp
| lhs=expr bin_op=BITWISE_XOR rhs=expr                  #binOp
| lhs=expr bin_op=BITWISE_OR rhs=expr                   #binOp
| lhs=expr bin_op=AND rhs=expr                          #binOp
| lhs=expr bin_op=OR rhs=expr                           #binOp
| OPEN_PARENTHESES expr CLOSE_PARENTHESES               #openParantheses
;

field: visib_ident type IDENT
| visib_ident type IDENT ASSIGN assign_rhs
;

visib_ident: PUBLIC
| PRIVATE
;

unary_oper:  NOT | MINUS  | LEN | ORD | CHR | BITWISE_NOT;

array_elem: IDENT (OPEN_SQUARE expr CLOSE_SQUARE)+;

array_liter: OPEN_SQUARE (expr (COMMA expr)*)? CLOSE_SQUARE;

int_liter2: (PLUS | MINUS)? INT_LITER;

pair_liter: NULL;