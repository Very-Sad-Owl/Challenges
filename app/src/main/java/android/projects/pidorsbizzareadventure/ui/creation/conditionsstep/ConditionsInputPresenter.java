package android.projects.pidorsbizzareadventure.ui.creation.conditionsstep;

import android.projects.pidorsbizzareadventure.common.BasePresenter;
import android.projects.pidorsbizzareadventure.pojo.Condition;
import android.projects.pidorsbizzareadventure.pojo.Zaruba;
import android.projects.pidorsbizzareadventure.storage.local.ChallengesStorage;
import android.util.Log;

public class ConditionsInputPresenter extends BasePresenter {

    ConditionsInputView view;
    ChallengesStorage storage;
    Zaruba currentCreation;

    public ConditionsInputPresenter(ConditionsInputView view, ChallengesStorage storage) {
        this.view = view;
        this.storage = storage;
    }

    void setCurrentCreation(Zaruba zaruba){
        Log.i("gott", zaruba.toString());
        currentCreation = zaruba;
        view.setTitle(currentCreation);
    }

    void addCondition(CharSequence text, int val){
        Condition condition = new Condition(text.toString(), val);
        currentCreation.addCondition(condition);
        //currentCreation.getConditions().add(condition);
//        view.updateRecycler(condition);
    }

    void uploadChallenge(){
        storage.addData(currentCreation);
    }
}
