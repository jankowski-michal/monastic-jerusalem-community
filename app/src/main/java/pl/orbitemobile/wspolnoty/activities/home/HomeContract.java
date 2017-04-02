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
    }
    
    interface Presenter extends BasePresenter {
        
        void onHoursButtonClick();
        
        void onWhereButtonClick();
        
        void onContactButtonClick();
        
        void onArticleClick(Article article);
        
        void onRetryClick();
    }
}
