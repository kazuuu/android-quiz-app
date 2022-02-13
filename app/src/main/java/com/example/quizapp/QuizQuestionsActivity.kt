package com.example.quizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener{
    var mUsername: String? = null

    var mQuestionsList: ArrayList<Question>? = null
    var mCurrentQuestionIndex: Int = 0
    var mSelectedOptionIndex: Int = 0
    var mIsAnswered: Boolean = false

    var mTotalCorrectAnswers: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        mUsername = intent.getStringExtra(Constants.USER_NAME)

        mQuestionsList = Constants.getQuestions()

        val progressBar: ProgressBar = findViewById(R.id.progressBar)
        progressBar.setMax(mQuestionsList!!.size)

        setQuestion()

        val tv_optionOne: TextView = findViewById(R.id.tv_optionOne)
        tv_optionOne.setOnClickListener(this)

        val tv_optionTwo: TextView = findViewById(R.id.tv_optionTwo)
        tv_optionTwo.setOnClickListener(this)

        val tv_optionThree: TextView = findViewById(R.id.tv_optionThree)
        tv_optionThree.setOnClickListener(this)

        val tv_optionFour: TextView = findViewById(R.id.tv_optionFour)
        tv_optionFour.setOnClickListener(this)

        val btn_submit: Button = findViewById(R.id.btn_submit)
        btn_submit.setOnClickListener(this)

    }

    private fun setQuestion() {
        val btn_submit: Button = findViewById(R.id.btn_submit)
        btn_submit.text = "SUBMIT"

        setDefaultOptionsView()

        mSelectedOptionIndex = 0
        mIsAnswered = false

        val progressBar: ProgressBar = findViewById(R.id.progressBar)
        progressBar.progress = mCurrentQuestionIndex + 1

        val tv_progressBar: TextView = findViewById(R.id.tv_progressBar)
        tv_progressBar.text = "${progressBar.progress}/${progressBar.max}"

        val iv_image: ImageView = findViewById(R.id.iv_image)
        iv_image.setImageResource(mQuestionsList!![mCurrentQuestionIndex].image)

        val tv_optionOne: TextView = findViewById(R.id.tv_optionOne)
        val tv_optionTwo: TextView = findViewById(R.id.tv_optionTwo)
        val tv_optionThree: TextView = findViewById(R.id.tv_optionThree)
        val tv_optionFour: TextView = findViewById(R.id.tv_optionFour)

        tv_optionOne.text = mQuestionsList!![mCurrentQuestionIndex].optionOne
        tv_optionTwo.text = mQuestionsList!![mCurrentQuestionIndex].optionTwo
        tv_optionThree.text = mQuestionsList!![mCurrentQuestionIndex].optionThree
        tv_optionFour.text = mQuestionsList!![mCurrentQuestionIndex].optionFour
    }

    private fun setDefaultOptionsView() {
        val options: ArrayList<TextView> = arrayListOf<TextView>()

        options.add(0,findViewById(R.id.tv_optionOne))
        options.add(1,findViewById(R.id.tv_optionTwo))
        options.add(2,findViewById(R.id.tv_optionThree))
        options.add(3,findViewById(R.id.tv_optionFour))

        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this, R.drawable.default_option_border_bg)
        }
    }

    fun selectOptionView(tv: TextView, selectedOptionIndex: Int) {
        if (mIsAnswered) {
//            Toast.makeText(this, "Already Answered, go to next", Toast.LENGTH_SHORT).show()
            return
        }

        setDefaultOptionsView()
        mSelectedOptionIndex = selectedOptionIndex

        tv.setTextColor(Color.parseColor("#7A8089"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(this, R.drawable.selected_option_border_bg)
    }

    override fun onClick(p0: View?) {
        when(p0?.id) {
            R.id.tv_optionOne -> {
                selectOptionView(findViewById(R.id.tv_optionOne), 1)
            }
            R.id.tv_optionTwo -> {
                selectOptionView(findViewById(R.id.tv_optionTwo), 2)
            }
            R.id.tv_optionThree -> {
                selectOptionView(findViewById(R.id.tv_optionThree), 3)
            }
            R.id.tv_optionFour -> {
                selectOptionView(findViewById(R.id.tv_optionFour), 4)
            }
            R.id.btn_submit -> {
                if (!mIsAnswered)
                    checkAnswer()
                else
                    if (mCurrentQuestionIndex == (mQuestionsList!!.size - 1)) {
                        goToFinish()
                    } else {
                        goToNextQuestion()
                    }

            }
        }
    }

    fun checkAnswer() {
        if (mSelectedOptionIndex == 0) {
            Toast.makeText(this, "Please select one option", Toast.LENGTH_SHORT).show()
            return
        }

        var tv_correct: TextView? = null
        var tv_inCorrect: TextView? = null
        val tv_optionOne: TextView = findViewById(R.id.tv_optionOne)

        mIsAnswered = true

        when(mQuestionsList!![mCurrentQuestionIndex].correctAnswer) {
            1 -> tv_correct = findViewById(R.id.tv_optionOne)
            2 -> tv_correct = findViewById(R.id.tv_optionTwo)
            3 -> tv_correct = findViewById(R.id.tv_optionThree)
            4 -> tv_correct = findViewById(R.id.tv_optionFour)
        }

        tv_correct!!.background = ContextCompat.getDrawable(this, R.drawable.correct_option_border_bg)

        if (mSelectedOptionIndex != mQuestionsList!![mCurrentQuestionIndex].correctAnswer) {
            when (mSelectedOptionIndex) {
                1 -> tv_inCorrect = findViewById(R.id.tv_optionOne)
                2 -> tv_inCorrect = findViewById(R.id.tv_optionTwo)
                3 -> tv_inCorrect = findViewById(R.id.tv_optionThree)
                4 -> tv_inCorrect = findViewById(R.id.tv_optionFour)
            }

            tv_inCorrect!!.background = ContextCompat.getDrawable(this, R.drawable.wrong_option_border_bg)
        } else
            mTotalCorrectAnswers = mTotalCorrectAnswers + 1

        val btn_submit: Button = findViewById(R.id.btn_submit)
        if (mCurrentQuestionIndex < (mQuestionsList!!.size - 1)) {
            btn_submit.text = "GO TO NEXT QUESTION"
        } else {
            btn_submit.text = "FINISH"
        }
    }

    private fun goToNextQuestion() {
        if (mCurrentQuestionIndex == (mQuestionsList!!.size - 1))
            return

        mCurrentQuestionIndex = mCurrentQuestionIndex + 1

        setQuestion()
    }

    private fun goToFinish() {
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra(Constants.USER_NAME, mUsername)
        intent.putExtra(Constants.TOTAL_QUESTIONS, mQuestionsList!!.size)
        intent.putExtra(Constants.TOTAL_CORRECT_ANSWERS, mTotalCorrectAnswers)
        startActivity(intent)
        finish()
    }

}