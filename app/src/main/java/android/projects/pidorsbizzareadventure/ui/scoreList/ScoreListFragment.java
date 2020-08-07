package android.projects.pidorsbizzareadventure.ui.scoreList;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.projects.pidorsbizzareadventure.R;
import android.projects.pidorsbizzareadventure.adapters.ConditionsAdapter;
import android.projects.pidorsbizzareadventure.adapters.ScoreAdapter;
import android.projects.pidorsbizzareadventure.common.BaseFragment;
import android.projects.pidorsbizzareadventure.common.BaseFragmentWithAdapter;
import android.projects.pidorsbizzareadventure.pojo.Condition;
import android.projects.pidorsbizzareadventure.pojo.Zaruba;
import android.projects.pidorsbizzareadventure.storage.local.ChallengesStorage;
import android.projects.pidorsbizzareadventure.ui.scoreList.ScoreListPresenter;
import android.projects.pidorsbizzareadventure.ui.scoreList.ScoreListView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class ScoreListFragment extends BaseFragmentWithAdapter<ScoreListPresenter> implements ScoreListView {

    private ScoreListPresenter presenter;
    private ChallengesStorage storage;
    private ScoreAdapter adapter;

    private RecyclerView conditions;

    int currPos;

    public static ScoreListFragment newInstance(Bundle args){
        ScoreListFragment fragment = new ScoreListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private ScoreListFragment(){}

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        storage = new ChallengesStorage();
        presenter = new ScoreListPresenter(this, storage);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_conditions_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //recyclerView = view.findViewById()
        conditions = view.findViewById(R.id.recyclerConditionFragment);

        Bundle args = getArguments();
        currPos = args.getInt("CURRENT_CHALLENGE_POS");

        presenter.getData((Zaruba)args.getSerializable("CURRENT_CHALLENGE"));

    }

    @Override
    public ScoreListPresenter getPresenter() {
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
    public void initAdapter() {
        adapter = new ScoreAdapter(presenter);
        conditions.setLayoutManager(new LinearLayoutManager(getContext()));
        conditions.setAdapter(adapter);
    }

    @Override
    public void notifyAdapter(){
        adapter.notifyDataSetChanged();
    }
}
