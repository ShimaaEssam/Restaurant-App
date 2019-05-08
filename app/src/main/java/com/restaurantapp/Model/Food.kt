package com.restaurantapp.Model

class Food :BaseOrder {
    var Image: String? = null
    var Name: String? = null
    var Price: String? = null
    var nameArabic: String? = null
    var MenuId:String?=null
    constructor(){}
    constructor(Image: String?, Name: String?, Price: String?, nameArabic: String?, MenuId: String?) {
        this.Image = Image
        this.Name = Name
        this.Price = Price
        this.nameArabic = nameArabic
        this.MenuId = MenuId
    }

}