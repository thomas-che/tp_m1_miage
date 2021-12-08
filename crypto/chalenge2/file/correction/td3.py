
# Correction exercice 3 du TD3

# > select * from users;
# demo|affe 164f 2b4d 9462 fb5d
# alice|affe 0f0e 3a4d 9d65 a67c
# bob|affe 1a10 2a0c ce34 fc3f
# charlie|affe 1b48 7c5e ce6e fc72


# Le mot de passe de demo est nocake4U


def byte_xor(b1, b2):
    """ XOR two byte strings """
    return bytes([x ^ y for x, y in zip(b1, b2)])


if __name__ == '__main__':


# On encode en bytes le mot de passe de démo
    motDePassedemo = "nocake4U".encode()
# On traduit en bytes les différents mots de passe définis en hexa
    motDePasseDemoChiffre = bytes.fromhex("164f2b4d9462fb5d")
    motDePasseAliceChiffre = bytes.fromhex("0f0e3a4d9d65a67c")
    motDePasseBobChiffre = bytes.fromhex("1a102a0cce34fc3f")
    motDePasseCharlie = bytes.fromhex("1b487c5ece6efc72")


# Grâce aux données liées à démo on peut calculer tous les blocs secrets du
# CTR sachant que le CTR a été lancé avec les mêmes paramètres (vecteur
# initialisation + mot de passe)

    blocsCTRSecrets= byte_xor(motDePassedemo,motDePasseDemoChiffre)

# En xorant ainsi le mot de passe chiffré d'alice avec les blocs secrets,
# nous obtiendrons directement le message clair. On procède de même
# pour les autres

    print(byte_xor(motDePasseAliceChiffre,blocsCTRSecrets))
    print(byte_xor(blocsCTRSecrets,motDePasseCharlie))
    print(byte_xor(blocsCTRSecrets,motDePasseBobChiffre))
