config = {
    _id: "my-mongo-set",
    members: [
        {_id: 0, host: "mongodb_primary:27017", priority: 0, slaveDelay: 5},
        {_id: 1, host: "mongodb_secondary_1:27017"},
        {_id: 2, host: "mongodb_secondary_2:27017"}]
}

rs.initiate(config);