package com.raj.mymusicapp.ui

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {


    var dialog: ProgressDialog? = null


    fun showProgress() {
        dialog = ProgressDialog(this@BaseActivity);
        dialog?.setMessage("Please wait.");
        dialog?.show()
    }


}