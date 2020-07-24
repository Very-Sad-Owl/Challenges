package android.projects.pidorsbizzareadventure.adapters;

import android.projects.pidorsbizzareadventure.R;
import android.projects.pidorsbizzareadventure.dialogs.ConditionEditDialog;
import android.projects.pidorsbizzareadventure.pojo.Condition;
import android.projects.pidorsbizzareadventure.pojo.Zaruba;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ConditionsAdapter extends RecyclerView.Adapter<ConditionsAdapter.ConditionViewHolder> {

    private List<Condition> conditions;

    public ConditionsAdapter() {
        this.conditions = new ArrayList<>();
    }

    @NonNull
    @Override
    public ConditionViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        View unit = LayoutInflater.from(parent.getContext()).inflate
                (R.layout.condition_unit, parent, false);

        return new ConditionViewHolder(unit);
    }

    @Override
    public void onBindViewHolder(@NonNull ConditionViewHolder holder, int position) {
        Condition currentCondition = conditions.get(position);
        holder.condition.setText(currentCondition.getCondition());
        holder.penalty.setText(String.format("%d", currentCondition.getPenalty()));

    }

    @Override
    public int getItemCount() {
        return conditions.size();
    }

    public void add(Condition data) {
        conditions.add(data);
    }

    class ConditionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView condition;
        private TextView penalty;
        private LinearLayout layout;

        public ConditionViewHolder(@NonNull final View itemView) {
            super(itemView);
            condition = itemView.findViewById(R.id.conditionUnit);
            penalty = itemView.findViewById(R.id.penaltyUnit);
            layout = (LinearLayout)itemView.findViewById(R.id.dialogLayout);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
