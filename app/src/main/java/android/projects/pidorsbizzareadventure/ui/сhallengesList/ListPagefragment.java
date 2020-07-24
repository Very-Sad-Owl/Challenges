package android.projects.pidorsbizzareadventure.ui.сhallengesList;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.projects.pidorsbizzareadventure.R;


public class ListPagefragment extends Fragment {

    private ListFragment listFragment;

    public ListPagefragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        listFragment = new ListFragment();

        getChildFragmentManager()
                .beginTransaction()
                .replace(R.id.list_fragment_place, listFragment)
                .commit();

        return inflater.inflate(R.layout.fragment_list_pagefragment, container, false);
    }
}
