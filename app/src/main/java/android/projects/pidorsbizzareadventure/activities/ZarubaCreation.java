package android.projects.pidorsbizzareadventure.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.projects.pidorsbizzareadventure.R;
import android.projects.pidorsbizzareadventure.pojo.Participator;
import android.projects.pidorsbizzareadventure.pojo.Zaruba;
import android.view.View;
import android.widget.TextView;
import static android.projects.pidorsbizzareadventure.pojo.FBConstants.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ZarubaCreation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zaruba_creation);
    }


    public void saveZaruba(View view) {
        Map<String, String> values = collectValues();
        List<Participator> partis = new ArrayList<>();
        partis.add(new Participator(values.get("nick"), USER_UID));

        Zaruba newZaruba = new Zaruba(
                null,
                values.get("title"),
                partis,
                values.get("description"),
                values.get("winReward"),
                values.get("looseReward"),
                null,
                USER_UID);
        Intent next = new Intent(this, ConditionsCreation.class);
        next.putExtra("zaruba", newZaruba);
        startActivity(next);
    }

    private Map<String, String> collectValues(){
        Map<String, String> values = new HashMap<>();
        values.put("title", ((TextView)findViewById(R.id.editName)).getText().toString());
        values.put("description", ((TextView)findViewById(R.id.editDescription)).getText().toString());
        values.put("nick", ((TextView)findViewById(R.id.textViewPartis)).getText().toString());
        values.put("winReward", ((TextView)findViewById(R.id.editWin)).getText().toString());
        values.put("looseReward", ((TextView)findViewById(R.id.editLoose)).getText().toString());

        return values;
    }


}
