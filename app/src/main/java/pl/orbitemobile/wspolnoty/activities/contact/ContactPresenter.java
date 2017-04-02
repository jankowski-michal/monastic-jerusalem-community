/*
 * Copyright (C) 2017 Michał Jankowski.
 * www.michaeljankowski.com - michael.jankowski.com@gmail.com
 * All Rights Reserved.
 */
package pl.orbitemobile.wspolnoty.activities.contact;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.MenuItem;

import pl.orbitemobile.wspolnoty.R;
import pl.orbitemobile.wspolnoty.activities.home.logic.DialogBuilder;

public class ContactPresenter implements ContactContract.Presenter {
    
    private final ContactContract.View mView;
    
    private final Context mContext;
    
    public ContactPresenter(final ContactContract.View view, final Context context) {
        mView = view;
        mContext = context;
    }
    
    @Override
    public void onViewCreated() {
        //do nothing
    }
    
    @Override
    public void start() {
        // do nothing
    }
    
    @Override
    public void stop() {
        // do nothing
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
    
    public void onPhoneClick() {
        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:" + mContext.getString(R.string.contact_phone)));
        mContext.startActivity(callIntent);
    }
    
    public void onMailClick() {
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto", mContext.getString(R.string.contact_mail), null));
        mContext.startActivity(Intent.createChooser(intent, "Choose an Email client :"));
    }
    
    public void onWebsiteClick() {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://" + mContext.getString(R.string.contact_web)));
        mContext.startActivity(browserIntent);
    }
}