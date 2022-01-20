import PIL.Image
image_ecran = PIL.Image.open(r'/home/thomas/Documents/tp_m1_miage/crypto/sprint/dossier/xor_qrcode/ecran.png')
pixels_ecran = list(image_ecran.getdata())

pixel_ecran = bytearray()
pixel_ecran_bit = bytearray()
for pix in pixels_ecran:
    if(pix[0]==0 and pix[1]==0 and pix[2]==0):
        pixel_ecran_bit.append(0)
    else:
        pixel_ecran_bit.append(1)


image_ecran_g = PIL.Image.open(r'/home/thomas/Documents/tp_m1_miage/crypto/sprint/dossier/xor_qrcode/ecran.png')
pixels_ecran_g = list(image_ecran_g.getdata())

pixel_ecran_g = bytearray()
pixel_ecran_bit_g = bytearray()
for pix in pixels_ecran_g:
    if(pix[0]==0 and pix[1]==0 and pix[2]==0):
        pixel_ecran_bit_g.append(0)
    else:
        pixel_ecran_bit_g.append(1)


# xor
from Crypto.Util.strxor import strxor
le_xor = strxor(pixel_ecran_bit,pixel_ecran_bit_g) # ils ont la meme taille


pixel_xor = []
pixel_xor_bit = bytearray()
for bit in le_xor:
    if(bit==0):
        pixel_xor.append((0,0,0))
        a=[0,0,0]
        pixel_xor_bit.append(0)
    else:
        pixel_xor.append((254,254,254))
        a = [254,254,254]
        pixel_xor_bit.append(1)


image_ecran = PIL.Image.open(r'/home/thomas/Documents/tp_m1_miage/crypto/sprint/dossier/xor_qrcode/ecran.png')
image_ecran.convert()

im1 = PIL.Image.open(r'/home/thomas/Documents/tp_m1_miage/crypto/sprint/dossier/xor_qrcode/ecran_xor.png')

# save a image using extension
im1 = im1.save("geeks.jpg")


#!/usr/local/bin/python3
import numpy as np
from PIL import Image, ImageChops

# Open images
im1 = Image.open(r'/home/thomas/Documents/tp_m1_miage/crypto/sprint/dossier/xor_qrcode/ecran.png')
im2 = Image.open(r'/home/thomas/Documents/tp_m1_miage/crypto/sprint/dossier/xor_qrcode/ecran_b.png')

result = ImageChops.logical_xor(im1,im2)
result.save("./ecran_xor.png")


out_file = open('./ecran_xor.png', 'wb').write(pixel_xor)


print("0")