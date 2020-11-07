package com.example.application

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.application.Books.deleteBook
import kotlinx.android.synthetic.main.f_2_dialog.*
import kotlinx.android.synthetic.main.fragment_2.*
import kotlinx.android.synthetic.main.item.*

class Fragment2: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?)
            : View?{
        return inflater.inflate(R.layout.fragment_2, container, false)
    }
    private var adapter : BookAdapter? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = BookAdapter{
            deleteBook(it)
            update()
        }
        adapter?.submitList(Books.getBooks())
        rv.adapter = adapter
        rv.addItemDecoration(DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL))

        fab.setOnClickListener(){
            val builder = AlertDialog.Builder(requireContext())
            val inflater = requireActivity().layoutInflater
            builder.setTitle("Изменить")
            val dialogLayout = inflater.inflate(R.layout.f_2_dialog, null)
            val editText1  = dialogLayout.findViewById<EditText>(R.id.et_header)
            val editText2  = dialogLayout.findViewById<EditText>(R.id.et_description)
            val editText3  = dialogLayout.findViewById<EditText>(R.id.et_position)
            builder.setView(dialogLayout)
            builder.setPositiveButton("Добавить") { dialogInterface, i ->
                try{
                    var b = Book(editText1.text.toString(),editText2.text.toString())
                    Books.setBook(editText3.text.toString().toInt(),b)
                }
                catch (e : NumberFormatException){
                    var b = Book(editText1.text.toString(),editText2.text.toString())
                    Books.setBook(null,b)
                }
                adapter?.submitList(Books.getBooks())

            }
            builder.setNegativeButton("Отмена") { dialogInterface, i ->  }
            builder.show()
        }
    }

    fun update(){
        adapter?.submitList(Books.getBooks())
    }


}