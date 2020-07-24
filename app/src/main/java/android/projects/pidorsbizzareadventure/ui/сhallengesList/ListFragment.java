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

//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    public ListFragment() {
//        // Required empty public constructor
//    }
//
//    public static ListFragment newInstance(String param1, String param2) {
//        ListFragment fragment = new ListFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        storage = new ChallengesStorage();
        presenter = new ListFragmentPresenter(this, storage);

        adapter = new ChallengeListAdapter();

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//       }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        challenges = view.findViewById(R.id.challengeList);


        challenges.setLayoutManager(new LinearLayoutManager(view.getContext()));
        challenges.setAdapter(adapter);

        presenter.getData();
    }

    @Override
    protected ListFragmentPresenter getPresenter() {
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
        adapter.add(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void openInfo(Zaruba zaruba) {

    }
}
