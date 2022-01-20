#!/usr/local/bin/python3
import numpy as np
from PIL import Image, ImageChops

# Open images
im1 = Image.open(r'/home/thomas/Documents/tp_m1_miage/crypto/sprint/dossier/xor_qrcode/ecran.png').convert('1')
im2 = Image.open(r'/home/thomas/Documents/tp_m1_miage/crypto/sprint/dossier/xor_qrcode/ecran_g.png').convert('1')

result = ImageChops.logical_xor(im1,im2)
result.show()
result.save("./ecran_xor.png")

