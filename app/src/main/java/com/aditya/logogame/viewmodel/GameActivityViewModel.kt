package com.aditya.logogame.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aditya.logogame.models.ButtonItem
import com.aditya.logogame.models.Logo
import com.aditya.logogame.repository.LogoRepository
import com.aditya.logogame.utils.StringUtils

/**
 * ViewModel for [GameActivity]
 */
class GameActivityViewModel: ViewModel() {

    private var logoList = emptyList<Logo>()
    val answerButtonList = MutableLiveData<List<ButtonItem>>()
    val suggestedButtonList = MutableLiveData<List<ButtonItem>>()
    val logoItem =  MutableLiveData<Logo>()
    val logoIndex = MutableLiveData<Int>()
    val isFinished = MutableLiveData<Boolean>()

    /**
     * Retrieves the list of logos and set the counter to 0
     */
    fun startGame(context: Context) {
        logoList = LogoRepository().getLogos(context)
        logoIndex.value = 0
    }

    /**
     * initialize new logo view with default buttons
     */
    fun initLogoView(logo: Logo) {
        answerButtonList.value = getDefaultAnswerButtons(logo)
        suggestedButtonList.value = getSuggestionButtons(logo)
    }

    /**
     * Validates if provided input is correct or not
     * Proceeds to next counter if input is correct
     */
    private fun validateAndProceed() {
        if (getAnswerString().contentEquals(logoItem.value?.name)) {
            logoIndex.value = logoIndex.value?.plus(1)
        } else {
            initLogoView(logoItem.value!!)
        }
    }

    /**
     * @return answer string
     */
    private fun getAnswerString() : String {
        var inputAns = StringUtils.EMPTY_STRING
        answerButtonList.value?.forEach{
            inputAns = inputAns.plus(it.value)
        }
        return inputAns
    }

    /**
     * Get random char list with the input mixed with it
     * so that input is always there in buttons
     */
    private fun getSuggestionButtons(logo: Logo): List<ButtonItem> {
        val buttonList = emptyList<ButtonItem>().toMutableList()
        logo.name.toCharArray().forEach {
            buttonList.add(ButtonItem(it.toString(), true))
        }

        for (i in 1..logo.name.length) {
            val random = (0..25).random()
            buttonList.add(ButtonItem(random.plus(65).toChar().toString(), true))
        }

        return buttonList.shuffled()
    }

    /**
     * @return default answer button list
     */
    private fun getDefaultAnswerButtons(logo: Logo): List<ButtonItem> {
        val buttonList = emptyList<ButtonItem>().toMutableList()
        logo.name.toCharArray().forEach { _ ->
            buttonList.add(ButtonItem(StringUtils.DEFAULT_ANSWER_CHAR, false))
        }
        answerButtonList.value = buttonList
        return buttonList
    }

    /**
     * Updates Input Buttons
     */
    fun updateAnswerButtons(buttonItem: ButtonItem) {
        var answerString = ""
        answerButtonList.value?.forEach{
           answerString = answerString.plus(it.value)
        }

        val position = answerString.indexOf(StringUtils.DEFAULT_ANSWER_CHAR)
        answerString = answerString.substring(0, position) + buttonItem.value + answerString.substring(position + 1)


        val buttonList = emptyList<ButtonItem>().toMutableList()
        answerString.toCharArray().forEach {
            buttonList.add(ButtonItem(it.toString(), false))
        }
        answerButtonList.value = buttonList

        val nextPosition = answerString.indexOf(StringUtils.DEFAULT_ANSWER_CHAR)
        if (nextPosition == -1) {
            validateAndProceed()
        }
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