package com.example.key2iq.fragments

import android.content.Context
import android.content.res.ColorStateList
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.key2iq.QuestionActivity
import com.example.key2iq.R
import com.example.key2iq.databinding.FragmentQuestionBinding
import com.example.key2iq.model.QuestionModel
import com.example.key2iq.modeldata.ModelData
import com.google.android.material.radiobutton.MaterialRadioButton

class QuestionFragment : Fragment() {

    private var itemNumber: Int? = 0
    private lateinit var rb_1: MaterialRadioButton
    private lateinit var rb_2: MaterialRadioButton
    private lateinit var rb_3: MaterialRadioButton
    private lateinit var submitButton: Button
    private lateinit var radioGroup: RadioGroup
    private lateinit var questionTextView: TextView
    private lateinit var binding: FragmentQuestionBinding
    private lateinit var img_1: ImageView
    private lateinit var img_2: ImageView
    private lateinit var img_3: ImageView
    private lateinit var progressBanner: LinearLayout
    private lateinit var correctWrongBanner: LinearLayout
    private lateinit var bannerImage: ImageView
    private lateinit var bannerText: TextView
    private lateinit var continueButton: Button
    private lateinit var buttonClickListener: UpdateViews

    interface UpdateViews {
        fun onClickListened(itemNumber: Int)

        fun onViewUpdate()
    }


    companion object {
        val KEY_ITEM_NUMBER = "item"
        fun newInstance(position: Int): QuestionFragment {
            val bundle = Bundle()
            bundle.putInt(KEY_ITEM_NUMBER, position)

            val fragment = QuestionFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        buttonClickListener = context as UpdateViews
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        itemNumber = arguments?.getInt(KEY_ITEM_NUMBER)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_question, container, false)

        binding = FragmentQuestionBinding.bind(view)

        rb_1 = binding.rb1
        rb_2 = binding.rb2
        rb_3 = binding.rb3

        img_1 = binding.img1
        img_2 = binding.img2
        img_3 = binding.img3

        progressBanner = binding.progressBanner
        correctWrongBanner = binding.correctWrongBanner
        bannerImage = binding.bannerImage
        bannerText = binding.bannerText


        continueButton = binding.continueButton
        submitButton = binding.submitButton
        radioGroup = binding.radioGroup
        questionTextView = binding.question

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val data = ModelData(requireContext()).listQuestions
        questionTextView.setText(data[itemNumber!!].question)

        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            submitButton.isEnabled = true
            val rbSelected = view.findViewById(checkedId) as RadioButton
            val selectedAnswer = group.indexOfChild(rbSelected)

            submitButton.setOnClickListener {
                checkAnswer(data, selectedAnswer)
                submitButton.visibility = View.GONE
                continueButton.visibility = View.VISIBLE
                rb_1.isEnabled = false
                rb_2.isEnabled = false
                rb_3.isEnabled = false

                continueButton.setOnClickListener {
                    buttonClickListener.onClickListened(itemNumber!!)
                    buttonClickListener.onViewUpdate()
                }
            }
        }

    }

    private fun checkAnswer(data: MutableList<QuestionModel>, selectedId: Int) {
        progressBanner.visibility = View.VISIBLE
        if (selectedId == data[itemNumber!!].answer) {
            setCorrectAnsViewToRadioBtn(selectedId)
            setCorrectAnsBanners()
        } else {
            setWrongAnsViewToRadioBtn(selectedId, data[itemNumber!!].answer)
            setWrongAnsBanners()
        }
    }

    private fun setWrongAnsBanners() {
        correctWrongBanner.backgroundTintList =
            ColorStateList.valueOf(resources.getColor(R.color.errorRed))
        bannerImage.setImageDrawable(resources.getDrawable(R.drawable.wrong))
        bannerText.setText("Wrong!")
        correctWrongBanner.visibility = View.VISIBLE
    }

    private fun setCorrectAnsBanners() {
        correctWrongBanner.backgroundTintList =
            ColorStateList.valueOf(resources.getColor(R.color.correctgreen))
        bannerImage.setImageDrawable(resources.getDrawable(R.drawable.correct))
        bannerText.setText("Correct!")
        correctWrongBanner.visibility = View.VISIBLE
    }

    private fun setWrongAnsViewToRadioBtn(selectedId: Int, answer: Int) {

        when (selectedId) {
            0 -> {
                img_1.setImageDrawable(resources.getDrawable(R.drawable.wrong))
                img_1.visibility = View.VISIBLE

            }

            1 -> {
                img_2.setImageDrawable(resources.getDrawable(R.drawable.wrong))
                img_2.visibility = View.VISIBLE
            }

            2 -> {
                img_3.setImageDrawable(resources.getDrawable(R.drawable.wrong))
                img_3.visibility = View.VISIBLE
            }
        }

        setCorrectAnsViewToRadioBtn(answer)
    }

    private fun setCorrectAnsViewToRadioBtn(selectedId: Int) {


        when (selectedId) {
            0 -> {
                img_1.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_check_circle_24))
                img_1.imageTintList =
                    ColorStateList.valueOf(resources.getColor(R.color.correctgreen))
                img_1.visibility = View.VISIBLE

            }

            1 -> {
                img_2.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_check_circle_24))
                img_2.imageTintList =
                    ColorStateList.valueOf(resources.getColor(R.color.correctgreen))
                img_2.visibility = View.VISIBLE
            }

            2 -> {
                img_3.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_check_circle_24))
                img_3.imageTintList =
                    ColorStateList.valueOf(resources.getColor(R.color.correctgreen))
                img_3.visibility = View.VISIBLE
            }
        }
    }

}
