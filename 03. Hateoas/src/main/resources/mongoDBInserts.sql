use new_project

db.appointments.insertMany([
    {
        "end_date_time": "2020-11-01 15:30:00.000000",
        "message": null,
        "start_date_time": "2020-11-01 15:00:00.000000",
        "canceled": null
    },
    {
        "end_date_time": "2020-11-02 15:30:00.000000",
        "message": null,
        "start_date_time": "2020-11-02 15:00:00.000000",
        "canceled": null
    }
]);

db.doctors.insertMany([
    {
        "first_name": "doctor1FirstName",
        "last_name": "doctor1LastName",
        "office": "1a",
        "specialty": "DENTIST",
    },
    {
        "first_name": "doctor2FirstName",
        "last_name": "doctor2LastName",
        "office": "2b",
        "specialty": "UROLOGIST",
    },
    {
        "first_name": "1",
        "last_name": "1",
        "office": null,
        "specialty": "DERMATOLOGIST",
    }
]);

db.hospitals.insertMany([
    {
        "address": "address",
        "name": "hospital1"
    }
]);

db.medical_cards.insertMany([
    {
        "info": "info",
        "status": "CONFIRMED",
    },
    {
        "id": 2,
        "info": "info",
        "status": "NOT_CONFIRMED",
    }
]);

db.users.insertMany([
    {
        "created_at": "2020-10-28 10:38:19.288380",
        "first_name": "user1FirstName",
        "last_name": "user1LastName",
    },
    {
        "created_at": "2020-10-28 10:38:19.367186",
        "first_name": "user2FirstName",
        "last_name": "user2LastName",
    }
]);


db.users.update({_id: ObjectId(''5fb5c04f229591695ec5bb64'')}, {
    $set: {
        medical_card: ObjectId(''5fb5c04f229591695ec5bb62'')
    }
});

db.medical_cards.update({_id: ObjectId(''5fb5c04f229591695ec5bb62'')}, {
    $set: {user: ObjectId(''5fb5c04f229591695ec5bb64'')}
});

db.doctors.update({_id: ObjectId(''5fb5c04e229591695ec5bb5e'')}, {$set: {hospital: ObjectId(''5fb5c04e229591695ec5bb61'')}});
db.doctors.update({_id: ObjectId(''5fb5c04e229591695ec5bb5f'')}, {$set: {hospital: ObjectId(''5fb5c04e229591695ec5bb61'')}});
db.doctors.update({_id: ObjectId(''5fb5c04e229591695ec5bb60'')}, {$set: {hospital: ObjectId(''5fb5c04e229591695ec5bb61'')}});

db.appointments.update({_id: ObjectId(''5fb5c04e229591695ec5bb5c'')} , {$set : {doctor: ObjectId(''5fb5c04e229591695ec5bb5e''), user: ObjectId(''5fb5c04f229591695ec5bb64'')}})
db.appointments.update({_id: ObjectId(''5fb5c04e229591695ec5bb5d'')} , {$set : {doctor: ObjectId(''5fb5c04e229591695ec5bb5e''), user: ObjectId(''5fb5c04f229591695ec5bb64'')}})
