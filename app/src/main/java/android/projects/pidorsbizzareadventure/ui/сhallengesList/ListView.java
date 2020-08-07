package android.projects.pidorsbizzareadventure.ui.сhallengesList;

import android.projects.pidorsbizzareadventure.common.BaseView;
import android.projects.pidorsbizzareadventure.pojo.Zaruba;

import java.util.List;

public interface ListView extends BaseView {

    void showData(List<Zaruba> data);
    void initAdapter(List<Zaruba> data);
}
