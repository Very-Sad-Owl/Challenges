package android.projects.pidorsbizzareadventure.adapters;

import android.content.Intent;
import android.projects.pidorsbizzareadventure.R;
import android.projects.pidorsbizzareadventure.pojo.Zaruba;
import android.projects.pidorsbizzareadventure.activities.ZarubaInfo;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UnitsAdapter extends RecyclerView.Adapter<UnitsAdapter.UnitsViewHolder> {

    private List<Zaruba> list;

    public UnitsAdapter(List<Zaruba> list){
        this.list = list;
    }

    @NonNull
    @Override
    public UnitsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate
                (R.layout.activity_challenge_note_unit, parent, false);
        return new UnitsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UnitsViewHolder holder, int position) {
        Zaruba zaruba = list.get(position);
        holder.title.setText(zaruba.getTitle());
        holder.description.setText(zaruba.getDescription());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    static class UnitsViewHolder extends RecyclerView.ViewHolder  {

        private TextView title;
        private TextView description;

        public UnitsViewHolder(@NonNull final View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textViewShortTitle);
            description = itemView.findViewById(R.id.textViewShortDescription);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "Clicked", Toast.LENGTH_SHORT).show();
                    Log.i("click", "clicked");
                    int position = getAdapterPosition();
                    Intent next = new Intent(itemView.getContext(), ZarubaInfo.class);
                    next.putExtra("pos", position);
                    v.getContext().startActivity(next);
                }
            });
        }
    }
}
