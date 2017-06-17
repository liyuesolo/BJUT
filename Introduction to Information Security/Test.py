from DES import *
from RSA import *
from time import time

class Test(object):
	"""docstring for Test"""
	def __init__(self, testData):
		self.testData = testData
		self.desRuntime = []
		self.rsaRuntime = []
		self.runtime = ''

	def RuntimeTest(self):
		data = self.testData.strip().split('\n')
		for i in range(len(data)):
			start = time()
			des = DES()
			key = '0001110111001011011010000011101011011001010000001111110010110011'
			des.SetKey(key)
			des.EncryptKey()
			des.SetInputTxt(data[i])
			des.SetCipher(des.Encrypt())
			desResult = des.Decrypt()
			stop = time()
			self.runtime += ('DES runtime:' + str(stop-start) + "s\n")
			start = time()
			rsa = RSA()
			rsa.KeyGeneration(128)
			rsaResult = rsa.Decryption(rsa.Encryption(int(data[i]), rsa.keyPublic, rsa.n), rsa.keyPrivate, rsa.n)
			stop = time()
			self.runtime += ('RSA runtime:' + str(stop-start) + "s\n\n")
		return self.runtime




		