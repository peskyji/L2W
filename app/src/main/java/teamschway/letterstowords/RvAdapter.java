package teamschway.letterstowords;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.RvViewHolder> {
    ArrayList<String> al;
    public RvAdapter(ArrayList<String> al){
        this.al=al;
        System.out.println(al);
    }
   // @NonNull
    @Override
    public RvViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater=LayoutInflater.from(viewGroup.getContext());
        View view=inflater.inflate(R.layout.list_item_layout,viewGroup,false);
        return new RvViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RvViewHolder rvViewHolder, int i) {
        String word=al.get(i);
        rvViewHolder.tvAdapter.setText(word);
        rvViewHolder.tvAdapter.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
    }

    @Override
    public int getItemCount() {
        return al.size();
    }

    public class RvViewHolder extends RecyclerView.ViewHolder{
        TextView tvAdapter;
        public RvViewHolder(@NonNull View itemView) {
            super(itemView);
            tvAdapter=(TextView)itemView.findViewById(R.id.tvWordHeading);
        }
    }
}
