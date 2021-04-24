package com.example.key2iq

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.viewpager2.widget.ViewPager2
import com.example.key2iq.adapter.QuestionPageAdapter
import com.example.key2iq.databinding.ActivityQuestionBinding
import com.example.key2iq.fragments.QuestionFragment
import com.example.key2iq.model.QuestionModel
import com.example.key2iq.modeldata.ModelData

class QuestionActivity : AppCompatActivity(), QuestionFragment.UpdateViews {

    private lateinit var progressIndicator: ProgressBar
    private lateinit var refreshButton: ImageView
    private lateinit var viewPager: ViewPager2
    private lateinit var backButton: ImageView
    private lateinit var binding: ActivityQuestionBinding


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initalizeViews()
        backButton.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onStart() {
        super.onStart()
        val data = ModelData(this).listQuestions
        viewPager.adapter = QuestionPageAdapter(data.size, this)

        setProgressIndicator(data)

    }

    private fun setProgressIndicator(data: MutableList<QuestionModel>) {
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                for (q in 0..data.size) {
                    if (q == position) {
                        Log.d("pager", position.toString() + data.size.toString())
                        val progressValue =
                            (((position + 1).toFloat() / (data.size).toFloat()) * 100).toInt()
                        Log.d("pager", progressValue.toString())
                        progressIndicator.progress = progressValue
                    }
                }
            }
        })
    }

    private fun initalizeViews() {
        viewPager = binding.viewPager
        refreshButton = binding.refreshButton
        progressIndicator = binding.progress
        backButton = binding.backButton
    }

    override fun onClickListened(itemNumber: Int) {
        viewPager.setCurrentItem(itemNumber + 1)
    }

    override fun onViewUpdate() {
        refreshButton.visibility = View.VISIBLE
    }
}