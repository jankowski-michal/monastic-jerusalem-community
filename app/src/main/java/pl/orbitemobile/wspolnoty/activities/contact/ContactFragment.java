/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */

package pl.orbitemobile.wspolnoty.activities.contact;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.orbitemobile.wspolnoty.R;
import pl.orbitemobile.wspolnoty.activities.home.logic.AnalyticsLogger;
import pl.orbitemobile.wspolnoty.BaseApplication;

public class ContactFragment extends Fragment implements ContactContract.View {
    
    private static final String TAG = ContactFragment.class.getSimpleName();
    
    @BindView(R.id.phone_layout)
    protected View phoneLayout;
    
    @BindView(R.id.mail_layout)
    protected View mailLayout;
    
    @BindView(R.id.web_layout)
    protected View webLayout;
    
    private ContactContract.Presenter mPresenter;
    
    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.contact_view, container, false);
        ButterKnife.bind(this, root);
        setOnClickListeners();
        logAnalytics();
        return root;
    }
    
    @Override
    public void setPresenter(final ContactContract.Presenter presenter) {
        mPresenter = presenter;
    }
    
    private void logAnalytics() {
        AnalyticsLogger logger = new AnalyticsLogger();
        BaseApplication baseApplication = (BaseApplication) getActivity().getApplication();
        logger.LogAnalytics(TAG, baseApplication);
    }
    
    private void setOnClickListeners() {
        phoneLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                mPresenter.onPhoneClick();
            }
        });
        
        mailLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                mPresenter.onMailClick();
            }
        });
        
        webLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                mPresenter.onWebsiteClick();
            }
        });
    }
}
