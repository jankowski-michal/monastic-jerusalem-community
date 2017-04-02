/*
 * Copyright (C) 2017 Michał Jankowski.
 * www.michaeljankowski.com - michael.jankowski.com@gmail.com
 * All Rights Reserved.
 */
package pl.orbitemobile.wspolnoty.activities.article;

import pl.orbitemobile.wspolnoty.R;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArticleActivity extends AppCompatActivity {
    
    @BindView(R.id.appbar_collapsing_image)
    protected ImageView appbarCollapsingImage;
    
    private ArticleContract.Presenter mPresenter;
    
    private ArticleFragment mArticleFragment;
    
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.appbar_collapsing_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(this);
        appbarCollapsingImage.setImageResource(R.drawable.home_top);
        
        mArticleFragment = (ArticleFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (mArticleFragment == null) {
            //todo: check if this should be added once again
            mArticleFragment = new ArticleFragment();
            mArticleFragment.setAppbarCollapsingImage(appbarCollapsingImage);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.fragment_container, mArticleFragment);
            transaction.commit();
        }
        mPresenter = new ArticlePresenter(mArticleFragment, this, getIntent());
        mArticleFragment.setPresenter(mPresenter);
        mPresenter.start();
    }
    
    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    
    @Override
    protected void onDestroy() {
        mPresenter.stop();
        super.onDestroy();
    }
    
    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        return mPresenter.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }
    
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}