Hi Clement and Dimitri, this README is for you!

mongoimport --drop -d blog -c posts posts.json

mongo


use blog

db.posts.createIndex({date:-1})

db.posts.createIndex({permalink:1})

db.posts.createIndex({tags:1})