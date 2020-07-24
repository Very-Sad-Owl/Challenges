package android.projects.pidorsbizzareadventure.storage.local;

import android.projects.pidorsbizzareadventure.pojo.Condition;
import android.projects.pidorsbizzareadventure.pojo.Participator;
import android.projects.pidorsbizzareadventure.pojo.Zaruba;
import android.projects.pidorsbizzareadventure.storage.firebase.FBConstants;
import android.projects.pidorsbizzareadventure.storage.firebase.FirebaseHelper;
import android.projects.pidorsbizzareadventure.storage.firebase.ReadingCallback;
import android.util.Log;

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
        FirebaseHelper.ChallengesInteraction.searchByUid(uid, found, new ReadingCallback() {
            @Override
            public void onSuccess() {
                callback.onSuccess();
            }
        });
    }

    public void addData(Zaruba zaruba){
        Log.i("cs", zaruba.toString());
        FirebaseHelper.ChallengesInteraction.uploadChallenge(zaruba);
    }

    public void updateChallenge(Zaruba obj){
        FirebaseHelper.ChallengesInteraction.updateChallenge(obj);
    }

//    public void getData(List<Zaruba> list, GettingCallback callback){
//        if(challenges == null){
//            loadData(callback);
//        }
//        Log.i("fromget", challenges.toString());
//        list = challenges;
//    }

}
