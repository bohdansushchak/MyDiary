package com.example.bohdansushchak.mydiary.activity

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.annotation.Nullable
import android.view.MenuItem
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.EditText
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.example.bohdansushchak.mydiary.R
import com.example.bohdansushchak.mydiary.view.CTextView

class PasswordActivity : BaseActivity() {

    @Nullable
    @BindView(R.id.ed_pin_code)
    lateinit var edPassword: EditText

    @Nullable
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

    @Nullable
    @OnClick(R.id.btn_login)
    fun login(view: View) {

        val savedPassword = sharedPreferences.getString("password", DEF_PASSWORD)?.trim().toString()
        val pass = edPassword.text?.trim().toString()

        if (pass.isEmpty())
        {
            tvPasswordError.text = getString(R.string.err_msg_password_is_empty)

            val anim = AnimationUtils.loadAnimation(this, R.anim.shake)
            tvPasswordError.startAnimation(anim)
        }

        else if (!pass.equals(savedPassword)) {

            tvPasswordError.text = getString(R.string.err_msg_wrong_password)

            val anim = AnimationUtils.loadAnimation(this, R.anim.shake)
            tvPasswordError.startAnimation(anim)

        } else {
            tvPasswordError.text = null

            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("isLock", false)
            startActivity(intent)
            clearBackStack()
        }
    }
}
