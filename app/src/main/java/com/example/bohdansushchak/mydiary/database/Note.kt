package com.example.bohdansushchak.mydiary.database

import io.realm.RealmObject

open class Note : RealmObject()
{
    var title: String? = null

    var content: String? = null

    var date: String? = null

}