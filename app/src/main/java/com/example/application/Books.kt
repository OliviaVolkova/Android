package com.example.application

import java.text.FieldPosition
import java.util.*

object Books {
    private var list  = LinkedList<Book>()

    init {
        var b1 = Book("Martin Eden","Jack London")
        var b2 = Book("The necklace","Guy de Maupassant ")
        var b3 = Book("The 13th tale","Diane Setterfield")
        var b4 = Book("Lie to me","Paul Ekman")
        var b5 = Book("The live","Guy de Maupassant")
        var b6 = Book("Good girls go to heaven","Ute Erhart")
        list.add( b1)
        list.add( b2)
        list.add( b3)
        list.add( b4)
        list.add( b5)
        list.add( b6)
    }

    fun getBooks(): List<Book>{
        return ArrayList<Book>(list)
    }
    fun getBook(position: Int): Book? {
        return list.get(position)
    }
    fun setBook(position: Int?, book: Book){
        if(position == null){
            list.addLast(book)
        }
        else{
            list.add(position-1, book);
        }
    }
    fun deleteBook(position: Int){
        list.removeAt(position)
    }
}