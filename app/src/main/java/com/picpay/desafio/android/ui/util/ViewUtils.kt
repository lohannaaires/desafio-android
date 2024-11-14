package com.picpay.desafio.android.ui.util

import android.view.View
import android.widget.ProgressBar

object ViewUtils {
    fun ProgressBar.show() {
        this.visibility = View.VISIBLE
    }

    fun ProgressBar.dismiss() {
        this.visibility = View.GONE
    }
}