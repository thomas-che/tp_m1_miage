import unicodedata


# encrypt the "msg" apllying "key"
def encryptVigenere(msg, key):
	# normalize the string removing accents and uppercase
	msg = ''.join((c for c in unicodedata.normalize('NFD', msg) if unicodedata.category(c) != 'Mn'))
	msg = msg.upper()
	newMsg = ''
	shift_id = 0 # decallage courant
	shift = []	# liste des decallages
	# completion de la liste de décallages
	for x in key:
		shift.append( ord(x)-65 )

	# apply "decallage for each letter (others characters stay the same)"
	for letter in msg:
		code = ord(letter)
		if(code >= 65 and code <= 90):
			code += shift[shift_id]
			#incrementation du décallage
			shift_id += 1
			if(shift_id >= len(shift)):
				shift_id = 0

			if(code > 90):
				code = 65 + (code - 91)
			newMsg += chr(code)
		else:
			newMsg += letter

	return newMsg

# decrypt the "msg" apllying "key" (use same key to encrypt & decrypt methods)
def decryptVigenere(msg, key):
	newMsg = ''
	shift_id = 0 # decallage courant
	shift = []	# liste des decallages
	# completion de la liste de décallages
	for x in key:
		shift.append( ord(x)-65 )

	# apply "decallage for each letter (others characters stay the same)"
	for letter in msg:
		code = ord(letter)
		if(code >= 65 and code <= 90):
			code -= shift[shift_id]
			#incrementation du décallage
			shift_id += 1
			if(shift_id >= len(shift)):
				shift_id = 0

			if(code < 65):
				code = 90 - (64 - code)
			newMsg += chr(code)
		else:
			newMsg += letter

	return newMsg


def test():
	# Vigenere
	key = 'ABC'
	message = 'Il était un petit navire.'
	enc_message = ''

	enc_message = encryptVigenere(message, key)
	print(enc_message)

	message = decryptVigenere(enc_message, key)
	print(message)

test()
