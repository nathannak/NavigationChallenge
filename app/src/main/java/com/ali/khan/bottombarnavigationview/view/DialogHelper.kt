package com.ali.khan.bottombarnavigationview.view

import android.app.AlertDialog
import android.content.Context

object DialogHelper {

    fun postErrordialog(context: Context) {
        val alertDialog = AlertDialog.Builder(context).create()
        alertDialog.setTitle("Error fetching products")
        alertDialog.setMessage("An error happened getting list of products")
        alertDialog.setButton(
            AlertDialog.BUTTON_NEUTRAL, "OK"
        ) { dialog, which -> dialog.dismiss() }
        alertDialog.show()
    }

}