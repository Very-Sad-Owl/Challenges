package android.projects.pidorsbizzareadventure.ui.info.editing;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import android.projects.pidorsbizzareadventure.common.BaseFragment;
import android.projects.pidorsbizzareadventure.pojo.Zaruba;
import android.projects.pidorsbizzareadventure.storage.local.ChallengesStorage;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.projects.pidorsbizzareadventure.R;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChallengeEditFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChallengeEditFragment extends BaseFragment<ChallengeEditPresenter> implements ChallengeEditView {

    private EditText titleEdit;
    private EditText descriptionEdit;
    private EditText rewardEdit;
    private EditText punishmentEdit;

    private Toolbar toolbar;

    private onOptionSelected callback;

    private ChallengeEditPresenter presenter;
    private ChallengesStorage storage;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ChallengeEditFragment() {
        // Required empty public constructor
    }


    public static ChallengeEditFragment newInstance(Bundle args) {
        ChallengeEditFragment fragment = new ChallengeEditFragment();
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        storage = new ChallengesStorage();
        presenter = new ChallengeEditPresenter(this, storage);

        if(context instanceof onOptionSelected){
            callback = (onOptionSelected) context;
        } else {
            throw new ClassCastException("Class must implement onActionChooseCallback");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setHasOptionsMenu(true);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_challenge_edit, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        titleEdit = view.findViewById(R.id.editTextInfoTitle);
        descriptionEdit = view.findViewById(R.id.editTextInfoDescription);
        rewardEdit = view.findViewById(R.id.editTextReward);
        punishmentEdit = view.findViewById(R.id.editTextPunishment);

        Bundle args = getArguments();
        presenter.setCurrentChallenge(args.get("CURRENT_CHALLENGE"));

        toolbar = view.findViewById(R.id.editToolBar);
        //((AppCompatActivity)getActivity()).getSupportActionBar().hide();
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        Log.i("tool", ((AppCompatActivity)getActivity()).getSupportActionBar() != null ? "true" : "false");
//        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_done, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.itemDone:
                Toast.makeText(getContext(), "Done", Toast.LENGTH_SHORT).show();
                presenter.update(titleEdit.getText(), descriptionEdit.getText(), rewardEdit.getText(),
                        punishmentEdit.getText());
                callback.apply();
                return true;
            case R.id.itemCancel:
                Toast.makeText(getContext(), "Cancel", Toast.LENGTH_SHORT).show();
                callback.cancel();
                return true;
//            case R.id.home:
//                Toast.makeText(getContext(), "Home", Toast.LENGTH_SHORT).show();
//                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    protected ChallengeEditPresenter getPresenter() {
        return presenter;
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
    public void setCurrentMetaInfo(Zaruba challenge) {
        titleEdit.setText(challenge.getTitle());
        descriptionEdit.setText(challenge.getDescription());
        rewardEdit.setText(challenge.getReward());
        punishmentEdit.setText(challenge.getPunishment());
    }

    public interface onOptionSelected{
        void apply();
        void cancel();
    }
}



