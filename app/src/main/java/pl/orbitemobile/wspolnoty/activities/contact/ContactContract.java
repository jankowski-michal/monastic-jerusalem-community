/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */

package pl.orbitemobile.wspolnoty.activities.contact;

import pl.orbitemobile.wspolnoty.BasePresenter;
import pl.orbitemobile.wspolnoty.BaseView;

public class ContactContract {
    
    interface View extends BaseView<Presenter> {
        
    }
    
    interface Presenter extends BasePresenter {
        
        void onPhoneClick();
        
        void onMailClick();
        
        void onWebsiteClick();
    }
}
