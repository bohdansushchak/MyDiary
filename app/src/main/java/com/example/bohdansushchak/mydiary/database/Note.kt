package com.example.bohdansushchak.mydiary.database

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.io.Serializable

open class Note(

    var title: String? = null,

    var content: String? = null,

    var date: String? = null

) : RealmObject()