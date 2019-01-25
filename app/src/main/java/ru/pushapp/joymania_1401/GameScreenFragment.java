package ru.pushapp.joymania_1401;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class GameScreenFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<Integer> urlList = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.game_screen, container, false);

        recyclerView = view.findViewById(R.id.blocks_rv);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();


        urlList.add(R.drawable.fire_block_little);
        urlList.add(R.drawable.water_block_little);
        urlList.add(R.drawable.heart_block_little);
        urlList.add(R.drawable.leaf_block_little);
        urlList.add(R.drawable.sun_block_little);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        rvAdapter adapter = new rvAdapter(getContext(), urlList);
        recyclerView.setAdapter(adapter);

    }
}
