package android.projects.pidorsbizzareadventure.ui.welcome.admitFragment;

import android.projects.pidorsbizzareadventure.common.BaseView;
import android.projects.pidorsbizzareadventure.pojo.Zaruba;

import java.util.List;

public interface AdmitView extends BaseView {

    void showConditions(List<String> list);
    void showChallenges(List<String> list);
}
