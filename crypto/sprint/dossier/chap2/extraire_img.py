import PIL.Image
image = PIL.Image.open(r'/home/thomas/Documents/tp_m1_miage/crypto/sprint/dossier/chap2/7z.png')
pixels = list(image.getdata())

pixels_bleu = bytearray()
for pix in pixels:
    pixels_bleu.append(pix[0])
    pixels_bleu.append(pix[1])
    pixels_bleu.append(pix[2])


print(pixels_bleu[:50])

f = open('file.7z', 'wb')
f.write(pixels_bleu)
f.close()




print("1")