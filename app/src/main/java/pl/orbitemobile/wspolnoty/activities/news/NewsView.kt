/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */
package pl.orbitemobile.wspolnoty.activities.news

import android.support.v7.widget.CardView
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.squareup.picasso.Picasso
import pl.orbitemobile.mvp.bind
import pl.orbitemobile.wspolnoty.R
import pl.orbitemobile.wspolnoty.activities.utils.DownloadViewUtil
import pl.orbitemobile.wspolnoty.data.dto.ArticleDTO

class NewsView : NewsContract.View(R.layout.news_view) {
    override var viewContent: View? = null

    var frameLayout: FrameLayout? = null

    override var progressBar: View? = null

    override var errorLayout: LinearLayout? = null

    override var errorButton: TextView? = null

    var recyclerView: RecyclerView? = null

    var loadMoreNews: TextView? = null

    private var adapter: NewsAdapter? = null

    override fun View.bindViews(): View {
        frameLayout = bind(R.id.news_frame_layout)
        progressBar = bind(R.id.progress_bar)
        errorLayout = bind(R.id.error_layout)
        errorButton = bind(R.id.error_button)
        recyclerView = bind(R.id.news_recycler_view)
        loadMoreNews = bind(R.id.load_more_news)
        viewContent = loadMoreNews
        setRecyclerViewMatchScreenSize()
        return this
    }

    override fun showErrorMessage() = DownloadViewUtil.showErrorMessage(this) { mPresenter!!.onRetryClick() }

    override fun showLoadingScreen() = DownloadViewUtil.showLoadingScreen(this)

    override fun showArticles(articleDTOs: Array<ArticleDTO>) {
        DownloadViewUtil.showViewContent(this)
        loadMoreNews!!.setOnClickListener { mPresenter!!.onShowMore() }
        setRecyclerArticles(articleDTOs)
    }

    override fun showNetworkToast() = Toast.makeText(context, context.getString(R.string.no_network_message), Toast.LENGTH_LONG).show()


    private fun setRecyclerArticles(articleDTOs: Array<ArticleDTO>) {
        if (adapter == null) {
            initNewAdapter(articleDTOs)
        } else {
            adapter?.addArticles(articleDTOs)
            adapter?.notifyDataSetChanged()
        }
    }

    private fun initNewAdapter(articleDTOs: Array<ArticleDTO>) {
        val layoutManager = GridLayoutManager(context, 1)
        recyclerView?.layoutManager = layoutManager
        adapter = NewsAdapter(articleDTOs)
        recyclerView?.adapter = adapter
    }

    private fun setRecyclerViewMatchScreenSize() {
        val height = frameLayout?.layoutParams!!.height
        val width = frameLayout?.layoutParams!!.width
        recyclerView?.layoutParams = LinearLayout.LayoutParams(width, height)
    }

    private inner class NewsAdapter internal constructor(var mArticleDTOs: Array<ArticleDTO>) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapter.ViewHolder {
            val attachToRoot = false
            val view = LayoutInflater.from(parent.context).inflate(R.layout.news_article_entry, parent, attachToRoot)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: NewsAdapter.ViewHolder, position: Int) =
                holder.setArticle(mArticleDTOs[position])

        override fun getItemCount(): Int = mArticleDTOs.size

        fun addArticles(articleDTOs: Array<ArticleDTO>) {
            mArticleDTOs += articleDTOs
        }

        internal inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            private val mTitle: TextView = view.bind(R.id.news_title)
            private val mDataCreated: TextView = view.bind(R.id.news_data_created)
            private val mThumbnail: ImageView = view.bind(R.id.news_thumbnail)
            private val newsElement: CardView = view.bind(R.id.news_element)

            fun setArticle(article: ArticleDTO) {
                mDataCreated.text = article.dataCreated
                mTitle.text = article.title
                Picasso.with(context).load(article.imgUrl).into(mThumbnail)
                newsElement.setOnClickListener { mPresenter!!.onArticleClick(article) }
            } //todo: add placeholder to picture
        }
    }
}
