/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */
package pl.orbitemobile.wspolnoty.activities.home;

import pl.orbitemobile.wspolnoty.BasePresenter;
import pl.orbitemobile.wspolnoty.BaseView;
import pl.orbitemobile.wspolnoty.data.entities.Article;

public class HomeContract {
    
    interface View extends BaseView<Presenter> {
        
        void showLoadingScreen();
        
        void showErrorMessage();
        
        void setTodaysMass(String content);
        
        void showArticles(Article[] articles);
        
        void showNetworkToast();
    }
    
    interface Presenter extends BasePresenter {
        
        void onHoursButtonClick();
        
        void onWhereButtonClick();
        
        void onContactButtonClick();
        
        void onNewsButtonclick();
        
        void onArticleClick(Article article);
        
        void onRetryClick();
    }
}
