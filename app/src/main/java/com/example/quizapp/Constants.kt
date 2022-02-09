package com.example.quizapp

object Constants {
    const val USER_NAME: String = "USER_NAME"
    const val TOTAL_QUESTIONS: String = "TOTAL_QUESTIONS"
    const val TOTAL_CORRECT_ANSWERS: String = "TOTAL_CORRECT_ANSWERS"

    fun getQuestions(): ArrayList<Question> {
        val questionsList = ArrayList<Question>()

        // 1
        val question1 = Question(
            1,
            "What country does this flag belong to?",
            R.drawable.ic_flag_of_argentina,
            "Argentina",
            "Australia",
            "Armenia",
            "Austria",
            1
        )
        questionsList.add(question1)

        // 2
        val question2 = Question(
            2, "What country does this flag belong to?",
            R.drawable.ic_flag_of_australia,
            "Angola", "Austria",
            "Australia", "Armenia", 3
        )

        questionsList.add(question2)

        // 3
        val question3 = Question(
            3, "What country does this flag belong to?",
            R.drawable.ic_flag_of_brazil,
            "Belarus", "Belize",
            "Brunei", "Brazil", 4
        )

        questionsList.add(question3)

        // 4
        val question4 = Question(
            4, "What country does this flag belong to?",
            R.drawable.ic_flag_of_belgium,
            "Bahamas", "Belgium",
            "Barbados", "Belize", 2
        )

        questionsList.add(question4)

        // 5
        val question5 = Question(
            5, "What country does this flag belong to?",
            R.drawable.ic_flag_of_fiji,
            "Gabon", "France",
            "Fiji", "Finland", 3
        )

        questionsList.add(question5)

        // 6
        val question6 = Question(
            6, "What country does this flag belong to?",
            R.drawable.ic_flag_of_germany,
            "Germany", "Georgia",
            "Greece", "none of these", 1
        )

        questionsList.add(question6)

        // 7
        val question7 = Question(
            7, "What country does this flag belong to?",
            R.drawable.ic_flag_of_denmark,
            "Dominica", "Egypt",
            "Denmark", "Ethiopia", 3
        )

        questionsList.add(question7)

        // 8
        val question8 = Question(
            8, "What country does this flag belong to?",
            R.drawable.ic_flag_of_india,
            "Ireland", "Iran",
            "Hungary", "India", 4
        )

        questionsList.add(question8)

        // 9
        val question9 = Question(
            9, "What country does this flag belong to?",
            R.drawable.ic_flag_of_new_zealand,
            "Australia", "New Zealand",
            "Tuvalu", "United States of America", 2
        )

        questionsList.add(question9)

        // 10
        val question10 = Question(
            10, "What country does this flag belong to?",
            R.drawable.ic_flag_of_kuwait,
            "Kuwait", "Jordan",
            "Sudan", "Palestine", 1
        )
        questionsList.add(question10)

        return questionsList
    }
}