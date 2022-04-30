package com.shinjh1253.googlebooksearch.core.extensions

import android.app.Activity
import androidx.appcompat.app.AlertDialog
import com.shinjh1253.googlebooksearch.R

fun Activity.createAlertDialog(title: String, message: String?, dismissCallback: () -> Unit) =
    AlertDialog.Builder(this)
        .setTitle(title)
        .setMessage(message)
        .setNegativeButton(getString(R.string.ok)) { dialog, _ ->
            dialog.dismiss()
            dismissCallback.invoke()
        }
        .create()