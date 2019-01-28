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

public class ResultFragment extends Fragment implements View.OnClickListener{

    ImageButton playAgainBtn;
    TextView resultTV;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.result_fragment, container, false);

        playAgainBtn = view.findViewById(R.id.play_again_btn);
        playAgainBtn.setOnClickListener(this);

        resultTV = view.findViewById(R.id.result_tv);

        int i = getArguments().getInt("lvl");
        resultTV.setText(String.valueOf(i));

        return view;
    }

    @Override
    public void onClick(View view) {
        getActivity().onBackPressed();
//        Navigation.findNavController(playAgainBtn).navigate(R.id.action_resultFragment_to_gameScreenFragment);
    }
}
