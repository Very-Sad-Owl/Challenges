package android.projects.pidorsbizzareadventure.ui.info.show;

import android.projects.pidorsbizzareadventure.common.BasePresenter;
import android.projects.pidorsbizzareadventure.storage.local.ChallengesStorage;
import android.projects.pidorsbizzareadventure.storage.local.GettingCallback;
import android.util.Log;


public class InfoPresenter extends BasePresenter {

    private InfoView view;
    private ChallengesStorage storage;

    public InfoPresenter(InfoView view, ChallengesStorage storage) {
        this.view = view;
        this.storage = storage;
    }

    public void getData(final int pos) {
        storage.loadData(new GettingCallback() {
            @Override
            public void onSuccess() {
                Log.i("storage", storage.getChallenges().toString());
                view.showMetaInfo(storage.getChallenges().get(pos));
            }
        });
    }
}
