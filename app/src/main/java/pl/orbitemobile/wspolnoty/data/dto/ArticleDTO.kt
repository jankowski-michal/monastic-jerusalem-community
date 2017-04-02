/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */
package pl.orbitemobile.wspolnoty.data.dto

class ArticleDTO(val title: String, val imgUrl: String, val articleUrl: String) {

    var description: String = "Nie udało się znaleźć opisu"

    var dataCreated: String? = null

    enum class KEY {
        ARTICLE_URL, TITLE, IMG_URL
    }
}
