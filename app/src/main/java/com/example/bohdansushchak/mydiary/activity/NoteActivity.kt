package com.example.bohdansushchak.mydiary.activity

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.Nullable
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import com.example.bohdansushchak.mydiary.view.CEditText
import com.example.bohdansushchak.mydiary.R
import com.example.bohdansushchak.mydiary.database.Note
import com.example.bohdansushchak.mydiary.view.CTextView
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.kotlin.createObject
import io.realm.kotlin.where
import kotlinx.android.synthetic.main.activity_note.*
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class NoteActivity : AppCompatActivity() {

    private lateinit var realm: Realm

    private val pattern = "dd.M.yyyy hh:mm:ss"

    private var note: Note? = null

    @Nullable
    @BindView(R.id.ed_Title)
    lateinit var edTitle: CEditText

    @Nullable
    @BindView(R.id.ed_Content)
    lateinit var edContent: CEditText
    
    @Nullable
    @BindView(R.id.tv_Date)
    lateinit var tvDate: CTextView

    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)
        ButterKnife.bind(this)

        val config = RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build()
        realm = Realm.getInstance(config)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initView()
    }

    private fun initView() {

        val pos = intent?.extras?.get("Position")

        if (pos is Int)
            note = realm.where<Note>().findAll()?.get(pos)

        if (note != null) {
            tv_Date.text = note?.date
            edContent.setText(note?.content)
            edTitle.setText(note?.title)

/*
            edTitle.clearFocus()
            edContent.clearFocus()
*/
            edTitle.setFocusableInTouchMode(false)
            edTitle.setFocusable(false)
            edTitle.setFocusableInTouchMode(true)
            edTitle.setFocusable(true)

            //val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            //imm.hideSoftInputFromWindow(edTitle.windowToken, 0)
            //imm.showSoftInput(edContent, 0)


        } else {
            val sdf = SimpleDateFormat(pattern)
            val currentDate = sdf.format(Date())

            tv_Date.text = currentDate.toString()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.note_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun saveNote() {

        val title = edTitle.text.toString()
        val content = edContent.text.toString()
        val date = tvDate.text.toString()

        if (note != null) {

            realm.executeTransaction { realm ->

                note?.title = title
                note?.content = content
                note?.date = date
            }

        } else {

            realm.executeTransaction { realm ->

                note = realm.createObject<Note>()

                note?.title = title
                note?.content = content
                note?.date = date
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item?.itemId) {

            R.id.menu_save -> {
                try {
                    saveNote()
                    Toast.makeText(this, R.string.suc_msg_note_saved, Toast.LENGTH_SHORT).show()
                } catch (e: Exception) {
                    Toast.makeText(this, R.string.err_msg_note_not_saved, Toast.LENGTH_SHORT).show()
                }
                return true
            }

            android.R.id.home -> {
                onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }
}
