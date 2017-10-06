/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */

package pl.orbitemobile.wspolnoty.activities.contact

import android.view.View
import pl.orbitemobile.mvp.bind
import pl.orbitemobile.wspolnoty.R

class ContactView : ContactContract.View(R.layout.contact_view) {
    var phoneLayout: View? = null
    var mailLayout: View? = null
    var webLayout: View? = null

    override fun View.bindViews(): View {
        phoneLayout = bind(R.id.phone_layout)
        mailLayout = bind(R.id.mail_layout)
        webLayout = bind(R.id.web_layout)
        phoneLayout?.setOnClickListener { presenter?.onPhoneClick() }
        mailLayout?.setOnClickListener { presenter?.onMailClick() }
        webLayout?.setOnClickListener { presenter?.onWebsiteClick() }
        return this
    }
}
