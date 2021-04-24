package com.example.key2iq.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.key2iq.QuestionActivity
import com.example.key2iq.databinding.SingleViewBinding
import com.example.key2iq.model.ModelHome

class HomeAdapter(context: Context) : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    private val context: Context

    init {
        this.context = context
    }

    private var data = emptyList<ModelHome>()

    fun getData(data: MutableList<ModelHome>) {
        this.data = data
        notifyDataSetChanged()
    }


    inner class HomeViewHolder(val binding: SingleViewBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding = SingleViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {

        with(holder) {
            with(data[position]) {
                binding.title.text = this.title
                binding.image.setImageDrawable(this.image)
                binding.desc.text = this.desc

                if (position == 0) {
                    binding.layout.setOnClickListener {
                        val intent = Intent(context, QuestionActivity::class.java)
                        context.startActivity(intent)
                    }
                }
            }
        }


    }

    override fun getItemCount(): Int {
        return data.size
    }
}