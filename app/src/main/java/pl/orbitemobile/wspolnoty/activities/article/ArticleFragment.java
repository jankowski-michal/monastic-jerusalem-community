/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */

package pl.orbitemobile.wspolnoty.activities.article;

import com.squareup.picasso.Picasso;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

public class ArticleFragment extends Fragment implements ArticleContract.View {
    
    private static final String TAG = ArticleFragment.class.getSimpleName();
    
    @BindView(R.id.article_title)
    protected TextView articleTitle;
    
    @BindView(R.id.article_description)
    protected TextView articleDescription;
    
    @BindView(R.id.progress_bar)
    protected View mProgressBar;
    
    @BindView(R.id.error_layout)
    protected LinearLayout mErrorLayout;
    
    @BindView(R.id.error_button)
    protected TextView mErrorButton;
    
    @BindView(R.id.article_layout)
    protected LinearLayout mArticleLayout;
    
    private ImageView appbarCollapsingImage;
    
    private ArticleContract.Presenter mPresenter;
    
    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.article_view, container, false);
        ButterKnife.bind(this, root);
        logAnalytics();
        return root;
    }
    
    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.onViewCreated();
    }
    
    @Override
    public void setAppbarCollapsingImage(final ImageView appbarCollapsingImage) {
        this.appbarCollapsingImage = appbarCollapsingImage;
    }
    
    @Override
    public void showErrorMessage() {
        mProgressBar.setVisibility(View.GONE);
        mArticleLayout.setVisibility(View.GONE);
        mErrorLayout.setVisibility(View.VISIBLE);
        mErrorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                mPresenter.onRetryClick();
            }
        });
    }
    
    @Override
    public void showLoadingScreen() {
        mArticleLayout.setVisibility(View.GONE);
        mErrorLayout.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.VISIBLE);
    }
    
    @Override
    public void showNetworkToast() {
        Toast.makeText(getContext(), getContext().getString(R.string.no_network_message), Toast.LENGTH_LONG).show();
    }
    
    @Override
    public void showArticleDetails() {
        mErrorLayout.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.GONE);
        mArticleLayout.setVisibility(View.VISIBLE);
    }
    
    @Override
    public void setPresenter(final ArticleContract.Presenter presenter) {
        mPresenter = presenter;
    }
    
    @Override
    public void setDescritpion(final String description) {
        articleDescription.setText(description);
    }
    
    @Override
    public void setTitle(final String title) {
        articleTitle.setText(title);
    }
    
    @Override
    public void setImgUrl(final String imgUrl) {
        Picasso.with(getContext())
                .load(imgUrl)
                .into(appbarCollapsingImage);
    }
    
    private void logAnalytics() {
        AnalyticsLogger logger = new AnalyticsLogger();
        BaseApplication baseApplication = (BaseApplication) getActivity().getApplication();
        logger.LogAnalytics(TAG, baseApplication);
    }
}
