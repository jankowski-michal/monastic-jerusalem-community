package pl.orbitemobile.wspolnoty.activities.home.domain;

import io.reactivex.Single;
import pl.orbitemobile.wspolnoty.data.RemoteRepository;
import pl.orbitemobile.wspolnoty.data.entities.Article;

public class HomeScreen {
    
    private RemoteRepository mRemoteRepository;
    
    public HomeScreen() {
        mRemoteRepository = new RemoteRepository();
    }
    
    public Single<Article[]> getRemoteArticles() {
        return mRemoteRepository.getArticles();
    }
}
