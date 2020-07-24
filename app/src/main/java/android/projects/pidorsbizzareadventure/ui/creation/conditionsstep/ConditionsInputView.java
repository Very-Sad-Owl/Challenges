package android.projects.pidorsbizzareadventure.ui.creation.conditionsstep;

import android.projects.pidorsbizzareadventure.common.BaseView;
import android.projects.pidorsbizzareadventure.pojo.Condition;
import android.projects.pidorsbizzareadventure.pojo.Zaruba;

public interface ConditionsInputView extends BaseView {

    void setTitle(Zaruba zaruba);
    void updateRecycler(Condition condition);
}
