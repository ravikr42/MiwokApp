package com.example.android.miwokapp.adaptors;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.miwokapp.R;
import com.example.android.miwokapp.pojo.Word;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ravik on 1/28/2018.
 */

public class WordAdaptor extends ArrayAdapter<Word> {

    private static final String LOG_TAG = WordAdaptor.class.getSimpleName();
    private int mColorResourceId;


    public WordAdaptor(Activity context, List<Word> wordList, int colorResourceId) {
        super(context, 0, wordList);
        mColorResourceId = colorResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        Word word = getItem(position);

        ImageView icon = (ImageView) listItemView.findViewById(R.id.symbol_icon);
        if (word.hasImage()) {
            icon.setImageResource(word.getImageResourceId());
            icon.setVisibility(View.VISIBLE);
        } else {
            icon.setVisibility(View.GONE);
        }
        TextView miwokWordTextView = (TextView) listItemView.findViewById(R.id.miwok_lang_keyword);
        miwokWordTextView.setText(word.getMiwokTranslation());

        TextView englishWordTextView = (TextView) listItemView.findViewById(R.id.english_keyowrd);
        englishWordTextView.setText(word.getDefaultTranslation());

        View textContainer = listItemView.findViewById(R.id.text_container);
        int color  = ContextCompat.getColor(getContext(), mColorResourceId);
        textContainer.setBackgroundColor(color);


        return listItemView;
    }
}
