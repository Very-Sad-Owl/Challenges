package android.projects.pidorsbizzareadventure.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
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

    private EditText editedCondition;
    private NumberPicker editedPenalty;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.condition_dialog, null);

        builder.setView(view)
                .setTitle("Condition Editing")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Apply", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        editedCondition = view.findViewById(R.id.editTextRewriteCondition);
        editedPenalty = view.findViewById(R.id.numberPickerEditPenalty);
        editedPenalty.setMinValue(1);
        editedPenalty.setMaxValue(10);

        return builder.create();

    }
}
