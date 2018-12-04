package com.example.bohdansushchak.mydiary.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

import com.example.bohdansushchak.mydiary.R
import com.example.bohdansushchak.mydiary.database.Note

import io.realm.Realm
import io.realm.kotlin.where


class MainActivity : AppCompatActivity() {

    private lateinit var realm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        realm = Realm.getDefaultInstance()

        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.menu_create -> {

                val intent = Intent(this, NoteActivity::class.java)
                startActivity(intent)
                return true
            }

            R.id.menu_lock -> {

                val result = realm.where<Note>().count()

                Toast.makeText(this,"size = " + result.toString(), Toast.LENGTH_SHORT).show()

                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }
}
