package android.projects.pidorsbizzareadventure.adapters;

import android.projects.pidorsbizzareadventure.R;
import android.projects.pidorsbizzareadventure.common.BasePresenter;
import android.projects.pidorsbizzareadventure.common.BasePresenterForAdapter;
import android.projects.pidorsbizzareadventure.common.BaseViewForAdapter;
import android.projects.pidorsbizzareadventure.dialogs.ConditionEditDialog;
import android.projects.pidorsbizzareadventure.pojo.Condition;
import android.projects.pidorsbizzareadventure.pojo.Zaruba;
import android.projects.pidorsbizzareadventure.ui.conditionsList.ConditionsListPresenter;
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
    private FragmentManager manager;

    private ConditionsListPresenter presenter;

    public ConditionsAdapter(FragmentManager manager, ConditionsListPresenter presenter) {
        this.conditions = new ArrayList<>();
        this.manager = manager;
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public ConditionViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewTypem) {
        View unit = LayoutInflater.from(parent.getContext()).inflate
                (R.layout.condition_unit, parent, false);

        return new ConditionViewHolder(unit, manager);
    }

    @Override
    public void onBindViewHolder(@NonNull final ConditionViewHolder holder, final int position) {
        holder.dialog = ConditionEditDialog.newInstance(conditions.get(position), new ConditionEditDialog.onConditionEditDialogClickListener() {
            @Override
            public void apply(String newText, int newValue) {
                holder.condition.setText(newText);
                holder.penalty.setText(String.format("%d", newValue));
                holder.dialog.dismiss();
                presenter.applyChanges(newText, newValue, position);
            }

            @Override
            public void cancel() {
                holder.dialog.dismiss();
            }
        });

        presenter.bind(position, holder);

    }

    @Override
    public int getItemCount() {
        return presenter.getCount();
    }

    public void add(Condition data) {
        conditions.add(data);
    }

    public void add(List<Condition> data) {
        conditions = data;
    }

    class ConditionViewHolder extends RecyclerView.ViewHolder implements BaseViewForAdapter {

        private TextView condition;
        private TextView penalty;
        private LinearLayout layout;
        private ConditionEditDialog dialog;

        public ConditionViewHolder(@NonNull final View itemView, final FragmentManager manager)  {
            super(itemView);
            condition = itemView.findViewById(R.id.conditionUnit);
            penalty = itemView.findViewById(R.id.penaltyUnit);
            layout = itemView.findViewById(R.id.dialogLayout);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.show(manager, "COND_EDITING");
                }
            });
        }

        @Override
        public void setConditionText(String condition){
            this.condition.setText(condition);
        }

        @Override
        public void setPenalty(int value){
            this.penalty.setText(String.format("%d", value));
        }
    }
}
