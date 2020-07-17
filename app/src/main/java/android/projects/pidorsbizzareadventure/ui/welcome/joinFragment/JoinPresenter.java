package android.projects.pidorsbizzareadventure.ui.welcome.joinFragment;

import android.projects.pidorsbizzareadventure.common.BasePresenter;
import android.projects.pidorsbizzareadventure.pojo.Zaruba;
import android.projects.pidorsbizzareadventure.storage.local.ChallengesStorage;
import android.projects.pidorsbizzareadventure.storage.local.GettingCallback;
import android.projects.pidorsbizzareadventure.ui.welcome.admitFragment.AdmitView;

public class JoinPresenter extends BasePresenter {

    private final JoinView view;
    private final ChallengesStorage storage;

    public JoinPresenter(JoinView view, ChallengesStorage storage) {
        this.view = view;
        this.storage = storage;
    }

        public void findChallengeToJoin(String uid){
        final Zaruba found = new Zaruba();
        storage.findChallengeByUid(uid, found, new GettingCallback() {
                @Override
                public void onSuccess() {
                    view.addNewChallenge(found);
                }
            });

    }
}
