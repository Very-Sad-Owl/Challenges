package android.projects.pidorsbizzareadventure.ui.creation.metainfostep;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.projects.pidorsbizzareadventure.common.BaseFragment;
import android.projects.pidorsbizzareadventure.pojo.Zaruba;
import android.projects.pidorsbizzareadventure.ui.creation.ChallengeCreation;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.projects.pidorsbizzareadventure.R;
import android.widget.Button;
import android.widget.EditText;

public class MetaInfoInputFragment extends BaseFragment<MetaInfoInputPresenter> implements MetaInfoInputView {

    private Button toCondition;

    EditText title;
    EditText description;
    EditText nick;
    EditText reward;
    EditText punishment;

    //NextStep nextStep;

    Button next;
    Button previous;

    private MetaInfoInputPresenter presenter;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        //nextStep = (NextStep) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_meta_info_input, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        title = view.findViewById(R.id.editTitle);
        description = view.findViewById(R.id.editDescription);
        nick = view.findViewById(R.id.editPartis);
        reward = view.findViewById(R.id.editWin);
        punishment = view.findViewById(R.id.editLoose);

        next = view.findViewById(R.id.buttonToCondStep);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("null", title.getText().toString() + description.getText().toString());
               // Zaruba zaruba = presenter.metaInfoCreated("title", "desc", "re", "p");
                Zaruba zaruba = presenter.metaInfoCreated(title.getText(), description.getText(),
                        reward.getText(), punishment.getText());
                Bundle result = new Bundle();
                result.putSerializable("bundleKey", zaruba);
                Log.i("too", zaruba.toString());
                getParentFragmentManager().setFragmentResult("requestKey", result);
                ((ChallengeCreation)getActivity()).nextStep();
            }
        });

        previous = view.findViewById(R.id.buttonToCancelsStep);
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ChallengeCreation)getActivity()).previousStep();
            }
        });

        presenter = new MetaInfoInputPresenter(this);

    }

    @Override
    protected MetaInfoInputPresenter getPresenter() {
        return presenter;
    }

    @Override
    public void onPause() {
        super.onPause();
//        Zaruba zaruba = presenter.metaInfoCreated(title.getText(), description.getText(),
//                        reward.getText(), punishment.getText());
//        Zaruba zaruba = presenter.metaInfoCreated("title", "desc", "re", "p");
//        Bundle result = new Bundle();
//        result.putSerializable("bundleKey", zaruba);
//        Log.i("too", zaruba.toString());
//        getParentFragmentManager().setFragmentResult("requestKey", result);
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
    public void inputEnded() {

    }

    @Override
    public void toNextStep(Zaruba zaruba) {
        Bundle result = new Bundle();
        result.putSerializable("META_INFO", zaruba);
        Log.i("too", zaruba.toString());
        getParentFragmentManager().setFragmentResult("INTERMEDIATE_CHALLENGE_CREATION_RESULT", result);
    }

}
