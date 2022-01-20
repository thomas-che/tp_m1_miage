## Enquête emploi 2001 INSEE
## sujet SPRINT 2021
###################################################
## Code de démarrage pour la Question 2
## ACP des variables quantitatives et qualitatives ordinales

## Sélection des colonnes 
rm(list = ls()) # vider le workspace (repart de zéro)
load("/home/thomas/Documents/tp_m1_miage/add/sprint/Emploi01.Rdata")
# Sélection des variables utiles pour la Question 2
# 10 num (ordinales) et 5 facteurs qual suppl
varQ2 <- c("AGCM", "SALRED", "SALFR", "DUHAB", "NP", "NBCHMEN", 
           "PIECES", "TU90", "ADFE", "NEGR", 
           "S", "FI", "DDIPL", "RG")
dataQ2 <- Emploi01[, varQ2] # sélection des variables

## recodage de FI cf Q1
FI2 <- dataQ2$FI; FI2[FI2 == ""] <- NA
FI2 <- factor(FI2, labels = c("actifs", "chômeur", "étudiant", "militaire", 
                              "retraité", "retiré", "ffoyer", "inactif"))
table(dataQ2$FI, FI2, useNA = "ifany") # vérifier le recodage 
dataQ2$FI <- FI2

summary(dataQ2) # nombreux NA dans SALRED...

### recodage des factors ordinaux en "numeric": 
colnum <- 1:10   # colonnes concernées
for (j in colnum) dataQ2[,j] <- as.numeric(as.character(dataQ2[,j]))

# suppression des NA: 
dataNA <- is.na(dataQ2)
NArow <- apply(dataNA, 1, sum)
sum(NArow == 0) # 4159 lignes (obs) complètes = sans NA
dataQ2 <- dataQ2[NArow == 0, ]
summary(dataQ2)
save(dataQ2, file="EmploiQ2.Rdata") # si besoin
##### A vous de continuer...

















