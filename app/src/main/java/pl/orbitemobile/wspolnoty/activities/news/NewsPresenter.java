/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */
package pl.orbitemobile.wspolnoty.activities.news;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.MenuItem;

import io.reactivex.SingleObserver;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import pl.orbitemobile.wspolnoty.R;
import pl.orbitemobile.wspolnoty.activities.article.ArticleActivity;
import pl.orbitemobile.wspolnoty.activities.article.ArticlePresenter;
import pl.orbitemobile.wspolnoty.activities.news.domain.NewsScreen;
import pl.orbitemobile.wspolnoty.data.entities.Article;
import pl.orbitemobile.wspolnoty.utilities.ConnectivityCheck;
import pl.orbitemobile.wspolnoty.utilities.DialogBuilder;

public class NewsPresenter implements NewsContract.Presenter {

    private static final String TAG = NewsPresenter.class.getSimpleName();

    private static final int FIRST_PAGE = 1;

    private final SingleObserver<Article[]> mNewsRemoteObserver = new NewsRemoteObserver();

    private NewsContract.View mView;

    private Context mContext;

    private NewsScreen mUseCase;

    private CompositeDisposable mDisposable = new CompositeDisposable();

    private ConnectivityCheck mConnectivityCheck;

    private int page;

    public NewsPresenter(final NewsContract.View view, final Context context) {
        mView = view;
        mContext = context;
        mUseCase = new NewsScreen();
        page = FIRST_PAGE;
        mConnectivityCheck = new ConnectivityCheck(mContext);
    }

    @Override
    public void onViewCreated() {
    }

    @Override
    public void start() {
        mUseCase.getRemoteArticles(page).subscribe(mNewsRemoteObserver);
    }

    @Override
    public void stop() {
        mDisposable.clear();
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        int itemThatWasClickedId = item.getItemId();
        if (itemThatWasClickedId == R.id.about_app) {
            DialogBuilder builder = new DialogBuilder();
            builder.showAboutDialog(mContext);
            return true;
        }
        return false;
    }

    @Override
    public void onRetryClick() {
        if (mConnectivityCheck.isNetworkAvailable()) {
            mView.showLoadingScreen();
            mUseCase.getRemoteArticles(page).subscribe(mNewsRemoteObserver);
        } else {
            mView.showNetworkToast();
        }
    }

    @Override
    public void onArticleClick(final Article article) {
        Intent intent = new Intent(mContext, ArticleActivity.class);
        intent.putExtra(ArticlePresenter.ARTICLE_URL, article.getArticleUrl());
        intent.putExtra(ArticlePresenter.TITLE, article.getTitle());
        intent.putExtra(ArticlePresenter.IMG_URL, article.getImgUrl());

        mContext.startActivity(intent);
    }

    @Override
    public void onShowMore() {
        page += 1;
        mView.showLoadingScreen();
        mUseCase.getRemoteArticles(page).subscribe(mNewsRemoteObserver);
    }

    private class NewsRemoteObserver implements SingleObserver<Article[]> {

        @Override
        public void onSubscribe(@NonNull final Disposable d) {
            mDisposable.add(d);
        }

        @Override
        public void onError(Throwable e) {
            mView.showErrorMessage();
            Log.d(TAG, "onError");
            e.printStackTrace();
        }

        @Override
        public void onSuccess(@NonNull final Article[] articles) {
            Log.d(TAG, "onSuccess - receives articles: " + articles.length);
            mView.showArticles(articles);
        }
    }
}
