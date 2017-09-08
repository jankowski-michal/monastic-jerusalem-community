/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */
package pl.orbitemobile.wspolnoty.data.entities

class Article(val title: String, val imgUrl: String, val articleUrl: String) {

    var description: String? = null //todo: add Missing Description instead

    var dataCreated: String? = null

    enum class KEY {
        ARTICLE_URL, TITLE, IMG_URL
    }
}
