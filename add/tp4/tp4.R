
jussac <- read.table("/home/thomas/Documents/tp_m1_miage/add/tp4/Jussac.txt", header = T, row.names = 1)
jussac

jussac <- jussac[,-1]
jussac

jussacA <- head(jussac,-1)
jussacA

jussacJ <- tail(jussac, 1)
jussacJ

actifs <- jussac[jussac$Type != 'J', ] # tout sauf Jussac
actifs
indsup <- jussac[jussac$Type == 'J', ] # Jussac

raceCl <- factor(actifs$Type, labels = c("Chien", "Loup"))
table(actifs$Typ, raceCl)

apply(actifs, 2, sd) # trop grose diff 23 et 0.4
acp <- dudi.pca(actifs[, 0:6], scale=FALSE, scannf=TRUE, nf=6)

Imen <- inertia.dudi(acp, col.inertia=F, row.inertia=T)
round(Imen$tot.inertia, 2)
