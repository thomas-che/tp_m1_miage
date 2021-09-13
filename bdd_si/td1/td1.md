# Td 1 : BD et Ingénierie des SI


### Ex 1:

&nbsp;
#### Question 1
Se connecter au docker hub dans le repertoire `/var/lib/docker/`.
```bash
docker login
```

Puis récupérer l'image httpd qui correspond à un appache2
```bash
docker pull httpd:2.4
docker run httpd:2.4
```

&nbsp;
#### Question 2
Il y a deja un apache sur la machine en :8080

&nbsp;
#### Question 3
Récupère l'ip du container : IPAddress
```bash
docker inspect <id_container>
```

&nbsp;
#### Question 4
On affiche le container

&nbsp;
#### Question 5
Stop le container
```bash
docker stop <id_container>
```

&nbsp;
#### Question 6
Supprime le container
```bash
docker rm <id_container>
```

&nbsp;
#### Question 7
Lancer le container mapper avec un port
```bash
docker run -p 8080:80 httpd:2.4
```

&nbsp;
#### Question 8
OK

&nbsp;
#### Question 9
Stop et supprime le container
```bash
docker rm -f <id_container>
```

&nbsp;
#### Question 10
Lancer le container mapper avec un repertoire
```bash
docker run -p 8088:80 -v /home/thomas/Documents/tp_m1_miage/bdd_si/td1/:/usr/local/apache2/htdocs httpd:2.4
```


&nbsp;
&nbsp;
### Ex 2:

&nbsp;
#### Question 2
Container mysql sans mdp
```bash
docker run -p 33061:3306 -e MYSQL_ALLOW_EMPTY_PASSWORD=yes mysql
```

&nbsp;
#### Question 3
On rentre dans le container en prenant le contrôle avec bash
```bash
docker exec -it 67783aceb96f /bin/bash
```

&nbsp;
#### Question 4

```bash
# commande sql pour cree des tables...
```

