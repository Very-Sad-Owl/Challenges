package android.projects.pidorsbizzareadventure.ui.conditionsList;

import android.projects.pidorsbizzareadventure.common.BasePresenter;
import android.projects.pidorsbizzareadventure.common.BasePresenterForAdapter;
import android.projects.pidorsbizzareadventure.common.BaseViewForAdapter;
import android.projects.pidorsbizzareadventure.pojo.Condition;
import android.projects.pidorsbizzareadventure.pojo.Zaruba;
import android.projects.pidorsbizzareadventure.storage.local.ChallengesStorage;
import android.projects.pidorsbizzareadventure.storage.local.GettingCallback;
import android.util.Log;

public class ConditionsListPresenter extends BasePresenterForAdapter {

    private ConditionsListView view;
    private ChallengesStorage storage;
    private int currPos;

    public ConditionsListPresenter(ConditionsListView view, ChallengesStorage storage){
        this.view = view;
        this.storage = storage;
    }

    public void getData(final int pos) {
        currPos = pos;
        storage.loadData(new GettingCallback() {
            @Override
            public void onSuccess() {
                Log.i("storage", storage.getChallenges().toString());
                view.initAdapter();
                view.showData(storage.getChallenges().get(pos).getConditions());
            }
        });
    }

    public void applyChanges(int pos){
        storage.updateChallenge(storage.getChallenges().get(pos));
    }

    @Override
    public void bind(int pos, BaseViewForAdapter view) {
        Condition conditionCurr = storage.getChallenges().get(currPos).getConditions().get(pos);
        view.setConditionText(conditionCurr.getCondition());
        view.setPenalty(conditionCurr.getPenalty());

    }

    @Override
    public int getCount() {

        return storage.getChallenges().get(currPos).getConditions().size();
    }

    public void applyChanges(String newCond, int newVal, int condPos) {
        Zaruba currentChallenge = storage.getChallenges().get(currPos);
        Condition currentCondition =  currentChallenge.getConditions().get(condPos);
        currentCondition.setCondition(newCond);
        currentCondition.setPenalty(newVal);
        storage.updateChallenge(storage.getChallenges().get(currPos));
    }
}
