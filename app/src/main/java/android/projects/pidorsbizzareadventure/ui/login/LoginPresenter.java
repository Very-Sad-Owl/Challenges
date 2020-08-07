package android.projects.pidorsbizzareadventure.ui.login;

import android.projects.pidorsbizzareadventure.common.BasePresenter;
import android.projects.pidorsbizzareadventure.storage.local.ChallengesStorage;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import static android.projects.pidorsbizzareadventure.storage.firebase.FBConstants.CURRENT_USER;
import static android.projects.pidorsbizzareadventure.storage.firebase.FBConstants.USER_UID;
import static android.projects.pidorsbizzareadventure.storage.firebase.FBConstants.auth;
import static android.projects.pidorsbizzareadventure.storage.firebase.FBConstants.user;

public class LoginPresenter extends BasePresenter {

    private LoginView view;
    private ChallengesStorage storage;

    LoginPresenter(LoginView view, ChallengesStorage storage){
        this.view = view;
        this.storage = storage;
    }

    void checkUserStatus(){
        if (isVerified()){
            //CURRENT_USER.setUid(user.getUid());
            view.startMainActivity();
        } else {
            view.showSignIn();
        }
//        view.showSignIn();
    }

    void signUp(final CharSequence email, final CharSequence password, final CharSequence nickname){
        auth.createUserWithEmailAndPassword(email.toString(), password.toString())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            verify();
                            CURRENT_USER.setEmail(email.toString());
                            CURRENT_USER.setPassword(password.toString());
                            CURRENT_USER.setNickname(nickname.toString());
                            CURRENT_USER.setUid(auth.getCurrentUser().getUid());
                            storage.register(CURRENT_USER);
                            Log.i("usercheck", auth.getCurrentUser().getUid());
                            view.showSignIn();
                        } else {
                            view.showToast("Registration failed");
                        }
                    }
                });
    }

    void signIn(final CharSequence email, final CharSequence password){
        auth.signInWithEmailAndPassword(email.toString(), password.toString())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(isVerified()) {
                                //CURRENT_USER.setUid(auth.getCurrentUser().getUid());
                                Log.i("usercheck", auth.getCurrentUser().getUid());
                                view.showToast("Signing in success");
                                view.startMainActivity();
                            } else {
                                view.showToast("Please, complete verification");
                            }
                        }
                    });
    }


    boolean isEmpty(CharSequence ... args){
        for (CharSequence el : args){
            if (el.toString().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    private void verify(){
        auth.getCurrentUser().sendEmailVerification()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            view.showToast("Check your email");
                        } else {
                            view.showToast("Something went wrong. Try to register again");
                        }
                    }
                });
    }

    private boolean isVerified(){
        return auth.getCurrentUser() != null && auth.getCurrentUser().isEmailVerified();
    }

}
