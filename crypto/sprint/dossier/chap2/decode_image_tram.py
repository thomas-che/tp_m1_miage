c="""SidhZG9yZSBxdWFuZCB1biBwbGFuIHNlIGTDqXJvdWxlIHNhbnMgYWNjcm9jIDotKCBSUlJFUlNO
QVVPTFRHSUVPRExTT0VDVEwK"""
from base64 import b64decode
print(b64decode(c).decode())



