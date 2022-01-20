#Salut Alice, j'ai réfléchis à ton histoire de protocole.
#Oui ? Finalement tu as retenu quelles briques ?
#Je me suis dit que RSA c'est une valeur sûre et je m'en suis servi pour partager le secret.
#C'est Alice ou Bob qui choisit le secret du coup ?
#Les deux mon général ! On fait un XOR de leurs choix. Tiens, regarde :

from Crypto.Cipher import AES
from Crypto.Hash import HMAC, SHA256
from Crypto.Random.random import randint
from Crypto.Util.Padding import pad, unpad
import keys

kab=None
kba=None

def boi(i,n=None):
    if n is not None:
        return i.to_bytes(n,'big')
    else:
        h=hex(i)[2:]
        if len(h)%2 == 1: h='0'+h
        return bytes.fromhex(h)

def iob(b):
    return int.from_bytes(b,'big')

def envoie(s,qui,quoi):
    s.send('{}: {}\n'.format(qui,quoi.hex()).encode())

def recoit(s):
    data=s.recv()
    st=data.index(b' ')
    return bytes.fromhex(data[st+1:-1].decode())

def start_alice(s):
   "invoqué avant toute opération chez alice"
   global kab,kba
   # RSA
   key=keys.rsa_alice
   na=key.n
   ea=key.e
   da=key.d
   envoie(s,'alice',boi(na))
   envoie(s,'alice',boi(ea))
   nb=iob(recoit(s))
   eb=iob(recoit(s))
   a=randint(0,nb)
   x=pow(a,eb,nb)
   envoie(s,'alice',boi(x))
   y=iob(recoit(s))
   # secret partagé
   b=pow(y,da,na)
   k=boi(a^b)
   # dérivation de clés
   h=HMAC.new(k, digestmod=SHA256)
   h.update(b'alice->bob')
   kab=h.digest()
   h=HMAC.new(k, digestmod=SHA256)
   h.update(b'bob->alice')
   kba=h.digest()

def send_alice(s,data):
   "invoqué pour envoyer un message côté alice"
   cipher=AES.new(kab, AES.MODE_CBC)
   assert len(cipher.iv)==16
   c=cipher.encrypt(pad(data,16))
   msg=cipher.iv+c
   envoie(s,'alice',msg)

def recv_alice(s):
   "invoqué pour recevoir un message côté alice"
   msg=recoit(s)
   iv=msg[:16]
   c=msg[16:]
   cipher=AES.new(kba, AES.MODE_CBC, iv=iv)
   data=cipher.decrypt(c)
   return unpad(data,16)

def start_bob(s):
   "invoqué avant toute opération chez bob"
   global kab,kba
   # RSA
   key=keys.rsa_bob
   nb=key.n
   eb=key.e
   db=key.d
   na=iob(recoit(s))
   ea=iob(recoit(s))
   envoie(s,'bob',boi(nb))
   envoie(s,'bob',boi(eb))
   x=iob(recoit(s))
   b=randint(0,na)
   y=pow(b,ea,na)
   envoie(s,'bob',boi(y))
   # secret partagé
   a=pow(x,db,nb)
   k=boi(a^b)
   # dérivation de clés
   h=HMAC.new(k, digestmod=SHA256)
   h.update(b'alice->bob')
   kab=h.digest()
   h=HMAC.new(k, digestmod=SHA256)
   h.update(b'bob->alice')
   kba=h.digest()

def send_bob(s,data):
   "invoqué pour envoyer un message côté alice"
   cipher=AES.new(kba, AES.MODE_CBC)
   assert len(cipher.iv)==16
   c=cipher.encrypt(pad(data,16))
   msg=cipher.iv+c
   envoie(s,'bob',msg)

def recv_bob(s):
   "invoqué pour recevoir un message côté alice"
   msg=recoit(s)
   iv=msg[:16]
   c=msg[16:]
   cipher=AES.new(kab, AES.MODE_CBC, iv=iv)
   data=cipher.decrypt(c)
   return unpad(data,16)