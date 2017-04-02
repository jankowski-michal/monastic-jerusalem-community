package pl.orbitemobile.wspolnoty.activities.article;

import android.widget.ImageView;

import pl.orbitemobile.wspolnoty.BasePresenter;
import pl.orbitemobile.wspolnoty.BaseView;

public class ArticleContract {
    
    interface View extends BaseView<Presenter> {
        
        void setDescritpion(String description);
        
        void setTitle(String title);
        
        void setImgUrl(String imgUrl);
        
        void setAppbarCollapsingImage(final ImageView appbarCollapsingImage);
        
        void showErrorMessage();
        
        void showLoadingScreen();
        
        void showArticleDetails();
    }
    
    interface Presenter extends BasePresenter {
        
        void onRetryClick();
    }
}
