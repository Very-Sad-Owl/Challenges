package android.projects.pidorsbizzareadventure.ui.welcome.admitFragment;

import android.projects.pidorsbizzareadventure.common.BasePresenter;
import android.projects.pidorsbizzareadventure.pojo.Condition;
import android.projects.pidorsbizzareadventure.pojo.Zaruba;
import android.projects.pidorsbizzareadventure.storage.local.ChallengesStorage;
import android.projects.pidorsbizzareadventure.storage.local.GettingCallback;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class WelcomePresenter extends BasePresenter {
    private final AdmitView view;
    private final ChallengesStorage storage;

//    public WelcomePresenter(AdmitView view) {
//        this.view = view;
//    }


    public WelcomePresenter(AdmitView view, ChallengesStorage storage) {
        this.view = view;
        this.storage = storage;
    }

    void findCurrentConditions(int pos) {

    }

    void getChallenges(){
//        final List<Zaruba> current = new ArrayList<>();
//
//        FirebaseHelper.ChallengesInteraction.getAllCurrent(current, new ReadingCallback() {
//            @Override
//            public void onSuccess() {
//                List<String> titles = new ArrayList<>();
//                for(Zaruba ch : current){
//                    titles.add(ch.getTitle());
//                }
//                Log.i("presenter", titles.toString());
//
//                view.showChallenges(titles);
//            }
//        });
        if(storage.getChallenges() != null){
            List<Zaruba> current = storage.getChallenges();
            List<String> titles = new ArrayList<>();

            for (Zaruba ch : current) {
                titles.add(ch.getTitle());
            }
            Log.i("presenter", titles.toString());

        } else {
            storage.loadData(new GettingCallback() {
                @Override
                public void onSuccess() {
                    List<Zaruba> current = storage.getChallenges();
                    List<String> titles = new ArrayList<>();

                    for (Zaruba ch : current) {
                        titles.add(ch.getTitle());
                    }
                    Log.i("presenter", titles.toString());

                    view.showChallenges(titles);
                }
            });
        }
    }

    public void findConditions(int pos){
        List<Zaruba> challenges = storage.getChallenges();
        Zaruba current = challenges.get(pos);
        List<String> conditions = new ArrayList<>();
        for(Condition el : current.getConditions()){
            conditions.add(el.getCondition());
        }
        view.showConditions(conditions);
    }

    public void admitFault(int ch, int cond){
        storage.updateMyPenalty(ch, cond);
    }

//    public void findChallengeToJoin(String uid){
//        storage.findChallengeByUid(uid);
//        view.addNewChallenge();
//    }

}
