import unicodedata

# encrypt the "msg" apllying "decallage"
def encryptCaesar(msg, decallage):
	# normalize the string removing accents and uppercase
	msg = ''.join((c for c in unicodedata.normalize('NFD', msg) if unicodedata.category(c) != 'Mn'))
	msg = msg.upper()
	newMsg = ''

	# apply "decallage for each letter (others characters stay the same)"
	for letter in msg:
		code = ord(letter)
		if(code >= 65 and code <= 90):
			code += decallage
			if(code > 90):
				code = 65 + (code - 91)
			newMsg += chr(code)
		else:
			newMsg += letter

	return newMsg

# decrypt the "msg" apllying "decallage" (use same decallage to encrypt & decrypt methods)
def decryptCaesar(msg, decallage):
	# normalize the string removing accents and uppercase
	newMsg = ''

	# apply "decallage for each letter (others characters stay the same)"
	for letter in msg:
		code = ord(letter)
		if(code >= 65 and code <= 90):
			code += decallage
			if(code < 65):
				code = 90 - (64 - code)
			newMsg += chr(code)
		else:
			newMsg += letter

	return newMsg


def test():
	# Caesar
	message = "Bateau, sur l'eau, la rivière, la rivière..."
	enc_message = "Zyrcys, qsp j'cys, jy pgtgèpc, jy pgtgèpc..."

	enc_message = encryptCaesar(message, 1)
	print("enc = "+enc_message)

	message = decryptCaesar(enc_message, 1)
	print(message)

test()