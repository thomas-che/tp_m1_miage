# Exercice 1

# import un fichier de donnée
states <- read.table("/home/thomas/Documents/tp_m1_miage/ada/tp1/StateFacts.txt", header = T, row.names = 1)

# afficher le donnée que l'on a 
ls()

# affiche les premiere ligne du fichier
head(states)

# cree une sauvarde du jeu de donnée
save(states, file = "/home/thomas/Documents/tp_m1_miage/ada/tp1/StateFacts.Rdata")
# Charge le jeu de donnée
load("/home/thomas/Documents/tp_m1_miage/ada/tp1/StateFacts.Rdata")

# 
dim(states)
# 
nrow(states)
# rendre visible les var
attach(states) # comme ca il connai les col de la dennée comme une var individuel
detach(states)

# nom des col
colnames(states)
# 
row.names(states)

# on affiche les val de la col Pop
states$Pop
states[[2]]
class(states$Pop)

# cree une nouvelle var
Reg = as.factor(states$Region)
Reg



# Exercice 2

# 1)
head(states)

# 2)
colnames(states)

# 3)


# 4)
states$Etat

# 5)
attach(states)
Etat

# 6)
save(states, file = "/home/thomas/Documents/tp_m1_miage/ada/tp1/StateFacts.Rdata")
load("/home/thomas/Documents/tp_m1_miage/ada/tp1/StateFacts.Rdata")

# 7)
summary(states)
mean(states$Pop)
var(states$Pop)
sd(states$Pop)
max(states$Pop)

# 8)
# Donne une moyenne sur les meutre par region
tapply(states$Meurtre,states$Region,mean)
tapply(states$Meurtre,states$Region,sd)
by(states[,-6],states$Region, colMeans)



# Exercice 3

# 1)
mean(states$Revenu)

# 2)
var(states$Revenu)

# 3)
states$Etat[states$Revenu > 5000]
table(states$Etat[states$Revenu > 5000])

# 4) 
colMeans(states[,-c(1,10)])

# 5)
attach(states)
detach(states)
# Cange le nom de la 4 dans la var states
colnames(states)[4.]="Apb"
attach(states)
head(states)

# 6)

table_apb = table(states$Apb)
chisq.test(table_apb)


# Exercice 4
save(states, file = "/home/thomas/Documents/tp_m1_miage/ada/tp1/StateFacts.Rdata")


# Exercice 5
load("/home/thomas/Documents/tp_m1_miage/ada/tp1/StateFacts.Rdata")
head(states)

# Exercice 6

# a)
states[1:10,]

# b)
states[-5:-0,]

# c)
states[1:5,c(1,6,10)]



