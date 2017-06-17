import ply.lex as lex

# List of token names.  
tokens = [
   'IDN',
   'COMMENT',
   'REAL10',
   'REAL8',
   'REAL16',
   'INT8',
   'INT16',
   'INT10',
   'PLUS',
   'MINUS',
   'TIMES',
   'DIVIDE',
   'LPAREN',
   'RPAREN',
   'RELOP',
   'ASSIGNOP',
   'SEMI',
   'LBRACKET',
   'RBRACKET'
]

# Regular expression rules for simple tokens
t_PLUS    = r'\+'
t_MINUS   = r'\-'
t_TIMES   = r'\*'
t_DIVIDE  = r'\/'
t_LPAREN  = r'\('
t_RPAREN  = r'\)'
t_RELOP   = r'[>]|[<]'
t_ASSIGNOP  =  r'[=]'
t_SEMI    = r'[;]'
t_LBRACKET = r'\{'
t_RBRACKET = r'\}'

reserved  = {
   'if' : 'IF',
   'then' : 'THEN',
   'else' : 'ELSE',
   'while' : 'WHILE',
   'for' : 'FOR',
   'and' : 'AND',
   'or' : 'OR',
   'struct' : 'STRUCT',
   'switch' : "SWITCH",
   'do' : 'DO',
   'print' : 'PRINT',
}

tokens = tokens + list(reserved.values())

def t_REAL8(t):
	r'([0][0-7]+)\.[0-7]*'
	item = str(t.value).split('.')
	first = int(item[0], 8)
	second = 0
	for i in range(len(item[1])):
		second += (int(item[1][i], 8))*(8**(-1*(i+1)))
	result = first + second
	t.value = result
	return t

def t_REAL16(t):
	r'([0][Xx][0-9A-Fa-f]+)\.[0-9A-Fa-f]+'
	item = str(t.value).split('.')
	first = int(item[0], 16)
	second = 0
	for i in range(len(item[1])):
		second += (int(item[1][i], 16))*(16**(-1*(i+1)))
	result = first + second
	t.value = result
	return t

def t_REAL10(t):
	r'([0]|[1-9][0-9]*)\.[0-9]+'
	t.value = float(t.value)
	return t

def t_IDN(t):
	r'[a-zA-Z_][a-zA-Z_0-9]*'
	# Check for reserved words
	t.type = reserved.get(t.value,'IDN')    
	return t

def t_INT8(t):
	r'([0][0-8][0-8]*) | ([0][0-7][0-7]*)'
	if '8' in str(t.value):
		print "Illegal character 8 is found in an octadecimal number\n"
		pass
	else:
		t.value = int(t.value, 8)    
		return t

def t_INT16(t):
	r'([0][Xx][0-9_a-f][0-9_a-f]*)'
	t.value = int(t.value, 16)    
	return t

def t_INT10(t):
	#r'(([1-9][0-9]*|[0])\b)?![.]'
	r'0|([1-9]([0-9])*)' 
	t.value = int(t.value)
	return t

# No return value. Token discarded   
def t_COMMENT(t):
	r'\#.*'
	pass

# track line numbers
def t_newline(t):
	r'\n+'
	t.lexer.lineno += len(t.value)

# A string containing ignored characters (spaces and tabs)
t_ignore  = ' \t'

# Error handling rule
def t_error(t):
	print "Illegal character '%s'" % t.value[0]
	t.lexer.skip(1)

# Build the lexer
lexer = lex.lex()


# Give the lexer some input
# lexer.input(data2)

# # Tokenize
# while True:
# 	tok = lexer.token()
# 	if not tok: 
# 		break      # No more input
# 	print (tok.type,tok.value)