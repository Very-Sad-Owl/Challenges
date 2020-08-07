package android.projects.pidorsbizzareadventure.ui.conditionsList;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.projects.pidorsbizzareadventure.R;
import android.projects.pidorsbizzareadventure.adapters.ConditionsAdapter;
import android.projects.pidorsbizzareadventure.common.BaseFragment;
import android.projects.pidorsbizzareadventure.common.BaseFragmentWithAdapter;
import android.projects.pidorsbizzareadventure.pojo.Condition;
import android.projects.pidorsbizzareadventure.pojo.Zaruba;
import android.projects.pidorsbizzareadventure.storage.local.ChallengesStorage;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class ConditionsListFragment extends BaseFragmentWithAdapter<ConditionsListPresenter> implements ConditionsListView {

    private ConditionsListPresenter presenter;
    private ChallengesStorage storage;
    private ConditionsAdapter adapter;

    private RecyclerView conditions;

    int currPos;

    public static ConditionsListFragment newInstance(Bundle args){
        ConditionsListFragment fragment = new ConditionsListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public ConditionsListFragment(){}

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        Bundle args = getArguments();

        storage = new ChallengesStorage();
        presenter = new ConditionsListPresenter(this, new Zaruba((Zaruba)args.getSerializable("CURRENT_CHALLENGE")));


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
        presenter.attachAdapter();


        //presenter.getData((Zaruba)args.getSerializable("CURRENT_CHALLENGE"));

    }

    @Override
    public ConditionsListPresenter getPresenter() {
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
    public void showData(List<Condition> data) {
//        adapter.add(data);
//        adapter.notifyDataSetChanged();
    }

    @Override
    public void initAdapter() {
        adapter = new ConditionsAdapter(getParentFragmentManager(), presenter);
        conditions.setLayoutManager(new LinearLayoutManager(getContext()));
        conditions.setAdapter(adapter);
    }

    @Override
    public void notifyAdapter(){
        adapter.notifyDataSetChanged();
    }
}
