package android.projects.pidorsbizzareadventure.ui.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.projects.pidorsbizzareadventure.R;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class ConditionEditDialog extends AppCompatDialogFragment {

    EditText conditionText;
    NumberPicker picker;
    onConditionEditDialogListener callback;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof onConditionEditDialogListener){
            callback = (onConditionEditDialogListener) context;
        } else {
            throw new ClassCastException("class must implement onConditionEditDialogListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.condition_dialog, null);

        conditionText = view.findViewById(R.id.editTextRewriteCondition);
        picker = view.findViewById(R.id.numberPickerEditPenalty);

        builder.setView(view)
                .setTitle("Edit Condition")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        callback.apply(conditionText.getText().toString(), picker.getValue());
                    }
                });

        return builder.create();
    }

    public interface onConditionEditDialogListener {
        void apply(String condition, int penalty);
    }
}
