package ru.pushapp.joymania_1401;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.navigation.Navigation;

public class ResultFragment extends Fragment implements View.OnClickListener {

    ImageButton playAgainBtn;
    ImageButton closeBtn;
    TextView resultTV;

    OnResultListener callback;

    public void setOnResultListener(Fragment fragment) {
        callback = (OnResultListener) fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.result_fragment, container, false);

        closeBtn = view.findViewById(R.id.res_back_btn);
        closeBtn.setOnClickListener(this);

        playAgainBtn = view.findViewById(R.id.play_again_btn);
        playAgainBtn.setOnClickListener(this);

        resultTV = view.findViewById(R.id.result_tv);

        int i = getArguments().getInt("lvl");
        resultTV.setText(String.valueOf(i));

        return view;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.play_again_btn:
                callback.playAgain();
                getActivity().onBackPressed();
                break;
            case R.id.res_back_btn:
                Navigation.findNavController(closeBtn).popBackStack();
                break;
        }

    }

    public interface OnResultListener {
        void playAgain();
    }
}
