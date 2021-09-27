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

create database musee;
use musee;
create table salle (id int, nom varchar(32));

```

&nbsp;
&nbsp;
### Ex 3:

Init du container et de la bdd

```bash
docker run -p 3307:3306 -e MYSQL_ALLOW_EMPTY_PASSWORD=yes mysql
docker exec -it 67783aceb96f /bin/bash
mysql

# cmd mysql
create database musee;
use musee;
create table salle (id int AUTO_INCREMENT, nom varchar(32), PRIMARY KEY (id) );
create table oeuvre (id int AUTO_INCREMENT, nom varchar(32), PRIMARY KEY (id) );
create table stockage (id_oeuvre int, id_salle int, date_deb date, date_fin date null );

alter table stockage add foreign key (id_salle) references salle(id);
alter table stockage add foreign key (id_oeuvre) references oeuvre(id);

insert into salle values (0 , "salle 2");
insert into oeuvre values (0, "oeuvre 2");
insert into stockage values (1, 1, "2021-09-27", null);

```

Dans le projet Maven on change la ligne de connexion avec les bon port de notre container `3307` et la base `musee`.
```java
Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3307/musee","root","");
```

&nbsp;
#### Question 1
```java
ResultSet mouv=stmt.executeQuery("select salle.nom, oeuvre.nom, date_deb, date_fin from stockage join salle on salle.id = id_salle join oeuvre on oeuvre.id = id_oeuvre;");
while(mouv.next())
    System.out.println(mouv.getString(1) +"  "+mouv.getString(2)+"  "+mouv.getString(3)+"  "+mouv.getString(4));
```

&nbsp;
#### Question 2
```java
stmt.execute("insert into oeuvre values (0, 'triceratops') ");
stmt.execute("insert into salle values (0, 'grand hall') ");

ResultSet id_oeuvre = stmt.executeQuery("SELECT id from oeuvre where nom='triceratops'");
int id_ouuvre_vrai = 0;
if(id_oeuvre.next()){
    id_ouuvre_vrai = id_oeuvre.getInt("id");
}
ResultSet id_salle = stmt.executeQuery("SELECT id from salle where nom='grand hall'");
int id_salle_vrai = 0;
if(id_salle.next()){
    id_salle_vrai = id_salle.getInt("id");
}

stmt.execute("insert into stockage values ("+id_ouuvre_vrai+", "+id_salle_vrai+", '2021-09-28', null) ");
ResultSet mouv2=stmt.executeQuery("select salle.nom, oeuvre.nom, date_deb, date_fin from stockage join salle on salle.id = id_salle join oeuvre on oeuvre.id = id_oeuvre;");
while(mouv2.next())
    System.out.println(mouv2.getString(1) +"  "+mouv2.getString(2)+"  "+mouv2.getString(3)+"  "+mouv2.getString(4));

```