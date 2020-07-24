package android.projects.pidorsbizzareadventure.ui.creation;

import android.projects.pidorsbizzareadventure.common.BasePresenter;
import android.projects.pidorsbizzareadventure.pojo.Zaruba;
import android.projects.pidorsbizzareadventure.storage.local.ChallengesStorage;
import android.projects.pidorsbizzareadventure.ui.creation.metainfostep.MetaInfoInputView;
import android.util.Log;

public class CreationPresenter extends BasePresenter {

    private CreationView view;
    private ChallengesStorage storage;

    public CreationPresenter(CreationView view, ChallengesStorage storage) {
        this.view = view;
        this.storage = storage;
    }

    void submit(Zaruba zaruba){
        Log.i("cp", zaruba.toString());
        storage.addData(zaruba);
    }
}
