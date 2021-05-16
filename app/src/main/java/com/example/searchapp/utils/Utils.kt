/*
 * (c) Binate Station Private Limited. All rights reserved.
 */

package com.example.searchapp.utils

import android.content.Context
import java.io.IOException

object Utils {

    /**
     * this method reads file from assets folder and returns in string form
     * @param context Context -  fragment or activity context
     * @param fileName  String - File to be read
     */
    fun getJsonDataFromAsset(context: Context, fileName: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }
}
