package com.aditya.logogame.utils

/**
 * Util class for String related opertaions
 */
class StringUtils {
    companion object {
        const val EMPTY_STRING = ""
        const val DEFAULT_ANSWER_CHAR = "_"

        fun getSystemNewLine(): String? {
            return System.getProperty("line.separator", "\n")
        }
    }
}