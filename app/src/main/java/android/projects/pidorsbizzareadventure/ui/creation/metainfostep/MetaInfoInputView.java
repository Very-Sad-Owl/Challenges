package android.projects.pidorsbizzareadventure.ui.creation.metainfostep;

import android.projects.pidorsbizzareadventure.common.BaseView;
import android.projects.pidorsbizzareadventure.pojo.Zaruba;

public interface MetaInfoInputView extends BaseView {

    void inputEnded();
    void toNextStep(Zaruba zaruba);
}
