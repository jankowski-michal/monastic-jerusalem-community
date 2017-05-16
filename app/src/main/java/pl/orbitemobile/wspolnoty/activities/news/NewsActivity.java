/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */
package pl.orbitemobile.wspolnoty.activities.news;

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
import pl.orbitemobile.wspolnoty.R;

public class NewsActivity extends AppCompatActivity {
    
    @BindView(R.id.appbar_collapsing_image)
    protected ImageView appbarCollapsingImage;
    
    private NewsContract.Presenter mPresenter;
    
    private NewsFragment mNewsFragment;
    
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.appbar_collapsing_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(this);
        appbarCollapsingImage.setImageResource(R.drawable.news_top);
        
        mNewsFragment = (NewsFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (mNewsFragment == null) {
            mNewsFragment = new NewsFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.fragment_container, mNewsFragment);
            transaction.commit();
        }
        mPresenter = new NewsPresenter(mNewsFragment, this);
        mNewsFragment.setPresenter(mPresenter);
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
