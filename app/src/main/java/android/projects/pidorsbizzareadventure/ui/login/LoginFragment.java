package android.projects.pidorsbizzareadventure.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.projects.pidorsbizzareadventure.common.BaseFragment;
import android.projects.pidorsbizzareadventure.pojo.Condition;
import android.projects.pidorsbizzareadventure.storage.local.ChallengesStorage;
import android.projects.pidorsbizzareadventure.ui.MainActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.projects.pidorsbizzareadventure.R;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.projects.pidorsbizzareadventure.storage.firebase.FBConstants.USER_UID;

public class LoginFragment extends BaseFragment<LoginPresenter> implements LoginView {

    private EditText email;
    private EditText password;
    private EditText nickname;
    private Button singIn;
    private Button signUp;
    private TextView refToSignUp;
    private TextView orText;

    private LoginPresenter presenter;
    private ChallengesStorage storage;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        storage = new ChallengesStorage();
        presenter = new LoginPresenter(this, storage);
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        presenter.checkUserStatus();
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        presenter.checkUserStatus();
    }

    @Override
    public LoginPresenter getPresenter() {
        return presenter;
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void startMainActivity() {
        Intent start = new Intent(getContext(), MainActivity.class);
        startActivity(start);
    }

    @Override
    public void showSignIn() {
        email.setVisibility(View.VISIBLE);
        password.setVisibility(View.VISIBLE);
        nickname.setVisibility(View.GONE);
        singIn.setVisibility(View.VISIBLE);
        signUp.setVisibility(View.GONE);
        orText.setVisibility(View.VISIBLE);
        refToSignUp.setVisibility(View.VISIBLE);
    }

    @Override
    public void showSignUp() {
        email.setVisibility(View.VISIBLE);
        password.setVisibility(View.VISIBLE);
        nickname.setVisibility(View.VISIBLE);
        singIn.setVisibility(View.GONE);
        signUp.setVisibility(View.VISIBLE);
        orText.setVisibility(View.GONE);
        refToSignUp.setVisibility(View.GONE);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError() {

    }

    private void init(View view){
        email = view.findViewById(R.id.edEmail);
        password = view.findViewById(R.id.edPassword);
        nickname = view.findViewById(R.id.edNickname);
        signUp = view.findViewById(R.id.buttonSignUp);
        singIn = view.findViewById(R.id.buttonSignIn);
        orText = view.findViewById(R.id.or_text);

        refToSignUp = view.findViewById(R.id.sign_up_ref);
        refToSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSignUp();
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!presenter.isEmpty(email.getText(), password.getText(), nickname.getText())) {
                    presenter.signUp(email.getText(), password.getText(), nickname.getText());
                }
            }
        });

        singIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!presenter.isEmpty(email.getText(), password.getText())){
                    presenter.signIn(email.getText(), password.getText());
                }
            }
        });
    }
}
