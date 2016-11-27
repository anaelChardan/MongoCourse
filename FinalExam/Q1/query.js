db = db.getSiblingDB("enron");

function get_results (result) {
    print(tojson(result));
}

db.messages.find(
    {"headers.From":"andrew.fastow@enron.com","headers.To":"jeff.skilling@enron.com"},
    {"headers.From":1, "headers.To":1}
).forEach(get_results);
