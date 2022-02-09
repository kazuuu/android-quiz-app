package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val tv_username = findViewById<TextView>(R.id.tv_username)
        tv_username.text = intent.getStringExtra(Constants.USER_NAME)

        val total_score: Int = intent.getIntExtra(Constants.TOTAL_CORRECT_ANSWERS, 0)
        val total_questions: Int = intent.getIntExtra(Constants.TOTAL_QUESTIONS, 0)
        val tv_score = findViewById<TextView>(R.id.tv_score)
        tv_score.text = "${total_score.toString()}/${total_questions.toString()}"

        val btn_finish: Button = findViewById(R.id.btn_finish)
        btn_finish.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}