package android.projects.pidorsbizzareadventure.storage.local;

import android.projects.pidorsbizzareadventure.pojo.Condition;
import android.projects.pidorsbizzareadventure.pojo.Participator;
import android.projects.pidorsbizzareadventure.pojo.Zaruba;
import android.projects.pidorsbizzareadventure.storage.firebase.FirebaseHelper;
import android.projects.pidorsbizzareadventure.storage.firebase.ReadingCallback;

import java.util.ArrayList;
import java.util.List;
import static android.projects.pidorsbizzareadventure.storage.firebase.FBConstants.*;

public class ChallengesStorage {

    private List<Zaruba> challenges;

    public ChallengesStorage() {
        //challenges = new ArrayList<>();
    }

    public List<Zaruba> getChallenges() {
        return challenges;
    }

    public void loadData(final GettingCallback callback){
        challenges = new ArrayList<>();
        FirebaseHelper.ChallengesInteraction.getAllCurrent(challenges, new ReadingCallback() {
            @Override
            public void onSuccess() {
                callback.onSuccess();
            }
        });
    }

    public void updateMyPenalty(int ch, int cond){
        Zaruba currentChallenge = challenges.get(ch);
        Condition currentCondition = currentChallenge.getConditions().get(cond);
        for(Participator participator : currentChallenge.getParticipators()){
            if(participator.getUid().equals(USER_UID)){
                participator.setTotalScore(participator.getTotalScore() + currentCondition.getPenalty());
            }
        }
        FirebaseHelper.ChallengesInteraction.updateChallenge(currentChallenge);
    }

    public void findChallengeByUid(String uid, Zaruba found, final GettingCallback callback){
        //Zaruba found = new Zaruba();
        FirebaseHelper.ChallengesInteraction.searchByUid(uid, found, new ReadingCallback() {
            @Override
            public void onSuccess() {
                callback.onSuccess();
            }
        });
        //return found;
    }
}
