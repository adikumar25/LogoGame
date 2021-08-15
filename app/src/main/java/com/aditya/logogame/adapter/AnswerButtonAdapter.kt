package com.aditya.logogame.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aditya.logogame.databinding.ItemButtonBinding
import com.aditya.logogame.models.ButtonItem
import com.aditya.logogame.viewholder.AnswerButtonViewHolder

/**
 * Answer buttons adapter
 */
class AnswerButtonAdapter: RecyclerView.Adapter<AnswerButtonViewHolder>() {

    private var answerButtonList = emptyList<ButtonItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnswerButtonViewHolder {
        val dataBinding = ItemButtonBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return AnswerButtonViewHolder(dataBinding)
    }

    override fun onBindViewHolder(holder: AnswerButtonViewHolder, position: Int) {
        holder.setupButtons(answerButtonList[position])
    }

    override fun getItemCount(): Int {
        return answerButtonList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(answerButtons: List<ButtonItem>) {
        answerButtonList = answerButtons
        notifyDataSetChanged()
    }
}