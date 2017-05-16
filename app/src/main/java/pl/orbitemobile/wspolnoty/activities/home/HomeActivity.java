/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */
package pl.orbitemobile.wspolnoty.activities.home;

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

public class HomeActivity extends AppCompatActivity {
    
    @BindView(R.id.appbar_collapsing_image)
    protected ImageView appbarCollapsingImage;
    
    private HomeContract.Presenter mPresenter;
    
    private HomeFragment mHomeFragment;
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.appbar_collapsing_toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        appbarCollapsingImage.setImageResource(R.drawable.home_top);
        
        mHomeFragment = (HomeFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (mHomeFragment == null) {
            mHomeFragment = new HomeFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.fragment_container, mHomeFragment);
            transaction.commit();
        }
        mPresenter = new HomePresenter(mHomeFragment, this);
        mHomeFragment.setPresenter(mPresenter);
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
}
