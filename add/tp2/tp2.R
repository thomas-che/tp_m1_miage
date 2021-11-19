# Exercice 1

load("/home/thomas/Documents/tp_m1_miage/add/tp1/StateFacts.Rdata")
states
attach(states)

# 1)
fd <- cut(states$Diplome, breaks = c(min(states$Diplome),47,57,max(states$Diplome)), labels = c("faible","moyen","fort"),include.lowest = TRUE, right = TRUE, dig.lab = 3, ordered_result = FALSE)
fd
table(fd)

prop.table(table(fd))


# 2)
plot(table(fd))
barplot(table(fd))
barplot(prop.table(table(fd)))


# 3)
# table de contingence
tFdR<-table(states$Region, fd)
tFdR
barplot(tFdR)

# table des contingence en frequence 
prop.table(tFdR)

# ajout des marges : somme des colonnes et lignes
addmargins(tFdR)

# 4)
# profil ligne : 1 ligne = 1 
prop.table(tFdR, margin=1)


# profil collonne : 1 collonne = 1 
prop.table(tFdR, margin=2)


# 5)
spineplot(tFdR)




# Exercice 2

# 1)
# p-value : risque que rejete Ho -> si <5% alors on rejete Ho
res<-chisq.test(tFdR)
res
# il y a une donc une dependance entre Fd et Region
# d=6 : (nb de modaliter y -1) * (nb de modaliter x -1) ex : (3-1)*(4-1)
# x-squared : distance entre l'observation et l'independance 

# comment devrai etre la repartition des var si elles etaient indep
res$expected
spineplot(res$expected)

# la distance jusqu'a 95% pour 6 degre
qchisq(0.95, 6)

# Et notre distance du res=40, et 12<40 donc 40 bien trop loin

# Pas asser de personne dans le tab tFdR où chauque casse doit avoir 5 individue max


# 2)
x<-seq(0,25, by=0.1)
plot(x,dchisq(x,6),type='l')


# 3)
quant<-quantile(states$Diplome, probs=seq(0,1,by=1/3))
fd2 <- cut(states$Diplome, breaks = c(quant), labels = c("faible","moyen","fort"),include.lowest = TRUE, right = TRUE, dig.lab = 3, ordered_result = FALSE)
fd2
table(fd2)
tFdR2<-table(states$Region, fd2)
tFdR2
barplot(tFdR2)

# tj meme pb pr le test du Qui2 => faire que 2 classes : fable / fort


# 4)

recode<-function(X,k,m){
    q<-quantile(X, probs=seq(0,1,by=1/k))
    f<-cut(X, breaks = c(q), labels = m, include.lowest = TRUE, right = TRUE, dig.lab = 3, ordered_result = FALSE)
    return(f)
}

e<-recode(states$Diplome, 3, c("faible","moyen","fort"))
table(e)

recode(states$Diplome, 2, c("1","2"))


# Exercice 3

# 1)
head(states)
summary(states)

# recupere la plus grosse valeur, puis affiche la ligne
which.max(states$Pop)
states[5,]

# sommes des NA = not atributed
sum(is.na(states))


# 2)
statesBis<-states
# affecte pour la ligne 12 et la col 3 remplacer par NA
statesBis[12,2]<-NA
statesBis[12,]
sum(is.na(statesBis))

which(is.na(statesBis$Pop))


# 4)
par(mfrow=c(2,4))
ind=3:10
for(j in ind){
    hist(statesBis[,j], col=8, xlab="", cex.main=5, cex.axis=1.2, main=colnames(statesBis)[j])
}


# 5)
# exemple
c<-c(3,10,9,4)
# tri le tableau
sort(c)
# donne les indices dans l'ordre croissant
order(c)

# ordoner les indices du Revenu decroissant
ordreRevenu <- order(states$Revenu, decreasing= TRUE)
# afficher States selon l'ordre des Revenu
states[ordreRevenu,]


# 6)
par(mfrow=c(2,4))
ind=3:10
for(j in ind){
    boxplot(statesBis[,j], col=8, xlab="", cex.main=5, cex.axis=1.2, main=colnames(statesBis)[j])
}


# EX 4

# 1)
mr <- tapply(states$Diplome, states$Region, mean)
par(mfrow=c(1,1))
barplot(mr)

sdR <- tapply(states$Diplome, states$Region, sd)
par(mfrow=c(1,1))
barplot(sdR)

# 2)


# 3)


# 4)
par(mfrow=c(2,4))
ind=3:10
for(j in ind){
    boxplot(states[,j]~states$Region, main=colnames(states)[j])
}


# EX 5

# 1)
par(mfrow=c(1,1))
plot(states$Apb, states$Meurtre)
plot(~states$Pop + states$Apb + states$Meurtre)

# 3)
plot(Apb, Meurtre, xlab="analphabetisme", ylab="criminaliter", main="Nuage de point")

plot(Apb, Meurtre, type="n", xlab="analphabetisme", ylab="criminaliter", main="Nuage de point")
text(Apb, Meurtre, row.names(states), cex=0.5, col=as.numeric(Region) )
legend("bottomright", legend=levels(Region), fill=as.numeric(Region), cex=0.7)

#4)
#install.packages("ade4")
library(ade4)
s.class(data.frame(Apb, Meurtre), add.plot=TRUE, Region, col=1:4)

