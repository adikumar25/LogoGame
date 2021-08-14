package com.aditya.logogame.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aditya.logogame.models.Logo
import com.aditya.logogame.repository.LogoRepository

/**
 * ViewModel for [GameActivity]
 */
class GameActivityViewModel: ViewModel() {

    private var logoList = emptyList<Logo>()
    val logoItem =  MutableLiveData<Logo>()
    val counter = MutableLiveData<Int>()
    val randomChars = MutableLiveData<List<Char>>()
    val isFinished = MutableLiveData<Boolean>()
    val score = MutableLiveData<Int>()

    /**
     * Retrieves the list of logos and set the counter to 0
     */
    fun startGame(context: Context) {
        logoList = LogoRepository().getLogos(context)
        counter.value = 0
    }

    /**
     * Validates if provided input is correct or not
     * Proceeds to next counter if input is correct
     */
    fun validateAndProceed(name:String): Boolean {
        if (name.contentEquals(logoItem.value?.name)) {
            counter.value = counter.value?.plus(1)
            score.value = score.value?.plus(100)
            return true
        }
        return false
    }

    /**
     * Refresh the Buttons with new set of random characters
     */
    fun refreshButtons(logo: Logo) {
        randomChars.value =  getRandomChar(logo)
    }

    /**
     * Get random char list with the input mixed with it
     * so that input is always there in buttons
     */
    private fun getRandomChar(logo: Logo): List<Char> {
        val charList = emptyList<Char>().toMutableList()
        logo.name.toCharArray().forEach {
            charList.add(it)
        }

        val restItem = 10 - logo.name.length

        for (i in 1..restItem) {
            val random = (0..25).random()
            charList.add((random.plus(65)).toChar())
        }

        return charList.shuffled()
    }

    /**
     * Upon counter is incremented update the logoItem
     * If reached all logos then set isFinished
     */
    fun nextLogo(count: Int) {
        if (count < logoList.size) {
            logoItem.value = logoList[count]
            return
        }
        isFinished.value = true
    }
}