## TPs R M1 MIAGE 2021 - Didier Chauveau
#############################################
## TP F3 - Analyse en Composantes Principales
#############################################
## ACP donnees Menages

# (1) DONNEES
men <- read.table("/home/thomas/Documents/tp_m1_miage/add/tp3/menages.txt", header=TRUE)

head(men)

# separation des labels en CSP et nbEnfants (NE) 
CSP <- as.factor(substr(men$Menage,1,2))
NE <- as.factor(substr(men$Menage,3,3))

# row.names contient 1,2,.. = R default
# Menages -> labels individus 
row.names(men) <- men$Menage
men2 <- data.frame(CSP, NE, men[,-1])
head(men2) # ok, then save
save(men2, file = "Menages.Rdata")



###########################
# (2) ETUDE   PRELIMINAIRE
# toujours commencer par
summary(men2) # vérifier classes, min , max, NA's...

# cf TP F2 
par(mfrow=c(2,4))  
for (j in 3:9) {
  boxplot(men2[,j], col=8, xlab="",
       cex.main=1.5, cex.axis=1.2, # char sizes
       main=colnames(men2)[j])
}

for (j in 3:9) {
  hist(men2[,j], col=8, xlab="",
       cex.main=1.5, cex.axis=1.2, # char sizes
       main=colnames(men2)[j])
}
## => INTERPRETER (mais ici n petit)


## (3) pour l'ACP: Choix de la métrique?
print(apply(men2[, 3:9], 2, var), 3)
print(apply(men2[, 3:9], 2, sd), 3)
# ici tout en Franc, et écart ~ 5x, on peut essayer les 2 métriques 

print( cor(men2[, 3:9]), 3)
# cor maximale (Viande, Volaille) = +98%
## => INTERPRETER



###########################
## (4) ACP avec package ade4
library(ade4)
?dudi.pca # check Value = object returned
# scale = TRUE : ACP normée (default)
# scale = FALSE: ACP non normée = distance Euclidienne usuelle

# On commence par l'ACP avec la distance usuelle : on garde l'unité (Franc)
acp <- dudi.pca(men2[,3:9], scale=FALSE, scannf=F, nf=3) # ACP non normée
class(acp)
names(acp)
head(acp$li) # Principal Components's, 3 premiers axes


## (5)
?inertia.dudi
Imen <- inertia.dudi(acp, col.inertia=F, row.inertia=T)
names(Imen)
Imen$tot.inertia # val propres et % inertie cumulés (ratio)
# => cf Cours, sorties standard d'une ACP

par(mfrow=c(1,2)) 
# eigen bargraph Eboulis des valeurs propres
scatterutil.eigen(acp$eig,nf=3,box=T,sub="")
# ou direct
barplot(acp$eig, names.arg = 1:7) # p =  7 ici

## => INTERPRETER




### (6) CERCLES DE CORRELATIONS
?s.corcircle # see example code
s.corcircle(acp$co) # ??faux dans le cas ACP NON NORME
# dans le cas ACP non normee, $co = covariances

## (6) donc calcul des correlations anciens (X), nouveaux (Psi):
?cor
cc <- cor(men2[,3:9], acp$li)
cc

par(mfrow=c(1,2)) # avoir le cercle et le plan individus sur le même plot
## Cercle principal (adapter clabel taille des noms de var)
s.corcircle(cc, clabel=0.8, sub="Cercle de corrélations 1-2") 
title("ACP non normée")
# next... si besoin

## => INTERPRETER



### (7) PLANS FACTORIELS INDIVIDUS
# plan principal avec CSP en facteur supplémentaire
#    labels individus colorés par CSP
#    coloration par CSP
#    barycentres/classes avec s.class
# cf TP2  
# la fonction s.class ne donne pas les labels individus
par(mfrow=c(1,1))
plot(acp$li$Axis1, acp$li$Axis2, type="n",
     xlab = "Axe principal  1", ylab = "Axe principal 2")
text(acp$li$Axis1, acp$li$Axis2, row.names(men2), 
     col = as.numeric(CSP))
