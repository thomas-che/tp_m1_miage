# Td 2  : Requêtes sur une BD non normalisée


### Ex 1:

Dezip l'archive
```bash
unzip dblp.json.zip -d /tmp/bddTd2/data
```

Lance le docker avec un volume
```bash
docker run -p 27171:27017 --name bddsiTd2 -v /tmp/bddTd2/data:/data -d mongo
```

Se connect au docker
```bash
docker exec -it bddsiTd2 bash
```

Dans le container on lance l'import des data
```bash
mongoimport --db dblp --collection publis --file /data/dblp.json --jsonArray
```



### Ex 2:

&nbsp;
#### 1)
```bash
use dblp
db.publis.find({"type":"Book"})
```

&nbsp;
#### 2)
```bash
db.publis.find({"year":{$gte : 2011}})
```

&nbsp;
#### 3)
```bash
db.publis.find({"year":{$gt : 2014}})
```

&nbsp;
#### 4)
```bash
db.publis.find({"publisher":{$exists : true}})
```

&nbsp;
#### 5)
```bash
db.publis.find({"authors":"Jeffrey D. Ullman"})
db.publis.find({"authors" : {$in:["Jeffrey D. Ullman"]}})
```

&nbsp;
#### 6)
```bash
db.publis.find({"authors.0" : "Jeffrey D. Ullman"},{"title":1})
```

&nbsp;
#### 7)
```bash
db.publis.find({"authors" : ["Jeffrey D. Ullman"]})
```

&nbsp;
#### 7)
```bash
db.publis.find({"authors" : ["Jeffrey D. Ullman"]},{title:1, publisher:1})
```

&nbsp;
#### 8)
```bash
db.publis.find({title : {$regex :"database"}},{title:1})
```

&nbsp;
#### 9)
```bash
db.publis.distinct("publisher")
```

&nbsp;
#### 10)
```bash
db.publis.distinct("authors")
```

&nbsp;
#### 11)
```bash
db.publis.aggregate([{$match : {title : {$regex :"database", $options: "i"}, booktitle : {$exists : true}, "pages.start" : {$exists : true}}}, {$sort : {booktitle:1, "pages.start":1}}])
```

&nbsp;
#### 12) sûrement faux
```bash
db.publis.find({title : {$regex :"database"}, booktitle : {$exists : true}, "pages.start" : {$exists : true}},{title:1, booktitle:1, pages:1})
```

&nbsp;
#### 13)
```bash
db.publis.count({title : {$regex :"database"}, booktitle : {$exists : true}, "pages.start" : {$exists : true}})
db.publis.find({title : {$regex :"database"}, booktitle : {$exists : true}, "pages.start" : {$exists : true}}).count()
db.publis.find({title : {$regex :"database"}, booktitle : {$exists : true}, "pages.start" : {$exists : true}}).length()
```

&nbsp;
#### 14)
```bash

```

&nbsp;
#### 15)
```bash

```