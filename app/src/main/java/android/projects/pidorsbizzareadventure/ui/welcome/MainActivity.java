package android.projects.pidorsbizzareadventure.ui.welcome;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.projects.pidorsbizzareadventure.R;
import android.projects.pidorsbizzareadventure.ui.welcome.admitFragment.Admit;
import android.projects.pidorsbizzareadventure.ui.welcome.joinFragment.JoinChallenge;
import android.widget.Spinner;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Fragment join;
    Fragment admit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent fromAuth = getIntent();
        setContentView(R.layout.activity_main);

        admit = new Admit();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_admit, admit)
                .commit();

        join = new JoinChallenge();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_join, join)
                .commit();

//        challenges = findViewById(R.id.spinnerChallenges);
//        conditions = findViewById(R.id.spinnerConditions);
//
//        titles = new ArrayList<>();
//
//        join = new JoinChallenge();
//        getSupportFragmentManager().beginTransaction()
//                .replace(R.id.fragment_join, join)
//                .commit();
//
//
//        readChallengesFromFBAsync(ChallengesContainer.getInstance(), new FBCallback() {
//            @Override
//            public void onCallback() {
//                ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(),
//                android.R.layout.simple_spinner_item, ChallengesContainer.getChallengesTitles());
//                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                challenges.setAdapter(adapter);
//
//                challenges.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                    public void onItemSelected(AdapterView<?> parent,
//                                               View itemSelected, int selectedItemPosition, long selectedId) {
//
//                        ArrayAdapter<String> conAdapter = new ArrayAdapter<>(getApplicationContext(),
//                                android.R.layout.simple_spinner_item, ChallengesContainer.getChallengeConditions(selectedItemPosition));
//                        conAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                        conditions.setAdapter(conAdapter);
//                        selectedChallenge = selectedItemPosition;
//                    }
//                    public void onNothingSelected(AdapterView<?> parent) {
//                    }
//                });
//
//                conditions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                    public void onItemSelected(AdapterView<?> parent,
//                                               View itemSelected, int selectedItemPosition, long selectedId) {
//                        selectedCondition = selectedItemPosition;
//                    }
//                    public void onNothingSelected(AdapterView<?> parent) {
//                    }
//                });
//            }
//        });
//
//        if(getSupportActionBar()!=null) getSupportActionBar().hide();
    }

//    public void createZaruba(View view) {
//        Intent toZaruba = new Intent(this, ZarubaCreation.class);
//        startActivity(toZaruba);
//    }
//
//    public static void readChallengesFromFBAsync(final List<Zaruba> list, final FBCallback callback){
//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference(CHALLENGE_PATH);
//        ValueEventListener listener = new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if(!list.isEmpty()) list.clear();
//                for( DataSnapshot ds : dataSnapshot.getChildren() ) {
//                    Zaruba zaruba = ds.getValue(Zaruba.class);
//                    Log.i("mainact", zaruba.toString() + " " + USER_UID);
//                    for(Participator participator : zaruba.getParticipators()) {
//                        if (participator.getUid().equals(USER_UID)) {
//                            list.add(zaruba);
//                        }
//                    }
//                }
//                callback.onCallback();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        };
//        reference.addValueEventListener(listener);
//    }
//
//    public void admit(View view) {
//        Zaruba current = ChallengesContainer.getInstance().get(selectedChallenge);
//        Log.i("main obj", current.toString());
//        Participator p = current.getParticipators().get(0);
//        p.setTotalScore(p.getTotalScore() + current.getConditions().get(selectedCondition).getPenalty());
//        Log.i("main obj", current.toString());
//        updateData(current);
//    }
//
//
//    public static void updateData(Zaruba obj){
//        ref.child(CHALLENGE_PATH).child(obj.getUid()).setValue(obj);
//    }
//
//    private interface FBCallback{
//        void onCallback();
//    }
}
