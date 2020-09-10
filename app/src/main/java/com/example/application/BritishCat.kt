package com.example.application

class BritishCat(voice: String, name: String, age: Int):Cat(voice,name, age) {
    override fun voice(){
        print("Do you want some tea?")
    }
}