package com.ilv.yuka.utils

import android.graphics.Typeface
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import android.widget.TextView
import androidx.core.text.set

fun TextView.setTextOrEmpty(prefix: String, list: List<String>, emptyText: String) {
    if (list.isEmpty()) {
        titleValue(title = prefix, value = emptyText)
    } else {
        titleValue(title = prefix, value = list.joinToString(", "))
    }
}


fun TextView.titleValue(title: String, value: String) {
    val builder = SpannableStringBuilder("$title : ")
    builder[0, builder.length] = StyleSpan(Typeface.BOLD)
    builder.append(value)

    text = builder
}