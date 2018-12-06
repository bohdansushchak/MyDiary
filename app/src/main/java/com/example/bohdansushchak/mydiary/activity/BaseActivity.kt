package com.example.bohdansushchak.mydiary.activity

import android.support.v7.app.AppCompatActivity
import android.content.Intent
import android.content.IntentFilter
import android.content.BroadcastReceiver
import android.content.Context
import android.os.Bundle
import android.support.v4.content.LocalBroadcastManager


open class BaseActivity : AppCompatActivity(){

    private val ACTION_FINISH = "action_finish"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        LocalBroadcastManager.getInstance(this).registerReceiver(broadCastReceiver, IntentFilter(ACTION_FINISH))
    }

    val broadCastReceiver = object : BroadcastReceiver() {
        override fun onReceive(contxt: Context?, intent: Intent?) {
            finish()
        }
    }

    fun clearBackStack() {
        LocalBroadcastManager.getInstance(this).sendBroadcast(Intent(ACTION_FINISH))
    }

    override fun onDestroy() {
        LocalBroadcastManager.getInstance(this)
            .unregisterReceiver(broadCastReceiver)
        super.onDestroy()
    }
}