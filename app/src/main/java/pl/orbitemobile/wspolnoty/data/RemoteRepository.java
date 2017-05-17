/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */

package pl.orbitemobile.wspolnoty.data;

import org.jsoup.Connection;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import pl.orbitemobile.wspolnoty.data.entities.Article;
import pl.orbitemobile.wspolnoty.data.remote.Downloader;
import pl.orbitemobile.wspolnoty.data.remote.Mapper;

public class RemoteRepository {
    
    private static final String HOMEPAGE = "http://wspolnoty-jerozolimskie.pl/";
    
    private static final String NEWS = "http://wspolnoty-jerozolimskie.pl/category/aktualnosci/page/";
    
    private Downloader mDownloader;
    
    private Mapper mMapper;
    
    @Inject
    public RemoteRepository() {
        mMapper = new Mapper();
        mDownloader = new Downloader();
    }
    
    public Single<Article[]> getArticles() {
        return Single.create(new SingleOnSubscribe<Article[]>() {
            @Override
            public void subscribe(final SingleEmitter<Article[]> e) throws Exception {
                Connection.Response response = mDownloader.getResponse(HOMEPAGE);
                Article[] articles = mMapper.mapArticles(response);
                e.onSuccess(articles);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    
    public Single<Article[]> getNews(final int page) {
        return Single.create(new SingleOnSubscribe<Article[]>() {
            @Override
            public void subscribe(final SingleEmitter<Article[]> e) throws Exception {
                Connection.Response response = mDownloader.getResponse(NEWS + page);
                Article[] articles = mMapper.mapNews(response);
                e.onSuccess(articles);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    
    public Single<Article> getArticleDetails(final String eventUrl) {
        return Single.create(new SingleOnSubscribe<Article>() {
            @Override
            public void subscribe(final SingleEmitter<Article> e) throws Exception {
                Connection.Response response = mDownloader.getResponse(eventUrl);
                Article article = mMapper.mapArticle(response);
                e.onSuccess(article);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
