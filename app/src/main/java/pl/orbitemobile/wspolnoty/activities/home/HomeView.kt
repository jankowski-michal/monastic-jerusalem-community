/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */

package pl.orbitemobile.wspolnoty.activities.home

import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.squareup.picasso.Picasso
import pl.orbitemobile.mvp.bind
import pl.orbitemobile.wspolnoty.R
import pl.orbitemobile.wspolnoty.activities.mvp.DownloadViewUtil
import pl.orbitemobile.wspolnoty.data.dto.ArticleDTO

class HomeView : HomeContract.View(R.layout.home_view) {
    override var viewContent: View? = null

    var whereLayout: TextView? = null
    var contactLayout: TextView? = null
    var hoursLayout: TextView? = null
    var todaysMass: TextView? = null
    override var progressBar: View? = null
    override var errorLayout: LinearLayout? = null
    var eventsViewPager: ViewPager? = null
    override var errorButton: TextView? = null
    var newsButton: TextView? = null
    var wordButton: TextView? = null
    private var eventsAdapter: EventsAdapter? = null

    override fun View.bindViews(): View {
        whereLayout = bind(R.id.where_botton)
        contactLayout = bind(R.id.contact_botton)
        hoursLayout = bind(R.id.hours_botton)
        todaysMass = bind(R.id.todays_mass_text)
        progressBar = bind(R.id.progress_bar)
        errorLayout = bind(R.id.error_layout)
        eventsViewPager = bind(R.id.view_pager)
        errorButton = bind(R.id.error_button)
        newsButton = bind(R.id.news_botton)
        wordButton = bind(R.id.word_botton)
        contactLayout?.setOnClickListener { presenter!!.onContactButtonClick() }
        whereLayout?.setOnClickListener { presenter!!.onWhereButtonClick() }
        hoursLayout?.setOnClickListener { presenter!!.onHoursButtonClick() }
        newsButton?.setOnClickListener { presenter!!.onNewsButtonClick() }
        wordButton?.setOnClickListener { presenter!!.onWordButtonClick() }
        viewContent = eventsViewPager
        showLoadingScreen()
        return this
    }

    override fun showLoadingScreen() = DownloadViewUtil.showLoadingScreen(this)

    override fun showErrorMessage() {
        DownloadViewUtil.showErrorMessage(this, { presenter!!.onRetryClick() })
    }

    override fun setTodayMass(content: String) {
        todaysMass?.text = content
    }

    override fun showArticles(articles: Array<ArticleDTO>) {
        eventsViewPager?.visibility = View.VISIBLE
        eventsAdapter = EventsAdapter(articles)
        eventsViewPager?.adapter = eventsAdapter
        progressBar?.visibility = View.GONE
        errorLayout?.visibility = View.GONE
    }

    private inner class EventsAdapter(private val mArticles: Array<ArticleDTO>) : PagerAdapter() {

        override fun instantiateItem(collection: ViewGroup, position: Int): Any {
            val inflater = LayoutInflater.from(context)
            val layout = inflater.inflate(R.layout.home_event_entry, collection, false) as ViewGroup
            layout.setOnClickListener { presenter!!.onArticleClick(mArticles[position]) }
            collection.addView(layout)
            setPageContent(position, layout)
            return layout
        }

        override fun getItemPosition(`object`: Any?): Int = PagerAdapter.POSITION_NONE

        override fun destroyItem(collection: ViewGroup, position: Int, view: Any) {
            collection.removeView(view as View)
        }

        override fun getCount(): Int = mArticles.size

        override fun isViewFromObject(view: View, `object`: Any): Boolean = view === `object`

        override fun getPageTitle(position: Int): CharSequence = mArticles[position].title

        private fun setPageContent(position: Int, layout: ViewGroup) {
            val article = mArticles[position]
            val textView = layout.findViewById(R.id.textView) as TextView
            textView.text = article.title
            val pagination = layout.findViewById(R.id.pagination) as TextView
            pagination.text = pagination(position)
            val thumbnail = layout.findViewById(R.id.short_event_image) as ImageView

            Picasso.with(context)
                    .load(article.imgUrl)
                    .error(R.drawable.hours_top)
                    .into(thumbnail)
        }

        private fun pagination(position: Int): String {
            val positionToDisplay = position + 1
            return " " + positionToDisplay + " / " + mArticles.size + " "
        }
    }
}
