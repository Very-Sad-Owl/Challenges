package android.projects.pidorsbizzareadventure.adapters;

import android.projects.pidorsbizzareadventure.R;
import android.projects.pidorsbizzareadventure.common.BaseViewForAdapter;
import android.projects.pidorsbizzareadventure.pojo.Participator;
import android.projects.pidorsbizzareadventure.ui.conditionsList.ConditionsListPresenter;
import android.projects.pidorsbizzareadventure.ui.scoreList.ScoreListPresenter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.ScoreViewHolder> {

    private ScoreListPresenter presenter;

    public ScoreAdapter(ScoreListPresenter presenter) {
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public ScoreAdapter.ScoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View unit = LayoutInflater.from(parent.getContext()).inflate
                (R.layout.score_single_view, parent, false);
        return new ScoreAdapter.ScoreViewHolder(unit);
    }

    @Override
    public void onBindViewHolder(@NonNull final ScoreAdapter.ScoreViewHolder holder, final int position) {

        presenter.bind(position, holder);
    }

    @Override
    public int getItemCount() {
        return presenter.getCount();
    }


    public void add(Participator data) {
        presenter.getParticipators().add(data);
        notifyDataSetChanged();
    }
//
//    public void add(List<Condition> data) {
//        presenter.getConditions() = data;
//    }

    class ScoreViewHolder extends RecyclerView.ViewHolder implements BaseViewForAdapter {

        private TextView participator;
        private TextView score;

        public ScoreViewHolder(@NonNull final View itemView)  {
            super(itemView);
            participator = itemView.findViewById(R.id.conditionUnit);
            score = itemView.findViewById(R.id.penaltyUnit);
        }

        @Override
        public void setConditionText(String name){
            this.participator.setText(name);
        }

        @Override
        public void setPenalty(int value){
            this.score.setText(String.format("%d", value));
        }
    }
}