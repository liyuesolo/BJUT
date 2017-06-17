import random


class RSA(object):

    n = 0;
    keyPublic = 0
    keyPrivate = 0

    """docstring for RSA"""
    def __init__(self):
        pass

    def FastExpMod(self, b, e, m):
        """
        e = e0*(2^0) + e1*(2^1) + e2*(2^2) + ... + en * (2^n)

        b^e = b^(e0*(2^0) + e1*(2^1) + e2*(2^2) + ... + en * (2^n))
            = b^(e0*(2^0)) * b^(e1*(2^1)) * b^(e2*(2^2)) * ... * b^(en*(2^n)) 

        b^e mod m = ((b^(e0*(2^0)) mod m) * (b^(e1*(2^1)) mod m) * (b^(e2*(2^2)) mod m) * ... * (b^(en*(2^n)) mod m) mod m
        """
        result = 1
        while e != 0:
            if (e&1) == 1:
                # ei = 1, then mul
                result = (result * b) % m
            e >>= 1
            # b, b^2, b^4, b^8, ... , b^(2^n)
            b = (b*b) % m
        return result

    def IsPrimeNumber(self, n):
        q = n - 1
        k = 0
        #Find k, q, satisfied 2^k * q = n - 1
        while q % 2 == 0:
            k += 1;
            q /= 2
        a = random.randint(2, n-2);
        #If a^q mod n= 1, n maybe is a prime number
        if self.FastExpMod(a, q, n) == 1:
            return True
        #If there exists j satisfy a ^ ((2 ^ j) * q) mod n == n-1, n maybe is a prime number
        for j in range(0, k):
            if self.FastExpMod(a, (2**j)*q, n) == n - 1:
                return True
        #a is not a prime number
        return False

    def FindPrime(self, halfkeyLength):
        while True:
            #Select a random number n 
            n = random.randint(0, 1<<halfkeyLength)
            if n % 2 != 0:
                found = True
                #If n satisfy primeTest 10 times, then n should be a prime number
                for i in range(0, 10):
                    if not self.IsPrimeNumber(n):
                        found = False
                        break
                if found:
                    return n

    def ExtendedGCD(self, a, b):
        #a*xi + b*yi = ri
        if b == 0:
            return (1, 0, a)
        #a*x1 + b*y1 = a
        x1 = 1
        y1 = 0
        #a*x2 + b*y2 = b
        x2 = 0
        y2 = 1
        while b != 0:
            q = a / b
            #ri = r(i-2) % r(i-1)
            r = a % b
            a = b
            b = r
            #xi = x(i-2) - q*x(i-1)
            x = x1 - q*x2
            x1 = x2
            x2 = x
            #yi = y(i-2) - q*y(i-1)
            y = y1 - q*y2
            y1 = y2
            y2 = y
        return(x1, y1, a)

    def GeneratePublicKey(self, fn, halfkeyLength):
        while True:
            #e and fn are relatively prime
            e = random.randint(0, 1<<halfkeyLength)
            (x, y, r) = self.ExtendedGCD(e, fn)
            if r == 1:
                return e

    def GeneratePrivateKey(self, fn, keyPublic):
        (x, y, r) = self.ExtendedGCD(fn, self.keyPublic)
        #y maybe < 0, so convert it 
        if y < 0:
            return fn + y
        return y

    def KeyGeneration(self, keyLength):
        #generate public key and private key
        p = self.FindPrime(keyLength/2)
        q = self.FindPrime(keyLength/2)
        self.n = p * q
        fn = (p-1) * (q-1)
        self.keyPublic = self.GeneratePublicKey(fn, keyLength / 2)
        self.keyPrivate = self.GeneratePrivateKey(fn, self.keyPublic)
        # return (n, keyPublic, keyPrivate)

    def Encryption(self, M, keyPublic, n):
        #Ciper text = M^e mod n
        return self.FastExpMod(M, self.keyPublic, n)

    def Decryption(self, C, keyPrivate, n):
        #Message = C^d mod n
        return self.FastExpMod(C, self.keyPrivate, n)

# start = time()
# #Unit Testing
# rsa = RSA()
# rsa.KeyGeneration(256)

# # #AES keyLength = 256
# X = random.randint(0, 1<<4)

# C = rsa.Encryption(X, rsa.keyPublic, rsa.n)
# M = rsa.Decryption(C, rsa.keyPrivate, rsa.n)
# print "PlainText:", X
# print "Encryption of plainText:", C
# print "Decryption of cipherText:", M

# stop = time()
# print(str(stop-start) + "s")
