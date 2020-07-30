package android.projects.pidorsbizzareadventure.ui.creation.conditionsstep;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.projects.pidorsbizzareadventure.adapters.ConditionsAdapter;
import android.projects.pidorsbizzareadventure.common.BaseFragment;
import android.projects.pidorsbizzareadventure.pojo.Condition;
import android.projects.pidorsbizzareadventure.pojo.Zaruba;
import android.projects.pidorsbizzareadventure.storage.local.ChallengesStorage;
import android.projects.pidorsbizzareadventure.ui.conditionsList.ConditionsListFragment;
import android.projects.pidorsbizzareadventure.ui.creation.ChallengeCreation;
import android.projects.pidorsbizzareadventure.ui.info.editing.ChallengeEditFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.projects.pidorsbizzareadventure.R;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.util.List;

public class ConditionsInputFragment extends BaseFragment<ConditionsInputPresenter> implements ConditionsInputView {

    ConditionsInputPresenter presenter;
    ChallengesStorage storage;

    private onFinishedCallback callback;
    private Bundle args;

    TextView title;
    NumberPicker picker;
    EditText textCondition;
    Zaruba fromPrev;
    //RecyclerView conditions;
    ConditionsAdapter adapter;
    Button add;
    Button finish;
    Button previous;
    ConditionsListFragment conditions;

    public static ConditionsInputFragment newInstance(Bundle args) {
        ConditionsInputFragment fragment = new ConditionsInputFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        storage = new ChallengesStorage();
        presenter = new ConditionsInputPresenter(this, storage);

        if(context instanceof onFinishedCallback){
            callback = (onFinishedCallback) context;
        } else {
            throw  new ClassCastException("class must implement onFinishedCallback");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_conditions_input, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        title = view.findViewById(R.id.textViewTitleFromPrev);
        picker = view.findViewById(R.id.numberPicker);
        picker.setMinValue(1);
        picker.setMaxValue(10);
        picker.setValue(1);
        textCondition = view.findViewById(R.id.editTextNewConditions);
        add = view.findViewById(R.id.buttonAddCondition);

        args = getArguments();
        presenter.setCurrentCreation((Zaruba)args.getSerializable("meta_info_filled_challenge"));

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.addCondition(textCondition.getText(), picker.getValue());
            }
        });

        finish = view.findViewById(R.id.buttonFinish);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //((ChallengeCreation)getActivity()).finishCreation(presenter.getResultChallenge());
                presenter.uploadChallenge();
                callback.creationFinished();
            }
        });

        previous = view.findViewById(R.id.buttonToMeta);
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ((ChallengeCreation)getActivity()).previousStep();
                callback.previousStep();
            }
        });

        conditions = ConditionsListFragment.newInstance(args);

//        LinearLayoutManager manager = new LinearLayoutManager(getContext());
//        conditions = view.findViewById(R.id.conditionsList);
//        conditions.setLayoutManager(manager);
//
//        //adapter = new ConditionsAdapter(getParentFragmentManager());
//        conditions.setAdapter(adapter);
    }

    @Override
    protected ConditionsInputPresenter getPresenter() {
        return null;
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

    @Override
    public void setTitle(Zaruba zaruba) {
        title.setText(zaruba.getTitle());
    }

    @Override
    public void updateRecycler(Condition condition) {
        //adapter.add(condition);
        //adapter.notifyDataSetChanged();
    }

    public interface onFinishedCallback{
        void creationFinished();
        void previousStep();
    }
}
