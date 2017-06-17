import Tkinter
from Tkinter import *
import tkMessageBox
import tkFileDialog

from DES import *
from RSA import *
from Test import *

# build tk root
root = Tkinter.Tk()
# rename the title
root.title("Information Security Lab by Yue Li")
des = DES()
rsa = RSA()
# user interface active function to get the keys from inputting directly
def InputKey():
	root.update()
	key = keyInput.get()
	des.SetKey(key)
	des.EncryptKey()
# user interface active function to get the keys from file
def ChooseKeyfromFile():
	file = tkFileDialog.askopenfile(parent=root,mode='rb',title='Choose a file')
	if file != None:
		key = file.read()
		keyInput.insert(0, key)
		des.SetKey(key)
		des.EncryptKey()
		file.close()
# user interface active function to get the plaintxt from file
def ChooseTxtfromFile():
	file = tkFileDialog.askopenfile(parent=root,mode='rb',title='Choose a file')
	if file != None:
		txt = file.read()
		textInput.insert(0, txt)
		file.close()
# user interface active function to peform encryption	
def DESEncrypt():
	des.SetInputTxt(textInput.get())
	tkMessageBox.showinfo('cipher', des.Encrypt())
# user interface active function to peform decryption
def DESDecrypt():
	des.SetCipher(textInput.get())
	tkMessageBox.showinfo('plaintxt', des.Decrypt())

def RSAKeyGeneration():
	rsa.KeyGeneration(int(keyInputRSA.get()))
	keys = 'Public: \t' + str(rsa.keyPublic) +'\n\nPrivate:' + str(rsa.keyPrivate)
	tkMessageBox.showinfo('Keys', keys)

def RSAEncrypt():
	tkMessageBox.showinfo('Cipher', rsa.Encryption(int(textInputRSA.get()), rsa.keyPublic, rsa.n))

def RSADecrypt():
	tkMessageBox.showinfo('plaintxt', rsa.Decryption(int(textInputRSA.get()), rsa.keyPrivate, rsa.n))

def ComparisonGroup():
	file = tkFileDialog.askopenfile(parent=root,mode='rb',title='Choose a file')
	if file != None:
		txt = file.read()
		test = Test(txt)
		file.close()
	test.RuntimeTest()
	runtime = test.RuntimeTest()
	tkMessageBox.showinfo('runtime', runtime)

def ComparisonSingle():
	test = Test(textInputComparison.get())
	runtime = test.RuntimeTest()
	tkMessageBox.showinfo('runtime', runtime)

root.configure(background='LightCyan')

# texbox title
Tkinter.Label(root,text="DES Test:",width = 10,height = 2, bg = 'LightCyan', font = 'Helvetica 14 bold italic').grid(row = 0)
Tkinter.Label(root,text="Key:",width = 6,height=3, bg = 'LightCyan').grid(row = 1)
Tkinter.Label(root,text="Text:",width = 6,height=3, bg = 'LightCyan').grid(row = 2)
Tkinter.Label(root,text="RSA Test:",width = 10, height = 2, bg = 'LightCyan', font = 'Helvetica 14 bold italic').grid(row = 3)
Tkinter.Label(root,text="KeyLength:",width = 8,height=3, bg = 'LightCyan').grid(row = 4)
Tkinter.Label(root,text="Text:",width = 6,height=3, bg = 'LightCyan').grid(row = 5)
Tkinter.Label(root,text="Text:",width = 8,height=3, bg = 'LightCyan').grid(row = 6)
Tkinter.Label(root,text="Comparison\nTest:",width = 14, height = 2, bg = 'LightCyan', font = 'Helvetica 14 bold italic').grid(row = 6)
Tkinter.Label(root,text="Text:",width = 8,height=3, bg = 'LightCyan').grid(row = 7)
# text box
keyInput = Tkinter.Entry(root, text = "", width = 25, highlightbackground = 'LightCyan')
keyInput.grid(row = 1, column = 1)
textInput = Tkinter.Entry(root, text = "", width = 25, highlightbackground = 'LightCyan')
textInput.grid(row = 2, column = 1)

keyInputRSA = Tkinter.Entry(root, text = "", width = 25, highlightbackground = 'LightCyan')
keyInputRSA.grid(row = 4, column = 1)
textInputRSA = Tkinter.Entry(root, text = "", width = 25, highlightbackground = 'LightCyan')
textInputRSA.grid(row = 5, column = 1)

textInputComparison = Tkinter.Entry(root, text = "", width = 25, highlightbackground = 'LightCyan')
textInputComparison.grid(row = 7, column = 1)
# DES
Tkinter.Button(root, text="File", width = 2, command = ChooseKeyfromFile, highlightbackground = 'LightCyan').grid(row = 1, column = 2)
Tkinter.Button(root, text="Confirm", width = 6, command = InputKey, highlightbackground = 'LightCyan').grid(row = 1, column = 3)
Tkinter.Button(root, text="File", width = 2, command = ChooseTxtfromFile, highlightbackground = 'LightCyan').grid(row = 2, column = 2)
Tkinter.Button(root, text="Encrypt", width = 6, command = DESEncrypt, highlightbackground = 'LightCyan').grid(row = 2, column = 3) 
Tkinter.Button(root, text="Decrypt", width = 6, command = DESDecrypt, highlightbackground = 'LightCyan').grid(row = 2, column = 4)
# RSA
Tkinter.Button(root, text="Gen", width = 2, command = RSAKeyGeneration, highlightbackground = 'LightCyan').grid(row = 4, column = 2)
Tkinter.Button(root, text="File", width = 2, command = ChooseTxtfromFile, highlightbackground = 'LightCyan').grid(row = 5, column = 2)
Tkinter.Button(root, text="Encrypt", width = 6, command = RSAEncrypt, highlightbackground = 'LightCyan').grid(row = 5, column = 3) 
Tkinter.Button(root, text="Decrypt", width = 6, command = RSADecrypt, highlightbackground = 'LightCyan').grid(row = 5, column = 4)
# Comparison
Tkinter.Button(root, text="File", width = 2, command = ChooseTxtfromFile, highlightbackground = 'LightCyan').grid(row = 7, column = 2)
Tkinter.Button(root, text="Test", width = 4, command = ComparisonSingle, highlightbackground = 'LightCyan').grid(row = 7, column = 3)
Tkinter.Button(root, text="GroupTest", width = 8, command = ComparisonGroup, highlightbackground = 'LightCyan').grid(row = 7, column = 4)
root.mainloop()
