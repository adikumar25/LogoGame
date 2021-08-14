package com.aditya.logogame.utils

import android.content.Context
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.Reader
import java.lang.StringBuilder
import java.nio.charset.StandardCharsets

/**
 * Util Class for IO operations on file
 */
class IOUtils {

    companion object{
        @JvmStatic
        fun readFileFromAssets(context:Context, fileName: String): String{
            return readContent(InputStreamReader(context.assets.open(fileName), StandardCharsets.UTF_8))
        }

        @Throws(IOException::class)
        private fun readContent(reader: Reader): String {
            val buffer = StringBuilder()
            BufferedReader(reader).use { bufferedReader ->
                var line: String?
                val lineSeparator = StringUtils.getSystemNewLine()
                while (bufferedReader.readLine().also { line = it } != null) {
                    buffer.append(line).append(lineSeparator)
                }
            }
            return buffer.toString()
        }
    }
}