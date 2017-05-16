/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */

package pl.orbitemobile.wspolnoty.activities.news.domain;

import io.reactivex.Single;
import pl.orbitemobile.wspolnoty.data.RemoteRepository;
import pl.orbitemobile.wspolnoty.data.entities.Article;

public class NewsScreen {
    
    private RemoteRepository mRemoteRepository;
    
    public NewsScreen() {
        mRemoteRepository = new RemoteRepository();
    }
    
    public Single<Article[]> getRemoteArticles(int page) {
        return mRemoteRepository.getNews(page);
    }
    
}
