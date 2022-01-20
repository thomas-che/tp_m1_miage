import PIL.Image
image = PIL.Image.open(r'/home/thomas/Documents/tp_m1_miage/crypto/sprint/dossier/xor_qrcode/ecran.png')
pixels = list(image.getdata())

pixels_r = bytearray()
pixels_g = bytearray()
pixels_b = bytearray()
for pix in pixels:
    pixels_r.append(pix[0])
    pixels_g.append(pix[1])
    pixels_b.append(pix[2])

from base64 import b64decode

out_file = open('./ecran_r.png', 'wb').write(pixels_r)
