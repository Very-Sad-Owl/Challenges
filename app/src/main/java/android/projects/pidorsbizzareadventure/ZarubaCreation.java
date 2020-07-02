package android.projects.pidorsbizzareadventure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class ZarubaCreation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zaruba_creation);
    }


    public void saveZaruba(View view) {
        Map<String, String> values = collectValues();

        if(checkInputValidation(values)) {
            Zaruba newZaruba = new Zaruba(values.get("title"), values.get("participators"),
                    values.get("description"), values.get("winReward"), values.get("looseReward"),
                    values.get("conditions"));
            Intent toList = new Intent(this, ZarubasList.class);
            toList.putExtra("zaruba", newZaruba);
            startActivity(toList);
        } else {
            Toast.makeText(this, "Fill all required fields", Toast.LENGTH_SHORT).show();
        }
    }

    private Map<String, String> collectValues(){
        Map<String, String> values = new HashMap<>();
        values.put("title", ((TextView)findViewById(R.id.editName)).getText().toString());
        values.put("participators", ((TextView)findViewById(R.id.editPartis)).getText().toString());
        values.put("description", ((TextView)findViewById(R.id.editDescription)).getText().toString());
        values.put("winReward", ((TextView)findViewById(R.id.editWin)).getText().toString());
        values.put("looseReward", ((TextView)findViewById(R.id.editLoose)).getText().toString());
        values.put("conditions", ((TextView)findViewById(R.id.editConditions)).getText().toString());

        return values;
    }

    private boolean checkInputValidation(Map<String, String> values){
        return !values.get("participators").isEmpty();
    }

}
