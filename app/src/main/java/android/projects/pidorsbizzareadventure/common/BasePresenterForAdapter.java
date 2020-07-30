package android.projects.pidorsbizzareadventure.common;

public abstract class BasePresenterForAdapter extends BasePresenter {

    public abstract void bind(int pos, BaseViewForAdapter view);
    public abstract int getCount();

}
