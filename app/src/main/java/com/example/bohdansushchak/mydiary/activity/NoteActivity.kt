package com.example.bohdansushchak.mydiary.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.example.bohdansushchak.mydiary.R
import com.example.bohdansushchak.mydiary.database.Note
import io.realm.Realm
import io.realm.kotlin.createObject

class NoteActivity : AppCompatActivity() {

    private lateinit var realm : Realm

    @BindView(R.id.ed_Title) lateinit var edTitle: EditText
    @BindView(R.id.ed_Content) lateinit var edContent: EditText
    @BindView(R.id.tv_Date) lateinit var tvDate: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)
        ButterKnife.bind(this)

        realm = Realm.getDefaultInstance()

        supportActionBar?.setDisplayShowHomeEnabled(true)
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
