package android.projects.pidorsbizzareadventure.ui.welcome;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.projects.pidorsbizzareadventure.ui.welcome.admitFragment.Admit;
import android.projects.pidorsbizzareadventure.ui.welcome.joinFragment.JoinChallenge;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.projects.pidorsbizzareadventure.R;


public class WelcomePageFragment extends Fragment {

    Fragment join;
    Fragment admit;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        admit = new Admit();
        getChildFragmentManager().beginTransaction()
                .replace(R.id.fragment_admit_place, admit)
                .commit();

        join = new JoinChallenge();
        getChildFragmentManager().beginTransaction()
                .replace(R.id.fragment_join_place, join)
                .commit();

        return inflater.inflate(R.layout.fragment_welcome_page, container, false);
    }
}
