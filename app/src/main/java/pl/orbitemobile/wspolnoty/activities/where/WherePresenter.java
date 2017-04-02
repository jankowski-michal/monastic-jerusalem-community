/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */
package pl.orbitemobile.wspolnoty.activities.where;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

import pl.orbitemobile.wspolnoty.R;
import pl.orbitemobile.wspolnoty.utilities.DialogBuilder;
import pl.orbitemobile.wspolnoty.activities.where.logic.MapsIntentBuilder;

public class WherePresenter implements WhereContract.Presenter {
    
    private final WhereContract.View mView;
    
    private Context mContext;
    
    public WherePresenter(final WhereContract.View view, final Context context) {
        mView = view;
        mContext = context;
    }
    
    @Override
    public void onViewCreated() {
        
    }
    
    @Override
    public void start() {
        
    }
    
    @Override
    public void stop() {
        
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
    public void onMapClick() {
        MapsIntentBuilder mapsPresenter = new MapsIntentBuilder(mContext);
        Intent mapsIntent = mapsPresenter.getMapsIntent();
        mContext.startActivity(mapsIntent);
    }
}
