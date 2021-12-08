import PIL.Image
image = PIL.Image.open(r'/home/thomas/Documents/tp_m1_miage/crypto/chalenge2/indice.png')
pixels = list(image.getdata())

pixels_bleu = bytearray()
cpt = 0
for pixel in pixels:
    if cpt%2 == 1:
        pix = (old_pixel*16 + pixel[2]%16)
        pixels_bleu.append(pix)
    else:
        old_pixel = pixel[2]%16
    cpt+=1    


print(pixels_bleu[:50])

# extrait es 16 premier octects
vect_init = pixels_bleu[:16]
message = pixels_bleu[16:]

cle = b"LACRYPTOGRAPHIECESTLAVIE!CESTTOP"

from Crypto.Cipher import AES
from Crypto.Util.Padding import pad, unpad
from Crypto.Random import get_random_bytes

c = message
iv = vect_init
k = cle

print(len(iv))

cipher = AES.new(k,AES.MODE_CBC, iv)
m = cipher.decrypt(c)
m_unpad = unpad(m, 16)

f = open('file.7z', 'wb')
f.write(m_unpad)
f.close()




print("1")