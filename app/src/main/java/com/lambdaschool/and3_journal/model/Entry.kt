package com.lambdaschool.and3_journal.model

import java.io.Serializable

class Entry(
    var dateStamp: Long = System.currentTimeMillis() / 1000,
    var text: String = "Lorem Ipsum",
    var rating: Int = 0
) : Serializable