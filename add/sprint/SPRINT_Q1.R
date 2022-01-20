## Enquête emploi 2001 INSEE
## sujet SPRINT 2021
###################################################
## Code de démarrage pour la Question 1
## typologie du travail a temps partiel chez les actifs

rm(list = ls()) # vider le workspace (au cas où...)
load("/home/thomas/Documents/tp_m1_miage/add/sprint/Emploi01.Rdata") # Charger le fichier de data
dim(Emploi01) # vérifier que cela correspond au sujet
colnames(Emploi01) # 113 variables...

# Sélection des variables utiles pour la Question 1
varQ1 <- c("FI", "TP", "AGCM", "S", "DDIPL", "NR", "RG")
dataQ1 <- Emploi01[, varQ1]
summary(dataQ1) # attention aux codages: tous class "factor"

colSums(is.na(dataQ1)) # pas de NA au sens de R
# mais en fait codage issu de l'export SAS, cf. Variables_emploi2001.pdf

## EXEMPLE de recodage en recodant NA les non réponses
## pour FI: 3205 sans objet ou non renseigné codés "" -> NA
table(dataQ1$FI)
levels(dataQ1$FI) # non renseigné codé "" (chaîne vide)
FI2 <- dataQ1$FI
FI2[FI2 == ""] <- NA
table(FI2) # la modalité NA n'est pas affiché, mais "" subsiste avec count 0
FI2 <- factor(FI2, labels = c("actifs", "chômeur", "étudiant", "militaire", 
                              "retraité", "retiré", "ffoyer", "inactif"))
table(FI2) # modalité "" supprimée, et NA non affichés
table(FI2, useNA = "ifany") # avec le nb de NA
# TOUJOURS vérifier le recodage cf TPs
table(dataQ1$FI, FI2, useNA = "ifany")

# une fois vérif ok, remplacer la variable dans le data frame
# (ne pas attacher avant, cf TP)
dataQ1$FI <- FI2
summary(dataQ1) # OK pour FI: le nb de NA apparaît 

# Exemple de recodage d'un "factor" en "numeric": 
# attention au codage des "factor" cf TPs
AGEnum <- as.numeric(as.character(dataQ1$AGCM))
summary(AGEnum)
dataQ1$AGCM <- AGEnum
#### => etc... à vous de continuer! ####


#Limitez l’étude à la sous-population des actifs selon la variable FI, de 20 à 50 ans selon la variable AGCM.
#Combien reste-t-il d’individus?


# Pour manipuler plus simplement les variables
attach(dataQ1)
# On stocke dans un tableau que les actifs
tab <- FI=="actifs"
# On supprime les individus sans valeur (NA)
tab[is.na(tab)]<- FALSE
# Dans un variable on met les ligne de notre tableau
dataQuetion1 <- dataQ1[tab,]
# On ne garde que les individus avec un age compris entre 20 ans et 50 ans
dataquestion1P2 <- dataQuetion1[dataQuetion1$AGCM <= 50 & dataQuetion1$AGCM >= 20 ,]
# Affiche le resumer des valeurs
summary(dataquestion1P2)


# 2)b) Recodez Région (RG) en 7 ou 8 “grandes régions” en perdant le moins possible l’information.
dataquestion1P2
levels(NR)
summary(levels(NR))




# On regarde le nombre d'individu par nationalité
table(dataquestion1P2$NR)
# On stocke la repatition individus par nationalité
NR2 <- dataquestion1P2$NR
table(NR2) 
# On recode les facteurs nationalités en 4 groupes
NR2 <- factor(NR2, labels = c("France", "Afrique", "Afrique", "Europe", "Europe", "Europe", "Europe", "Europe", "Autres", "Autres"))
# On controle notre repatiotion
table(NR2) 
# On controle la repartition avant le recodage
table(dataquestion1P2$NR, NR2)

# On remplace la variable NR par notre nouvelle variable
dataquestion1P2$NR <- NR2
# Controle que la nouevlle variable soit bien dans la table
summary(dataquestion1P2$NR) 

par(mfrow=c(1,1))

contingence = table(dataquestion1P2$TP, dataquestion1P2$AGCM ) # creation de la table de contingence
contingence
dataquestion1P2

RG2 <- dataquestion1P2$RG
RG2 <- factor(RG2,labels=c("IDF","EST","NORD","NORD","CENTRE","OUEST","CENTRE","NORD","EST","EST","EST","OUEST","OUEST","OUEST","SUD_OUEST","SUD_OUEST","CENTRE","SUD_EST","CENTRE","SUD_OUEST","SUD_EST"))
dataquestion1P2$RG <- RG2



contingence = table(dataquestion1P2$TP, dataquestion1P2$AGCM ) # creation de la table de contingence
chisq.test(dataquestion1P2$TP,dataquestion1P2$AGCM) #test du chi2 pour voir si on rejete ou non l'hypothese d'indépendance
spineplot(t(contingence), xlab = "Age", ylab = "temps partiel(2)/complet(1)") # affichage de la repartition générée par le tableau de contingence
title("Represenstation temps de travail/Age")

contingence2 = table(dataquestion1P2$TP, dataquestion1P2$S )
chisq.test(dataquestion1P2$TP,dataquestion1P2$S)
spineplot(t(contingence2), xlab = "Sexe (1 = homme, 2 = femme)", ylab = "temps partiel(2)/complet(1)") 
title("Represenstation temps de travail/Sexe")

contingence3 = table(dataquestion1P2$TP, dataquestion1P2$DDIPL )
chisq.test(dataquestion1P2$TP,dataquestion1P2$DDIPL)
spineplot(t(contingence3), xlab = "niveau de diplome", ylab = "temps partiel(2)/complet(1)")
title("Represenstation temps de travail/niveau de diplome")

contingence4 = table(dataquestion1P2$TP, dataquestion1P2$RG)
chisq.test(dataquestion1P2$TP,dataquestion1P2$RG)
spineplot(t(contingence4), xlab = "Regions", ylab = "temps partiel(2)/complet(1)")
title("Represenstation temps de travail/Region")

contingence5 = table(dataquestion1P2$TP, dataquestion1P2$NR )
chisq.test(dataquestion1P2$TP,dataquestion1P2$NR)
spineplot(t(contingence5),  xlab = "Nationalités", ylab = "temps partiel(2)/complet(1)")
title("Represenstation temps de travail/Nationalité")
