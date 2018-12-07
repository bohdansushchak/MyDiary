package com.example.bohdansushchak.mydiary.activity

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.example.bohdansushchak.mydiary.R
import com.example.bohdansushchak.mydiary.view.CTextView

class PasswordActivity : BaseActivity() {

    @BindView(R.id.ed_pin_code)
    lateinit var edPassword: EditText

    @BindView(R.id.tv_password_error)
    lateinit var tvPasswordError: CTextView

    private val DEF_PASSWORD = "1111"

    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password)
        ButterKnife.bind(this)

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    @OnClick(R.id.btn_login)
    fun login(view: View) {

        val savedPassword = sharedPreferences.getString("password", DEF_PASSWORD)?.trim().toString()
        val pass = edPassword.text?.trim().toString()

        if (pass.isEmpty())
            tvPasswordError.text = getString(R.string.err_msg_password_is_empty)
        else if (!pass.equals(savedPassword)) {
            tvPasswordError.text = getString(R.string.err_msg_wrong_password)
        } else {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("isLock", false)
            startActivity(intent)
            clearBackStack()
        }
    }
}
