menages <- read.table("/home/thomas/Documents/tp_m1_miage/add/tp3/menages.txt", header = T, row.names = 1)
save(menages, file = "/home/thomas/Documents/tp_m1_miage/add/tp3/menages.Rdata")
load("/home/thomas/Documents/tp_m1_miage/add/tp3/menages.Rdata")
menages
attach(menages)
library(ade4)

# EX 1

# 1)
row.names(menages)

CSP <- as.factor(substr(row.names(menages), 1, 2))
CSP # class social

NE <- as.factor(substr(row.names(menages), 3, 3))
NE # nb enfants

# 2)
par(mfrow=c(2,4))
ind=1:7
for(j in ind){
    hist(menages[,j], col=8, xlab="", cex.main=1, cex.axis=1, main=colnames(menages)[j])
}


# 3)
#est-ce normée ?? regrade l'ecart type, si beaucoup dif alors on norme les valeur
apply(menages, 2, sd)


# 4)
acp <- dudi.pca(menages[ ,1:7], scale=FALSE, scannf = F, nf= 7 )
names(acp)
par(mfrow=c(1,1))
acpmenages <- dudi.pca(menages)
# acp => resume les données du tab en 2 var

plot(acpmenages$eig, type="l")


acpmenages$eig/7
cumsum(acpmenages$eig/7) # on voit bien qu'a la 3eme valeur, 97%
# on prend 3 var car le coude est a la 4eme donc on prend coude-1 variable


# 5)
# acp non normé
imenn <- inertia.dudi(acp,  inertia = F, row.inertia = T )
names(imenn)

acp <- dudi.pca(menages[ ,1:7], scannf = F, nf= 3)
s.corcircle(acp$co)

