/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */
package pl.orbitemobile.wspolnoty.activities.news;

import pl.orbitemobile.wspolnoty.BasePresenter;
import pl.orbitemobile.wspolnoty.BaseView;
import pl.orbitemobile.wspolnoty.data.entities.Article;

public class NewsContract {
    
    interface View extends BaseView<Presenter> {
        
        void showErrorMessage();
        
        void showLoadingScreen();
        
        void showArticles(Article[] articles);
        
        void showNetworkToast();
    }
    
    interface Presenter extends BasePresenter {
        
        void onRetryClick();
        
        void onArticleClick(Article article);
        
        void onShowMore();
    }
}
