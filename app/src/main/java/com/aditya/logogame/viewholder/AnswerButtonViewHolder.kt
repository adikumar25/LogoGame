package com.aditya.logogame.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.aditya.logogame.BR
import com.aditya.logogame.databinding.ItemButtonBinding
import com.aditya.logogame.models.ButtonItem

/**
 * Viewholder for Answer buttons
 */
class AnswerButtonViewHolder(private val dataBinding: ItemButtonBinding): RecyclerView.ViewHolder(dataBinding.root) {

    fun setupButtons(button: ButtonItem){
        dataBinding.setVariable(BR.buttonItem, button)
        dataBinding.executePendingBindings()
    }
}