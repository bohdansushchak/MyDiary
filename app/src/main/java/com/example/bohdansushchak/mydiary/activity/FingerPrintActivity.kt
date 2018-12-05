package com.example.bohdansushchak.mydiary.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import butterknife.ButterKnife
import butterknife.OnClick
import com.example.bohdansushchak.mydiary.R

class FingerPrintActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fingerprint)
        ButterKnife.bind(this)

    }

    @OnClick(R.id.tv_login_password)
    fun clickPassword(view: View){

        var intent = Intent(this, PasswordActivity::class.java)
        startActivity(intent)
    }
}
