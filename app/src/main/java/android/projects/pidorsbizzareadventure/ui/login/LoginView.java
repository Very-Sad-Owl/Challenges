package android.projects.pidorsbizzareadventure.ui.login;

import android.projects.pidorsbizzareadventure.common.BaseView;

public interface LoginView extends BaseView {
    void showToast(String msg);
    void startMainActivity();
    void showSignIn();
    void showSignUp();
}
