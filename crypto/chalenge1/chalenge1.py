
c = """Atzx sj ujsxnje ytzy ij rjrj ufx vzj stzx fqqntsx atzx ktzwsnw
lwfyznyjrjsy ytzyjx qjx htwwjhyntsx ? Ifsx qj hfiwj ij qf uwjufwfynts iz
XUWNSY stzx fqqtsx atzx uwtutxjw ijx ijknx mjgitrfifnwjx. Ifsx hmfvzj
ijkn nq d fzwf zsj htwwjhynts i'jcjwhnhjx f wjhzujwjw. Qf nq x'fajwj vzj
ytzy hj vzn xzny jxy knsfqjrjsy zs knhmnjw uik jshtij js gfxj64. F atzx
ij ijhtijw ytzy hjqf jy fnsxn wjhtsxynyzjw qj ithzrjsy yfsy jxujwj.
Fyyjsynts xn atzx atzqje ywfsvznqqjrjsy ijhtijw qj gfxj64 vzn xzny stzx
jxujwtsx vzj atzx faje fuuqnvzj qj ijhfqflj vzn atzx f ujwrnx ij qnwj hj
rjxxflj !"""


def ic(c):
    """calcule l'IC à partir du dictionnaire de fréquences freq"""
    # cree une chaine de char
    s=''.join([x for x in c if x.isalpha()])
    s=s.lower()

    # occurence de chaque char
    freq={}
    for x in s:
        freq[x]=freq.get(x,0)+1

    n=len(s)
    somme = 0
    for x in freq:
        nx=freq[x]  # nombre d'occurences du caractère x
        somme += (nx*(nx-1))/(n*(n-1))   
    return somme

def dechiffrementMonoAlphabetique(c,mask,chaine):

    # cree une chaine de char
    s=''.join([x for x in c if x.isalpha()])
    s=s.lower()

    # occurence de chaque char
    freq={}
    for x in s:
        freq[x]=freq.get(x,0)+1

    # tri decroissant ocurence
    l=[(freq[c],c) for c in freq]
    l.sort(reverse=True)

    m = c.translate(str.maketrans(mask, chaine))
    return m


print(ic(c))

vigenere = "EAISTNRULODMPCVQGBFJHZXYKW"
vigenereMin = "eaistnrulodmpcvqgbfjhzxykw"

mask   = "jxztfnywsiqhuavrkmlgedc"
chaine = "eaistnrulodmpcvqgbfjhzx"
print(dechiffrementMonoAlphabetique(c, mask, chaine))



#---------------------------


print("\n\n---------\n")

# cree une chaine de char
s=''.join([x for x in c])

# occurence de chaque char
freq={}
for x in s:
    freq[x]=freq.get(x,0)+1

# tri decroissant ocurence
l=[(freq[c],c) for c in freq]
l.sort(reverse=True)

print(l)
a=""
for i in l:
    a+=i[1]
print(a)

vigenereMin = "eaistnrulodmpcvqgbfjhzxykw"

mask   = "ribelsfnhgadqtcozpyuvxwkjm"
chaine = "eaistnrulodmpcvqgbfjhzxykw"
m = dechiffrementMonoAlphabetique(c, a, chaine)

fichier = open("chalenge1/m.pdf", "a")
fichier.write(m)
fichier.close()

#-----------------------------------

m = c.translate(str.maketrans('AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz','VvWwXxYyZzAaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUu'))
fichier = open("chalenge1/m.pdf", "a")
fichier.write(m)
fichier.close()