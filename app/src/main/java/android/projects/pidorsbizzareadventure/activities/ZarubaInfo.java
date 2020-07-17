package android.projects.pidorsbizzareadventure.activities;

import android.content.Intent;
import android.os.Bundle;
import android.projects.pidorsbizzareadventure.R;
import android.projects.pidorsbizzareadventure.adapters.ConditionsAdapter;
import android.projects.pidorsbizzareadventure.pojo.ChallengesContainer;
import android.projects.pidorsbizzareadventure.pojo.Zaruba;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import static android.projects.pidorsbizzareadventure.storage.firebase.FBConstants.*;

public class ZarubaInfo extends AppCompatActivity {

    Zaruba currentChallenge;

    TextView title;
    TextView description;
    TextView reward;
    TextView punishment;

    TextView titleEdit;
    TextView descriptionEdit;
    TextView rewardEdit;
    TextView punishmentEdit;

    ViewSwitcher switcher;

    Toolbar toolbar;

    RecyclerView conditions;

    List<View> modified;

    private ActionMode actionMode = null;

    final int REQUESTCODE_EDIT = 100;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zaruba_info);
        initScreen();

    }

    private void initScreen(){
        Intent fromChoose = getIntent();
        int pos = fromChoose.getIntExtra("pos", 0);
        currentChallenge = ChallengesContainer.getInstance().get(pos);
        Log.i("recievedByInfo", currentChallenge.toString());

        title = findViewById(R.id.textViewInfoTitle);
        description = findViewById(R.id.textViewInfoDescription);
        reward = findViewById(R.id.textViewReward);
        punishment = findViewById(R.id.textViewPunishment);

        titleEdit = findViewById(R.id.editTextInfoTitle);
        descriptionEdit = findViewById(R.id.editTextInfoDescription);
        rewardEdit = findViewById(R.id.editTextReward);
        punishmentEdit = findViewById(R.id.editTextPunishment);

//        titleEdit.addTextChangedListener(new MultipleTextWatcher(titleEdit));
//        descriptionEdit.addTextChangedListener(new MultipleTextWatcher(descriptionEdit));
//        rewardEdit.addTextChangedListener(new MultipleTextWatcher(rewardEdit));
//        punishmentEdit.addTextChangedListener(new MultipleTextWatcher(punishmentEdit));

        switcher = (ViewSwitcher) findViewById(R.id.switcher);

        toolbar = findViewById(R.id.editToolBar);

        modified = new ArrayList<>();

        LinearLayoutManager manager = new LinearLayoutManager(this);
        conditions = findViewById(R.id.recyclerConditions);
        conditions.setLayoutManager(manager);
        ConditionsAdapter adapter = new ConditionsAdapter(currentChallenge.getConditions());
        conditions.setAdapter(adapter);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setValues();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.munu_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.itemEdit:
                Toast.makeText(this, "Edit started", Toast.LENGTH_SHORT).show();
                if (actionMode != null){
                    return false;
                } else{
                    actionMode = startActionMode(mActionModeCallback);
                    //switcher.showNext();
                    return true;
                }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setDisplayShowHomeEnabled(false);

            mode.getMenuInflater().inflate(R.menu.menu_done, menu);
            mode.setTitle("Editing Mode");
            setEditValues();
            switcher.showNext();
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()){
                case R.id.itemDone:
                    Toast.makeText(getApplicationContext(), "Done", Toast.LENGTH_SHORT).show();
                    changeValues();
                    setValues();
                    switcher.showPrevious();
                    updateData(currentChallenge);
                    mode.finish();
                    return true;
                case R.id.itemCancel:
                    Toast.makeText(getApplicationContext(), "Cancel", Toast.LENGTH_SHORT).show();
                    setValues();
                    switcher.showPrevious();
                    mode.finish();
                    return true;
                case R.id.home:
                    finish();
                    return true;
                default:
                    return false;
            }
        }


        @Override
        public void onDestroyActionMode(ActionMode mode) {
            actionMode = null;
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    };


    private void changeValues(){
        currentChallenge.setTitle(titleEdit.getText().toString());
        currentChallenge.setDescription(descriptionEdit.getText().toString());
        currentChallenge.setReward(rewardEdit.getText().toString());
        currentChallenge.setPunishment(punishmentEdit.getText().toString());
    }

    public void setValues(){
        title.setText(currentChallenge.getTitle());
        description.setText(currentChallenge.getDescription());
        reward.setText(currentChallenge.getReward());
        punishment.setText(currentChallenge.getPunishment());
    }

    private void setEditValues(){
        titleEdit.setText(currentChallenge.getTitle());
        descriptionEdit.setText(currentChallenge.getDescription());
        rewardEdit.setText(currentChallenge.getReward());
        punishmentEdit.setText(currentChallenge.getPunishment());
    }

    public void editConditions(View view) {
//        Intent toEdit = new Intent(this, EditChallenge.class);
//        startActivityForResult(toEdit, REQUESTCODE_EDIT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUESTCODE_EDIT) {
            if (resultCode == RESULT_OK) {

            }
        }
    }

//    private class MultipleTextWatcher implements TextWatcher {
//
//        private View view;
//
//        private MultipleTextWatcher(View view) {
//            this.view = view;
//        }
//
//        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
//        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
//        public void afterTextChanged(Editable editable) {
//            modified.add(view);
//        }
//    }

    public static void updateData(Zaruba obj){
        ref.child(CHALLENGE_PATH).child(obj.getUid()).setValue(obj);
    }
}
