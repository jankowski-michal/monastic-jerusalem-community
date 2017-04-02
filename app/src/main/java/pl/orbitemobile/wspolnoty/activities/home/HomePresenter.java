package pl.orbitemobile.wspolnoty.activities.home;

import pl.orbitemobile.wspolnoty.activities.article.ArticlePresenter;
import pl.orbitemobile.wspolnoty.activities.contact.ContactActivity;
import pl.orbitemobile.wspolnoty.activities.home.domain.HomeScreen;
import pl.orbitemobile.wspolnoty.activities.home.logic.TodaysMassCalculator;
import pl.orbitemobile.wspolnoty.activities.hours.HoursActivity;
import pl.orbitemobile.wspolnoty.R;
import pl.orbitemobile.wspolnoty.activities.article.ArticleActivity;
import pl.orbitemobile.wspolnoty.activities.home.logic.DialogBuilder;
import pl.orbitemobile.wspolnoty.activities.where.WhereActivity;
import pl.orbitemobile.wspolnoty.data.entities.Article;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

import io.reactivex.SingleObserver;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class HomePresenter implements HomeContract.Presenter {
    
    private final HomeContract.View mView;
    
    private final SingleObserver<Article[]> mArticlesRemoteObserver = new ArticlesRemoteObserver();
    
    private CompositeDisposable mDisposable = new CompositeDisposable();
    
    private Context mContext;
    
    private HomeScreen mUseCase;
    
    HomePresenter(HomeContract.View view, Context context) {
        mView = view;
        mContext = context;
        mUseCase = new HomeScreen();
    }
    
    @Override
    public void onViewCreated() {
        setTodaysMassTimes();
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
        mUseCase.getRemoteArticles().subscribe(mArticlesRemoteObserver);
        mView.showLoadingScreen();
    }
    
    @Override
    public void start() {
        mUseCase.getRemoteArticles().subscribe(mArticlesRemoteObserver);
    }
    
    @Override
    public void stop() {
        mDisposable.clear();
    }
    
    public void onHoursButtonClick() {
        startActivity(HoursActivity.class);
    }
    
    public void onWhereButtonClick() {
        startActivity(WhereActivity.class);
    }
    
    public void onContactButtonClick() {
        startActivity(ContactActivity.class);
    }
    
    public void onArticleClick(Article article) {
        Intent intent = new Intent(mContext, ArticleActivity.class);
        intent.putExtra(ArticlePresenter.ARTICLE_URL, article.getArticleUrl());
        intent.putExtra(ArticlePresenter.TITLE, article.getTitle());
        intent.putExtra(ArticlePresenter.IMG_URL, article.getImgUrl());
        
        mContext.startActivity(intent);
    }
    
    private void startActivity(Class nextActivityClass) {
        Intent intent = new Intent(mContext, nextActivityClass);
        mContext.startActivity(intent);
    }
    
    private void setTodaysMassTimes() {
        TodaysMassCalculator todaysMassCalculator = new TodaysMassCalculator();
        String todays_mass = todaysMassCalculator.calculateTodaysMass();
        mView.setTodaysMass(todays_mass);
    }
    
    private class ArticlesRemoteObserver implements SingleObserver<Article[]> {
        
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
        public void onSuccess(@NonNull final Article[] articles) {
            mView.showArticles(articles);
        }
    }
}
