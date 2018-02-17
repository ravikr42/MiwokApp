package com.example.android.miwokapp.pojo;

/**
 * Created by rkumardm on 27/01/18.
 */

public class Word {

    private static final int NO_IMAGE_PROVIDED = -1;
    private String mDefaultTranslation;
    private String mMiwokTranslation;
    private int imageResourceId = NO_IMAGE_PROVIDED;
    private int audioResourceId;

    public Word(String mDefaultTranslation, String mMiwokTranslation) {
        this.mDefaultTranslation = mDefaultTranslation;
        this.mMiwokTranslation = mMiwokTranslation;
    }

    public Word(String mDefaultTranslation, String mMiwokTranslation, int audioResourceId) {
        this.mDefaultTranslation = mDefaultTranslation;
        this.mMiwokTranslation = mMiwokTranslation;
        this.audioResourceId = audioResourceId;
    }

    public Word(String mDefaultTranslation, String mMiwokTranslation, int imageResourceId, int audioResourceId) {
        this.mDefaultTranslation = mDefaultTranslation;
        this.mMiwokTranslation = mMiwokTranslation;
        this.imageResourceId = imageResourceId;
        this.audioResourceId = audioResourceId;
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

    public int getAudioResourceId() {
        return audioResourceId;
    }
}
