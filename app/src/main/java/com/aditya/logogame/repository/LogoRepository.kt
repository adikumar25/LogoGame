package com.aditya.logogame.repository

import android.content.Context
import com.aditya.logogame.models.Logo
import com.aditya.logogame.utils.IOUtils
import com.aditya.logogame.utils.JsonUtils

class LogoRepository {

    /**
     * Reads the logo.json file from assets folder and returns list of [Logo]
     */
    fun getLogos(context: Context) : List<Logo> {
        val logoJson =  JsonUtils.getJsonObjectFromString(IOUtils.readFileFromAssets(context, "logo.json"))
        return JsonUtils.parseLogo(logoJson)
    }


}