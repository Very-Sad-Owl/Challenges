package android.projects.pidorsbizzareadventure.ui.info.editing;

import android.projects.pidorsbizzareadventure.common.BasePresenter;
import android.projects.pidorsbizzareadventure.pojo.Zaruba;
import android.projects.pidorsbizzareadventure.storage.local.ChallengesStorage;
import android.util.Log;

public class ChallengeEditPresenter extends BasePresenter {

    private ChallengeEditView view;
    private Zaruba currentChallenge;
    private ChallengesStorage storage;

    public ChallengeEditPresenter(ChallengeEditView view, ChallengesStorage storage) {
        this.view = view;
        this.currentChallenge = new Zaruba();
        this.storage = storage;
    }

    void setCurrentChallenge(Object chellange){
        currentChallenge = (Zaruba)chellange;
        Log.i("ddf", currentChallenge.toString());
        view.setCurrentMetaInfo(currentChallenge);
    }

    void update(CharSequence title, CharSequence description, CharSequence reward, CharSequence punishment){
        currentChallenge.setTitle(title.toString());
        currentChallenge.setDescription(description.toString());
        currentChallenge.setReward(reward.toString());
        currentChallenge.setPunishment(punishment.toString());
        storage.updateChallenge(currentChallenge);
        Log.i("fbup", currentChallenge.toString());
    }
}
