package com.example.bohdansushchak.mydiary.activity

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.example.bohdansushchak.mydiary.view.CEditText
import com.example.bohdansushchak.mydiary.R
import com.example.bohdansushchak.mydiary.database.Note
import com.example.bohdansushchak.mydiary.view.CTextView
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.kotlin.createObject
import kotlinx.android.synthetic.main.activity_note.*
import java.text.SimpleDateFormat
import java.util.*

class NoteActivity : AppCompatActivity() {

    private lateinit var realm : Realm

    private val pattern = "dd.M.yyyy hh:mm:ss"

    @BindView(R.id.ed_Title) lateinit var edTitle: CEditText
    @BindView(R.id.ed_Content) lateinit var edContent: CEditText
    @BindView(R.id.tv_Date) lateinit var tvDate: CTextView

    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)
        ButterKnife.bind(this)

        val config = RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build()
        realm = Realm.getInstance(config)

        supportActionBar?.setDisplayShowHomeEnabled(true)

        val sdf = SimpleDateFormat(pattern)
        val currentDate = sdf.format(Date())

        tv_Date.text = currentDate.toString()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.note_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when(item?.itemId){

            R.id.menu_save ->
            {
                val title = edTitle.text.toString()
                val content = edContent.text.toString()
                val date = tvDate.text.toString()

                realm.executeTransaction { realm ->

                    var note = realm.createObject<Note>()
                    note.title = title
                    note.content = content
                    note.date = date
                }

                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
