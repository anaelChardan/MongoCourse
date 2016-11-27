db = db.getSiblingDB("enron");

function get_results (result) {
    print(tojson(result));
}

db.messages.find(
    {"headers.Message-ID" : "<8147308.1075851042335.JavaMail.evans@thyme>"},
    {'headers.Message-ID':1, 'headers.To':1}
).forEach(get_results);

db.messages.update(
    {"headers.Message-ID" : "<8147308.1075851042335.JavaMail.evans@thyme>"},
    {"$push" : {"headers.To" : "mrpotatohead@mongodb.com"}}
);

db.messages.find(
    {"headers.Message-ID" : "<8147308.1075851042335.JavaMail.evans@thyme>"},
    {'headers.Message-ID':1, 'headers.To':1}
).forEach(get_results);

//Answer : vOnRg05kwcqyEFSve96R