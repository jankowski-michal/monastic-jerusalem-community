package pl.orbitemobile.wspolnoty.activities.article.domain;

import io.reactivex.Single;
import pl.orbitemobile.wspolnoty.data.RemoteRepository;
import pl.orbitemobile.wspolnoty.data.entities.Article;

public class ArticleScreen {
    
    private RemoteRepository mRemoteRepository;
    
    public ArticleScreen() {
        mRemoteRepository = new RemoteRepository();
    }
    
    public Single<Article> getRemoteArticleDetails(String articleDetailsUrl) {
        return mRemoteRepository.getArticleDetails(articleDetailsUrl);
    }
}