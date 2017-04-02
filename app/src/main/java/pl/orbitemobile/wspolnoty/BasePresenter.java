package pl.orbitemobile.wspolnoty;

import android.view.MenuItem;

public interface BasePresenter {
    
    void start();
    
    void onViewCreated();
    
    void stop();
    
    boolean onOptionsItemSelected(final MenuItem item);
}
