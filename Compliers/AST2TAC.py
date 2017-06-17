class AST2TAC(object):

 	tempVarCount = 0
 	tacCount = 0

 	def newTemp(self):
		self.tempVarCount += 1
		return self.tempVarCount

	def newLabel(self):
		self.tacCount += 1
		return self.tacCount
		
	def PreOrderLabel(self, node):
		if node.value not in ['WHILE-DO', 'IF-THEN-ELSE', 'IF-THEN', 'program']:
			return
		if node.value == 'program':
			for i in range(len(node.children)):
				node.children[i].begin = self.newLabel()
			for i in range(len(node.children) - 1):
				node.children[i].next = node.children[i + 1].begin
		elif node.value == 'WHILE-DO':
			node.children[0].false = node.next					# C.false := S.next
			node.children[0].true = self.newLabel()				# C.true := newlabel
			node.children[1].begin = node.children[0].true
			node.children[1].next = node.begin
		elif node.value == 'IF-THEN':
			node.children[0].true = self.newLabel() 			# C.true := newlabel
			node.children[0].false = node.next      			# C.false := S.next
			node.children[1].next = node.children[0].false		# S1.next := C.false
			node.children[1].begin = node.children[0].true
		elif node.value == 'IF-THEN-ELSE':
			node.children[0].true = self.newLabel()
			node.children[0].false = self.newLabel()
			node.children[2].next = node.next
			node.children[1].next = node.next
			node.children[1].begin = node.children[0].true
			node.children[2].begin = node.children[0].false
		for i in range(len(node.children)):
			self.PreOrderLabel(node.children[i])

	def PostOrderPrint(self, node):
		if node.children == []:
			return
		for i in range(len(node.children)):
			self.PostOrderPrint(node.children[i])

		if node.value in ['TIMES', 'DIVIDE', 'PLUS', 'MINUS']:
			node.code = node.children[0].code + node.children[1].code + '\tt'
			newtemp = str(self.newTemp())
			node.code += newtemp
			node.code += ' := '
			node.code = node.code + node.children[0].value + ' ' + node.value + ' ' + node.children[1].value +'\n' 
			node.value = 't' + newtemp

		elif node.value == 'WHILE-DO':
			node.code += node.children[0].code        # C.code
			node.code = node.code + '\nL' + str(node.children[0].true) + ':' # gen(C.true)
			node.code += node.children[1].code        # S1.code
			node.code = node.code + '\n\tgoto L' + str(node.begin)
			
		elif node.value == 'IF-THEN':
			node.code = node.children[0].code # C.code
			node.code = node.code + '\nL' + str(node.children[0].true) + ':' # C.true
			node.code += node.children[1].code # S1.code

		elif node.value == 'ASSIGNOP':
			node.code += node.children[1].code
			node.code += '   \t'
			node.code += node.children[0].value
			node.code += ' := '
			node.code += node.children[1].value

		elif node.value in ['<', '>', '=']:
			node.code = node.children[0].code + node.children[1].code 
			node.code = node.code + '\tif ' + node.children[0].value + node.value + node.children[1].value + ' goto L' + str(node.true)
			node.code = node.code + '\n' + '\tgoto L' + str(node.false)

		elif node.value == 'IF-THEN-ELSE':
			node.code = node.children[0].code
			node.code = node.code + '\nL' + str(node.children[0].true) + ':' # C.true
			node.code += node.children[1].code # S1.code
			node.code = node.code + '\n\tgoto L' + str(node.next)
			node.code = node.code + '\nL' + str(node.children[0].false) + ':' # C.false
			node.code += node.children[2].code
			
		elif node.value == 'program':
			for i in range(len(node.children)):
				node.code = node.code + '\nL' + str(node.children[i].begin) + ':'+ node.children[i].code

		

		