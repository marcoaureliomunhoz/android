package br.com.marcoaureliomunhoz.biblio.helpers.base

import java.text.SimpleDateFormat
import java.util.*

object DateHelper {

    fun getCurrentDateTime(): Date {
        return Calendar.getInstance().time
    }

    fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
        val formatter = SimpleDateFormat(format, locale)
        return formatter.format(this)
    }

    fun stringOfCurrentDate(): String {
        val date = getCurrentDateTime()
        return date.toString("yyyy/MM/dd HH:mm:ss")
    }
}