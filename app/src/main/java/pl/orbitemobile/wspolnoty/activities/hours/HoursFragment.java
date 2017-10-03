/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */

package pl.orbitemobile.wspolnoty.activities.hours;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import pl.orbitemobile.wspolnoty.R;

public class HoursFragment extends Fragment implements HoursContract.View {

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.hours_view, container, false);
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void setPresenter(final HoursContract.Presenter presenter) {

    }
}
