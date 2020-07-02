package android.projects.pidorsbizzareadventure;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ZarubasList extends AppCompatActivity {

    RecyclerView challenges;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zarubas_list);

        challenges = findViewById(R.id.challengeList);

        Intent fromCreation = getIntent();
        Zaruba newZaruba = (Zaruba)fromCreation.getSerializableExtra("zaruba");
        ChallengesContainer.getInstance().add(newZaruba);

        UnitsAdapter adapter = new UnitsAdapter(ChallengesContainer.getInstance());
        challenges.setLayoutManager(new LinearLayoutManager(this));
        challenges.setAdapter(adapter);
//
//        final ArrayAdapter<Zaruba> adapter = new ArrayAdapter<>
//                (getApplicationContext(), android.R.layout.simple_list_item_1, ChallengesContainer.getInstance());
//        //challenges.setAdapter(adapter);
//        //adapter.notifyDataSetChanged();
//
        Log.i("test", ChallengesContainer.getInstance().toString());
//
//        //adapter.notifyDataSetChanged();
//
////        TextView textView = findViewById(R.id.textViewTest);
////        textView.setText(newZaruba.toString());
    }
}
