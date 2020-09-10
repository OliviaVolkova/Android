package com.example.application

open class Cat(var voice: String ,var name: String, var age: Int = 0): Voiceable {

    override fun voice(){
        print("Meow meow meow")
    }

}