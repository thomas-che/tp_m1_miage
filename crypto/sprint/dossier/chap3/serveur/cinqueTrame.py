#J'ai eu une idée lumineuse cette nuit, ça va révolutionner la crypto.
#Ah bon ? Tu es sûre... parce que la dernière fois ça s'est mal terminé...
#Mais oui, plutôt que tous ces trucs compliqués je mélange TOTP avec MT19937 : random.Random

from random import Random
from hashlib import sha256
from datetime import datetime
import hmac
import keys

def epoch():
    return int(datetime.now().timestamp())

def tick():
    return (epoch()-1234567890)//225

def totp(k,digits=6,algo=sha256,tx=None):
    if tx is None:
        tx=tick()
    dgst=hmac.new(k,tx.to_bytes(8,"big"),algo).digest()
    offset=dgst[-1]&15
    extrait=bytearray(dgst[offset:offset+4])
    extrait[0]=extrait[0]&127
    return int.from_bytes(extrait,"big")%(10**digits)

kab=None
kba=None

def gimme(rnd,n):
    return bytes([ rnd.randint(0,255) for i in range(n) ])

def strxor(x,y):
    return bytes([ x[i] ^ y[i] for i in range(len(x)) ])

def envoie(s,qui,quoi):
    s.send('{}: {}\n'.format(qui,quoi.hex()).encode())

def recoit(s):
    data=s.recv()
    st=data.index(b' ')
    return bytes.fromhex(data[st+1:-1].decode())

def start_alice(s):
   "invoqué avant toute opération chez alice"
   global kab,kba
   # dérivation de clés, totp évite le rejeu
   k=keys.k
   ha=hmac.new(k, b'alice->bob', sha256)
   seeda=totp(ha.digest())
   kab=Random(seeda)
   hb=hmac.new(k, b'bob->alice', sha256)
   seedb=totp(hb.digest())
   kba=Random(seedb)

def send_alice(s,data):
   "invoqué pour envoyer un message côté alice"
   n=len(data)
   mask=gimme(kab,n)
   msg=strxor(data,mask)
   envoie(s,'alice',msg)

def recv_alice(s):
   "invoqué pour recevoir un message côté alice"
   msg=recoit(s)
   n=len(msg)
   mask=gimme(kba,n)
   data=strxor(msg,mask)
   return data

def start_bob(s):
   "invoqué avant toute opération chez bob"
   global kab,kba
   # dérivation de clés, totp évite le rejeu
   k=keys.k
   ha=hmac.new(k, b'alice->bob', sha256)
   seeda=totp(ha.digest())
   kab=Random(seeda)
   hb=hmac.new(k, b'bob->alice', sha256)
   seedb=totp(hb.digest())
   kba=Random(seedb)

def send_bob(s,data):
   "invoqué pour envoyer un message côté alice"
   n=len(data)
   mask=gimme(kba,n)
   msg=strxor(data,mask)
   envoie(s,'alice',msg)


def recv_bob(s):
   "invoqué pour recevoir un message côté alice"
   msg=recoit(s)
   n=len(msg)
   mask=gimme(kab,n)
   data=strxor(msg,mask)
   return data
