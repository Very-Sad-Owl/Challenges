package android.projects.pidorsbizzareadventure.ui.scoreList;

import android.projects.pidorsbizzareadventure.common.BasePresenterForAdapter;
import android.projects.pidorsbizzareadventure.common.BaseViewForAdapter;
import android.projects.pidorsbizzareadventure.pojo.Participator;
import android.projects.pidorsbizzareadventure.pojo.Zaruba;
import android.projects.pidorsbizzareadventure.storage.local.ChallengesStorage;

import java.util.List;

public class ScoreListPresenter extends BasePresenterForAdapter {

    private ScoreListView view;
    private ChallengesStorage storage;
    private Zaruba currChallenge;

    public ScoreListPresenter(ScoreListView view, ChallengesStorage storage){
        this.view = view;
        this.storage = storage;
        currChallenge = new Zaruba();
    }

    public void getData(Zaruba challenge) {

        currChallenge = challenge;
        view.initAdapter();
    }


    @Override
    public void bind(int pos, BaseViewForAdapter view) {
        Participator participator = currChallenge.getParticipators().get(pos);
        view.setConditionText(participator.getUid());
        view.setPenalty(participator.getTotalScore());

    }

    @Override
    public int getCount() {

        return currChallenge.getParticipators().size();
    }


    public List<Participator> getParticipators(){
        return currChallenge.getParticipators();
    }

}