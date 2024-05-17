package com.prakhar.reciipiie.components

import android.text.Html

fun String.clearString(): String {
    return Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY).toString().trim()
}