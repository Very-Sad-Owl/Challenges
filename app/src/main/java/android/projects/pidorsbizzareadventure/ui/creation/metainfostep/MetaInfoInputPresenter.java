package android.projects.pidorsbizzareadventure.ui.creation.metainfostep;

import android.projects.pidorsbizzareadventure.common.BasePresenter;
import android.projects.pidorsbizzareadventure.pojo.Participator;
import android.projects.pidorsbizzareadventure.pojo.Zaruba;
import android.projects.pidorsbizzareadventure.storage.local.ChallengesStorage;

import static android.projects.pidorsbizzareadventure.storage.firebase.FBConstants.*;

public class MetaInfoInputPresenter extends BasePresenter {

    private MetaInfoInputView view;
    private Zaruba storage;

    public MetaInfoInputPresenter(MetaInfoInputView view) {
        this.view = view;
    }

    Zaruba metaInfoCreated(CharSequence title, CharSequence descr,
                         CharSequence reward, CharSequence punishment){

        return new Zaruba.Builder()
                .withTitle(title.toString())
                .withDescription(descr.toString())
                .withHost(USER_UID)
                .withReward(reward.toString())
                .withPunishment(punishment.toString())
                .withParticipator(new Participator("host", USER_UID))
                .build();
    }

    Zaruba getZaruba(){
        return storage;
    }

}
