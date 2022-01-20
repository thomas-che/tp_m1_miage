# micpass.jpg, oracle.jpg, ctr.jpg

image=[]
file = open(r'/home/thomas/Documents/tp_m1_miage/crypto/sprint/dossier/chap2/ctr.jpg','rb')
content = file.read()
content7z = b"7z" + content.split(b"7z")[-1]
file2 = open("micpass.7z", "wb").write(content7z)
