## Enquête emploi 2001 INSEE
## sujet SPRINT 2021
###################################################
## Code de démarrage pour la Question 3
## AFC
library(ade4) # using ade4 package
## une fois votre data.frame contenant DDIPL et RG prêt, 
## exécutez le code ci-dessous pour 
##  - créer la table de contingence
##  - la convertir en data.frame acceptable par dudi.coa pour l'AFC



rm(list = ls()) # vider le workspace (au cas où...)
load("/home/thomas/Documents/tp_m1_miage/add/sprint/Emploi01.Rdata") # Charger le fichier de data

# On recode les regions en 7 groupe comme à la question 1
varQ1 <- c("FI", "TP", "AGCM", "S", "DDIPL", "NR", "RG")
dataQ1 <- Emploi01[, varQ1]
attach(dataQ1)
RG2 <- RG
RG2 <- factor(RG2,labels=c("IDF","EST","NORD","NORD","CENTRE","OUEST","CENTRE","NORD","EST","EST","EST","OUEST","OUEST","OUEST","SUD_OUEST","SUD_OUEST","CENTRE","SUD_EST","CENTRE","SUD_OUEST","SUD_EST"))
RG <- RG2

#actifs, chomeurs, étudiants, retraités
#1 2 3 5
# On conseve que les individus qui sont : soit actifs, soit chomeurs, soit étudiants, soit retraités
dataQ1$FI
dataQ1 <- dataQ1[dataQ1$FI == 1 | dataQ1$FI == 2 | dataQ1$FI == 3 | dataQ1$FI == 5 ,]
dataQ1
# recode les diplomes
DDIPL2 <- DDIPL
DDIPL2 <- factor(DDIPL2,labels=c("NA", "Dip sup", "Bac +2", "Bac ou Brevet", "Cap / Bep", "BEPC", "Autre diplome"))
DDIPL <- DDIPL2



tb <- table(DDIPL, RG)
tbdf <- as.data.frame(unclass(tb))  # convertir en data frame pour afc !
class(tbdf) # "data.frame"
tbdf # doit redonner la table de contingence; mais autre format

## puis AFC...



afc <-dudi.coa(tbdf, scan = FALSE) # df as input
summary(afc) # donne la répartition de l'inertie (call inertia.dudi)

afcin <- inertia.dudi(afc,col.inertia=T,row.inertia=T)
# Decomposition de l'inertie
afcin$tot.inertia 

# On garde 3 colonne pour avoir 96% d'inertie
afc <-dudi.coa(tbdf, scan = FALSE, nf=3)
afcin <- inertia.dudi(afc,col.inertia=T,row.inertia=T)
afcin$tot.inertia 

# Controle visul de la regle du code, car on a choisi avant 3 colonnes (valeur de boulies)
scatterutil.eigen(afc$eig,nf=3,box=T,sub="")
title("Eboulis des valeurs propres")



# Test chi^2
tind <- chisq.test(tbdf)
tind$stat/afc$N
sum(afc$eig)
# on est a 0.020 donc on rejet l'hypoteze H0, il n'y a pas de independance


# REPRESENTATIONS PLANS FACTORIELS: defaults functions from ade4

par(mfrow=c(1,1))

scatter(afc, method=1, posieig="none"); title("method=1")
scatter(afc, method=2, posieig="none"); title("method=2")
scatter(afc, method=3, posieig="none"); title("method=3")





## Voir le contenu de l'object afc: 

# poids des lignes et des colonnes (cf cours: marginales X et Y)
afc$lw 
afc$cw
## interpréter cf table

## coordonnées: par exemple
afc$l1
afc$c1

# stockage des cos2 cumulés dans le plan principal (colonne 2)
cos2li <- afcin$row.cum[,2] # cos2 cumulés 1-2 pour les lignes
cos2co <- afcin$col.cum[,2] # idem colonnes

# adapter taille des points si besoin
cos2li <- cos2li/90
cos2co <- cos2co/90

par(mfrow=c(1,1))
## $li et $c1: 
## echelle depend des 2 nuages: assembler les 2 nuages avant plot()
mt <- rbind(as.matrix(afc$li), as.matrix(afc$c1))
plot(mt[,1], mt[,2], type="n", xlab="Axe 1", ylab="Axe 2")
text(afc$li$Axis1, afc$li$Axis2, row.names(afc$li), cex=cos2li)
text(afc$c1$CS1, afc$c1$CS2, row.names(afc$c1), col=2, cex=cos2co)
title("li et c1: ACP col - barycentres lignes") # Yeux sont a l'intérieur des nuages => ACP
abline(h=0, col=8); abline(v=0, col=8)

## $l1 et $co:  
mt <- rbind(as.matrix(afc$l1), as.matrix(afc$co))
plot(mt[,1], mt[,2], type="n", xlab="Axe 1", ylab="Axe 2")
text(afc$l1$RS1, afc$l1$RS2, row.names(afc$l1), cex=cos2li)
text(afc$co$Comp1, afc$co$Comp2,row.names(afc$co), col=2, cex=cos2co)
title("l1 et co: ACP lignes - barycentres col")  
abline(h=0, col=8); abline(v=0, col=8)


## $l1 et $c1:  
mt <- rbind(as.matrix(afc$l1), as.matrix(afc$c1))
plot(mt[,1], mt[,2], type="n", xlab="Axe 1", ylab="Axe 2")
text(afc$l1$RS1, afc$l1$RS2, row.names(afc$l1), cex=cos2li)
text(afc$c1$CS1, afc$c1$CS2,row.names(afc$c1), col=2, cex=cos2co)
title("l1 et c1: ACP lignes - ACP col") # simultanée ACP col OK
abline(h=0, col=8); abline(v=0, col=8)

