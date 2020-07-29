package android.projects.pidorsbizzareadventure.common;

import androidx.appcompat.app.AppCompatDialogFragment;

public abstract class BaseDialog<P extends BasePresenter> extends AppCompatDialogFragment {
    protected abstract P getPresenter();

    @Override
    public void onDetach() {
        if (getPresenter() != null) {
            getPresenter().detachView();
        }
        super.onDetach();
    }
}
