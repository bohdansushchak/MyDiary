package com.example.bohdansushchak.mydiary.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bohdansushchak.mydiary.R
import com.example.bohdansushchak.mydiary.database.Note
import io.realm.RealmChangeListener
import io.realm.RealmResults
import kotlinx.android.synthetic.main.item_main_view.view.*

open class MyRecyclerAdapter(val context: Context, var notes:RealmResults<Note>)
    : RecyclerView.Adapter<ViewHolder>(), RealmChangeListener<RealmResults<Note>> {

    init {
        notes.addChangeListener(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder{
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_main_view, parent, false))
    }

    override fun getItemCount(): Int {
       return notes.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.tvTitle.text = notes.get(position)?.title
        holder.tvDescription.text = notes.get(position)?.content
        holder.tvDate.text = notes.get(position)?.date
    }

    override fun onChange(t: RealmResults<Note>) {
       notifyDataSetChanged()
    }
}

open class ViewHolder (view: View) : RecyclerView.ViewHolder(view){
    val tvTitle = view.tv_Title
    val tvDescription = view.tv_description
    val tvDate = view.tv_Date
}