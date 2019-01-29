package ru.pushapp.joymania_1401;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import androidx.navigation.Navigation;

public class CaptchaScreenActivity extends Fragment implements View.OnClickListener{

    ImageButton btn0;
    ImageButton btn1;
    ImageButton btn2;
    ImageButton btn3;
    ImageButton btn4;
    ImageButton btn5;
    ImageButton btn6;
    ImageButton btn7;
    ImageButton btn8;
    ImageButton btn9;
    ImageButton generateCaptchaBtn;

    ImageButton deleteBtn;

    CheckBox checkBox;

    TextView generatedCaptchaTV;
    TextView usersCaptcha;

    Animation rotateAnimation;
    Animation moveAnimation;

    int rand;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.captcha_screen, container, false);

        generatedCaptchaTV = view.findViewById(R.id.generated_captcha_tv);
        usersCaptcha = view.findViewById(R.id.user_captcha_tv);
        checkBox = view.findViewById(R.id.check_box_not_robot);

        btn0 = view.findViewById(R.id.btn_0);
        btn0.setOnClickListener(this);

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

        btn6 = view.findViewById(R.id.btn_6);
        btn6.setOnClickListener(this);

        btn7 = view.findViewById(R.id.btn_7);
        btn7.setOnClickListener(this);

        btn8 = view.findViewById(R.id.btn_8);
        btn8.setOnClickListener(this);

        btn9 = view.findViewById(R.id.btn_9);
        btn9.setOnClickListener(this);

        deleteBtn = view.findViewById(R.id.delete_user_captcha);
        deleteBtn.setOnClickListener(this);

        generateCaptchaBtn = view.findViewById(R.id.image_btn_captcha);
        generateCaptchaBtn.setOnClickListener(this);

        generateCaptcha();

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        rotateAnimation = AnimationUtils.loadAnimation(getContext(),
                R.anim.rotate_anim);

        moveAnimation = AnimationUtils.loadAnimation(getContext(),
                R.anim.move_anim);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.delete_user_captcha:
                usersCaptcha.setText("");
                break;
            case R.id.image_btn_captcha:
                generateCaptcha();
                break;

            case R.id.btn_0:
                addSymbol(0);
                break;
            case R.id.btn_1:
                addSymbol(1);
                break;
            case R.id.btn_2:
                addSymbol(2);
                break;
            case R.id.btn_3:
                addSymbol(3);
                break;
            case R.id.btn_4:
                addSymbol(4);
                break;
            case R.id.btn_5:
                addSymbol(5);
                break;
            case R.id.btn_6:
                addSymbol(6);
                break;
            case R.id.btn_7:
                addSymbol(7);
                break;
            case R.id.btn_8:
                addSymbol(8);
                break;
            case R.id.btn_9:
                addSymbol(9);
                break;
        }
    }


    private void generateCaptcha() {
        rand = new Random().nextInt(8999);
        generatedCaptchaTV.setText(String.valueOf(rand + 1000));
    }

    private void error() {
        checkBox.startAnimation(rotateAnimation);
    }

    private void addSymbol(int i) {
        if (!checkBox.isChecked()) {
            error();
            return;
        }

        String captcha = usersCaptcha.getText().toString() + i + " ";
        SpannableString content = new SpannableString(captcha);

        int s = 0;
        while (s < captcha.length()) {
            content.setSpan(new UnderlineSpan(), s, s + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            s += 2;
        }

        usersCaptcha.setText(content);
        if (content.length() > 8){
            usersCaptcha.setText("");
            addSymbol(i);
        }

        checkCaptcha();
    }

    private void checkCaptcha() {
        String captcha = usersCaptcha.getText().toString();

        if (captcha.length() == 8) {
            String generated = generatedCaptchaTV.getText().toString();
            captcha = captcha.replace(" ", "");
            if (generated.equals(captcha)) {
                Navigation.findNavController(btn0).navigate(R.id.action_captchaScreenActivity_to_gameScreenFragment);
            } else {
                usersCaptcha.startAnimation(moveAnimation);
                Toast.makeText(getContext(), "Try again!!", Toast.LENGTH_LONG).show();
            }
        }
    }
}
