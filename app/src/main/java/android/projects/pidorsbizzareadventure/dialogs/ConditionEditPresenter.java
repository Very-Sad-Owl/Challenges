package android.projects.pidorsbizzareadventure.dialogs;

import android.projects.pidorsbizzareadventure.common.BasePresenter;
import android.projects.pidorsbizzareadventure.pojo.Zaruba;
import android.projects.pidorsbizzareadventure.storage.local.ChallengesStorage;

public class ConditionEditPresenter extends BasePresenter {

    private ConditionEditView view;
    private ChallengesStorage storage;

    public ConditionEditPresenter(ConditionEditView view, ChallengesStorage storage) {
        this.view = view;
        this.storage = storage;
    }

    void setData(Zaruba challenge){
        view.showData();
    }
}
