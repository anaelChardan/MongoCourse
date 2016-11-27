db = db.getSiblingDB("enron");

function get_results (result) {
    print(tojson(result));
}

db.messages.aggregate(
    {"$unwind":"$headers.To"},
    {
        "$group" : {
            "_id" : {
                "_id" : "$_id",
                "from" : "$headers.From"
            },
            "to" : {"$addToSet" : "$headers.To"}
        }
    },
    {"$unwind" : "$to"},
    {
        "$group" : {
            "_id" : {
                "from" : "$_id.from",
                "to" : "$to"
            },
            "count_msg" : {
                "$sum" : 1
            }
        }
    },
    { "$sort" : {"count_msg" : -1} },
    { "$limit" : 5 }
).forEach(get_results);