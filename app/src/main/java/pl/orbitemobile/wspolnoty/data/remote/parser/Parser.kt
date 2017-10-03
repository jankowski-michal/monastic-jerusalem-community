/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */

package pl.orbitemobile.wspolnoty.data.remote.parser

//todo: refactor class and write tests
class Parser private constructor(){

    companion object {
        val instance = Parser()
    }

    fun parse(html: String) = html.stripFromHtmlToLinkingText()

    fun parseReadingVerse(verseHtml: String): Pair<String, String> {
        val verse = verseHtml.substringBefore("<div class=\"row extra\">")
        val title = parse(verse.substringBefore("<sup>").substringBefore("<strong class=\"cap\">"))
                .replace("<br>  ", "\n")
                .replace("<br> ", "\n")
                .replace("<br>", "\n")
                .trim()
        val content = parse("{sup}" + verse.masSups().substringAfter("{sup}")).replace("<br>", "\n").unmasSups().replace("*", "")
        return title to content
    }

    //todo: probably we can omit most of this by using html parser and linked method display

    private fun String.stripFromHtmlToLinkingText(): String {
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
    private fun String.masSups() = this
            .replace("<sup>", "{sup}")
            .replace("</sup>", "{/sup}")


    private fun String.unmasSups() = this
            .replace("{sup}", "<small>")
            .replace("{/sup}", "</small>")

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