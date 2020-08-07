package android.projects.pidorsbizzareadventure.ui.welcome.joinFragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.projects.pidorsbizzareadventure.R;
import android.projects.pidorsbizzareadventure.ui.creation.ChallengeCreation;
import android.projects.pidorsbizzareadventure.ui.info.ZarubaInfo;
import android.projects.pidorsbizzareadventure.common.BaseFragment;
import android.projects.pidorsbizzareadventure.pojo.Zaruba;
import android.projects.pidorsbizzareadventure.storage.local.ChallengesStorage;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class JoinChallenge extends BaseFragment<JoinPresenter> implements JoinView{

    private EditText uid;
    private Button join;
    private Button add;
    private JoinPresenter presenter;
    private ChallengesStorage storage;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_join_challenge, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //presenter = new WelcomePresenter(this);
        join = view.findViewById(R.id.buttonJoinChallenge);
        uid = view.findViewById(R.id.editTextChallengeUIDInput);
        add = view.findViewById(R.id.buttonCeateNew);

        ChallengesStorage storage = new ChallengesStorage();
        presenter = new JoinPresenter(this, storage);

        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.findChallengeToJoin(uid.getText().toString());
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toCreate = new Intent(getContext(), ChallengeCreation.class);
                startActivity(toCreate);
            }
        });
    }

    @Override
    public JoinPresenter getPresenter() {
        return presenter;
    }

    @Override
    public void clearInput(){
        uid.getText().clear();
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
}
