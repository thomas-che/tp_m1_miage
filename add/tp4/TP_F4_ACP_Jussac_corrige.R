## TP R M1 MIAGE 2021 - Didier Chauveau
#################################################
## TP F4 - ACP avec individu(s) supplémentaire(s)
#################################################
## ACP - exemple du canidé de Jussac
## ACP package ade4 avec 
# var qualitative supplémentaire et barycentres
# individus supplémentaires
library(ade4)

# DONNEES ET CONSTRUCTION DES TABLES
jussac <- read.table("/home/thomas/Documents/tp_m1_miage/add/tp4/Jussac.txt", header=TRUE)
head(jussac)

summary(jussac)

row.names(jussac) # just 1,2,... (R default)
# la colonne "no" ne sert à rien
jussac <- jussac[,-1]

## séparer en table des individus actifs et supplémentaires
actifs <- jussac[jussac$Type != "J",] # tous sauf le jussac
indsup <- jussac[jussac$Type == "J",] # que le jussac


summary(actifs) # Type et Iden sont "factor" ou "char" suivant version de R

## il FAUT que ce soit des factors:
raceCL <- factor(actifs$Type, labels=c("Chien","Loup"))

table(actifs$Type, raceCL) # check...
#


par(mfrow=c(2,3))
for (j in 1:6) {
  hist(actifs[,j], col=8, xlab="",
       main=colnames(actifs)[j])
}



# individus supplémentaires = jussac seul ici
indsup



## pour l'ACP: Choix de la métrique?
# ici tout en mm, donc la question de normer ou pas se pose
ectp <- apply(actifs[, 1:6], 2, sd)
print(ectp, 3)
max(ectp)/min(ectp) # plutôt normer.. justifier!
## => ACP normée



# ACP NORMEE des indiv chiens et Loups
acp <- dudi.pca(actifs[, 1:6], scale=TRUE, scannf=F, nf=3) # ACP normée

iCL <- inertia.dudi(acp,col.inertia=F,row.inertia=T)
print(iCL$tot.inertia,2) # val propres et % inertie cumulés (ratio)
# ou iCL$TOT suivant version de ade4


## => CHOIX DU NOMBRE D'AXES
# scatterutil.eigen(acp$eig,nf=3,box=T,sub="Eboulis des valeurs propres")
par(mfrow=c(1,1))
barplot(acp$eig)


## GRAPHIQUES
par(mfrow=c(2,1)) # 2 subplots 
# plan caractères = CC
s.corcircle(acp$co,clabel=0.5,sub="Cercle de corrélations 1-2")
title("ACP Normée")
# s.corcircle(acpCL$co, 1,3, clabel=0.9,sub="Cercle de corrélations 1-3")

# plans individus avec barycentres d'un facteur
# ici on peut utiliser s.class sans labels individus, ou faire comme TP3
# plan factorel acp des C et L seuls, avec coloration etc
# colCL <- seq(1,length(levels(raceCL))) # pour couleurs; 1,2 ici
s.class(acp$li, raceCL, cstar=0,cpoint=1,clabel=0.8, col=1:2)
title("Plan principal ACP actifs")
## => INTERPRETER



## Plan 1-3:
par(mfrow=c(2,1)) # 2 subplots 
s.corcircle(acp$co, yax=3, clabel=0.6, 
            sub="Cercle de corrélations 1-3")
s.class(acp$li, raceCL, yax=3, cstar=0, 
        cpoint=1,clabel=0.8, col=1:2)
title("Plan 1-3 ACP actifs")




# CALCUL ET PROJECTION DES INDIVIDUS SUPPLEMENTAIRES
par(mfrow=c(2,1))
s.class(acp$li, raceCL,cstar=0,cpoint=1,clabel=0.8,col=1:2) # plan individus 
# calculs projetés indiv supplementaires (1 seul ici)
?suprow
acpsup <- suprow(acp, indsup[,1:6])

# ajout avec une ft de ade4
# add.p=T pour qu'il s'ajoute au graphe courant
s.label(acpsup$lisup, clab=1.0, label=indsup$Iden, add.p=TRUE)
title('Plan principal avec Jussac supplémentaire')

# plan 1-3
s.class(acp$li, raceCL, yax=3, cstar=0,cpoint=1,clabel=0.8,col=1:2)
# rajout de l'indiv supplementaire, 
s.label(acpsup$lisup, yax=3, clab=1.01, label=indsup$Iden, add.p=T)
title('Plan 1-3 avec Jussac supplémentaire')



## AVEC LES RACES DE CHIENS
par(mfrow=c(1,1))
plot(acp$li$Axis1, acp$li$Axis2, type="n")
text(acp$li$Axis1, acp$li$Axis2, actifs$Iden, 
     cex=0.8, # taille des labels 
     col = as.numeric(actifs$Type))
abline(h=0); abline(v=0)
## barycentres/classes avec s.class
s.class(acp$li,fac=raceCL, cstar=0,cpoint=0, clabel=1.,
        col=1:2, axesell=FALSE, add.plot=TRUE)
s.label(acpsup$lisup[,c(1,3)],clab=1.4, label=indsup$Iden, add.p=T)





## VOIR ACP NON NORMEE (en gardant les mm)
# les variables liées aux dents ne jouent plus, 
# or c'est visiblement l'une d'elle (ou plusieur) qui discrimine
acp2 <- dudi.pca(actifs[, 1:6], scale=FALSE, scannf=F, nf=3)
iCL2 <- inertia.dudi(acp2,col.inertia=F,row.inertia=T)
iCL2$tot.inertia # val propres et % inertie cumulés (ratio)

## (6) calcul des correlations anciens (X), nouveaux (Psi):
## cf ACP menages, corcircle() n'est pas adapté à l'ACP non normée
cc <- cor(actifs[, 1:6], acp2$li)
cc 

par(mfrow=c(2,1)) # avoir le cercle et le plan individus surle même plot
s.corcircle(cc, clabel=0.8, sub="Cercle de corrélations 1-2") 
title("ACP non normée, actifs")
s.class(acp2$li, raceCL,
        cstar=0,    # supprime les lignes entre bary et individus
        cpoint=1,   # taille des points individus
        clabel=0.8, # tailles des labels du facteur
        col=1:2)    # couleurs des modalités (C, L)

acpsup2 <- suprow(acp2, indsup[,1:6])
s.label(acpsup2$lisup, clab=1, label=indsup$Iden, add.p=TRUE)
title('Plan principal des actifs + Jussac')



## plan 1-3
s.corcircle(cc, yax = 3,
            clabel=0.8, sub="Cercle 1-3") 

s.class(acp2$li, raceCL, yax=3,
        cstar=0,    # supprime les lignes entre bary et individus
        cpoint=1,   # taille des points individus
        clabel=0.8, # tailles des labels du facteur
        col=1:2)    # couleurs des modalités (C, L)
s.label(acpsup2$lisup, yax = 3,
        clab=1, label=indsup$Iden, add.p=TRUE)

## idem a la main (pour éviter l'échelle auto)
par(mfrow=c(1,1))
plot(acp2$li$Axis1, acp2$li$Axis3, type="n")
text(acp2$li$Axis1, acp2$li$Axis3,
     labels = actifs$Iden, col=as.numeric(raceCL))
title("ACP non normée Plan 1-3")
abline(h=0); abline(v=0)
text(acpsup2$lisup$Axis1, acpsup2$lisup$Axis3,
     labels = indsup$Iden, col=4, cex=1.4)

