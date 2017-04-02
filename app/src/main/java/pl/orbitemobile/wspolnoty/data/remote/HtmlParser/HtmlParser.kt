/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */

package pl.orbitemobile.wspolnoty.data.remote.HtmlParser

class HtmlParser {

    fun parse(html: String) = html.stripFromHtml()

    private fun String.stripFromHtml(): String {
        return this.replace("</p>".toRegex(), "\n")
                .replace("<br>".toRegex(), "\n")
                .maskLinks()
                .replace("<[^>]*>".toRegex(), "")
                .trim { it <= ' ' }
                .replace("\n ".toRegex(), "\n")
                .replace(" \n".toRegex(), "\n")
                .replace("\n\n\n\n\n\n".toRegex(), "\n")
                .replace("\n\n\n\n\n".toRegex(), "\n")
                .replace("\n\n\n\n".toRegex(), "\n")
                .replace("\n\n\n".toRegex(), "\n")
                .replace("&nbsp;".toRegex(), " ")
                .unmaskLinks()
                .removeEmptyLink()
                .trim { it <= ' ' }
                .replace("\n", "<br>")
    }

    private fun String.maskLinks() = this
            .replace("<a href", "{a href")
            .replace("</a>", "{/a}")
            .replace("</ a>", "{/a}")

    private fun String.unmaskLinks() = this
            .replace("{a href", "<a href")
            .replace("{/a}", "</a>")
            .replace("{/a}", "</ a>")

    private fun String.removeEmptyLink() = this.replace("<[^>]*></a>".toRegex(), "").replace("<[^>]*> </a>".toRegex(), "")

}