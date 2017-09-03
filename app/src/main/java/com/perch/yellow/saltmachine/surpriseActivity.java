package com.perch.yellow.saltmachine;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class surpriseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surprise);

        Button refresh = (Button)findViewById(R.id.refreshButton);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                finish();
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });

    }

    //Pull random phrase from the sugar child of the firebase db
    @Override
    protected void onStart() {
        super.onStart();
        final FirebaseDatabase db = FirebaseDatabase.getInstance();
        Random rand = new Random();
        int phraseType = rand.nextInt(2);
        final String phraseTypeS;
        if (phraseType==0) phraseTypeS = "salt";
        else phraseTypeS = "sugar";

        ValueEventListener countListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                int DBcount = Integer.parseInt(dataSnapshot.getValue().toString());

                Random rand = new Random();
                //nextInt(max) returns an int between 0 (inclusive) and max (exclusive)
                // DBcount+1 to include DBcount in the range and the outside plus 1
                // to set 1 as the min instead of 0
                final String key = Integer.toString(rand.nextInt(DBcount) + 1);

                //now that the random int key within range is found, get the phrase for it
                ValueEventListener phraseListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()) {
                            String phrase = "\" " + dataSnapshot.getValue().toString() + " \"";

                            Typeface lobster_font = Typeface.createFromAsset(getAssets(), "fonts/Lobster-Regular.ttf");

                            TextView resText = (TextView) findViewById(R.id.resultText3);
                            resText.setTypeface(lobster_font);
                            resText.setText(phrase);
                            resText.setTextSize(30);
                        }
                        else {
                            TextView resText = (TextView) findViewById(R.id.resultText3);
                            resText.setText(key);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        TextView resText = (TextView) findViewById(R.id.resultText3);
                        resText.setText(getString(R.string.errorMsg));
                    }
                };
                DatabaseReference phraseRef = db.getReference();
                phraseRef.child(phraseTypeS).child(key).addListenerForSingleValueEvent(phraseListener);


            }

            public void onCancelled(DatabaseError databaseError) {

            }
        };

        DatabaseReference countRef = db.getReference();
        String phraseTypeS2 = phraseTypeS + "_counter";
        countRef.child(phraseTypeS2).addListenerForSingleValueEvent(countListener);

    }
}
