package android.projects.pidorsbizzareadventure.ui.info.show;

import android.projects.pidorsbizzareadventure.common.BasePresenter;
import android.projects.pidorsbizzareadventure.pojo.Zaruba;
import android.projects.pidorsbizzareadventure.storage.local.ChallengesStorage;
import android.projects.pidorsbizzareadventure.storage.local.GettingCallback;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;


public class InfoPresenter extends BasePresenter {

    private InfoView view;
    private Zaruba currentChallenge;

    public InfoPresenter(InfoView view, Zaruba currentChallenge) {
        this.view = view;
        this.currentChallenge = currentChallenge;
    }

    public void setCurrentChallenge(Zaruba currentChallenge) {
        this.currentChallenge = currentChallenge;
    }

    public void showMetaInfo(){
        view.showMetaInfo(currentChallenge);
    }

//    public void getData(final int pos) {
//        storage.loadData(new GettingCallback() {
//            @Override
//            public void onSuccess() {
//                Log.i("storage", storage.getChallenges().toString());
//                view.showMetaInfo(storage.getChallenges().get(pos));
//            }
//        });
//    }


}
