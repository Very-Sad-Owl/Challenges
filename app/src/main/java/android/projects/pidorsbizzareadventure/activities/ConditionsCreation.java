package android.projects.pidorsbizzareadventure.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.projects.pidorsbizzareadventure.R;
import android.projects.pidorsbizzareadventure.adapters.ConditionsAdapter;
import android.projects.pidorsbizzareadventure.pojo.ChallengesContainer;
import android.projects.pidorsbizzareadventure.pojo.Condition;
import android.projects.pidorsbizzareadventure.storage.firebase.FBConstants;
import android.projects.pidorsbizzareadventure.pojo.Zaruba;
import android.projects.pidorsbizzareadventure.ui.сhallengesList.ZarubasList;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ConditionsCreation extends AppCompatActivity {

    NumberPicker picker;
    EditText textCondition;
    List<Condition> con;
    Zaruba fromPrev;
    RecyclerView conditions;
    ConditionsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conditions_creation);

        picker = findViewById(R.id.numberPicker);
        picker.setMinValue(1);
        picker.setMaxValue(10);
        picker.setValue(1);
        textCondition = findViewById(R.id.editTextNewConditions);

        con = new ArrayList<>();

        LinearLayoutManager manager = new LinearLayoutManager(this);
        conditions = findViewById(R.id.conditionsList);
        conditions.setLayoutManager(manager);
        //adapter = new ConditionsAdapter(con);
        conditions.setAdapter(adapter);

        Intent fromCreation = getIntent();
        fromPrev = (Zaruba)fromCreation.getSerializableExtra("zaruba");
    }

    public void addCondition(View view) {
        Condition newCondition = new Condition();
        newCondition.setCondition(textCondition.getText().toString());
        newCondition.setPenalty(Integer.parseInt(picker.getValue() + ""));
        con.add(newCondition);
        adapter.notifyDataSetChanged();
        setDefaultFieldsValues();
    }

    private void setDefaultFieldsValues(){
        textCondition.getText().clear();
        picker.setValue(1);
    }

    public void submit(View view){
        fromPrev.setConditions(con);
        ChallengesContainer.getInstance().add(fromPrev);
        //FirebaseHelper.FBWriter.writeToFB("CHALLENGE", fromPrev);
        writeToFB(fromPrev);
        Intent next = new Intent(this, ZarubasList.class);
        startActivity(next);
    }

    public static void writeToFB(Zaruba object){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference(FBConstants.CHALLENGE_PATH);
        String uid = reference.push().getKey();
        object.setUid(uid);
        reference.child(uid).setValue(object);
    }
}
