package pl.orbitemobile.wspolnoty.activities.where;

import android.view.MenuItem;

import pl.orbitemobile.wspolnoty.BasePresenter;
import pl.orbitemobile.wspolnoty.BaseView;

public class WhereContract {
    
    interface View extends BaseView<Presenter> {
        
    }
    
    interface Presenter extends BasePresenter {
        
        boolean onOptionsItemSelected(final MenuItem item);
        
        void onMapClick();
    }
}
