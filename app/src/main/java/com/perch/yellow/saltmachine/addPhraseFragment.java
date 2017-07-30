package com.perch.yellow.saltmachine;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by resha on 17/07/2017.
 */ /*
 * A fragment representing the back of the card.
 */
public class addPhraseFragment extends Fragment {
    public addPhraseFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.activity_add_phrase, container, false);
    }
}
