
# récupère toute les composante rouge pr cree le masque jetable
import PIL.Image
from Crypto.Util.strxor import strxor

image = PIL.Image.open(r'/home/thomas/Documents/tp_m1_miage/crypto/chalenge3/image-defi.png')
pixels = list(image.getdata())

pixels_red = bytearray()
for pixel in pixels:
    pixels_red.append(pixel[0])

masque = pixels_red

image = PIL.Image.open(r'/home/thomas/Documents/tp_m1_miage/crypto/chalenge3/image-defi.png')
message = open(r'/home/thomas/Documents/tp_m1_miage/crypto/chalenge3/message-mystere.mj', "rb").read()

message_en_clair = strxor(message[:len(masque)],masque[:len(message)]) # ils ont la meme taille
print(message_en_clair.decode())

