package android.projects.pidorsbizzareadventure.common;

import androidx.fragment.app.Fragment;

public abstract class BaseFragmentWithAdapter<P extends BasePresenterForAdapter> extends Fragment {

    public abstract P getPresenter();

    public abstract void notifyAdapter();

    @Override
    public void onDetach() {
        if (getPresenter() != null) {
            getPresenter().detachView();
        }
        super.onDetach();
    }
}
