# Td 3  : Mongo java


### Ex 0:

Init de la bdd
```bash
docker run -p 2718:27017 --name bddsiTd3 -d mongo
docker exec -it bddsiTd3 bash

docker run -it --link bddsiTd3:mongoTd3 --rm -v /tmp/td3:/data mongo bash
mongoimport --db cine --collection movies --host mongoTd3 --port 27017 --file /data/movies.json --jsonArray
mongoimport --db cine --collection artists --host mongoTd3 --port 27017 --file /data/artits.json --jsonArray
```

### Ex 1:
Cree un nouveau projet Maven
```java
public Long numberFilm() {
    MongoDatabase database = this.getConnection();
    MongoCollection<Document> collection = database.getCollection("movies");

    Long nbFilm = collection.count();
    return nbFilm;
}

public Long numberArtists() {
    MongoDatabase database = this.getConnection();
    MongoCollection<Document> collection = database.getCollection("artists");

    Long nbArtists = collection.count();
    return nbArtists;
}
```

### Ex 2:

Récupère la listes des film que l'on met dans une liste d'objet Film
```java
public MongoCursor<Document> getListFilm(){
    MongoDatabase database = this.getConnection();
    MongoCollection<Document> collection = database.getCollection("movies");

    MongoCursor<Document> cursor = collection.find().iterator();

    return cursor;
}

// Programme
MongoCursor cursor = mongoBdd.getListFilm();
List<Film> lesFilms = new ArrayList<>();
while (cursor.hasNext()) {

    Document document = (Document) cursor.next();
    Film newFilm = new Film((String) document.get("_id"), (String) document.get("title"), (Integer) document.get("year"), (String) document.get("genre"), (String) document.get("summary"), (String) document.get("country"));
    lesFilms.add(newFilm);

}
System.out.println(lesFilms);
```

### Ex 3:

```java

```



