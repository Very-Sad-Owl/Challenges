package android.projects.pidorsbizzareadventure.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.preference.Preference;
import android.projects.pidorsbizzareadventure.R;
import android.projects.pidorsbizzareadventure.pojo.ChallengesContainer;
import android.projects.pidorsbizzareadventure.pojo.FirebaseHelper;
import android.projects.pidorsbizzareadventure.pojo.Participator;
import android.projects.pidorsbizzareadventure.pojo.Zaruba;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.projects.pidorsbizzareadventure.pojo.FBConstants.*;

public class MainActivity extends AppCompatActivity {

    Spinner challenges;
    Spinner conditions;
    List<String> titles;
    int selectedChallenge;
    int selectedCondition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent fromAuth = getIntent();
        setContentView(R.layout.activity_main);

        challenges = findViewById(R.id.spinnerChallenges);
        conditions = findViewById(R.id.spinnerConditions);

        titles = new ArrayList<>();


        //FirebaseHelper.FBReader.readChallengesFromFB(ChallengesContainer.getInstance(), "CHALLENGE");
        //Log.i("main", ChallengesContainer.getChallengesTitles().toString());

//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
//                android.R.layout.simple_spinner_item, ChallengesContainer.getChallengesTitles());
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        challenges.setAdapter(adapter);

        readChallengesFromFBAsync(ChallengesContainer.getInstance(), new FBCallback() {
            @Override
            public void onCallback() {
                ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_spinner_item, ChallengesContainer.getChallengesTitles());
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                challenges.setAdapter(adapter);

                challenges.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent,
                                               View itemSelected, int selectedItemPosition, long selectedId) {

                        ArrayAdapter<String> conAdapter = new ArrayAdapter<>(getApplicationContext(),
                                android.R.layout.simple_spinner_item, ChallengesContainer.getChallengeConditions(selectedItemPosition));
                        conAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        conditions.setAdapter(conAdapter);
                        selectedChallenge = selectedItemPosition;
                    }
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });

                conditions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent,
                                               View itemSelected, int selectedItemPosition, long selectedId) {
                        selectedCondition = selectedItemPosition;
                    }
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });
            }
        });

        if(getSupportActionBar()!=null) getSupportActionBar().hide();
    }

    public void createZaruba(View view) {
        Intent toZaruba = new Intent(this, ZarubaCreation.class);
        startActivity(toZaruba);
    }

    public static void readChallengesFromFBAsync(final List<Zaruba> list, final FBCallback callback){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference(CHALLENGE_PATH);
        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(!list.isEmpty()) list.clear();
                for( DataSnapshot ds : dataSnapshot.getChildren() ) {
                    Zaruba zaruba = ds.getValue(Zaruba.class);
                    Log.i("mainact", zaruba.toString() + " " + USER_UID);
                    for(Participator participator : zaruba.getParticipators()) {
                        if (participator.getUid().equals(USER_UID)) {
                            list.add(zaruba);
                        }
                    }
                }
                callback.onCallback();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        reference.addValueEventListener(listener);
    }

    public void admit(View view) {
        Zaruba current = ChallengesContainer.getInstance().get(selectedChallenge);
        Log.i("main obj", current.toString());
        Participator p = current.getParticipators().get(0);
        p.setTotalScore(p.getTotalScore() + current.getConditions().get(selectedChallenge).getPenalty());
        Log.i("main obj", current.toString());
        FirebaseHelper.FBWriter.updateData("CHALLENGE", current);

        final List<Zaruba> arr = new ArrayList<>();
        find(arr);
        Log.i("findFB", "heeeeey" + arr.toString());
    }

    public static void find(final List<Zaruba> arr){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("C1GRoO4Fu4MEvdz4TQVrFBxs5Bo2");
        ref.child("CHALLENGE").orderByChild("title").equalTo("title").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("findFB", dataSnapshot.toString());
                for (DataSnapshot userSnapshot: dataSnapshot.getChildren()) {
                    Zaruba zaruba = userSnapshot.getValue(Zaruba.class);
                    arr.add(zaruba);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                throw databaseError.toException();
            }
        });

//        Query test = ref.child("CHALLENGE").orderByChild("description").startAt("None").endAt("None" + "\uf8ff");
//        test.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot task : dataSnapshot.getChildren()) {
//                    Log.i("findd", dataSnapshot.getKey());
//                    Log.i("findd", dataSnapshot.child("None").getValue(String.class));
//
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//
//        });

    }

    private interface FBCallback{
        void onCallback();
    }
}
