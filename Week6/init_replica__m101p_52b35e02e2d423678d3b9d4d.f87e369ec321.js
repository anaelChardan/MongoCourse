config = {
    _id: "replMongoCourse", members: [
        {_id: 0, host: "mongodb1:27017"},
        {_id: 1, host: "mongodb2:27017"},
        {_id: 2, host: "mongodb3:27017"}
    ]
};

rs.initiate(config);
rs.status();


config = {"_id" : "my-mongo-set", "members" : [ {"_id" : 0, "host" : "mongodb_1:27017"}, {"_id" : 1, "host" : "mongodb_2:27017"}, {"_id" : 2, "host" : "mongodb_3:27017"}]}



