class Node(object):
	def __init__(self, value = None, children = []):
		self.value = value
		self.children = children
		self.code = ''

	def __repr__(self, level=0):
		result = "\t" * level + repr(self.value) + "\n"
		for child in self.children:
			result += child.__repr__(level + 1)
		return result

class ConditionNode(Node):
	
	def __init__(self, value = None, children = []):
		Node.__init__(self, value, children)
		self.true = None
		self.false = None

class StatementNode(Node):
	
	def __init__(self, value = None, children = []):
		Node.__init__(self, value, children)
		self.begin = None
		self.next = None

		