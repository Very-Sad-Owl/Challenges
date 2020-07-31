package android.projects.pidorsbizzareadventure.ui.conditionsList;

import android.projects.pidorsbizzareadventure.common.BasePresenter;
import android.projects.pidorsbizzareadventure.common.BasePresenterForAdapter;
import android.projects.pidorsbizzareadventure.common.BaseViewForAdapter;
import android.projects.pidorsbizzareadventure.pojo.Condition;
import android.projects.pidorsbizzareadventure.pojo.Zaruba;
import android.projects.pidorsbizzareadventure.storage.local.ChallengesStorage;
import android.projects.pidorsbizzareadventure.storage.local.GettingCallback;
import android.util.Log;

import java.util.List;

public class ConditionsListPresenter extends BasePresenterForAdapter {

    private ConditionsListView view;
    private ChallengesStorage storage;
    private Zaruba currChallenge;

    public ConditionsListPresenter(ConditionsListView view, ChallengesStorage storage){
        this.view = view;
        this.storage = storage;
        currChallenge = new Zaruba();
    }

    public void getData(Zaruba challenge) {
//        storage.findChallengeByUid(challenge.getUid(), currChallenge, new GettingCallback() {
//            @Override
//            public void onSuccess() {
//                view.initAdapter();
//                view.showData(currChallenge.getConditions());
//            }
//        });
        currChallenge = challenge;
        view.initAdapter();
        view.showData(currChallenge.getConditions());
    }


    @Override
    public void bind(int pos, BaseViewForAdapter view) {
        Condition conditionCurr = currChallenge.getConditions().get(pos);
        view.setConditionText(conditionCurr.getCondition());
        view.setPenalty(conditionCurr.getPenalty());

    }

    @Override
    public int getCount() {

        return currChallenge.getConditions().size();
    }

    public void applyChanges(String newCond, int newVal, int condPos) {
        Condition currentCondition =  currChallenge.getConditions().get(condPos);
        currentCondition.setCondition(newCond);
        currentCondition.setPenalty(newVal);
        storage.updateChallenge(currChallenge);
    }

//    public void addData(Condition condition){
//        currChallenge.getConditions().add(condition);
//    }

    public List<Condition> getConditions(){
        return currChallenge.getConditions();
    }

}
