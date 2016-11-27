// After doing this
// mongoimport --db final_exam_q7 --collection albums --file albums.json
// mongoimport --db final_exam_q7 --collection images --file images.json

db = db.getSiblingDB("final_exam_q7");

function get_results (result) {
    print(tojson(result));
}

db.images.find({"tags":"sunrises"}).count();

var notOrphans = [];

db.albums.aggregate([{$unwind: "$images"}, {$group: {_id: "$images"}}]).forEach(
    function (item)
    {
        notOrphans.push(item._id)
    }
);

print(db.images.find({"tags":"sunrises"}).count())

db.images.find({"_id":{$nin:notOrphans}}).forEach(item => db.images.remove({"_id":item._id}))

print(db.images.find({"tags":"sunrises"}).count())