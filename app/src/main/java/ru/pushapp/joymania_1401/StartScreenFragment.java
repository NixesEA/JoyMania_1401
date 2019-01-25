package ru.pushapp.joymania_1401;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.navigation.Navigation;

public class StartScreenFragment extends Fragment implements View.OnClickListener {

    ImageView image_btn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.start_screen, container, false);

        image_btn = view.findViewById(R.id.play_img_btn);
        image_btn.setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick(View view) {
        Navigation.findNavController(view).navigate(R.id.action_startScreenFragment_to_captchaScreenActivity);
    }
}
