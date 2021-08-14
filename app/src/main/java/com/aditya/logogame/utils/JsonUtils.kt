package com.aditya.logogame.utils

import com.aditya.logogame.models.Logo
import com.google.gson.*

/**
 * Utils class to read and parse Json Objects
 */
class JsonUtils {
    companion object {
        fun getJsonObjectFromString(json: String?): JsonArray? {
            if (!json.isNullOrBlank()) {
                try {
                    val jsonElement: JsonElement = Gson().fromJson(json, JsonElement::class.java)
                    return if (jsonElement.isJsonArray) jsonElement.asJsonArray else null
                } catch (jse: JsonSyntaxException) {
                    // STUB
                }
            }
            return null
        }

        fun parseLogo(logoObject: JsonElement?): List<Logo> {
            if (logoObject == null || !logoObject.isJsonArray) {
                return emptyList()
            }
            val logoJsonArray = logoObject.asJsonArray

            val logoList = ArrayList<Logo>()
            logoJsonArray.forEach {
                logoList.add(Gson().fromJson(it, Logo::class.java))
            }

            return logoList
        }
    }
}