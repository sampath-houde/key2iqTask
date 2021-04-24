package com.example.key2iq.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.key2iq.QuestionActivity
import com.example.key2iq.fragments.QuestionFragment

class QuestionPageAdapter(val numOfQuestions: Int, fragment: QuestionActivity) : FragmentStateAdapter(fragment){
    override fun getItemCount(): Int {
        return numOfQuestions
    }

    override fun createFragment(position: Int): Fragment {
        return QuestionFragment.newInstance(position)
    }


}