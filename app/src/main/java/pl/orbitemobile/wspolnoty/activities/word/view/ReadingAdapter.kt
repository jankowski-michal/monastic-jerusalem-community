package pl.orbitemobile.wspolnoty.activities.word.view

import android.graphics.Typeface
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.text.SpannableString
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import pl.orbitemobile.mvp.bind
import pl.orbitemobile.wspolnoty.R
import pl.orbitemobile.wspolnoty.data.dto.ReadingDTO


class ReadingAdapter(private val readings: List<ReadingDTO>) : RecyclerView.Adapter<ReadingAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.setReading(readings[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int) =
            ViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.word_reading_entry, parent, false))

    override fun getItemCount(): Int = readings.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var from: TextView = view.bind(R.id.reading_from)
        var title: TextView = view.bind(R.id.reading_title)
        var content: TextView = view.bind(R.id.reading_content)

        fun setReading(reading: ReadingDTO) {
            from.fontMerriweatherItalic()
            title.fontMerriweatherRegular()
            content.fontMerriweatherLight()
            from.text = reading.from
            title.text = reading.title
            content.linkingText(reading.content)
        }

        fun TextView.linkingText(text: String) {
            this.movementMethod = LinkMovementMethod.getInstance()
            this.text = SpannableString(Html.fromHtml(text))
        }

        fun TextView.fontMerriweatherLight() = font("fonts/merriweather/Merriweather-Light.ttf")
        fun TextView.fontMerriweatherItalic() = font("fonts/merriweather/Merriweather-Italic.ttf")
        fun TextView.fontMerriweatherRegular() = font("fonts/merriweather/Merriweather-Regular.ttf")


        fun TextView.font(font: String) {
            this.typeface = Typeface.createFromAsset(context.assets, font)
        }

//        val CZYTANIA_NA_DZIS = "http://www.opoka.org.pl/dzis/0.1,index.html"
//        val CZYTANIA_NA_DZIS_CONTENT = "https://wbiblii.pl/szukaj/"

    }

}