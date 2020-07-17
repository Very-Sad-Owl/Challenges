package android.projects.pidorsbizzareadventure.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Zaruba implements Serializable {
    private String uid;
    private String title;
    private List<Participator> participators;
    private String description;
    private String reward;
    private String punishment;
    private List<Condition> conditions;
    private String host;


    public Zaruba(){
        title = "Untitled";
        description = "None";
        reward = "no";
        punishment = "no";
        host = "ded";
        participators = new ArrayList<>();
        conditions = new ArrayList<>();
    }

    public Zaruba(Zaruba zaruba){
        this(zaruba.uid, zaruba.title, zaruba.participators, zaruba.description,
                zaruba.reward, zaruba.punishment, zaruba.conditions, zaruba.host);
    }

//    public Zaruba(String title, String description, Participator participator,
//                  String reward, String punishment, List<Condition> conditions) {
//        this.title = !title.isEmpty() ? title : "Untitled";
//        this.description = description.isEmpty() ? "None" : description;
//        this.reward = reward.isEmpty() ? "no" : reward;
//        this.participators = new ArrayList<>();
//        this.participators.add(participator);
//        this.host = participator.getUid();
//        this.punishment = punishment.isEmpty() ? "no" : punishment;
//        if(conditions != null) {
//            this.conditions = conditions;
//        } else {
//            this.conditions = new ArrayList<>();
//        }
//    }


    public Zaruba(String uid, String title, List<Participator> participators, String description,
                  String reward, String punishment, List<Condition> conditions, String host) {
        this.uid = uid;
        this.title = title;
        this.participators = participators;
        this.description = description;
        this.reward = reward;
        this.punishment = punishment;
        this.conditions = conditions;
        this.host = host;
    }

    public String getHost() {
        return host;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Participator> getParticipators() {
        return participators;
    }

    public void setParticipators(List<Participator> participators) {
        this.participators = participators;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }

    public String getPunishment() {
        return punishment;
    }

    public void setPunishment(String punishment) {
        this.punishment = punishment;
    }

    public List<Condition> getConditions() {
        return conditions;
    }

    public void setConditions(List<Condition> conditions) {
        this.conditions = conditions;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setHost(String host) {
        host = host;
    }

    @Override
    public String toString() {
        return "Zaruba{" +
                "uid='" + uid + '\'' +
                ", title='" + title + '\'' +
                ", participators=" + participators +
                ", description='" + description + '\'' +
                ", reward='" + reward + '\'' +
                ", punishment='" + punishment + '\'' +
                ", conditions=" + conditions +
                ", host='" + host + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Zaruba zaruba = (Zaruba) o;
        return Objects.equals(uid, zaruba.uid) &&
                Objects.equals(title, zaruba.title) &&
                Objects.equals(participators, zaruba.participators) &&
                Objects.equals(description, zaruba.description) &&
                Objects.equals(reward, zaruba.reward) &&
                Objects.equals(punishment, zaruba.punishment) &&
                Objects.equals(conditions, zaruba.conditions) &&
                Objects.equals(host, zaruba.host);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid, title, participators, description, reward, punishment, conditions, host);
    }
}
