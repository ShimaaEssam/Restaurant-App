package com.restaurantapp.Model

class FoodOrder :BaseOrder {
    var name: String? = null
    var price: String? = null

    constructor(){}
    constructor(name: String?, price: String?,userID:String?) {
        this.name = name
        this.price = price
        this.email=userID;
    }


}