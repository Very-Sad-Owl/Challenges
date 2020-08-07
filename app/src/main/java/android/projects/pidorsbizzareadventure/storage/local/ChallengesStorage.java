package android.projects.pidorsbizzareadventure.storage.local;

import android.projects.pidorsbizzareadventure.pojo.Condition;
import android.projects.pidorsbizzareadventure.pojo.Participator;
import android.projects.pidorsbizzareadventure.pojo.User;
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
        challenges = new ArrayList<>();
    }

    public List<Zaruba> getChallenges() {
        return challenges;
    }

    public void loadData(final GettingCallback callback){
        challenges = new ArrayList<>();
        FirebaseHelper.ChallengesInteraction.getAllCurrent(challenges, new ReadingCallback() {
            @Override
            public void onSuccess() {
                Log.i("localdata", challenges.toString());
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

    public void updateParticipators(Zaruba obj){
        FirebaseHelper.ChallengesInteraction.updateParticipators(obj);
    }


    public boolean isHost(int pos){
        Log.i("ishost", challenges.get(pos) + " " + USER_UID);
        return challenges.get(pos).getHost().equals(USER_UID);
    }

    public void register(User user){
        FirebaseHelper.UserInteraction.registerUser(user);
    }

    public void getUserData(User user, String uid, final GettingCallback callback){
        FirebaseHelper.UserInteraction.getUser(user, uid, new ReadingCallback() {
            @Override
            public void onSuccess() {
                callback.onSuccess();
            }
        });
    }
}
