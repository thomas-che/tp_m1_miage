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

Refractor le tout le PoJo
```java
// connexion
private final static Integer PORT = 2718; // changer normalement 27017
private final static String HOST = "localhost";
private final static String DATABASE = "cine";
private final MongoDatabase mongoDatabase;

public DaoMongo() {
    CodecProvider pojoCodecProvider = PojoCodecProvider.builder()
            .register("objets")
            .build();

    CodecRegistry pojoCodecRegistry =
            fromRegistries(MongoClient.getDefaultCodecRegistry(),
                    fromProviders(pojoCodecProvider));

    MongoClient mongoClient = new MongoClient(HOST, PORT);

    this.mongoDatabase = mongoClient.getDatabase(DATABASE).withCodecRegistry(pojoCodecRegistry);
}
```

### q1
```java
public Long numberFilm() {
    MongoCollection<Film> collection = mongoDatabase.getCollection("movies", Film.class);
    return collection.countDocuments();
}
```

### q2
```java
public ArrayList<Film> getFilm() {
    MongoCollection<Film> collection = mongoDatabase.getCollection("movies", Film.class);
    MongoCursor<Film> cursor = collection.find().iterator();

    ArrayList<Film> lesFilms = new ArrayList<>();
    while (cursor.hasNext()) {
        lesFilms.add(cursor.next());
    }

    return lesFilms;
}
```

### q3
```java
public ArrayList<Film> getFilmSF() {
    MongoCollection<Film> collection = mongoDatabase.getCollection("movies", Film.class);
    MongoCursor<Film> cursor = collection.find(eq("genre","Science-fiction")).iterator();

    ArrayList<Film> lesFilms = new ArrayList<>();
    while (cursor.hasNext()) {
        lesFilms.add(cursor.next());
    }

    return lesFilms;
}
```

### q4
```java
public ArrayList<String> getFilmGenre() {
    MongoCollection<Film> collection = mongoDatabase.getCollection("movies", Film.class);
    MongoCursor<String> cursor = collection.distinct("genre", String.class).iterator();

    ArrayList<String> lesGenre = new ArrayList<>();
    while (cursor.hasNext()) {
        lesGenre.add(cursor.next());
    }

    return lesGenre;
}
```

### q5
```java
public Artist getArtist(String prenom, String nom){
    MongoCollection<Artist> collection = mongoDatabase.getCollection("artists", Artist.class);
    Artist artist = collection.find(and(eq("last_name",nom), eq("first_name",prenom))).first();

    return artist;
}

public ArrayList<Film> getFilmAct(String prenom, String nom) {

    Artist artist = this.getArtist(prenom, nom);

    MongoCollection<Film> collection = mongoDatabase.getCollection("movies", Film.class);
    MongoCursor<Film> cursor = collection.find(eq("director._id", artist.getArtistId())).iterator();

    ArrayList<Film> lesFilms = new ArrayList<>();
    while (cursor.hasNext()) {
        lesFilms.add(cursor.next());
    }

    return lesFilms;
}
```

### q6
```java
public Artist getArtistId(String id){
    MongoCollection<Artist> collection = mongoDatabase.getCollection("artists", Artist.class);
    Artist artist = collection.find(eq("_id",id)).first();

    return artist;
}

public Artist getArtistRoleFilm(String filmName, String role){
    MongoCollection<Film> collection = mongoDatabase.getCollection("movies", Film.class);
    Film film = collection.find(eq("title", filmName)).first();

    for(Actor actor : film.getLesActors()){
        System.out.println("actor : "+actor.getActorId() + " ; role : "+actor.getRole());
        if(actor.getRole().equals(role)){
            System.out.println("ici");
            return this.getArtistId(actor.getActorId());
        }
    }
    return null;
}
```