/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */
package pl.orbitemobile.wspolnoty.activities.home.logic;

import pl.orbitemobile.wspolnoty.R;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DialogBuilder {
    
    public Dialog showAboutDialog(final Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final View mAboutView = inflater.inflate(R.layout.about_content, null, false);
        builder.setView(mAboutView);
        final Dialog dialog = builder.show();
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                {
                    ((ViewGroup) mAboutView.getParent()).removeView(mAboutView);
                }
            }
        });
        
        setOepnUrl(mAboutView, context);
        setCloseLayout(mAboutView, dialog);
        return dialog;
    }
    
    private void setOepnUrl(final View mAboutView, final Context context) {
        View author_layout = mAboutView.findViewById(R.id.author_layout);
        author_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://" + context.getString(R.string.author_web)));
                context.startActivity(browserIntent);
            }
        });
    }
    
    private void setCloseLayout(final View mAboutView, final Dialog dialog) {
        View close_layout = mAboutView.findViewById(R.id.close_layout);
        close_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
}
