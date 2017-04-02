/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */

package pl.orbitemobile.wspolnoty.data.remote.parser

import android.text.Html
import android.text.SpannableString

class Parser private constructor() {

    companion object {
        val instance = Parser()
    }

    @SuppressWarnings("deprecation")
    fun fromHtml(html: String): SpannableString {
        val spanned = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY)
        } else {
            Html.fromHtml(html)
        }
        return SpannableString(spanned)
    }

    fun parse(html: String) = html.stripFromHtmlToLinkingText()

    //fixme: probably we can omit most of this by using html parser and linked method display

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