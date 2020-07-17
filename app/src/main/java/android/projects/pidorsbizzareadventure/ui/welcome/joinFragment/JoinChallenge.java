package android.projects.pidorsbizzareadventure.ui.welcome.joinFragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.projects.pidorsbizzareadventure.R;
import android.projects.pidorsbizzareadventure.activities.ZarubaInfo;
import android.projects.pidorsbizzareadventure.common.BaseFragment;
import android.projects.pidorsbizzareadventure.pojo.ChallengesContainer;
import android.projects.pidorsbizzareadventure.pojo.Participator;
import android.projects.pidorsbizzareadventure.pojo.Zaruba;
import android.projects.pidorsbizzareadventure.storage.local.ChallengesStorage;
import android.projects.pidorsbizzareadventure.ui.welcome.admitFragment.WelcomePresenter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.projects.pidorsbizzareadventure.storage.firebase.FBConstants.*;


public class JoinChallenge extends BaseFragment<JoinPresenter> implements JoinView{

    private EditText uid;
    private Button join;
    private JoinPresenter presenter;
    private ChallengesStorage storage;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_join_challenge, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //presenter = new WelcomePresenter(this);
        join = view.findViewById(R.id.buttonJoinChallenge);
        uid = view.findViewById(R.id.editTextChallengeUIDInput);

        ChallengesStorage storage = new ChallengesStorage();
        presenter = new JoinPresenter(this, storage);

        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.findChallengeToJoin(uid.getText().toString());
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    @Override
    protected JoinPresenter getPresenter() {
        return presenter;
    }

    @Override
    public void addNewChallenge(Zaruba zaruba) {
        Log.i("joinfr", zaruba.toString());
        Intent openInfo = new Intent(getContext(), ZarubaInfo.class);
        openInfo.putExtra("currentChallenge", zaruba);
        startActivity(openInfo);
    }
}
