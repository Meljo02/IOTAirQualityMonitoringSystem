package com.example.airqualitymonitoringsystemver2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Home extends Fragment {

    private DatabaseReference ledStatusRef;
    private String ledStatus = "OFF";

    public Home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        ledStatusRef = database.getReference("ledStatus");

        final TextView statusTextView = view.findViewById(R.id.statusTextView);
        final Button toggleButton = view.findViewById(R.id.toggleButton);

        ledStatusRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String ledStatus = snapshot.getValue(String.class);
                statusTextView.setText("The LED is " + ledStatus);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        toggleButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if ("ON".equals(ledStatus)){
                    ledStatusRef.setValue("OFF");
                }
                else{
                    ledStatusRef.setValue("ON");
                }
            }
        });

        return view;
    }
}