package com.example.bohdansushchak.mydiary.activity

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.example.bohdansushchak.mydiary.R

class PasswordActivity : BaseActivity() {

    @BindView(R.id.ed_pin_code) lateinit var edPinCode: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password)
        ButterKnife.bind(this)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    @OnClick(R.id.btn_login)
    fun login(view: View){

        if(edPinCode.text.isNotEmpty()){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            clearBackStack()
        }
    }
}
