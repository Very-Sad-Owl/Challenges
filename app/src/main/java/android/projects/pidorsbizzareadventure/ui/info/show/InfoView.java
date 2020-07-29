package android.projects.pidorsbizzareadventure.ui.info.show;

import android.projects.pidorsbizzareadventure.common.BaseView;
import android.projects.pidorsbizzareadventure.pojo.Zaruba;
import android.view.Menu;
import android.view.MenuInflater;

import java.util.List;
import java.util.Map;

public interface InfoView extends BaseView {

    void showMetaInfo(Zaruba challenge);
    void inflateMenu(Menu menu, MenuInflater inflater);
}
