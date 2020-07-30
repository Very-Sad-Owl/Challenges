package android.projects.pidorsbizzareadventure.ui.conditionsList;

import android.projects.pidorsbizzareadventure.common.BaseView;
import android.projects.pidorsbizzareadventure.common.BaseViewForAdapter;
import android.projects.pidorsbizzareadventure.pojo.Condition;

import java.util.List;

public interface ConditionsListView extends BaseView {
    void showData(List<Condition> data);
    void initAdapter();
}
