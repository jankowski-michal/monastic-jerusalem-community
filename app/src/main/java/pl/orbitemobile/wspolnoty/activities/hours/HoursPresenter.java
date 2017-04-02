package pl.orbitemobile.wspolnoty.activities.hours;

import android.content.Context;
import android.view.MenuItem;

import pl.orbitemobile.wspolnoty.R;
import pl.orbitemobile.wspolnoty.activities.home.logic.DialogBuilder;

public class HoursPresenter implements HoursContract.Presenter {
    
    private HoursContract.View mView;
    
    private Context mContext;
    
    public HoursPresenter(final HoursContract.View view, final Context context) {
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
}
