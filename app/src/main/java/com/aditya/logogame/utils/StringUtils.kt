package com.aditya.logogame.utils

/**
 * Util class for String related opertaions
 */
class StringUtils {
    companion object {
        fun getSystemNewLine(): String? {
            return System.getProperty("line.separator", "\n")
        }
    }
}