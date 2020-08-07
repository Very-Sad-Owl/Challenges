package android.projects.pidorsbizzareadventure.ui.welcome.admitFragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.projects.pidorsbizzareadventure.common.BaseFragment;
import android.projects.pidorsbizzareadventure.storage.local.ChallengesStorage;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.projects.pidorsbizzareadventure.R;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Admit extends BaseFragment<WelcomePresenter> implements AdmitView{

    private TextView hello;
    private Spinner challenges;
    private Spinner conditions;
    private ArrayAdapter<String> challengesAdapter;
    private ArrayAdapter<String> conditionsAdapter;
    private List<String> titles;
    private List<String> penalty;
    private Button admit;
    private WelcomePresenter presenter;
    private ChallengesStorage storage;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.i("plsh", "onAttach");
        titles = new ArrayList<>();
        penalty = new ArrayList<>();

        challengesAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, titles);
        challengesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        conditionsAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, penalty);
        conditionsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        storage = new ChallengesStorage();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i("plsh", "onCreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i("plsh", "onCreateView");
        return inflater.inflate(R.layout.fragment_admit, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i("plsh", "onViewCreated");

        challenges = view.findViewById(R.id.spinnerChallenges);
        conditions = view.findViewById(R.id.spinnerConditions);
        admit = view.findViewById(R.id.buttonAdmit);


        challenges.setAdapter(challengesAdapter);
        conditions.setAdapter(conditionsAdapter);

        presenter = new WelcomePresenter(this, storage);
        presenter.getChallenges();
        presenter.getUserName();

        hello = view.findViewById(R.id.textViewHelloUser);

        challenges.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {
                presenter.findConditions(selectedItemPosition);
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        admit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int challengePos = challenges.getSelectedItemPosition();
                int conditionPos = conditions.getSelectedItemPosition();
                presenter.admitFault(challengePos, conditionPos);
            }
        });

//        join.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                presenter.findChallengeToJoin(uidToJoin.getText().toString());
//            }
//        });

    }

    @Override
    public WelcomePresenter getPresenter() {
        return presenter;
    }

    @Override
    public void showConditions(List<String> list) {
        Log.i("view", "showConditions");
        if (!penalty.isEmpty()) penalty.clear();
        penalty.addAll(list);
        Log.i("view", penalty.toString());
        conditionsAdapter.notifyDataSetChanged();
    }

    @Override
    public void showChallenges(List<String> list) {
        Log.i("view", "showChallenges");
        if (!titles.isEmpty()) titles.clear();
        titles.addAll(list);
        Log.i("view", titles.toString());
        challengesAdapter.notifyDataSetChanged();
    }

    @Override
    public void showUseName(String name) {
        hello.setText(String.format(getResources().getString(R.string.hello_user), name));
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

//    @Override
//    public void addNewChallenge() {
//        challengesAdapter.notifyDataSetChanged();
//    }
}
