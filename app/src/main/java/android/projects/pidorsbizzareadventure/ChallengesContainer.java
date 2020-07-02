package android.projects.pidorsbizzareadventure;

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

//    public void addChallenge(Zaruba challenge){
//        challenges.add(challenge);
//    }
//
//    public void removeChallenge(Zaruba challenge){
//        challenges.remove(challenge);
//    }

}
