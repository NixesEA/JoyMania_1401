package ru.pushapp.joymania_1401;

public class ImageModel {

    int bigImage;
    int littleImage;
    boolean isChecked = false;

    public ImageModel(int big, int little) {
        this.bigImage = big;
        this.littleImage = little;
    }

    public boolean getChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public int getLittleImage() {
        return littleImage;
    }

    public void setLittleImage(int littleImage) {
        this.littleImage = littleImage;
    }

    public int getBigImage() {
        return bigImage;
    }

    public void setBigImage(int bigImage) {
        this.bigImage = bigImage;
    }
}
