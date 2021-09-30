package com.example.numbersgameapp

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    lateinit var etNumber:EditText
    lateinit var button:Button
    private lateinit var conlay:ConstraintLayout
    private var numbers= arrayListOf<String>()
    lateinit var myRv:RecyclerView
    private val ranumber= Random.nextInt(0,11)
    private var count=3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etNumber=findViewById(R.id.etNumber)
        button=findViewById(R.id.button)
        conlay=findViewById(R.id.conlay)

        myRv=findViewById(R.id.rvMain)
        myRv.adapter=RecyclerViewAdapter(numbers)
        myRv.layoutManager= LinearLayoutManager(this)

        button.setOnClickListener(){
            addMessage()
        }
    }
    private fun disableEntry(){
        button.isEnabled = false
        button.isClickable = false
        etNumber.isEnabled = false
        etNumber.isClickable = false
    }

    private fun showAlertDialog(title: String) {
        val Builder = AlertDialog.Builder(this)

        Builder.setMessage(title)
            .setCancelable(false)
            .setPositiveButton("Yes", DialogInterface.OnClickListener {
                    dialog, id -> this.recreate()
            })
            .setNegativeButton("No", DialogInterface.OnClickListener {
                    dialog, id -> dialog.cancel()
            })

        val alert = Builder.create()
        alert.setTitle("Game Over")
        alert.show()
    }

    private fun addMessage(){
        val msg = etNumber.text.toString()
        if(msg.isNotEmpty()){
            if(count>0){
                if(msg.toInt() == ranumber){
                    disableEntry()
                    showAlertDialog("You win!\n\nPlay again?")
                }else{
                    count--
                    numbers.add("You guessed $msg")
                    numbers.add("You have $count guesses left")
                }
                if(count==0){
                    disableEntry()
                    numbers.add("You lose - The correct answer was $ranumber")
                    numbers.add("Game Over")
                    showAlertDialog("You lose...\nThe correct answer was $ranumber.\n\nPlay again?")
                }
            }
            etNumber.text.clear()
            etNumber.clearFocus()
            myRv.adapter?.notifyDataSetChanged()
        }else{
            Snackbar.make(conlay, "Please enter a number", Snackbar.LENGTH_LONG).show()
        }
    }
}
