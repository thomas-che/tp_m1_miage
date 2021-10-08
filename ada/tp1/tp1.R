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
head(states, 10)

# b)
tail(states, 5)


# c)
states[1:5,c(1,6,10)]
states[1:5,c("Etat","Meurtre","Region")]


# d)
intersect(states$Etat[states$Revenu > 4500], states$Etat[states$Region == "South"])

# cmd Thb mais ne retourne pas chez moi...
states[states["Etat"]=="South" & states["Revenu"]>4500,]


# Exercice 7

# a)

# cree var avec les revenus en €
RevenuE <- states$Revenu * 0.8

# copie du tab states et ajoute une col avec la col revenuE
statesE <- states
statesE$RevenuE <- RevenuE
statesE

# b)
rg <- factor(states$Region,
       levels = c("North_Central", "Northeast", "South", "West"),
       labels = c("NC", "NE", "S", "W")
)
rg

# c)
fd <- cut(states$Diplome, breaks = c(min(states$Diplome),47,57,max(states$Diplome)), labels = c("faible","moyen","fort"),
    include.lowest = TRUE, right = TRUE, dig.lab = 3, ordered_result = FALSE)
fd
table(fd)


# d)

tab <- data.frame(RevenuE,states$Meurtre,rg,fd)
tab


# Execice 8

# a)
tab[tab["rg"]=="S" & tab["fd"]=="moyen",]

# b)
tab[tab["rg"]=="NC" & tab["RevenuE"]>4000,]






