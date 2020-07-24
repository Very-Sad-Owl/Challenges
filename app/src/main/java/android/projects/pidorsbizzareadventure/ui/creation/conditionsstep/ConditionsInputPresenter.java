package android.projects.pidorsbizzareadventure.ui.creation.conditionsstep;

import android.projects.pidorsbizzareadventure.common.BasePresenter;
import android.projects.pidorsbizzareadventure.pojo.Condition;
import android.projects.pidorsbizzareadventure.pojo.Zaruba;
import android.projects.pidorsbizzareadventure.storage.local.ChallengesStorage;

public class ConditionsInputPresenter extends BasePresenter {

    ConditionsInputView view;
    ChallengesStorage storage;
    Zaruba currentCreation;

    public ConditionsInputPresenter(ConditionsInputView view, ChallengesStorage storage) {
        this.view = view;
        this.storage = storage;
    }

    void setCurrentCreation(Zaruba zaruba){
        currentCreation = zaruba;
        view.setTitle(currentCreation);
    }

    void addCondition(CharSequence text, int val){
        Condition condition = new Condition(text.toString(), val);
        currentCreation.getConditions().add(condition);
        //storage.addData(currentCreation);
        view.updateRecycler(condition);
        //currentCreation.a
    }

    Zaruba getResultChallenge(){
        return currentCreation;
    }
}
