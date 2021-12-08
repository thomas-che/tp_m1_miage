from Crypto.Util.strxor import strxor

c = open(r'/home/thomas/Documents/tp_m1_miage/crypto/chalenge3/ctr-1', "rb").read()
d = open(r'/home/thomas/Documents/tp_m1_miage/crypto/chalenge3/ctr-2', "rb").read()

print(c[:26])
print(d[:26])


e = strxor(c[:len(d)],d[:len(c)])
print("e = ", e[:45])




nonce = c[:8] # sur les 8 premier octec
cc = c[8:]
dd = d[8:]

hint = b"Bravo"

sortie_aes = strxor(hint,cc[:len(hint)])
dec_d = strxor(sortie_aes,dd[:len(hint)])

print("En clair :", dec_d)