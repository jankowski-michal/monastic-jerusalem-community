/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */
package pl.orbitemobile.wspolnoty.activities.contact

import android.content.Intent
import android.net.Uri
import io.reactivex.disposables.CompositeDisposable
import pl.orbitemobile.wspolnoty.R

class ContactPresenter(override var disposable: CompositeDisposable? = null,
                       override var view: ContactContract.View? = null) : ContactContract.Presenter {

    override fun onViewAttached() {}


    override fun onPhoneClick() {
        val callIntent = Intent(Intent.ACTION_DIAL)
        callIntent.data = Uri.parse("tel:" + view?.context?.getString(R.string.contact_phone))
        view?.context?.startActivity(callIntent)
    }

    override fun onMailClick() {
        val intent = Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto", view?.context?.getString(R.string.contact_mail), null))
        view?.context?.startActivity(Intent.createChooser(intent, "Choose an Email client :"))
    }

    override fun onWebsiteClick() {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("http://" + view?.context?.getString(R.string.contact_web)))
        view?.context?.startActivity(browserIntent)
    }
}
