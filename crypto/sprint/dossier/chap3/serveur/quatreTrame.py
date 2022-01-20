#Bob, je ne suis pas sûr qu'on soit fait pour la crypto...
#Mais Alice, ne dit pas ça ! On est nés avec la crypto ! Allez, regarde plutôt mon protocole !
#Tu sais Bob, je suis sûre que là aussi il va y avoir une faille...
#Hum... Non, je ne crois pas, j'ai pensé à tout, regarde : Alice choisit le secret, elle l'envoie à Bob...
#...avec RSA ?
#Oui, et Bob lui, il montre à Alice qu'il a la bonne valeur en lui renvoyant.

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
   key=keys.alice_rsa
   na=key.n
   ea=key.e
   da=key.d
   envoie(s,'alice',boi(na))
   envoie(s,'alice',boi(ea))
   nb=iob(recoit(s))
   eb=iob(recoit(s))
   n=min(na,nb)
   a=randint(0,n)
   x=pow(a,eb,nb)
   envoie(s,'alice',boi(x))
   y=iob(recoit(s))
   # secret partagé
   b=pow(y,da,na)
   assert a==b
   k=boi(a)
   # dérivation de clés
   h=HMAC.new(k, digestmod=SHA256)
   h.update(b'alice->bob')
   kab=h.digest()
   h=HMAC.new(k, digestmod=SHA256)
   h.update(b'bob->alice')
   kba=h.digest()

def send_alice(s,data):
   "invoqué pour envoyer un message côté alice"
   cipher=AES.new(kab, AES.MODE_CTR)
   assert len(cipher.nonce)==8
   c=cipher.encrypt(data)
   msg=cipher.nonce+c
   envoie(s,'alice',msg)

def recv_alice(s):
   "invoqué pour recevoir un message côté alice"
   msg=recoit(s)
   nonce=msg[:8]
   c=msg[8:]
   cipher=AES.new(kba, AES.MODE_CTR, nonce=nonce)
   data=cipher.decrypt(c)
   return data

def start_bob(s):
   "invoqué avant toute opération chez bob"
   global kab,kba
   # RSA
   key=keys.bob_rsa
   nb=key.n
   eb=key.e
   db=key.d
   na=iob(recoit(s))
   ea=iob(recoit(s))
   envoie(s,'bob',boi(nb))
   envoie(s,'bob',boi(eb))
   x=iob(recoit(s))
   a=pow(x,db,nb)
   y=pow(a,ea,na)
   envoie(s,'bob',boi(y))
   # secret partagé
   k=boi(a)
   # dérivation de clés
   h=HMAC.new(k, digestmod=SHA256)
   h.update(b'alice->bob')
   kab=h.digest()
   h=HMAC.new(k, digestmod=SHA256)
   h.update(b'bob->alice')
   kba=h.digest()

def send_bob(s,data):
   "invoqué pour envoyer un message côté alice"
   cipher=AES.new(kba, AES.MODE_CTR)
   assert len(cipher.nonce)==8
   c=cipher.encrypt(data)
   msg=cipher.nonce+c
   envoie(s,'bob',msg)

def recv_bob(s):
   "invoqué pour recevoir un message côté alice"
   msg=recoit(s)
   nonce=msg[:8]
   c=msg[8:]
   cipher=AES.new(kab, AES.MODE_CTR, nonce=nonce)
   data=cipher.decrypt(c)
   return data