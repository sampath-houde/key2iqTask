package com.example.key2iq.modeldata

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import com.example.key2iq.R
import com.example.key2iq.model.ModelHome
import com.example.key2iq.model.QuestionModel

class ModelData(context: Context) {

    var listHome = mutableListOf<ModelHome>()
    private var title = mutableListOf<String>()
    val listQuestions = mutableListOf<QuestionModel>()
    private var questions = mutableListOf<String>()
    private var answers = mutableListOf<Int>()
    private var desc = mutableListOf<String>()
    private var image = mutableListOf<Drawable>()

    init {

        generateData(context)
        generateList()
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun generateData(context: Context) {
        title.add("Complete Now")
        title.add("Meet new rappers")
        title.add("Explore")
        title.add("Play & Learn")
        title.add("Learn Rap")
        title.add("Practice Rap")

        desc.add("Challenge for you")
        desc.add("Competition with other rappers")
        desc.add("See rappers with you")
        desc.add("Learn how to rap")
        desc.add("Rap with other players and win prizes")
        desc.add("How to rap with other players")

        image.add(context.getDrawable(R.drawable.ic_baseline_check_circle_24)!!)
        image.add(context.getDrawable(R.drawable.ic_baseline_feedback_24)!!)
        image.add(context.getDrawable(R.drawable.ic_baseline_home_24)!!)
        image.add(context.getDrawable(R.drawable.ic_baseline_person_pin_24)!!)
        image.add(context.getDrawable(R.drawable.ic_baseline_community)!!)
        image.add(context.getDrawable(R.drawable.ic_baseline_explore_24)!!)

        questions.add("1. Jorge runs faster than Drew. \n2. Drew run faster than Bernard.\n3. Jorge runs faster than Bernard.")
        questions.add("1. Katy is older than Drew. \n2. Drew is older than Bernard.\n3. Bernard is older than Katy.")
        questions.add("1. Ram is older than Kishan. \n2. Kishan is older than Raj.\n3. Raj is elder than Ram.")
        questions.add("1. Radha is older than Priya. \n2. Priya is older than Ritu.\n3. Ritu is elder than Ganga.")

        answers.add(0)
        answers.add(1)
        answers.add(0)
        answers.add(2)

    }

    private fun generateList() {
        for (data in 0..5) {
            val titleData = title[data]
            val imageData = image[data]
            val descData = desc[data]
            val model = ModelHome(titleData, imageData, descData)
            listHome.add(model)
        }

        for(q in 0..3) {
            val questionSingle = questions[q]
            val answerSingle = answers[q]
            val model = QuestionModel(questionSingle, answerSingle)
            listQuestions.add(model)
        }
    }
}