/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */
package pl.orbitemobile.wspolnoty.utilities

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import pl.orbitemobile.wspolnoty.R

class AboutDialogBuilder private constructor(){
    companion object {
        val instance = AboutDialogBuilder()
    }

    fun showAboutDialog(context: Context): Dialog {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val builder = AlertDialog.Builder(context)
        val mAboutView = inflater.inflate(R.layout.about_content, null, false)
        builder.setView(mAboutView)
        val dialog = builder.show()
        dialog.setOnDismissListener(object : DialogInterface.OnDismissListener {
            override fun onDismiss(dialog: DialogInterface) {
                run { (mAboutView.parent as ViewGroup).removeView(mAboutView) }
            }
        })

        setOepnUrl(mAboutView, context)
        setCloseLayout(mAboutView, dialog)
        return dialog
    }

    private fun setOepnUrl(mAboutView: View, context: Context) {
        val author_layout = mAboutView.findViewById(R.id.author_layout)
        author_layout.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("http://" + context.getString(R.string.author_web)))
            context.startActivity(browserIntent)
        }
    }

    private fun setCloseLayout(mAboutView: View, dialog: Dialog) {
        val close_layout = mAboutView.findViewById(R.id.close_layout)
        close_layout.setOnClickListener { dialog.dismiss() }
    }
}
