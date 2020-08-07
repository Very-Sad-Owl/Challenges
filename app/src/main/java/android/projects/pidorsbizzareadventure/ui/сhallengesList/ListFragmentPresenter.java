package android.projects.pidorsbizzareadventure.ui.сhallengesList;

import android.projects.pidorsbizzareadventure.common.BasePresenter;
import android.projects.pidorsbizzareadventure.pojo.Zaruba;
import android.projects.pidorsbizzareadventure.storage.local.ChallengesStorage;
import android.projects.pidorsbizzareadventure.storage.local.GettingCallback;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class ListFragmentPresenter extends BasePresenter {

    private ListView view;
    private ChallengesStorage storage;

    public ListFragmentPresenter(ListView view, ChallengesStorage storage) {
        this.view = view;
        this.storage = storage;
    }

    public void loadData() {
        storage.loadData(new GettingCallback() {
            @Override
            public void onSuccess() {
                Log.i("storage", storage.getChallenges().toString());
                view.showData(storage.getChallenges());
            }
        });
    }

    public void getData(){
        view.showData(storage.getChallenges());
    }
}
