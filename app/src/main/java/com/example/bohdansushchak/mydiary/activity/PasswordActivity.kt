package com.example.bohdansushchak.mydiary.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import butterknife.ButterKnife
import com.example.bohdansushchak.mydiary.R

class PasswordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password)
        ButterKnife.bind(this)
        
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}
