package android.projects.pidorsbizzareadventure.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.projects.pidorsbizzareadventure.R;
import android.projects.pidorsbizzareadventure.adapters.UnitsAdapter;
import android.projects.pidorsbizzareadventure.pojo.ChallengesContainer;
import android.projects.pidorsbizzareadventure.pojo.FirebaseHelper;
import android.projects.pidorsbizzareadventure.pojo.Zaruba;
import android.util.Log;

public class ZarubasList extends AppCompatActivity {

    RecyclerView challenges;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zarubas_list);

        challenges = findViewById(R.id.challengeList);

        Intent fromCreation = getIntent();

        UnitsAdapter adapter = new UnitsAdapter(ChallengesContainer.getInstance());
        challenges.setLayoutManager(new LinearLayoutManager(this));
        challenges.setAdapter(adapter);

        //FirebaseHelper.FBReader.readChallengesFromFB(ChallengesContainer.getInstance(), "CHALLENGE", adapter);

        Log.i("test", ChallengesContainer.getInstance().toString());

    }
}
