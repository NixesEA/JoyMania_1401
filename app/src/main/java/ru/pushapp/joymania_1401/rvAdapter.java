package ru.pushapp.joymania_1401;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import java.util.List;

public class rvAdapter extends RecyclerView.Adapter<rvAdapter.rvAdapterHolder> {

    class rvAdapterHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;

        rvAdapterHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_block);
        }
    }


    private Context context;
    private LayoutInflater inflater;
    private List<Integer> list_items;

    public rvAdapter(Context context, List<Integer> items) {
        this.list_items = items;
        this.context = context;

        inflater = LayoutInflater.from(context);
    }

    @Override
    public rvAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.blocks_list_item, parent, false);
        return new rvAdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final rvAdapterHolder holder, int position) {
        holder.imageView.setImageResource(list_items.get(position));
    }

    @Override
    public int getItemCount() {
        return list_items.size();
    }

}