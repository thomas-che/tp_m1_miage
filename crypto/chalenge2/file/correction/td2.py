#!/bin/env python3

# M1 Informatique / M1 MIAGE - Univ. Orléans - Crypto - 2021-2022
# TD2 - Chiffrements symétriques I - Mini RC4

import itertools


#############################################################################
# Fonctions d'encodage et décodage en octal

# Représentation "en ligne" du tableau de codage en octal
alnum = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789. "


def encoder(message):
    """
    Encode un message en octal.
    Chaque caractère (lettres minuscules et majuscules, chiffres, point et
    espace) est codé par 2 entiers de 3 bits. Par exemple, le caractère 'U'
    est codé en [2, 4].
    """
    code = []
    for c in message:
        v = alnum.index(c)
        code.append(v // 8)
        code.append(v % 8)
    return code


def decoder(code):
    """
    Décode un message encodé en octal.
    """
    message = ""
    for i in range(len(code) // 2):
        message += alnum[8 * code[2 * i] + code[2 * i + 1]]
    return message


#############################################################################
# Exercice 4. Mini RC4

# 1. Écrire les deux algorithmes init() et get_bits() pour des mots de 3 bits.


def init(k):
    """
    Retourne un état initial pour Mini RC4, à partir d'une clé k (donnée
    sous la forme d'une liste d'entiers).
    """
    S = [i for i in range(8)]
    n = len(k)
    j = 0
    for i in range(8):
        j = (j + S[i] + k[i % n]) % 8
        S[i], S[j] = S[j], S[i]
    return S, 0, 0


def get_bits(S, i, j):
    """
    Retourne un nouvel état et une valeur octale pseudo-aléatoire, à partir
    d'un état du système Mini RC4.
    """
    i = (i + 1) % 8
    j = (j + S[i]) % 8
    S[i], S[j] = S[j], S[i]
    t = (S[i] + S[j]) % 8
    y = S[t]
    return S, i, j, y


def mini_rc4(message, k):
    """Chiffre un message, à partir d'une clé k."""
    S, i, j = init(k)
    c = []
    for x in message:
        S, i, j, y = get_bits(S, i, j)
        c.append(x ^ y)
    return c


# 2. Calculer les 6 premiers mots de 3 bits générés à partir de la clé 1,4,2,9.
def exo4_q2():
    S, i, j = init([1, 4, 2, 9])
    for i in range(6):
        S, i, j, mot = get_bits(S, i, j)
        print(mot)


# 3. Chiffrer le message "OAI" avec la clé 1,4,2,9.
def exo4_q3():
    OAI = "OAI"
    print("Message en clair : {} ({})".format(OAI, encoder(OAI)))
    k = [1, 4, 2, 9]
    chiffre = mini_rc4(encoder(OAI), k)
    print("Message chiffré  : {} ({})".format(decoder(chiffre), chiffre))


# 4. Déchiffrer le message "PPx" avec la clé 4,2.
def exo4_q4():
    PPx = "PPx"
    print("Message chiffré  : {} ({})".format(PPx, encoder(PPx)))
    k = [4, 2]
    message = mini_rc4(encoder(PPx), k)
    print("Message en clair : {} ({})".format(decoder(message), message))


#############################################################################
# Bonus. Qui a écrit ce texte ?


def calculer_occurrences(texte):
    """Calcule le nombre d'occurrences de chaque caractère dans un texte."""
    freq = {}
    for x in texte:
        freq[x] = freq.get(x, 0) + 1
    return freq


def ic(texte):
    """Calcule l'indice de coïncidence d'un texte."""
    n = 0
    val_ic = 0
    freq = calculer_occurrences(texte)
    for x in freq:
        nx = freq[x]
        val_ic += nx * (nx - 1)
        n = n + nx
    val_ic = val_ic / (n * n - 1)
    return val_ic


def decipher_mini_rc4_with_initial_state(chiffre, initial_state):
    """
    Applique un déchiffrement Mini RC4 à un message chiffré, à partir d'un
    état initial donné. Le résultat obtenu peut ne pas être correct
    (c'est-à-dire ne pas être un message en clair), si l'état initial
    considéré n'est pas celui qui avait réellement servi à chiffrer le
    message.
    """
    c = encoder(chiffre)
    i, j = 0, 0
    m = []
    S = initial_state
    for x in c:
        S, i, j, y = get_bits(S, i, j)
        m.append(x ^ y)
    return decoder(m)


def decipher_mini_rc4(chiffre):
    """
    Déchiffre un message chiffré avec Mini RC4.
    Pour cela, l'algorithme va tenter de déchiffrer le message à partir de
    chacun des états initiaux possibles ; le déchiffrement est réussi lorsque
    le message obtenu est en langue naturelle (IC > 0.05).
    Avec Mini RC4, chaque état initial est une permutation des entiers
    de 0 à 7. Il a donc au total 40320 états initiaux possibles, que l'on
    peut tester exhaustivement en un temps raisonnable.
    """
    for state in itertools.permutations([0, 1, 2, 3, 4, 5, 6, 7]):
        m = decipher_mini_rc4_with_initial_state(chiffre, list(state))
        if ic(m) > 0.05:
            # Le message déchiffré est en langue naturelle
            return state, m
    # Aucun déchiffrement n'a permis d'obtenir un texte en langue naturelle
    return None, None


def find_key_from_initial_state(etat_initial):
    """
    Recherche (en bruteforce) une clé de chiffrement permettant d'obtenir un
    certain état initial.
    """
    for i in range(1, 6):
        for k in itertools.product(range(8), repeat=i):
            if init(k)[0] == list(etat_initial):
                return k
    return None


def exo_bonus():
    # Message chiffré
    chiffre = (
        "KPTS1tVnJuHZMyrbclQHiD5IiJZrbxGw.9dBk8A9vZoezf A p3ZtlRlH.uemwi2VTQ"
        "DwL4lv8SO9KT9iFk9.8VoBn  sMhnFYX7JDavi19cVSKKTZFc7Xy4zXIUV.uznNcQQV"
        "wRw3RANC.b.3ruNAlkCQEF95WjkCHc5EpuBgTyjNZHZNRIWz2fymKKAnkQYL4n7E7g9"
        "UuicsQdmoIX.ixh2QYMCHzp5LVr0W7Gm41Yo8KAePUr5lhAiOSmjOtCb3R9fvi9p4q1"
        "lQtYcSw hOOF6eGfwQyiS9hrbpNjp6E3jrbln7jKZnuLsIHBMK2XtuDMDkVCR.S8V2U"
        "Niydhqzd731T71taDcyVrJ0upr5jU51CmDPiRFAqho6IVaXiplCmROfIuvjhap77vwa"
        "1lWw0LtjT3n766MDeIGkc SeoTciojgWA5VuD.km7WX5qK rf9Lv3DqgMcGQ rj5QZW"
        "qmEGcn0rw4IQ6kez8a1VxYL95BUPtCVa6Xqj2QwhDNUD7P6r3Mno49eHgSd6v iSAoz"
        "CO1ZQ.fA..JA0Ftte8AzW6sf4vMfYrl5vZPoLgQvSsV 1D0t3MSs9U1KONp0Zbu2VEz"
        "v t3ZWNm5TbpoVAdFP3bP Nh6uy74oxp 7"
    )

    print("Message chiffré : {}".format(chiffre))

    # Récupère le message en clair, et l'état initial du système ayant servi
    # à chiffrer le message
    etat_initial, message = decipher_mini_rc4(chiffre)
    print("Message en clair : {}".format(message))
    print("État initial correspondant : {}".format(etat_initial))

    # La clé de chiffrement est celle qui a permis d'obtenir l'état initial.
    # On va faire une recherche bruteforce de cette clé.
    k = find_key_from_initial_state(etat_initial)
    print("Clé de chiffrement : {}".format(k))


if __name__ == "__main__":
    print("TD2 - Chiffrements symétriques I - Mini RC4")

    print("\n== Exercice 4.")

    print(
        "\n-- 2. Calculer les 6 premiers mots de 3 bits générés à partir de "
        "la clé 1,4,2,9."
    )
    exo4_q2()

    print('\n-- 3. Chiffrer le message "OAI" avec la clé 1,4,2,9.')
    exo4_q3()

    print('\n-- 4. Déchiffrer le message "PPx" avec la clé 4,2.')
    exo4_q4()

    print("\n== Bonus. Qui a écrit ce texte ?")
    exo_bonus()
