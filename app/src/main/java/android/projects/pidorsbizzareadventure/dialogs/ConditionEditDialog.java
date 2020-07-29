package android.projects.pidorsbizzareadventure.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.projects.pidorsbizzareadventure.R;
import android.projects.pidorsbizzareadventure.common.BaseDialog;
import android.projects.pidorsbizzareadventure.common.BaseFragment;
import android.projects.pidorsbizzareadventure.common.BasePresenter;
import android.projects.pidorsbizzareadventure.pojo.Condition;
import android.projects.pidorsbizzareadventure.pojo.Zaruba;
import android.projects.pidorsbizzareadventure.storage.local.ChallengesStorage;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class ConditionEditDialog extends BaseDialog<ConditionEditPresenter> implements ConditionEditView {

    private EditText editedCondition;
    private NumberPicker editedPenalty;

    private onConditionEditDialogClickListener callback;

    private ConditionEditPresenter presenter;
    private ChallengesStorage storage;

    private ConditionEditDialog(onConditionEditDialogClickListener callback){
        this.callback = callback;
    }

    public static ConditionEditDialog newInstance(Condition condition, onConditionEditDialogClickListener callback){
        Bundle args = new Bundle();
        args.putSerializable("CURR_CHALLENGE", condition);
        ConditionEditDialog dialog = new ConditionEditDialog(callback);
        dialog.setArguments(args);

        return dialog;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

//        if(context instanceof onConditionEditDialogClickListener){
//            callback = (onConditionEditDialogClickListener) context;
//        } else {
//            throw new ClassCastException("class must implement onConditionEditDialogClickListener");
//        }

        storage = new ChallengesStorage();
        presenter = new ConditionEditPresenter(this, storage);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.condition_dialog, null);

        Bundle args = getArguments();
        Condition current = (Condition)args.getSerializable("CURR_CHALLENGE");

        editedCondition = view.findViewById(R.id.editTextRewriteCondition);
        editedPenalty = view.findViewById(R.id.numberPickerEditPenalty);
        editedPenalty.setMinValue(1);
        editedPenalty.setMaxValue(10);

        editedCondition.setText(current.getCondition());
        editedPenalty.setValue(current.getPenalty());

        builder.setView(view)
                .setTitle("Condition Editing")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        callback.cancel();
                    }
                })
                .setPositiveButton("Apply", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        callback.apply(editedCondition.getText().toString(), editedPenalty.getValue());
                    }
                });

        return builder.create();
    }

    @Override
    protected ConditionEditPresenter getPresenter() {
        return null;
    }

    @Override
    public void showData() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError() {

    }

    public interface onConditionEditDialogClickListener {
        void apply(String newText, int newValue);
        void cancel();
    }
}
