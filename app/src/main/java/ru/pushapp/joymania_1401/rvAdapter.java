package ru.pushapp.joymania_1401;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

public class rvAdapter extends RecyclerView.Adapter<rvAdapter.rvAdapterHolder> {

    int lastAdapterPosition = 0;
    static int currentAdapterPosition = -1;

    ImageView lastImageView;

    static ImageModel getSelectedItem(){
        if (currentAdapterPosition >= 0){
            return list_items.get(currentAdapterPosition);
        }else {
            return null;
        }
    }

    class rvAdapterHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView imageView;

        rvAdapterHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_block);
            imageView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            currentAdapterPosition = getAdapterPosition();

//            change current image
            if (list_items.get(currentAdapterPosition).getChecked()){
                imageView.setImageResource(list_items.get(currentAdapterPosition).getLittleImage());
                list_items.get(currentAdapterPosition).setChecked(false);
            }else {
                imageView.setImageResource(list_items.get(currentAdapterPosition).getBigImage());
                list_items.get(currentAdapterPosition).setChecked(true);
            }

            //if find double tap on one item
            if (currentAdapterPosition == lastAdapterPosition && !list_items.get(currentAdapterPosition).getChecked()){
                currentAdapterPosition = -1;
                return;
            }

//            change last image
            if (lastAdapterPosition != currentAdapterPosition && lastImageView!=null){
                lastImageView.setImageResource(list_items.get(lastAdapterPosition).getLittleImage());
                list_items.get(lastAdapterPosition).setChecked(false);
            }

            lastImageView = imageView;
            lastAdapterPosition = currentAdapterPosition;

        }
    }


    private LayoutInflater inflater;
    private static ArrayList<ImageModel> list_items;

    public rvAdapter(Context context, ArrayList<ImageModel> items) {
        this.list_items = items;

        inflater = LayoutInflater.from(context);
    }

    @Override
    public rvAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.blocks_list_item, parent, false);
        return new rvAdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final rvAdapterHolder holder, int position) {
        holder.imageView.setImageResource(list_items.get(position).getLittleImage());
    }

    @Override
    public int getItemCount() {
        return list_items.size();
    }

}