package com.example.lab4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    var comp_num = 0;
    lateinit var guessBtn : Button;
    lateinit var initialText : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val attempts = findViewById<TextView>(R.id.attempts_left_txt)
        comp_num = GuessNum.rndCompNum();
        guessBtn = findViewById(R.id.guess_btn);
        val clckLstnr = View.OnClickListener { view ->
            var inputNumber = findViewById<EditText>(R.id.num_user_txt).text.toString().toIntOrNull();

            if (attempts.text != "0") {

                if (comp_num == inputNumber) {
                    val t = Toast.makeText(applicationContext, R.string.wishYou, Toast.LENGTH_LONG)
                    t.show()
                    val messageShow = findViewById<TextView>(R.id.msg_show_txt)
                    messageShow.setText(R.string.guessed)
                }
                else if (inputNumber != null) {
                    attempts.setText((attempts.text.toString().toInt() - 1).toString())
                    val hint = findViewById<TextView>(R.id.hint_show_txt)
                    if (inputNumber > comp_num) {
                        hint.setText(R.string.less)
                    }
                    else {
                        hint.setText(R.string.more)
                    }
                }
            }
            else {
                findViewById<TextView>(R.id.guess_btn).setText(R.string.end)
            }
        }
        guessBtn.setOnClickListener(clckLstnr);
    }

    fun restart(view: View) {
        val attempts = findViewById<TextView>(R.id.attempts_left_txt)
        findViewById<TextView>(R.id.guess_btn).setText(R.string.guess_str)
        findViewById<TextView>(R.id.msg_show_txt).setText(R.string.msg_show_str)
        findViewById<TextView>(R.id.hint_show_txt).setText(R.string.hint_show_str)
        attempts.setText(R.string.attempts_left_str);
        comp_num = GuessNum.rndCompNum();
    }
}