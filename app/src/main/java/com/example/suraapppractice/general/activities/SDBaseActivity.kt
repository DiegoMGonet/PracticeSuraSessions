package com.example.suraapppractice.general.activities

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.suraapppractice.R

open class SDBaseActivity: AppCompatActivity() {

    private val progressDialog: AlertDialog by lazy {
        AlertDialog.Builder(this, R.style.SDTranparent_Dialog)
            .setCancelable(false)
            .setView(R.layout.sd_layout_loading)
            .create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
    }

    fun showLoading(show: Boolean) {
        if(show) {
            progressDialog.show()
        } else {
            progressDialog.dismiss()
        }
    }
}