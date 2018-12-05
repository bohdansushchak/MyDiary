package com.example.bohdansushchak.mydiary.database

import io.realm.RealmObject
import java.io.Serializable

open class Note : RealmObject(), Serializable
{
    var title: String? = null

    var content: String? = null

    var date: String? = null
}