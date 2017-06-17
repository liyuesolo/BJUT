import sys

import ply.yacc as yacc
# Get the token map from the lexer.
from scanner import tokens
from Node import *
from AST2TAC import *


def p_program(p):
	'program :  statementList' 
	p[0] = p[1]
	p[0].value = 'program'
	print 'program -> statementList'

def p_statement(p):
	'''statement : stmWithoutTrailingSubstm
				| whileStatement
				| ifThenStatement
				| ifThenElseStatement'''
	p[0] = p[1]
	print 'statement -> stmWithoutTrailingSubstm | whileStatement | ifThenStatement | ifThenElseStatement'

def p_statement_if_then(p):
	'ifThenStatement : IF condition THEN statement'
	p[0] = StatementNode('IF-THEN')
	p[0].children = [p[2], p[4]]
	print 'ifThenStatement -> IF condition THEN statement'

def p_statement_if_then_else(p):
	'ifThenElseStatement : IF condition THEN statementNoShortIf ELSE statement'
	p[0] = StatementNode('IF-THEN-ELSE')
	p[0].children = [p[2], p[4], p[6]]
	print 'ifThenElseStatement -> IF condition THEN statementNoShortIf ELSE statement'

def p_statement_no_short_if_without_trailing(p):
	'statementNoShortIf : stmWithoutTrailingSubstm'
	p[0] = p[1]
	print 'statementNoShortIf -> stmWithoutTrailingSubstm'

def p_statement_no_short_if_while(p):
	'statementNoShortIf : WHILE condition DO statementNoShortIf'
	p[0] = StatementNode('WHILE-DO')
	p[0].children = [p[2], p[4]]
	print 'statementNoShortIf -> WHILE condition DO statementNoShortIf'

def p_statement_no_short_if_if_then_else(p):
	'statementNoShortIf : IF condition THEN statementNoShortIf ELSE statementNoShortIf'
	p[0] = StatementNode('IF-THEN-ELSE')
	p[0].children = [p[2], p[4], p[6]]
	print 'statementNoShortIf -> IF condition THEN statementNoShortIf ELSE statementNoShortIf'

def p_statement_while(p):
	'whileStatement : WHILE condition DO statement'
	p[0] = StatementNode('WHILE-DO')
	p[0].children = [p[2], p[4]]
	print 'whileStatement -> WHILE condition DO statement'

def p_condition(p):
	'''condition : expression RELOP expression
				| expression ASSIGNOP expression'''
	p[0] = ConditionNode(str(p[2]))
	p[0].children = [p[1], p[3]]
	print 'condition -> expression RELOP | ASSIGNOP expression'

def p_statement_stm_without_trailing_substm(p):
	'''stmWithoutTrailingSubstm : singleStatement
								| statementBrackets'''
	p[0] = p[1]
	print 'stmWithoutTrailingSubstm -> singleStatement | statementBrackets'

def p_statement_single(p):
	'singleStatement : expression SEMI'
	p[0] = p[1]
	print 'singleStatement -> expression SEMI'

def p_statement_brackets(p):
	'statementBrackets : LBRACKET statementList RBRACKET'
	p[0] = p[2]
	print 'statementBrackets -> LBRACKET statementList RBRACKET'

def p_statement_assignop(p):
	'singleStatement : IDN ASSIGNOP expression SEMI'
	p[0] = StatementNode('ASSIGNOP')
	p[0].children = [Node(p[1]), p[3]]
	print 'expression -> IDN ASSIGN expression SEMI'

def p_statement_list_stm(p):
	'statementList : statement'
	p[0] = p[1]
	print 'statementList -> statement'

def p_statement_list_list(p):
	'statementList : statementList statement'
	p[0] = Node("statementList")
	p[0].children = [p[1], p[2]]
	print 'statementList -> statementList statement'

def p_expression_plus(p):
	'expression : expression PLUS term'
	p[0] = Node('PLUS')
	p[0].children = [p[1], p[3]]
	print 'expression -> expression PLUS term'

def p_expression_minus(p):
	'expression : expression MINUS term'
	p[0] = Node('MINUS')
	p[0].children = [p[1], p[3]]
	print 'expression -> expression MINUS term'

def p_expression_term(p):
	'expression : term'
	p[0] = p[1]
	print 'expression -> term'

def p_term_times(p):
	'term : term TIMES factor'
	p[0] = Node('TIMES')
	p[0].children = [p[1], p[3]]
	print 'term -> term TIMES factor'

def p_term_div(p):
	'term : term DIVIDE factor'
	p[0] = Node('DIVIDE')
	p[0].children = [p[1], p[3]]
	print 'term -> term DIVIDE factor'

def p_term_factor(p):
	'term : factor'
	p[0] = p[1]
	print 'term -> factor'

def p_factor_number(p):
	'''factor : INT10
				| INT8
				| INT16
				| REAL10
				| REAL8
				| REAL16'''
	# p[0] = p[1]
	p[0] = Node(str(p[1]))
	print 'factor -> INT10 | INT8 | INT16 | REAL10 | REAL8 | REAL16'

def p_factor_expr(p):
	'factor : LPAREN expression RPAREN'
	p[0] = p[2]
	print 'factor -> LPAREN expression RPAREN'

def p_factor_identity(p):
	'factor : IDN'
	# p[0] = p[1]
	p[0] = Node(p[1])
	print 'factor -> IDN'


# Error rule for syntax errors
def p_error(p):
	if p:
		print 'Error at line: ', p.lineno, ': parsing: ', p.value, 'with type: ', p.type
		exit(1)
	else:
		print "Syntax error at EOF"


# Build the parser
parser = yacc.yacc()
# data = 'while (a3+15)>0xa do if x2 = 07 then while y<z do y = x * y / z; c=b*c+d;'
# parserResult = yacc.parse('while (a3+15)>0xa do if x2 = 07 then while y<z do y = x * y / z; else c=b*c+d; x = x + 1;')

# ast2tac = AST2TAC()
# ast2tac.PreOrderLabel(parserResult)
# ast2tac.PostOrderPrint(parserResult)
# print parserResult.code

fromFile = False
while True:
	rawInput = ''
	if(len(sys.argv) == 1):
		rawInput = raw_input('Parser > ')
	else:
		parserFile = file(sys.argv[1], 'r')
		rawInput = parserFile.read()
		fromFile = True
	if (rawInput == ''):
		break
	parserResult = yacc.parse(rawInput)
	print '\nAbstract syntax tree: \n', (parserResult)

	print 'Three Address code:'
	ast2tac = AST2TAC()
	ast2tac.PreOrderLabel(parserResult)
	ast2tac.PostOrderPrint(parserResult)
	print parserResult.code
	if fromFile:
		break;
