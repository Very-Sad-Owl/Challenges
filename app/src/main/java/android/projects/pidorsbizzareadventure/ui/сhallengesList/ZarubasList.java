package android.projects.pidorsbizzareadventure.ui.сhallengesList;

import android.content.Intent;
import android.os.Bundle;
import android.projects.pidorsbizzareadventure.R;
import android.projects.pidorsbizzareadventure.pojo.ChallengesContainer;
import android.util.Log;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class ZarubasList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zarubas_list);

        Intent fromCreation = getIntent();

        Fragment recycler = new ListFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.recycler_fragment, recycler)
                .commit();

        //FirebaseHelper.FBReader.readChallengesFromFB(ChallengesContainer.getInstance(), "CHALLENGE", adapter);

        Log.i("test", ChallengesContainer.getInstance().toString());

    }
}
