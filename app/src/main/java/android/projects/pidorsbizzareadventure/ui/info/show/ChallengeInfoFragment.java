package android.projects.pidorsbizzareadventure.ui.info.show;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.projects.pidorsbizzareadventure.common.BaseFragment;
import android.projects.pidorsbizzareadventure.pojo.Zaruba;
import android.projects.pidorsbizzareadventure.storage.local.ChallengesStorage;
import android.projects.pidorsbizzareadventure.ui.conditionsList.ConditionsListFragment;
import android.projects.pidorsbizzareadventure.ui.info.ZarubaInfo;
import android.util.Log;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.projects.pidorsbizzareadventure.R;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Map;


public class ChallengeInfoFragment extends BaseFragment<InfoPresenter> implements InfoView{

    private TextView title;
    private TextView description;
    private TextView reward;
    private TextView punishment;

    ConditionsListFragment fragment;

    private Toolbar toolbar;
    private onEditCallback callback;

    private InfoPresenter presenter;
    private ChallengesStorage storage;

    private int pos;

    public ChallengeInfoFragment() {
        // Required empty public constructor
    }

    public static ChallengeInfoFragment newInstance(Bundle bundle) {
        ChallengeInfoFragment fragment = new ChallengeInfoFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.i("showmeta", "onAttach");
        storage =  new ChallengesStorage();
        presenter = new InfoPresenter(this, storage);

        if(context instanceof ZarubaInfo){
            callback = (onEditCallback) context;
        } else {
            throw new ClassCastException("Activity must implement onEditCallback");
        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("showmeta", "onCreate");
        setHasOptionsMenu(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i("showmeta", "onVCreateView");
        return inflater.inflate(R.layout.fragment_challenge_info, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i("showmeta", "onViewCreated");

        title = view.findViewById(R.id.textViewInfoTitle);
        description = view.findViewById(R.id.textViewInfoDescription);
        reward = view.findViewById(R.id.textViewReward);
        punishment = view.findViewById(R.id.textViewPunishment);

        Bundle args = getArguments();

        fragment = ConditionsListFragment.newInstance(args);

        getChildFragmentManager()
                .beginTransaction()
                .replace(R.id.conditions_info_place, fragment)
                .commit();

        pos = (Integer)args.get("CURRENT_CHALLENGE_POS");
        presenter.getData(pos);

        toolbar = view.findViewById(R.id.editToolBar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.munu_edit, menu);
 //       presenter.allowEditing(menu, inflater, pos);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.itemEdit:
                Toast.makeText(getContext(), "Edit started", Toast.LENGTH_SHORT).show();
                callback.enableEditMode();
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    protected InfoPresenter getPresenter() {
        return presenter;
    }

    @Override
    public void showMetaInfo(Zaruba challenge) {
        title.setText(challenge.getTitle());
        description.setText(challenge.getDescription());
        reward.setText(challenge.getReward());
        punishment.setText(challenge.getPunishment());
    }

    @Override
    public void inflateMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.munu_edit, menu);
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

    public interface onEditCallback {
        void enableEditMode();
    }
}
