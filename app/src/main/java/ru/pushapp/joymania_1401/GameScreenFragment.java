package ru.pushapp.joymania_1401;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Random;

public class GameScreenFragment extends Fragment implements Chronometer.OnChronometerTickListener, View.OnClickListener, ResultFragment.OnResultListener {

    RecyclerView recyclerView;
    ArrayList<ImageModel> urlList = new ArrayList<>();
    int[] answer = {-1, -1, -1, -1, -1};
    LinkedHashSet gameSet = new LinkedHashSet();
    int lvlCount = 1;

    FrameLayout frameLayout;

    ImageButton btnBack;

    ImageView showItem;
    ImageButton btn1;
    ImageButton btn2;
    ImageButton btn3;
    ImageButton btn4;
    ImageButton btn5;

    Chronometer chronometer;
    TextView timer;
    TextView lvlTV;

    int countTick = 0;
    static int durationTimer;

    int[] resArray = new int[]{R.drawable.fire_block_big,
            R.drawable.water_block_big,
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

        lvlTV = view.findViewById(R.id.lvl_text_view);
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

        frameLayout = view.findViewById(R.id.result_fragment_frame);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        playAgain();
    }

    //показываем сгенерированную последовательность
    private void showQuestionItem() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        urlList.clear();
        urlList.add(new ImageModel(R.drawable.fire_block_big, R.drawable.fire_block_little));
        urlList.add(new ImageModel(R.drawable.water_block_big, R.drawable.water_block_little));
        urlList.add(new ImageModel(R.drawable.sun_block_big, R.drawable.sun_block_little));
        urlList.add(new ImageModel(R.drawable.leaf_block_big, R.drawable.leaf_block_little));
        urlList.add(new ImageModel(R.drawable.heart_block_big, R.drawable.heart_block_little));

        rvAdapter adapter = new rvAdapter(getContext(), urlList);
        recyclerView.setAdapter(adapter);

        chronometer.stop();
        lvlTV.setText("LVL: " + lvlCount);

//        создаем случайную последовательность для игры
        for (int i = 0; i < answer.length; i++) {
            answer[i] = -1;
        }
        gameSet.clear();
        int r;
        countTick = 0;
        while (gameSet.size() != 5) {
            r = new Random().nextInt(5);
            gameSet.add(r);
        }

        Log.i("TEST", Arrays.toString(gameSet.toArray()));

        showTask(true);
        new CountDownTimer(5000, 1000) {
            public void onTick(long millisUntilFinished) {
                int indexSI = (int) gameSet.toArray()[countTick];
                showItem.setImageResource(resArray[indexSI]);
                timer.setText(String.valueOf(millisUntilFinished / 1000));
                countTick++;
            }

            public void onFinish() {
                showTask(false);
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
            showResult();

            Toast.makeText(getContext(), "Время вышло", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_back) {
            getActivity().onBackPressed();
            return;
        }

        ImageModel rez = rvAdapter.getSelectedItem();
        if (rez != null) {
            switch (view.getId()) {
                case R.id.btn_1:
                    answer[0] = rvAdapter.currentAdapterPosition;
                    btn1.setImageResource(rez.getLittleImage());
                    break;
                case R.id.btn_2:
                    answer[1] = rvAdapter.currentAdapterPosition;
                    btn2.setImageResource(rez.getLittleImage());
                    break;
                case R.id.btn_3:
                    answer[2] = rvAdapter.currentAdapterPosition;
                    btn3.setImageResource(rez.getLittleImage());
                    break;
                case R.id.btn_4:
                    answer[3] = rvAdapter.currentAdapterPosition;
                    btn4.setImageResource(rez.getLittleImage());
                    break;
                case R.id.btn_5:
                    answer[4] = rvAdapter.currentAdapterPosition;
                    btn5.setImageResource(rez.getLittleImage());
                    break;

            }

        } else {
            switch (view.getId()) {
                case R.id.btn_1:
                    answer[0] = -1;
                    btn1.setImageResource(R.drawable.btn_1_light);
                    break;
                case R.id.btn_2:
                    answer[1] = -1;
                    btn2.setImageResource(R.drawable.btn_2_light);
                    break;
                case R.id.btn_3:
                    answer[2] = -1;
                    btn3.setImageResource(R.drawable.btn_3_light);
                    break;
                case R.id.btn_4:
                    answer[3] = -1;
                    btn4.setImageResource(R.drawable.btn_4_light);
                    break;
                case R.id.btn_5:
                    answer[4] = -1;
                    btn5.setImageResource(R.drawable.btn_5_light);
                    break;

            }
        }

        checkAnswer();
    }

    private void checkAnswer() {
        int counter = 0;
        int countZero = 0;
        boolean flag = true;

        while (counter < 5) {
            if (answer[counter] == -1)
                countZero++;

            if (answer[counter] != (int) gameSet.toArray()[counter]) {
                flag = false;
            }
            counter++;
        }

        if (flag) {
            lvlCount++;
            durationTimer = (int) (0 - SystemClock.elapsedRealtime() + chronometer.getBase());

            showQuestionItem();
            Toast.makeText(getContext(), "Правильно!", Toast.LENGTH_SHORT).show();
        } else if (!(countZero > 0) && counter == 5) {
            Toast.makeText(getContext(), "Допущена ошибка!", Toast.LENGTH_SHORT).show();
        }
    }

    void showTask(boolean flag) {
        if (flag) {
            showItem.setVisibility(View.VISIBLE);

            recyclerView.setVisibility(View.GONE);
            btn1.setVisibility(View.GONE);
            btn2.setVisibility(View.GONE);
            btn3.setVisibility(View.GONE);
            btn4.setVisibility(View.GONE);
            btn5.setVisibility(View.GONE);
        } else {
            showItem.setVisibility(View.GONE);

            recyclerView.setVisibility(View.VISIBLE);
            btn1.setVisibility(View.VISIBLE);
            btn1.setImageResource(R.drawable.btn_1_light);
            btn2.setVisibility(View.VISIBLE);
            btn2.setImageResource(R.drawable.btn_2_light);
            btn3.setVisibility(View.VISIBLE);
            btn3.setImageResource(R.drawable.btn_3_light);
            btn4.setVisibility(View.VISIBLE);
            btn4.setImageResource(R.drawable.btn_4_light);
            btn5.setVisibility(View.VISIBLE);
            btn5.setImageResource(R.drawable.btn_5_light);
        }
    }

    private void showResult() {
        durationTimer = (int) (0 - SystemClock.elapsedRealtime() + chronometer.getBase());

        ResultFragment resultFragment = new ResultFragment();
        resultFragment.setOnResultListener(this);

        Bundle bundle = new Bundle();
        bundle.putInt("lvl", lvlCount - 1);
        resultFragment.setArguments(bundle);

        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.result_fragment_frame, resultFragment);

        transaction.addToBackStack(null);
        transaction.commit();

        frameLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDestroyView() {
        rvAdapter.currentAdapterPosition = -1;
        super.onDestroyView();
    }

    @Override
    public void playAgain() {
        lvlCount = 1;
        durationTimer = 60 * 1000;
        showQuestionItem();
    }
}
