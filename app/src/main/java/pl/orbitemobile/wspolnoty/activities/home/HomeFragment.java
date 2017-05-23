/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */

package pl.orbitemobile.wspolnoty.activities.home;

import com.squareup.picasso.Picasso;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.orbitemobile.wspolnoty.BaseApplication;
import pl.orbitemobile.wspolnoty.R;
import pl.orbitemobile.wspolnoty.utilities.AnalyticsLogger;
import pl.orbitemobile.wspolnoty.data.entities.Article;

public class HomeFragment extends Fragment implements HomeContract.View {
    
    private static final String TAG = HomeFragment.class.getSimpleName();
    
    @BindView(R.id.where_layout)
    protected TextView whereLayout;
    
    @BindView(R.id.contact_layout)
    protected TextView contactLayout;
    
    @BindView(R.id.hours_layout)
    protected TextView hoursLayout;
    
    @BindView(R.id.todays_mass_text)
    protected TextView mTodaysMass;
    
    @BindView(R.id.progress_bar)
    protected View mProgressBar;
    
    @BindView(R.id.error_layout)
    protected LinearLayout mErrorLayout;
    
    @BindView(R.id.view_pager)
    protected ViewPager mEventsViewPager;
    
    @BindView(R.id.error_button)
    protected TextView mErrorButton;
    
    @BindView(R.id.news_layout)
    protected TextView mNewsButton;
    
    private HomeContract.Presenter mPresenter;
    
    private EventsAdapter mEventsAdapter;
    
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.home_view, container, false);
        ButterKnife.bind(this, root);
        showLoadingScreen();
        logAnalytics();
        return root;
    }
    
    
    

    
    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.onViewCreated();
        contactLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.onContactButtonClick();
            }
        });
        whereLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.onWhereButtonClick();
            }
        });
        hoursLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.onHoursButtonClick();
            }
        });
        mNewsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                mPresenter.onNewsButtonClick();
            }
        });
    }
    
    @Override
    public void setPresenter(final HomeContract.Presenter presenter) {
        mPresenter = presenter;
    }
    
    
    @Override
    public boolean showNetworkToast() {
        Toast.makeText(getContext(), getContext().getString(R.string.no_network_message), Toast.LENGTH_LONG).show();
        return true;
    }
    @Override
    public void showLoadingScreen() {
        mProgressBar.setVisibility(View.VISIBLE);
        mEventsViewPager.setVisibility(View.GONE);
        mErrorLayout.setVisibility(View.GONE);
    }
    
    @Override
    public void showErrorMessage() {
        mProgressBar.setVisibility(View.GONE);
        mEventsViewPager.setVisibility(View.GONE);
        mErrorLayout.setVisibility(View.VISIBLE);
        mErrorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                mPresenter.onRetryClick();
            }
        });
    }
    
    @Override
    public void setTodaysMass(String content) {
        mTodaysMass.setText(content);
    }
    
    @Override
    public void showArticles(final Article[] articles) {
        mEventsViewPager.setVisibility(View.VISIBLE);
        mEventsAdapter = new EventsAdapter(articles);
        mEventsViewPager.setAdapter(mEventsAdapter);
        mProgressBar.setVisibility(View.GONE);
        mErrorLayout.setVisibility(View.GONE);
    }
    
    private void logAnalytics() {
        AnalyticsLogger logger = new AnalyticsLogger();
        BaseApplication baseApplication = (BaseApplication) getActivity().getApplication();
        logger.LogAnalytics(TAG, baseApplication);
    }
    
    private class EventsAdapter extends PagerAdapter {
        
        private Article[] mArticles;
        
        public EventsAdapter(Article[] articles) {
            mArticles = articles;
        }
        
        @Override
        public Object instantiateItem(ViewGroup collection, final int position) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.home_event_entry, collection, false);
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPresenter.onArticleClick(mArticles[position]);
                }
            });
            collection.addView(layout);
            setPageContent(position, layout);
            return layout;
        }
        
        @Override
        public int getItemPosition(Object object) {
            return PagerAdapter.POSITION_NONE;
        }
        
        @Override
        public void destroyItem(ViewGroup collection, int position, Object view) {
            collection.removeView((View) view);
        }
        
        @Override
        public int getCount() {
            return mArticles.length;
        }
        
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
        
        @Override
        public CharSequence getPageTitle(int position) {
            return mArticles[position].getTitle();
        }
        
        private void setPageContent(int position, ViewGroup layout) {
            Article article = mArticles[position];
            TextView textView = (TextView) layout.findViewById(R.id.textView);
            textView.setText(article.getTitle());
            TextView pagination = (TextView) layout.findViewById(R.id.pagination);
            pagination.setText(pagination(position));
            ImageView thumbnail = (ImageView) layout.findViewById(R.id.short_event_image);
            
            Picasso.with(getContext())
                    .load(article.getImgUrl())
                    .into(thumbnail);
        }
        
        private String pagination(int position) {
            int positionToDisplay = position + 1;
            return " " + positionToDisplay + " / " + mArticles.length + " ";
        }
    }
}
