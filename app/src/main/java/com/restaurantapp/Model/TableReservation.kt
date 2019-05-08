package com.restaurantapp.Model

class TableReservation  :BaseOrder {
    var name: String? = null
    var phoneNumber: String? = null
    var NumberofPeople: String? = null
    var date: String? = null
    var time: String? = null
    constructor(){}
    constructor(name: String?, phoneNumber: String?, NumberofPeople: String?, date: String?, time: String?,userID:String?) {
        this.name = name
        this.phoneNumber = phoneNumber
        this.NumberofPeople = NumberofPeople
        this.date = date
        this.time = time
        this.email=userID;

    }
}