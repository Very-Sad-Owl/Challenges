package android.projects.pidorsbizzareadventure.pojo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ChallengesContainer {

    private static List<Zaruba> challenges;

    private ChallengesContainer(){}

    public static List<Zaruba> getInstance(){
        if (challenges == null){
            challenges = new LinkedList<>();
        }
        return  challenges;
    }

    public static List<String> getChallengesTitles(){
        List<String> titles = new ArrayList<>();
        for(Zaruba el : challenges){
            titles.add(el.getTitle());
        }
        return titles;
    }

    public static List<String> getChallengeConditions(int pos){
        List<String> conditions = new ArrayList<>();
        for(Condition con : challenges.get(pos).getConditions()) {
            conditions.add(con.getCondition());
        }
        return conditions;
    }

//    public void addChallenge(Zaruba challenge){
//        challenges.add(challenge);
//    }
//
//    public void removeChallenge(Zaruba challenge){
//        challenges.remove(challenge);
//    }

}
