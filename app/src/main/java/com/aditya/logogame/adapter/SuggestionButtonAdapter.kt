package com.aditya.logogame.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aditya.logogame.databinding.ItemButtonBinding
import com.aditya.logogame.models.ButtonItem
import com.aditya.logogame.viewholder.SuggestionButtonViewHolder
import com.aditya.logogame.viewmodel.GameActivityViewModel

/**
 * Suggestion Buttons adapter
 */
class SuggestionButtonAdapter(private val viewModel: GameActivityViewModel): RecyclerView.Adapter<SuggestionButtonViewHolder>(), SuggestionButtonViewHolder.OnClickListener {

    private var suggestionButtonList = emptyList<ButtonItem>().toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuggestionButtonViewHolder {
        val dataBinding = ItemButtonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SuggestionButtonViewHolder(dataBinding, viewModel, this)
    }

    override fun onBindViewHolder(holder: SuggestionButtonViewHolder, position: Int) {
        holder.setupButtons(suggestionButtonList[position])
    }

    override fun getItemCount(): Int {
        return suggestionButtonList.size
    }

    private fun disableItem(position: Int) {
        suggestionButtonList[position].isEnabled = false
        notifyItemChanged(position)
    }

    override fun itemClicked(position: Int) {
        disableItem(position)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(suggestionButtons: List<ButtonItem>) {
        suggestionButtonList = suggestionButtons.toMutableList()
        notifyDataSetChanged()
    }
}