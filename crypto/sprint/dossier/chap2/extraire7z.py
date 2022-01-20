# strings 7z.7z >> 7z.txt
c="""Q2hhY3VuIGRlIGNlcyB0YWJsZWF1eCBjb250aWVudCB1bmUgYXJjaGl2ZSBhdSBmb3JtYXQgN3os
IMOgIGV4dHJhaXJlIGF2ZWMKbCdvdXRpbCBkdSBtw6ptZSBub20uIENlcGVuZGFudCwgY2hhcXVl
IGFyY2hpdmUgZXN0IHByb3TDqWfDqWUgcGFyIHVuIG1vdCBkZQpwYXNzZS4KClBvdXIgbmUgcGFz
IG91YmxpZXIgdG91cyBjZXMgbW90cyBkZSBwYXNzZSwgbm91cyBsZXMgYXZvbnMgc3RvY2vDqXMg
c3VyIGxlCndlYiwgY29tbWUgY2VjaSA6IGh0dHBzOi8vcGRpY29zdC51bml2LW9ybGVhbnMuZnIv
Y3J5cHRvZG0vbWljcGFzcy5waHA/dXNlcj1Eb3JpZGFzJmxvZ2luPTd6JmFwaWtleT02OWUyMmNl
ZWE0OWFjOTU3YTg1YzJhOTY4MjFiZmM1NQoKUG91ciB1bmUgaW1hZ2Ugbm9tbcOpZSAlYnppbnpv
bGluJXQsIGlsIGZhdXQgdXRpbGlzZXIgbCdVUkwgYXZlYyBsZSBjaGFtcAolYmxvZ2luJXQgw6ln
YWwgw6AgJWJ6aW56b2xpbiV0Lgo="""
from base64 import b64decode
print(b64decode(c).decode())

clef="f5cd5d030ad6780844f017cfe77f93e7"

image=[]
file = open(r'/home/thomas/Documents/tp_m1_miage/crypto/sprint/dossier/chap2/7z.png','rb')
content = file.read()
content7z = b"7z" + content.split(b"7z")[-1]
file2 = open("7z_thomas.7z", "wb").write(content7z)
