package android.projects.pidorsbizzareadventure.ui.conditionsList;

import android.projects.pidorsbizzareadventure.common.BasePresenter;
import android.projects.pidorsbizzareadventure.storage.local.ChallengesStorage;
import android.projects.pidorsbizzareadventure.storage.local.GettingCallback;
import android.util.Log;

public class ConditionsListPresenter extends BasePresenter {

    private ConditionsListView view;
    private ChallengesStorage storage;

    public ConditionsListPresenter(ConditionsListView view, ChallengesStorage storage){
        this.view = view;
        this.storage = storage;
    }

    public void getData(final int pos) {
        storage.loadData(new GettingCallback() {
            @Override
            public void onSuccess() {
                Log.i("storage", storage.getChallenges().toString());
                view.showData(storage.getChallenges().get(pos).getConditions());
            }
        });
    }

    public void applyChanges(int pos){
        storage.updateChallenge(storage.getChallenges().get(pos));
    }
}
