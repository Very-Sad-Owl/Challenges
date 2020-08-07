package android.projects.pidorsbizzareadventure.ui.info.editing;

import android.projects.pidorsbizzareadventure.common.BasePresenter;
import android.projects.pidorsbizzareadventure.pojo.Zaruba;
import android.projects.pidorsbizzareadventure.storage.local.ChallengesStorage;
import android.util.Log;

public class ChallengeEditPresenter extends BasePresenter {

    private ChallengeEditView view;
    private Zaruba editedChallenge;
    private Zaruba currentChallenge;
    private ChallengesStorage storage;

    public ChallengeEditPresenter(ChallengeEditView view, Zaruba currentChallenge, ChallengesStorage storage) {
        this.view = view;
        this.currentChallenge = currentChallenge;
        this.editedChallenge = new Zaruba(currentChallenge);
        this.storage = storage;
    }

    public void setMetaInfo(){
        view.setCurrentMetaInfo(editedChallenge);
    }


    void update(CharSequence title, CharSequence description, CharSequence reward, CharSequence punishment){
        editedChallenge.setTitle(title.toString());
        editedChallenge.setDescription(description.toString());
        editedChallenge.setReward(reward.toString());
        editedChallenge.setPunishment(punishment.toString());
        //storage.updateChallenge(editedChallenge);
        view.setCurrentMetaInfo(editedChallenge);
        Log.i("fbup", editedChallenge.toString());
    }

    void confirm(){
        storage.updateChallenge(editedChallenge);
    }

    public Zaruba getEditedChallenge() {
        return editedChallenge;
    }

    public Zaruba getCurrentChallenge() {
        return currentChallenge;
    }
}
