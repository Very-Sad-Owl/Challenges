package android.projects.pidorsbizzareadventure.ui.сhallengesList;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.projects.pidorsbizzareadventure.common.BaseFragment;
import android.projects.pidorsbizzareadventure.pojo.Zaruba;
import android.projects.pidorsbizzareadventure.storage.local.ChallengesStorage;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.projects.pidorsbizzareadventure.R;

import java.util.List;

public class ListFragment extends BaseFragment<ListFragmentPresenter> implements ListView {

    private RecyclerView challenges;
    private ChallengeListAdapter adapter;
    private ListFragmentPresenter presenter;
    private ChallengesStorage storage;

    ListFragment(){
        Log.i("placeholder", "constructor");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("placeholder", "resume");
        //presenter.getData();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        storage = new ChallengesStorage();
        presenter = new ListFragmentPresenter(this, storage);
        Log.i("placeholder", "onAttach");

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("placeholder", "onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.i("placeholder", "onCreateView");
        //presenter.getData();
        presenter.loadData();
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        challenges = view.findViewById(R.id.challengeList);
        Log.i("placeholder", "onViewCreated");

//        challenges.setLayoutManager(new LinearLayoutManager(view.getContext()));
//        challenges.setAdapter(adapter);

    }

    @Override
    public ListFragmentPresenter getPresenter() {
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
    public void showData(List<Zaruba> data) {
        Log.i("chlist", data.toString());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void initAdapter(List<Zaruba> data) {
        adapter = new ChallengeListAdapter(data);
        challenges.setLayoutManager(new LinearLayoutManager(getContext()));
        challenges.setAdapter(adapter);
    }

}
