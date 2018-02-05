package com.example.android.miwokapp.pojo;

/**
 * Created by rkumardm on 27/01/18.
 */

public class Word {

    private static final int NO_IMAGE_PROVIDED = -1;
    private String mDefaultTranslation;
    private String mMiwokTranslation;
    private int imageResourceId = NO_IMAGE_PROVIDED;

    public Word(String mDefaultTranslation, String mMiwokTranslation) {
        this.mDefaultTranslation = mDefaultTranslation;
        this.mMiwokTranslation = mMiwokTranslation;
    }

    public Word(String mDefaultTranslation, String mMiwokTranslation, int imageResourceId) {
        this.mDefaultTranslation = mDefaultTranslation;
        this.mMiwokTranslation = mMiwokTranslation;
        this.imageResourceId = imageResourceId;
    }

    public String getDefaultTranslation() {
        return this.mDefaultTranslation;
    }

    public String getMiwokTranslation() {
        return this.mMiwokTranslation;
    }

    public int getImageResourceId() {
        return this.imageResourceId;
    }

    public boolean hasImage() {
        return imageResourceId != NO_IMAGE_PROVIDED;
    }
}
