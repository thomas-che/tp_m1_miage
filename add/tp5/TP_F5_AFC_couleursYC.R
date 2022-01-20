## TP R M1 MIAGE 2021 - Didier Chauveau
#################################################
## TP F5 - AFC data Cours Couleurs Yeux-Cheveux
#################################################
library(ade4) # using ade4 package
cYC <- read.table("/home/thomas/Documents/tp_m1_miage/add/tp5/couleursYeuxCheveux.txt")
class(cYC)
cYC



# techniques from Chapter 1
## profils: require an object of class table 
tYC <- as.table(as.matrix(cYC)) # coerce to matrix first
tYC

print(prop.table(tYC,margin=1),2) # row profiles
print(prop.table(tYC,margin=2),2) # column profiles
spineplot(tYC, off=1, xlab="couleur des yeux", ylab="couleurs des cheveux")

spineplot(t(tYC)) # transposée, voir (t)
chisq.test(tYC) # conclusion...

## rem: allure de la densité de la loi sous H0 (cf TP2)
z <- seq(0,140, len=300)
plot(z, dchisq(x = z, df = 9), type="l")
abline(h=0, col=8)
#########################################


### AFC
?dudi.coa
afc <-dudi.coa(cYC, scan = FALSE) # df as input
class(afc)
summary(afc) # donne la répartition de l'inertie (call inertia.dudi)
scatterutil.eigen(afc$eig,nf=3,box=T,sub="") # eboulis
title("Eboulis des valeurs propres")

afcin <- inertia.dudi(afc,col.inertia=T,row.inertia=T)
names(afcin)
# $tot.inertia = val propres et % inertie cumulés (ratio)
#    NB: TOT dans version antérieure de ade4?
# $row.abs = contributions absolues lignes
# $row.rel = cos2 lignes
# $row.cum = cos2 cumulés lignes
# $col.rel et $col.cum = idem colonnes

# Decomposition de l'inertie
afcin$tot.inertia



### vérifier lien avec test chi^2
tind <- chisq.test(tYC)
tind$stat/afc$N
sum(afc$eig)



## REPRESENTATIONS PLANS FACTORIELS: defaults functions from ade4
?scatter.coa
# S3 method for class 'coa': Which are which?
par(mfrow=c(2,2))
afc$tab
scatter(afc, method=1, posieig="none"); title("method=1")
scatter(afc, method=2, posieig="none"); title("method=2")
scatter(afc, method=3, posieig="none"); title("method=3")
# => faire le lien avec les représentations vues en cours 



## Voir le contenu de l'object afc: 
?dudi # voir Value

# poids des lignes et des colonnes (cf cours: marginales X et Y)
afc$lw 
afc$cw
## interpréter cf table

## coordonnées: par exemple
afc$l1
afc$c1

#################################################
## Scatterplots des 3 représentations "a la main" 
# avec coloration par question (X et Y) 
# et labels proportionnels aux cos2 cf ACP

# stockage des cos2 cumulés dans le plan principal (colonne 2)
cos2li <- afcin$row.cum[,2] # cos2 cumulés 1-2 pour les lignes
cos2co <- afcin$col.cum[,2] # idem colonnes

# adapter taille des points si besoin
cos2li <- cos2li/90
cos2co <- cos2co/90

par(mfrow=c(2,2))
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

# rappel ft ade4
scatter(afc, method=1, posieig="none"); title("method=1")

# NB: facteurs multiplicatifs sur chaque axe dans la simultanée quasi-berycentrique
1/sqrt(afc$eig)

