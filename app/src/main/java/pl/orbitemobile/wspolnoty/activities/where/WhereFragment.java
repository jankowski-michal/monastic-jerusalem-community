/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */

package pl.orbitemobile.wspolnoty.activities.where;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.orbitemobile.wspolnoty.R;

public class WhereFragment extends Fragment implements WhereContract.View {

    private static final String TAG = WhereFragment.class.getSimpleName();

    @BindView(R.id.map_layout)
    protected View mapLayout;

    private WhereContract.Presenter mPresenter;

    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.where_view, container, false);
        ButterKnife.bind(this, root);
        setOnClickListeners();
        return root;
    }

    @Override
    public void setPresenter(final WhereContract.Presenter presenter) {
        mPresenter = presenter;
    }

    private void setOnClickListeners() {
        mapLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                mPresenter.onMapClick();
            }
        });
    }
}
