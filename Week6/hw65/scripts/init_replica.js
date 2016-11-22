config = {
    _id: "m101",
    members: [
        {_id: 0, host: "mongodb_primary:27017"},
        {_id: 1, host: "mongodb_secondary_1:27018"},
        {_id: 2, host: "mongodb_secondary_2:27019"}]
}

rs.initiate(config);