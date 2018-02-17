package com.example.android.miwokapp;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.android.miwokapp.adaptors.WordAdaptor;
import com.example.android.miwokapp.pojo.Word;

import java.util.ArrayList;
import java.util.List;

import static android.media.AudioManager.AUDIOFOCUS_GAIN;
import static android.media.AudioManager.AUDIOFOCUS_GAIN_TRANSIENT;
import static android.media.AudioManager.AUDIOFOCUS_LOSS;
import static android.media.AudioManager.AUDIOFOCUS_LOSS_TRANSIENT;
import static android.media.AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK;
import static android.media.AudioManager.AUDIOFOCUS_REQUEST_GRANTED;
import static android.media.AudioManager.STREAM_MUSIC;

public class ColorsActivity extends AppCompatActivity {

    private MediaPlayer player;

    private AudioManager audioManager;

    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    private AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {

            // The AUDIOFOCUS_LOSS_TRANSIENT case means that we've lost audio focus for a
            // short amount of time. The AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK case means that
            // our app is allowed to continue playing sound but at a lower volume. We'll treat
            // both cases the same way because our app is playing short sound files.

            // Pause playback and reset player to the start of the file. That way, we can
            // play the word from the beginning when we resume playback.
            if (focusChange == AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                player.pause();
                player.seekTo(0);
            } else if (focusChange == AUDIOFOCUS_GAIN) {
                // The AUDIOFOCUS_GAIN case means we have regained focus and can resume playback.
                player.start();
            } else if (focusChange == AUDIOFOCUS_LOSS) {
                // The AUDIOFOCUS_LOSS case means we've lost audio focus and
                // Stop playback and clean up resources
                releaseMediaPlayer();
            }
        }
    };

    private void releaseMediaPlayer() {
        if (player != null) {
            player.release();
            player = null;
            audioManager.abandonAudioFocus(onAudioFocusChangeListener);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);

        final List<Word> words = new ArrayList<>();
        words.add(new Word("red", "weṭeṭṭi", R.drawable.color_red, R.raw.color_red));
        words.add(new Word("mustard yellow", "chiwiiṭә", R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow));
        words.add(new Word("dusty yellow", "ṭopiisә", R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow));
        words.add(new Word("green", "chokokki", R.drawable.color_green, R.raw.color_green));
        words.add(new Word("brown", "ṭakaakki", R.drawable.color_brown, R.raw.color_brown));
        words.add(new Word("gray", "ṭopoppi", R.drawable.color_gray, R.raw.color_gray));
        words.add(new Word("black", "kululli", R.drawable.color_black, R.raw.color_black));
        words.add(new Word("white", "kelelli", R.drawable.color_white, R.raw.color_white));

        WordAdaptor adaptor = new WordAdaptor(this, words, R.color.category_colors);
        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(adaptor);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                releaseMediaPlayer();
                Word word = words.get(position);
//                player = MediaPlayer.create(getApplicationContext(), word.getAudioResourceId());


                int result = audioManager.requestAudioFocus(onAudioFocusChangeListener, STREAM_MUSIC,
                        AUDIOFOCUS_GAIN_TRANSIENT);


//                player.start();

                if (result == AUDIOFOCUS_REQUEST_GRANTED) {
                    player = MediaPlayer.create(ColorsActivity.this, word.getAudioResourceId());
                    player.start();
                    player.setOnCompletionListener(mCompletionListener);
                }


            }
        });


    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
}
