package com.perch.yellow.saltmachine;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class addPhraseFragment extends Fragment   {
    public addPhraseFragment() {

    }
    int count;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view =  inflater.inflate(R.layout.activity_add_phrase, container, false);


        Button submit = (Button) view.findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                RadioGroup donGroup = (RadioGroup) view.findViewById(R.id.donateGroup);
                EditText toAdd = (EditText) view.findViewById(R.id.addPhrase);
                final String newPhrase = toAdd.getText().toString();

                if (donGroup.getCheckedRadioButtonId() == view.findViewById(R.id.radioButton).getId()){
                    final FirebaseDatabase db = FirebaseDatabase.getInstance();


                    // Pulls the counter from db, increments it, uses counter as id for new phrase, updates counter
                    ValueEventListener postListener = new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            count = Integer.parseInt(dataSnapshot.getValue().toString());
                            count++;
                            String newID = Integer.toString(count);

                            DatabaseReference addRef = db.getReference();
                            addRef.child("salt").child(newID).setValue(newPhrase);
                            addRef.child("salt_counter").setValue(newID);
//                        DatabaseReference addRef = db.getReference("salt");
//                        addRef.push().setValue(newPhrase);

                        }

                        public void onCancelled(DatabaseError databaseError) {

                        }
                    };
                    DatabaseReference countRef = db.getReference("salt_counter");
                    countRef.addListenerForSingleValueEvent(postListener);

                }
                else {
                    final FirebaseDatabase db = FirebaseDatabase.getInstance();


                    // Pulls the counter from db, increments it, uses counter as id for new phrase, updates counter
                    ValueEventListener postListener = new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            count = Integer.parseInt(dataSnapshot.getValue().toString());
                            count++;
                            String newID = Integer.toString(count);

                            DatabaseReference addRef = db.getReference();
                            addRef.child("sugar").child(newID).setValue(newPhrase);
                            addRef.child("sugar_counter").setValue(newID);
//                        DatabaseReference addRef = db.getReference("sugar");
//                        addRef.push().setValue(newPhrase);

                        }

                        public void onCancelled(DatabaseError databaseError) {

                        }
                    };
                    DatabaseReference countRef = db.getReference("sugar_counter");
                    countRef.addListenerForSingleValueEvent(postListener);

                }

                getActivity().onBackPressed();
            }
        });






        return view;
    }


}
