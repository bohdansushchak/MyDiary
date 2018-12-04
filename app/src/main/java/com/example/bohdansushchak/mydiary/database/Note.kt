package com.example.bohdansushchak.mydiary.database

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required

open class Note : RealmObject()
{
    //@PrimaryKey
   // var id: Long = 0

    var title: String? = null

    var content: String? = null

    var date: String? = null

}