package com.example.bohdansushchak.mydiary.activity

import android.content.Intent
import android.graphics.drawable.Drawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

import android.view.Menu
import android.view.MenuItem
import butterknife.BindView
import butterknife.ButterKnife

import com.example.bohdansushchak.mydiary.R
import com.example.bohdansushchak.mydiary.adapter.MyRecyclerAdapter
import com.example.bohdansushchak.mydiary.database.Note
import com.example.bohdansushchak.mydiary.utils.isPermissionGranted

import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.kotlin.where


class MainActivity : AppCompatActivity() {

    private lateinit var realm: Realm

    @BindView(R.id.rv_main)
    lateinit var recyclerView: RecyclerView

    @BindView(R.id.floatingActionButton)
    lateinit var fab: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)

/*
        if (isPermissionGranted(android.Manifest.permission.USE_FINGERPRINT)) {
            val intent = Intent(this, FingerPrintActivity::class.java)
            startActivity(intent)
            finish()
        }
*/

        val config = RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build()
        realm = Realm.getInstance(config)

        initViews()
    }

    fun initViews() {

        val notes = realm.where<Note>()
            .findAll()

        val adapter = MyRecyclerAdapter(this, notes)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        adapter.onItemClick = { position: Int? ->

            val intent = Intent(this, NoteActivity::class.java)
            intent.putExtra("Position", position)
            startActivity(intent)
        }

        val horizontalDecoration = DividerItemDecoration(recyclerView.context, DividerItemDecoration.VERTICAL)
        val divider : Drawable? = ContextCompat.getDrawable(this, R.drawable.item_divider)
        if (divider != null) {
            horizontalDecoration.setDrawable(divider)
            recyclerView.addItemDecoration(horizontalDecoration)
        }

        fab.setOnClickListener {

            val intent = Intent(this, NoteActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {

            R.id.menu_lock -> {

                val intent : Intent

                if (isPermissionGranted(android.Manifest.permission.USE_FINGERPRINT))
                    intent = Intent(this, FingerPrintActivity::class.java)
                else
                    intent = Intent(this, PasswordActivity::class.java)

                startActivity(intent)
                finish()
                return true
            }

            R.id.menu_settings ->
            {
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }
}
