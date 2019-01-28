package ru.pushapp.joymania_1401;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Random;

import androidx.navigation.Navigation;

public class GameScreenFragment extends Fragment implements Chronometer.OnChronometerTickListener, View.OnClickListener{

    RecyclerView recyclerView;
    ArrayList<ImageModel> urlList = new ArrayList<>();

    ImageButton btnBack;

    ImageView showItem;
    ImageButton btn1;
    ImageButton btn2;
    ImageButton btn3;
    ImageButton btn4;
    ImageButton btn5;

    Chronometer chronometer;
    TextView timer;

    int countTick = 0;
    int durationTimer = 5 * 1000;

    LinkedHashSet<Integer> gameSet = new LinkedHashSet<>();

    int[] si = new int[]{R.drawable.water_block_big,
            R.drawable.fire_block_big,
            R.drawable.sun_block_big,
            R.drawable.leaf_block_big,
            R.drawable.heart_block_big};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.game_screen, container, false);

        recyclerView = view.findViewById(R.id.blocks_rv);
        chronometer = view.findViewById(R.id.chronometer);
        chronometer.setOnChronometerTickListener(this);

        timer = view.findViewById(R.id.timer_tv);
        showItem = view.findViewById(R.id.show_game_item);

        btnBack = view.findViewById(R.id.btn_back);
        btnBack.setOnClickListener(this);

        btn1 = view.findViewById(R.id.btn_1);
        btn1.setOnClickListener(this);
        btn2 = view.findViewById(R.id.btn_2);
        btn2.setOnClickListener(this);
        btn3 = view.findViewById(R.id.btn_3);
        btn3.setOnClickListener(this);
        btn4 = view.findViewById(R.id.btn_4);
        btn4.setOnClickListener(this);
        btn5 = view.findViewById(R.id.btn_5);
        btn5.setOnClickListener(this);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

//        создаем случайную последовательность для игры
        int r;
        while (gameSet.size() != 5) {
            r = new Random().nextInt(5);
            gameSet.add(r);
        }

//        показываем по 1 элементу созданную последовательность todo
//        showQuestionItem();
        changeVisibility();
        chronometer.setBase(SystemClock.elapsedRealtime() + durationTimer);
        chronometer.start();

        urlList.add(new ImageModel(R.drawable.fire_block_big,R.drawable.fire_block_little));
        urlList.add(new ImageModel(R.drawable.water_block_big,R.drawable.water_block_little));
        urlList.add(new ImageModel(R.drawable.sun_block_big,R.drawable.sun_block_little));
        urlList.add(new ImageModel(R.drawable.leaf_block_big,R.drawable.leaf_block_little));
        urlList.add(new ImageModel(R.drawable.heart_block_big,R.drawable.heart_block_little));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        rvAdapter adapter = new rvAdapter(getContext(), urlList);
        recyclerView.setAdapter(adapter);

    }

    private void showQuestionItem() {
        new CountDownTimer(5000, 1000) {

            public void onTick(long millisUntilFinished) {
                int indexSI = (int) gameSet.toArray()[countTick];
                showItem.setImageResource(si[indexSI]);
                timer.setText(String.valueOf(millisUntilFinished / 1000));
                countTick++;
            }

            public void onFinish() {
                changeVisibility();
                chronometer.setBase(SystemClock.elapsedRealtime() + durationTimer);
                chronometer.start();
            }

        }.start();
    }

    @Override
    public void onChronometerTick(Chronometer chronometer) {
        long f = (0 - (SystemClock.elapsedRealtime() - chronometer.getBase())) / 1000;
        String time = String.valueOf(f);
        timer.setText(time);
        if (f == 0) {
            chronometer.stop();
            Toast.makeText(getContext(), "end game", Toast.LENGTH_SHORT).show();
        }
    }

    void changeVisibility() {
        showItem.setVisibility(View.GONE);

        recyclerView.setVisibility(View.VISIBLE);
        btn1.setVisibility(View.VISIBLE);
        btn2.setVisibility(View.VISIBLE);
        btn3.setVisibility(View.VISIBLE);
        btn4.setVisibility(View.VISIBLE);
        btn5.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_back){
            getActivity().onBackPressed();
            return;
        }

        ImageModel rez = rvAdapter.getSelectedItem();
        if (rez != null){
            switch (view.getId()){
                case R.id.btn_1:
                    btn1.setImageResource(rez.getLittleImage());
                    break;
                case R.id.btn_2:
                    btn2.setImageResource(rez.getLittleImage());
                    break;
                case R.id.btn_3:
                    btn3.setImageResource(rez.getLittleImage());
                    break;
                case R.id.btn_4:
                    btn4.setImageResource(rez.getLittleImage());
                    break;
                case R.id.btn_5:
                    btn5.setImageResource(rez.getLittleImage());
                    break;

            }
        }else {
            switch (view.getId()){
                case R.id.btn_1:
                    btn1.setImageResource(R.drawable.btn_1_light);
                    break;
                case R.id.btn_2:
                    btn2.setImageResource(R.drawable.btn_2_light);
                    break;
                case R.id.btn_3:
                    btn3.setImageResource(R.drawable.btn_3_light);
                    break;
                case R.id.btn_4:
                    btn4.setImageResource(R.drawable.btn_4_light);
                    break;
                case R.id.btn_5:
                    btn5.setImageResource(R.drawable.btn_5_light);
                    break;

            }
        }
    }

    @Override
    public void onDestroyView() {
        rvAdapter.currentAdapterPosition = -1;
        super.onDestroyView();
    }
}
