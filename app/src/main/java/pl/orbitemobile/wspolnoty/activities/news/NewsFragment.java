/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */
package pl.orbitemobile.wspolnoty.activities.news;

import com.squareup.picasso.Picasso;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.orbitemobile.wspolnoty.BaseApplication;
import pl.orbitemobile.wspolnoty.R;
import pl.orbitemobile.wspolnoty.activities.home.logic.AnalyticsLogger;
import pl.orbitemobile.wspolnoty.data.entities.Article;

public class NewsFragment extends Fragment implements NewsContract.View {
    
    private static final String TAG = NewsFragment.class.getSimpleName();
    
    @BindView(R.id.news_frame_layout)
    protected FrameLayout mFrameLayout;
    
    @BindView(R.id.progress_bar)
    protected View mProgressBar;
    
    @BindView(R.id.error_layout)
    protected LinearLayout mErrorLayout;
    
    @BindView(R.id.error_button)
    protected TextView mErrorButton;
    
    @BindView(R.id.news_recycler_view)
    protected RecyclerView mRecyclerView;
    
    @BindView(R.id.load_more_news)
    protected TextView mLoadMoreNews;
    
    private NewsContract.Presenter mPresenter;
    
    private NewsAdapter mAdapter;
    
    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.news_view, container, false);
        ButterKnife.bind(this, root);
        logAnalytics();
        showLoadingScreen();
        return root;
    }
    
    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setRecyclerViewMatchScreenSize();
        mPresenter.onViewCreated();
    }
    
    @Override
    public void setPresenter(final NewsContract.Presenter presenter) {
        mPresenter = presenter;
    }
    
    @Override
    public void showErrorMessage() {
        Log.d(TAG, "showErrorMessage");
        mErrorLayout.setVisibility(View.VISIBLE);
        mErrorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                mPresenter.onRetryClick();
            }
        });
        mProgressBar.setVisibility(View.GONE);
        mLoadMoreNews.setVisibility(View.GONE);
    }
    
    @Override
    public void showLoadingScreen() {
        Log.d(TAG, "showLoadingScreen");
        mProgressBar.setVisibility(View.VISIBLE);
        mErrorLayout.setVisibility(View.GONE);
        mLoadMoreNews.setVisibility(View.GONE);
    }
    
    @Override
    public void showArticles(final Article[] articles) {
        Log.d(TAG, "showArticles");
        mLoadMoreNews.setVisibility(View.VISIBLE);
        mLoadMoreNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                mPresenter.onShowMore();
            }
        });
        mProgressBar.setVisibility(View.GONE);
        mErrorLayout.setVisibility(View.GONE);
        
        setRecyclerArticles(articles);
    }
    
    @Override
    public void showNetworkToast() {
        Toast.makeText(getContext(), getContext().getString(R.string.no_network_message), Toast.LENGTH_LONG).show();
    }
    
    private void setRecyclerArticles(Article[] articles) {
        if (mAdapter == null) {
            initNewAdapter(articles);
        } else {
            mAdapter.addArticles(articles);
            mAdapter.notifyDataSetChanged();
        }
    }
    
    private void initNewAdapter(final Article[] articles) {
        final GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 1);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new NewsAdapter(articles);
        mRecyclerView.setAdapter(mAdapter);
    }
    
    private void setRecyclerViewMatchScreenSize() {
        int height = mFrameLayout.getLayoutParams().height;
        int width = mFrameLayout.getLayoutParams().width;
        mRecyclerView.setLayoutParams(new LinearLayout.LayoutParams(width, height));
    }
    
    private void logAnalytics() {
        AnalyticsLogger logger = new AnalyticsLogger();
        BaseApplication baseApplication = (BaseApplication) getActivity().getApplication();
        logger.LogAnalytics(TAG, baseApplication);
    }
    
    private class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
        
        private List<Article> mArticles = new ArrayList<>();
        
        NewsAdapter(Article[] articles) {
            addArticles(articles);
        }
        
        @Override
        public NewsAdapter.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
            final boolean attachToRoot = false;
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_article_entry, parent, attachToRoot);
            return new ViewHolder(view);
        }
        
        @Override
        public void onBindViewHolder(final NewsAdapter.ViewHolder holder, final int position) {
            Log.d(TAG, "onBindViewHolder, position: " + position);
            final Article article = mArticles.get(position);
            holder.setImage(article.getImgUrl());
            holder.setTitle(article.getTitle());
            holder.setDataCreated(article.getDataCreated());
            holder.getNewsElement().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    mPresenter.onArticleClick(article);
                }
            });
        }
        
        @Override
        public int getItemCount() {
            return mArticles.size();
        }
        
        public void addArticles(Article[] articles) {
            for (Article article : articles) {
                mArticles.add(article);
            }
        }
        
        class ViewHolder extends RecyclerView.ViewHolder {
            
            private final TextView mTitle;
            
            private final TextView mDataCreated;
            
            private final ImageView mThumbnail;
            
            private final CardView mNewsElement;
            
            ViewHolder(final View view) {
                super(view);
                mThumbnail = (ImageView) view.findViewById(R.id.news_thumbnail);
                mTitle = (TextView) view.findViewById(R.id.news_title);
                mNewsElement = (CardView) view.findViewById(R.id.news_element);
                mDataCreated = (TextView) view.findViewById(R.id.news_data_created);
            }
            
            CardView getNewsElement() {
                return mNewsElement;
            }
            
            void setTitle(String title) {
                mTitle.setText(title);
            }
            
            void setDataCreated(String dataCreated) {
                mDataCreated.setText(dataCreated);
            }
            
            void setImage(String imgUrl) {
                Picasso.with(getContext())
                        .load(imgUrl)
                        .into(mThumbnail);
            }
        }
    }
}
