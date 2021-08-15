package com.aditya.logogame.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.aditya.logogame.adapter.AnswerButtonAdapter
import com.aditya.logogame.adapter.SuggestionButtonAdapter
import com.aditya.logogame.databinding.ActivityGameBinding
import com.aditya.logogame.models.ButtonItem
import com.aditya.logogame.viewmodel.GameActivityViewModel
import com.squareup.picasso.Picasso

class GameActivity : AppCompatActivity() {

    lateinit var dataBinding: ActivityGameBinding
    lateinit var viewModel: GameActivityViewModel
    lateinit var suggestionButtonAdapter: SuggestionButtonAdapter
    lateinit var answerButtonAdapter: AnswerButtonAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = ActivityGameBinding.inflate(layoutInflater).apply {
            lifecycleOwner = this@GameActivity
        }
        setContentView(dataBinding.root)

        viewModel = ViewModelProvider(this).get(GameActivityViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()
        setupObserver()
        viewModel.startGame(this)
    }

    /**
     * set up answer buttons adapter
     */
    private fun setupAnswerButtonsAdapter(list: List<ButtonItem>) {
        answerButtonAdapter = AnswerButtonAdapter()
        val answerLayoutManager = GridLayoutManager(this, list.size)
        dataBinding.answerRv.layoutManager = answerLayoutManager
        dataBinding.answerRv.adapter = answerButtonAdapter
    }

    /**
     * set up suggestion buttons adapter
     */
    private fun setupSuggestedButtonAdapter(list: List<ButtonItem>) {
        suggestionButtonAdapter = SuggestionButtonAdapter(viewModel)
        val suggestionLayoutManager = GridLayoutManager(this, list.size/2)
        dataBinding.suggestionRv.layoutManager = suggestionLayoutManager
        dataBinding.suggestionRv.adapter = suggestionButtonAdapter
    }

    /**
     * Sets observers on different items in viewmodel
     */
    private fun setupObserver() {
        viewModel.logoItem.observe(this, {
            viewModel.initLogoView(it)
            Picasso.get().isLoggingEnabled = true
            Picasso.get().load(it.imgUrl).into(dataBinding.logoImage)
        })

        viewModel.answerButtonList.observe(this, {
            setupAnswerButtonsAdapter(it)
            answerButtonAdapter.setList(it)
        })

        viewModel.logoIndex.observe(this, {
            viewModel.nextLogo(it)
        })

        viewModel.suggestedButtonList.observe(this, {
            setupSuggestedButtonAdapter(it)
            suggestionButtonAdapter.setList(it)
        })

        viewModel.isFinished.observe(this, {
            if (it){
                Toast.makeText(this, "Finished. Thanks", Toast.LENGTH_SHORT).show()
            }
        })
    }
}