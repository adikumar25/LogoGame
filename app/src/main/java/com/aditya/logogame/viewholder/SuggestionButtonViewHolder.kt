package com.aditya.logogame.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.aditya.logogame.BR
import com.aditya.logogame.databinding.ItemButtonBinding
import com.aditya.logogame.models.ButtonItem
import com.aditya.logogame.viewmodel.GameActivityViewModel

/**
 * ViewHolder for suggestion Buttons
 */
class SuggestionButtonViewHolder(private val dataBinding: ItemButtonBinding, private val viewModel: GameActivityViewModel, private val clickListener: OnClickListener): RecyclerView.ViewHolder(dataBinding.root) {

    fun setupButtons(button: ButtonItem){
        dataBinding.setVariable(BR.buttonItem, button)
        dataBinding.executePendingBindings()

        dataBinding.charButton.setOnClickListener {
            clickListener.itemClicked(adapterPosition)
            viewModel.updateAnswerButtons(button)
        }
    }

    /**
     * Click listener for communication between holder & adapter
     */
    interface OnClickListener {
        fun itemClicked(position: Int)
    }

}