abline(h=0); abline(v=0) # barycentre du nuage
## barycentres/calsses avec s.class
colCSP <- seq(1,length(levels(CSP)))
s.class(acp$li,fac=CSP, cstar=0,cpoint=0,clabel=1.4,
        col=colCSP, axesell=FALSE, add.plot=TRUE)
title("Plan principal ACP non normée")

## => INTERPRETER


## analyse par NE - ajout sur le même plot des barycentres/NE
colNE <- seq(1,length(levels(NE)))
# s.class(acpmen$li, nE,cstar=0,cpoint=1,clabel=0.8,col=colnE,axesell=FALSE)
# barycentres NE superposés sur le plan principal (add.plot=T)
s.class(acp$li, fac=NE, cstar=0, cpoint=0, clabel=1.8,
        cellipse=0, col=colNE, axesell=FALSE, add.plot=TRUE)

## => INTERPRETER



###########################
## (8) Usage des cos^2 et représentation
## Cos^2 ET CUMULES
Imen$row.cum # cos2 cumulés (%)
# dans le Plan Principal: colonne Axis1:2
cos2 <- Imen$row.cum[,2] # cos2 cumulés plan princ. en % (ade4 last versions)
cos2lab <- 1.3*cos2/100 # label sizes, tune here!
plot(acp$li$Axis1, acp$li$Axis2, type="n",
     xlab = "Axe principal  1", ylab = "Axe principal 2")
text(acp$li$Axis1, acp$li$Axis2, cex=cos2lab,
     labels=row.names(men2), col=as.numeric(CSP))
s.class(acp$li, CSP, cstar=0, cpoint=0, clabel=1.5,
        col=colCSP, axesell=F, add.plot=T)
abline(h=0, col=8); abline(v=0, col=8)
title("Plan Principal avec label proportionnels aux cos2")

## => INTERPRETER

### (9) Affichage des individus les moins bien représentés
o <- order(cos2)
mencos2 <- data.frame(CSP, NE, cos2)
row.names(mencos2) <- row.names(men2)
mencos2[o,] [1:5,] # les 5 individus moins bien représentés




##################### 
#### Essai ACP Normée : scale=TRUE
acp <- dudi.pca(men2[,3:9], scale=TRUE, scannf=F, nf=3) # ACP normée

Imen <- inertia.dudi(acp, col.inertia=F, row.inertia=TRUE)
round(Imen$tot.inertia,2)  # val propres et % inertie cumulés (ratio)

par(mfrow=c(2,2)) # 4 graphiques sur le même plot
# eigen bargraph Eboulis des valeurs propres
scatterutil.eigen(acp$eig,nf=3,box=T,sub="ACP Normée")

## (6) calcul des correlations anciens (X), nouveaux (Psi):
s.corcircle(acp$co) #
title("ACP normée")
# plan principal avec CSP en facteur supplémentaire
plot(acp$li$Axis1, acp$li$Axis2, type="n",
     xlab = "Axe principal  1", ylab = "Axe principal 2")
text(acp$li$Axis1, acp$li$Axis2, row.names(men2), 
     col = as.numeric(CSP))
abline(h=0); abline(v=0)
## barycentres/classes avec s.class
colCSP <- seq(1,length(levels(CSP)))
s.class(acp$li,fac=CSP, cstar=0,cpoint=0,clabel=1.4,
        col=colCSP, axesell=FALSE, add.plot=TRUE)

## analyse par NE - ajout sur le même plot des barycentres/NE
colNE <- seq(1,length(levels(NE)))
# s.class(acpmen$li, nE,cstar=0,cpoint=1,clabel=0.8,col=colnE,axesell=FALSE)
# barycentres NE superposés sur le plan principal (add.plot=T)
s.class(acp$li, fac=NE, cstar=0, cpoint=0,clabel=1.7,
        cellipse=0, col=colNE, axesell=FALSE, add.plot=TRUE)

## => INTERPRETER ET VOIR LA DIFFERENCE AVEC ACP NON NORMEE ICI

#### END ####

