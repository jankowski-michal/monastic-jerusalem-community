/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */
package pl.orbitemobile.wspolnoty.activities.article;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

import io.reactivex.SingleObserver;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import pl.orbitemobile.wspolnoty.R;
import pl.orbitemobile.wspolnoty.activities.article.domain.ArticleScreen;
import pl.orbitemobile.wspolnoty.utilities.ConnectivityCheck;
import pl.orbitemobile.wspolnoty.utilities.DialogBuilder;
import pl.orbitemobile.wspolnoty.data.entities.Article;

public class ArticlePresenter implements ArticleContract.Presenter {
    
    public static final String ARTICLE_URL = "article_url";
    
    public static final String TITLE = "title";
    
    public static final String IMG_URL = "img_url";
    
    private final SingleObserver<Article> mArticleRemoteObserver = new ArticleRemoteObserver();
    
    private ArticleContract.View mView;
    
    private Context mContext;
    
    private ArticleScreen mUseCase;
    
    private CompositeDisposable mDisposable = new CompositeDisposable();
    
    private String mArticleUrl;
    
    private ConnectivityCheck mConnectivityCheck;
    
    public ArticlePresenter(final ArticleContract.View view, final Context context, Intent intent) {
        mView = view;
        mContext = context;
        mUseCase = new ArticleScreen();
        mArticleUrl = getArticleUrl(intent);
        mConnectivityCheck = new ConnectivityCheck(mContext);
    }
    
    @Override
    public void onViewCreated() {
        mView.showLoadingScreen();
    }
    
    @Override
    public void start() {
        mUseCase.getRemoteArticleDetails(mArticleUrl).subscribe(mArticleRemoteObserver);
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
            mUseCase.getRemoteArticleDetails(mArticleUrl).subscribe(mArticleRemoteObserver);
            mView.showLoadingScreen();
        } else {
            mView.showNetworkToast();
        }
    }
    
    private String getArticleUrl(Intent intent) {
        if (intent.hasExtra(ARTICLE_URL)) {
            return intent.getStringExtra(ARTICLE_URL);
        } else {
            mView.showErrorMessage();
            return "";
        }
    }
    
    private class ArticleRemoteObserver implements SingleObserver<Article> {
        
        @Override
        public void onSubscribe(@NonNull final Disposable d) {
            mDisposable.add(d);
        }
        
        @Override
        public void onError(Throwable e) {
            mView.showErrorMessage();
            e.printStackTrace();
        }
        
        @Override
        public void onSuccess(@NonNull final Article article) {
            mView.setImgUrl(article.getImgUrl());
            mView.setTitle(article.getTitle());
            mView.setDescritpion(article.getDescription());
            mView.showArticleDetails();
        }
    }
}
