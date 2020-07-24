package android.projects.pidorsbizzareadventure.ui.сhallengesList;

import android.content.Intent;
import android.projects.pidorsbizzareadventure.R;
import android.projects.pidorsbizzareadventure.ui.info.ZarubaInfo;
import android.projects.pidorsbizzareadventure.pojo.Zaruba;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ChallengeListAdapter extends RecyclerView.Adapter<ChallengeListAdapter.ViewHolder> {
    private List<Zaruba> challenges;

    ChallengeListAdapter(){
        challenges = new ArrayList<>();
       // this.challenges = challenges;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate
                (R.layout.activity_challenge_note_unit, parent, false);
        return new ChallengeListAdapter.ViewHolder(view, challenges);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Zaruba zaruba = challenges.get(position);
        holder.title.setText(zaruba.getTitle());
        holder.description.setText(zaruba.getDescription());
    }

    @Override
    public int getItemCount() {
        return challenges.size();
    }

    public void add(Zaruba zaruba){
        challenges.add(zaruba);
    }

    public void add(List<Zaruba> list){
        challenges.addAll(list);
    }

    static class ViewHolder extends RecyclerView.ViewHolder  {

        private TextView title;
        private TextView description;

        ViewHolder(@NonNull final View itemView, final List<Zaruba> list) {
            super(itemView);
            title = itemView.findViewById(R.id.textViewShortTitle);
            description = itemView.findViewById(R.id.textViewShortDescription);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "Clicked", Toast.LENGTH_SHORT).show();
                    int position = getAdapterPosition();
                    Intent next = new Intent(itemView.getContext(), ZarubaInfo.class);
                    next.putExtra("CURRENT_CHALLENGE_POS", position);
                    next.putExtra("CURRENT_CHALLENGE", list.get(position));
                    v.getContext().startActivity(next);
                }
            });
        }
    }




}
