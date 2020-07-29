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
import android.projects.pidorsbizzareadventure.pojo.Condition;
import android.projects.pidorsbizzareadventure.pojo.Zaruba;
import android.projects.pidorsbizzareadventure.storage.local.ChallengesStorage;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class ConditionsListFragment extends BaseFragment<ConditionsListPresenter> implements ConditionsListView {

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
        storage = new ChallengesStorage();
        presenter = new ConditionsListPresenter(this, storage);

        adapter = new ConditionsAdapter(getParentFragmentManager());
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


        conditions.setLayoutManager(new LinearLayoutManager(view.getContext()));
        conditions.setAdapter(adapter);

        Bundle args = getArguments();
        currPos = args.getInt("CURRENT_CHALLENGE_POS");

        presenter.getData(currPos);
    }

    @Override
    protected ConditionsListPresenter getPresenter() {
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
        adapter.add(data);
        adapter.notifyDataSetChanged();
    }
}
