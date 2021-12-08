# https://stackoverflow.com/questions/54504811/how-to-include-punctuation-and-white-space-in-vigenere-cipher-python

vigenered_message = r'dfc jhjj ifyh yf hrfgiv xulk? vmph bfzo! qtl eeh gvkszlfl yyvww kpi hpuvzx dl tzcgrywrxll!'




keyword = 'CAVAFACILE'

def vigenere_decrypt(encrypted_vigener, keyword):
    keyword_length = len(keyword)
    keyword_as_int = [ord(i) for i in keyword]
    encrypted_vigener_int = [ord(i) for i in encrypted_vigener]
    plaintext = ''
    for i in range(len(encrypted_vigener_int)):
        if vigenered_message[i].isalpha():
            value = (encrypted_vigener_int[i] - keyword_as_int[i % keyword_length]) % 26
            plaintext += chr(value + 65)
        else:
            plaintext += vigenered_message[i]
    return plaintext

print(vigenere_decrypt(c2, keyword))