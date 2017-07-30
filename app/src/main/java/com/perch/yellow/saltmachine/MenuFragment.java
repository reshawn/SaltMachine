package com.perch.yellow.saltmachine;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Random;

/*
 * Created by resha on 16/07/2017.
 */ /*
 * A fragment representing the front of the card.
 */
public class MenuFragment extends Fragment implements View.OnClickListener {
    public MenuFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_menu, container, false);

        //Button code
        Button apButton = (Button) view.findViewById(R.id.addPhraseButton);
        apButton.setOnClickListener(this);
        Button sButton = (Button) view.findViewById(R.id.saltButton);
        sButton.setOnClickListener(this);
        Button uButton = (Button) view.findViewById(R.id.unsaltButton);
        uButton.setOnClickListener(this);
        Button supButton = (Button) view.findViewById(R.id.surpriseButton);
        supButton.setOnClickListener(this);

        //Label text pool
        final String[] addPhraseLabel = {
                getActivity().getString(R.string.addPhraseButtonLabel1),
                getActivity().getString(R.string.addPhraseButtonLabel2),
                getActivity().getString(R.string.addPhraseButtonLabel3),
        };
        final String[] saltLabel = {
                getActivity().getString(R.string.saltButtonLabel1),
                getActivity().getString(R.string.saltButtonLabel2),
                getActivity().getString(R.string.saltButtonLabel3),
        };
        final String[] unsaltLabel = {
                getActivity().getString(R.string.unsaltButtonLabel1),
                getActivity().getString(R.string.unsaltButtonLabel2),
                getActivity().getString(R.string.unsaltButtonLabel3),
        };
        final String[] surpriseLabel = {
                getActivity().getString(R.string.surpriseButtonLabel1),
                getActivity().getString(R.string.surpriseButtonLabel2),
                getActivity().getString(R.string.surpriseButtonLabel3),
        };

        //random text assignments
        Random r1 = new Random();
        apButton.setText(addPhraseLabel[r1.nextInt(addPhraseLabel.length)]);
        sButton.setText(saltLabel[r1.nextInt(saltLabel.length)]);
        uButton.setText(unsaltLabel[r1.nextInt(unsaltLabel.length)]);
        supButton.setText(surpriseLabel[r1.nextInt(surpriseLabel.length)]);


        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.addPhraseButton:
                ((Menu) getActivity()).flipCard();
                break;

            case R.id.saltButton:
                startActivity(new Intent(getActivity(), saltActivity.class));

                getActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                break;

            case R.id.unsaltButton:
                startActivity(new Intent(getActivity(), unsaltActivity.class));

                getActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                break;

            case R.id.surpriseButton:
                startActivity(new Intent(getActivity(), surpriseActivity.class));

                getActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                break;

            default:
                break;
        }

    }
}
