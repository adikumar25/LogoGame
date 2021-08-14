package com.aditya.logogame.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.aditya.logogame.R
import com.aditya.logogame.databinding.ActivityGameBinding
import com.aditya.logogame.viewmodel.GameActivityViewModel
import com.squareup.picasso.Picasso

class GameActivity : AppCompatActivity() {

    lateinit var dataBinding: ActivityGameBinding
    lateinit var viewModel: GameActivityViewModel
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
        setOnClickListeners()
        viewModel.startGame(this)
    }

    /**
     * Sets onClickListeners for all buttons
     */
    private fun setOnClickListeners() {
        dataBinding.btn1.setOnClickListener {
            var input = dataBinding.input.text
            input = input.toString().plus(dataBinding.btn1.text)
            dataBinding.input.text = input
            dataBinding.btn1.isEnabled = false
        }
        dataBinding.btn2.setOnClickListener {
            var input = dataBinding.input.text
            input = input.toString().plus(dataBinding.btn2.text)
            dataBinding.input.text = input
            dataBinding.btn2.isEnabled = false
        }
        dataBinding.btn3.setOnClickListener {
            var input = dataBinding.input.text
            input = input.toString().plus(dataBinding.btn3.text)
            dataBinding.input.text = input
            dataBinding.btn3.isEnabled = false
        }
        dataBinding.btn4.setOnClickListener {
            var input = dataBinding.input.text
            input = input.toString().plus(dataBinding.btn4.text)
            dataBinding.input.text = input
            dataBinding.btn4.isEnabled = false
        }
        dataBinding.btn5.setOnClickListener {
            var input = dataBinding.input.text
            input = input.toString().plus(dataBinding.btn5.text)
            dataBinding.input.text = input
            dataBinding.btn5.isEnabled = false
        }
        dataBinding.btn6.setOnClickListener {
            var input = dataBinding.input.text
            input = input.toString().plus(dataBinding.btn6.text)
            dataBinding.input.text = input
            dataBinding.btn6.isEnabled = false
        }
        dataBinding.btn7.setOnClickListener {
            var input = dataBinding.input.text
            input = input.toString().plus(dataBinding.btn7.text)
            dataBinding.input.text = input
            dataBinding.btn7.isEnabled = false
        }
        dataBinding.btn8.setOnClickListener {
            var input = dataBinding.input.text
            input = input.toString().plus(dataBinding.btn8.text)
            dataBinding.input.text = input
            dataBinding.btn8.isEnabled = false
        }
        dataBinding.btn9.setOnClickListener {
            var input = dataBinding.input.text
            input = input.toString().plus(dataBinding.btn9.text)
            dataBinding.input.text = input
            dataBinding.btn9.isEnabled = false
        }
        dataBinding.btn10.setOnClickListener {
            var input = dataBinding.input.text
            input = input.toString().plus(dataBinding.btn10.text)
            dataBinding.input.text = input
            dataBinding.btn10.isEnabled = false
        }
        dataBinding.check.setOnClickListener{
            if (!viewModel.validateAndProceed(dataBinding.input.text.toString())) {
                Toast.makeText(this, getString(R.string.try_again), Toast.LENGTH_SHORT).show()
                dataBinding.input.text = ""
                enableAllButtons()
            }
        }
    }

    /**
     * Enables all buttons after wrong input or after new logo
     */
    private fun enableAllButtons() {
        dataBinding.btn1.isEnabled = true
        dataBinding.btn2.isEnabled = true
        dataBinding.btn3.isEnabled = true
        dataBinding.btn4.isEnabled = true
        dataBinding.btn5.isEnabled = true
        dataBinding.btn6.isEnabled = true
        dataBinding.btn7.isEnabled = true
        dataBinding.btn8.isEnabled = true
        dataBinding.btn9.isEnabled = true
        dataBinding.btn10.isEnabled = true
    }

    /**
     * Sets observers on different items in viewmodel
     */
    private fun setupObserver() {
        viewModel.logoItem.observe(this, {
            viewModel.refreshButtons(it)
            Picasso.get().load(it.imgUrl).into(dataBinding.logoImage)
        })

        viewModel.counter.observe(this, {
            viewModel.nextLogo(it)
            dataBinding.input.text = ""
        })

        viewModel.randomChars.observe(this, {
            if (it.size < 10) {
                return@observe
            }
            dataBinding.btn1.text = it[0].toString()
            dataBinding.btn2.text = it[1].toString()
            dataBinding.btn3.text = it[2].toString()
            dataBinding.btn4.text = it[3].toString()
            dataBinding.btn5.text = it[4].toString()
            dataBinding.btn6.text = it[5].toString()
            dataBinding.btn7.text = it[6].toString()
            dataBinding.btn8.text = it[7].toString()
            dataBinding.btn9.text = it[8].toString()
            dataBinding.btn10.text = it[9].toString()
            enableAllButtons()
        })

        viewModel.isFinished.observe(this, {
            if (it){
                Toast.makeText(this, "Finished. Thanks", Toast.LENGTH_SHORT).show()
            }
        })
    }
}