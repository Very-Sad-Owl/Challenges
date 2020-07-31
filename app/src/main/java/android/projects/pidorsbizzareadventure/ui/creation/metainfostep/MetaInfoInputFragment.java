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
    EditText reward;
    EditText punishment;

    onMetaInfoCreatedCallback callback;

    Button next;
    Button previous;

    private MetaInfoInputPresenter presenter;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if(context instanceof onMetaInfoCreatedCallback){
            callback = (onMetaInfoCreatedCallback) context;
        } else {
            throw new ClassCastException("class must implement onMetaInfoCreatedCallback");
        }
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
        reward = view.findViewById(R.id.editWin);
        punishment = view.findViewById(R.id.editLoose);

        next = view.findViewById(R.id.buttonToCondStep);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.i("null", title.getText().toString() + description.getText().toString());
               // Zaruba zaruba = presenter.metaInfoCreated("title", "desc", "re", "p");
                Bundle bundle = new Bundle();
                Zaruba zaruba = presenter.metaInfoCreated(title.getText(), description.getText(),
                        reward.getText(), punishment.getText());
                bundle.putSerializable("CURRENT_CHALLENGE", zaruba);
                callback.metaInfoCreated(bundle);
//                Bundle result = new Bundle();
//                result.putSerializable("bundleKey", zaruba);
//                Log.i("too", zaruba.toString());
//                getParentFragmentManager().setFragmentResult("requestKey", result);
//                ((ChallengeCreation)getActivity()).nextStep();
            }
        });

        previous = view.findViewById(R.id.buttonToCancelsStep);
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //((ChallengeCreation)getActivity()).previousStep();
                callback.cancel();
            }
        });

        presenter = new MetaInfoInputPresenter(this);

    }

    @Override
    public MetaInfoInputPresenter getPresenter() {
        return presenter;
    }

    @Override
    public void onPause() {
        super.onPause();
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
//        Bundle result = new Bundle();
//        result.putSerializable("META_INFO", zaruba);
//        Log.i("too", zaruba.toString());
        //getParentFragmentManager().setFragmentResult("INTERMEDIATE_CHALLENGE_CREATION_RESULT", result);
    }

    public interface onMetaInfoCreatedCallback {
        void metaInfoCreated(Bundle bundle);
        void cancel();
    }

}
