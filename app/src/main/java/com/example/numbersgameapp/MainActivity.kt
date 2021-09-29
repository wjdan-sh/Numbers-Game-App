package com.example.numbersgameapp

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var RC: RecyclerView
    private lateinit var ed: EditText
    private lateinit var btn: Button
    private lateinit var guess: ArrayList<String>
    private lateinit var tv: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        RC = findViewById(R.id.rv)
        guess = ArrayList()

        tv = findViewById(R.id.tv)
        ed = findViewById(R.id.ed)
        btn = findViewById(R.id.btn)

        RC.adapter = RVaddapter(guess)
        RC.layoutManager = LinearLayoutManager(this)

        var count = 3
        val num = Random.nextInt(0, 10)
        btn.setOnClickListener {
            val Uinput = ed.text.toString()
            if (Uinput.isNotEmpty()) {
                if (count > 0) {
                    if (Uinput.toInt() == num) {

                        guess.add("You win!")
                        customAlert()
                    } else {

                        count--
                        guess.add("You guessed $Uinput")
                        guess.add("You have $count guesses left")


                    }
                    if (count == 0) {

                        guess.add("You lose - The correct answer was $num")
                        guess.add("Game Over")
                        customAlert()
                    }
                }
                ed.text.clear()
                ed.clearFocus()
                RC.adapter?.notifyDataSetChanged()


            }
        }
    }

    private fun customAlert(){

        val dialogBuilder = AlertDialog.Builder(this)

        dialogBuilder.setMessage("You want to play again?")

            .setPositiveButton("yes", DialogInterface.OnClickListener {
                    dialog, id -> this.recreate()
            })

            .setNegativeButton("no", DialogInterface.OnClickListener {
                    dialog, id -> dialog.cancel()
            })

        val alert = dialogBuilder.create()

        alert.setTitle("game over")


        alert.show()
    }
}