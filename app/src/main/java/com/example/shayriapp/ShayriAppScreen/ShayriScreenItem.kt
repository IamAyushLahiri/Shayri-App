package com.example.shayriapp.ShayriAppScreen

sealed class ShayriScreenItem (val route : String){

    object HomeScreen : ShayriScreenItem("home")

    object catagoryScreen : ShayriScreenItem("Catagory")

    object ShayriListScreen : ShayriScreenItem("List_Screen")

    object FinalScreen : ShayriScreenItem("finalScreenView")
}