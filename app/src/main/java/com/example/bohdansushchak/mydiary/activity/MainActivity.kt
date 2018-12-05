package com.example.bohdansushchak.mydiary.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife

import com.example.bohdansushchak.mydiary.R
import com.example.bohdansushchak.mydiary.adapter.MyRecyclerAdapter
import com.example.bohdansushchak.mydiary.database.Note

import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.kotlin.where
import java.io.Serializable


class MainActivity : AppCompatActivity() {

    private lateinit var realm: Realm

    @BindView(R.id.rv_Main)
    lateinit var recyclerView: RecyclerView

    @BindView(R.id.floatingActionButton)
    lateinit var fab: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)

        val config = RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build()
        realm = Realm.getInstance(config)

        initViews()
    }

    fun initViews() {

        val notes = realm.where<Note>()
            .findAll()

        var adapter = MyRecyclerAdapter(this, notes)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        adapter.onItemClick = {position: Int? ->

            val intent = Intent(this, NoteActivity::class.java)
            intent.putExtra("Position", position)
            startActivity(intent)
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

                val result = realm.where<Note>().count()

                Toast.makeText(this, "size = " + result.toString(), Toast.LENGTH_SHORT).show()

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
