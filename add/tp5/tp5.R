agriculture <- read.table("/home/thomas/Documents/tp_m1_miage/add/tp5/Agriculture.txt", header = T, row.names = 1)
agriculture

chisq.test(agriculture)

tag = as.table(as.matrix(agriculture))
prop.table(tag, margin = 1) # profil ligne = proba en ligne

acp <- dudi.coa(df=agriculture, scale=FALSE, nf= 4 )

scatter(afc,method=1,posieig="none");title("Method 1")
