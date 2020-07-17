package android.projects.pidorsbizzareadventure.common;

public abstract class BasePresenter {

    private BaseView view;

    public void attachView(BaseView usersActivity) {
        view = usersActivity;
    }

    public void detachView() {
        view = null;
    }

}
