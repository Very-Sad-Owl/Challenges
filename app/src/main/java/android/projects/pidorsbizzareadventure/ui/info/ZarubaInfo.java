package android.projects.pidorsbizzareadventure.ui.info;

import android.content.Intent;
import android.os.Bundle;
import android.projects.pidorsbizzareadventure.R;
import android.projects.pidorsbizzareadventure.pojo.Zaruba;
import android.projects.pidorsbizzareadventure.ui.info.editing.ChallengeEditFragment;
import android.projects.pidorsbizzareadventure.ui.info.show.ChallengeInfoFragment;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;


import static android.projects.pidorsbizzareadventure.storage.firebase.FBConstants.*;

public class ZarubaInfo extends AppCompatActivity
        implements ChallengeInfoFragment.onEditCallback, ChallengeEditFragment.onOptionSelected {

    //Toolbar toolbar;
    private ActionMode actionMode = null;

    Fragment info;
    Fragment edit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zaruba_info);
        initScreen();
    }

    private void initScreen(){
        Intent fromChoose = getIntent();
        Bundle currentChallenge = fromChoose.getExtras();

        Log.i("ssd", currentChallenge.get("CURRENT_CHALLENGE_POS") + "");

        info = ChallengeInfoFragment.newInstance(currentChallenge);
        edit = ChallengeEditFragment.newInstance(currentChallenge);

        info.setArguments(currentChallenge);

        //toolbar = findViewById(R.id.editToolBar);
        //setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setDisplayShowHomeEnabled(true);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentInfo, info)
                .commit();
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.munu_edit, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()){
//            case R.id.itemEdit:
//                Toast.makeText(this, "Edit started", Toast.LENGTH_SHORT).show();
//                if (actionMode != null){
//                    return false;
//                } else{
//                    actionMode = startActionMode(mActionModeCallback);
//                    return true;
//                }
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }

    private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setDisplayShowHomeEnabled(false);

            mode.getMenuInflater().inflate(R.menu.menu_done, menu);
            mode.setTitle("Editing Mode");

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentInfo, edit)
                    .commit();

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

                    mode.finish();
                    return true;
                case R.id.itemCancel:
                    Toast.makeText(getApplicationContext(), "Cancel", Toast.LENGTH_SHORT).show();

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

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentInfo, info)
                    .commit();
        }
    };


    @Override
    public void enableEditMode() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentInfo, edit)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void apply() {
        Toast.makeText(this, "Changes Saved", Toast.LENGTH_SHORT).show();
        getSupportFragmentManager()
                .popBackStack();
//        getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.fragmentInfo, info)
//                .commit();
    }

    @Override
    public void cancel() {
        getSupportFragmentManager()
                .popBackStack();
//        getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.fragmentInfo, info)
//                .commit();
    }

}
