package android.projects.pidorsbizzareadventure.ui.creation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.projects.pidorsbizzareadventure.R;
import android.projects.pidorsbizzareadventure.pojo.Zaruba;
import android.projects.pidorsbizzareadventure.storage.local.ChallengesStorage;
import android.projects.pidorsbizzareadventure.ui.creation.conditionsstep.ConditionsInputFragment;
import android.projects.pidorsbizzareadventure.ui.creation.metainfostep.MetaInfoInputFragment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ChallengeCreation extends AppCompatActivity implements CreationView {

    Button next;
    Button previous;
    Fragment meta;
    Fragment cond;

    CreationPresenter presenter;
    ChallengesStorage storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge_creation);

        meta = new MetaInfoInputFragment();
        cond = new ConditionsInputFragment();

        storage = new ChallengesStorage();
        presenter = new CreationPresenter(this, storage);

        getSupportFragmentManager().beginTransaction().replace(R.id.layframe, meta, "kavo").commit();

    }

    @Override
    public void nextStep() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if(meta != null && meta.isVisible()){
            transaction
                    .replace(R.id.layframe, cond, "kavo")
                    .commit();
            transaction.addToBackStack(null);
            next.setText("Finish");
            previous.setVisibility(View.VISIBLE);
        }
//        else if (cond != null && cond.isVisible()){
//            Toast.makeText(this, "finished", Toast.LENGTH_SHORT).show();
//        }
    }

    @Override
    public void previousStep() {
        if(meta != null && meta.isVisible()){
            getSupportFragmentManager().popBackStack();
        } else if (cond != null && cond.isVisible()){
            getSupportFragmentManager().popBackStack();
        }
    }

    @Override
    public void finishCreation(Zaruba result) {
        Toast.makeText(this, result.getTitle(), Toast.LENGTH_SHORT).show();
        Log.i("cc", result.toString());
        presenter.submit(result);
//        Intent toInfo = new Intent(this, ZarubaInfo.class);
//        startActivity(toInfo);
    }

}
