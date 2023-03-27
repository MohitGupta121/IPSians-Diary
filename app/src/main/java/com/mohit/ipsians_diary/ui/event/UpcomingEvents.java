package com.mohit.ipsians_diary.ui.event;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.mohit.ipsians_diary.R;
import com.mohit.ipsians_diary.datamodels.SaveSharedPreference;
import com.mohit.ipsians_diary.utils.FirebaseUtil;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class UpcomingEvents extends AppCompatActivity {
    private FloatingActionButton createEvent;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;
    Fragment upcomingevents = new EventsFragment();
    ValueEventListener listener;
    ImageButton back;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcoming_event);
        Toolbar toolbar = findViewById(R.id.toolbarcom);
        setSupportActionBar(toolbar);
        firebaseDatabase = FirebaseUtil.getDatabase();
        databaseReference = firebaseDatabase.getReference("EventAdmin");
        createEvent = findViewById(R.id.createEvent);
        Log.d("Upcoming Events", "onCreate: " + SaveSharedPreference.getUserName(this));
        databaseReference.addListenerForSingleValueEvent(listener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String admin = (String) dataSnapshot.child("user").getValue();
                Log.d("Upcoming Events", "onDataChange: " + dataSnapshot.hasChild("user") + SaveSharedPreference.getUserName(UpcomingEvents.this));
                if (Objects.requireNonNull(admin).equals(SaveSharedPreference.getUserName(UpcomingEvents.this)))
                    createEvent.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        createEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UpcomingEvents.this, CreateEvent.class));
            }
        });

        loadFragments(upcomingevents);
        back = findViewById(R.id.homeBack);
        back.setOnClickListener(v -> {
            FragmentManager mgr = getSupportFragmentManager();
            if (mgr.getBackStackEntryCount() == 0) {
                super.onBackPressed();
                finish();
            } else
                mgr.popBackStack();
        });
    }

    private boolean loadFragments(Fragment fragment) {
        if (fragment != null) {
            Log.d("navigation", "loadFragments: Frag is loaded");
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frameupcomingevents, fragment)
                    .commit();

            return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStackImmediate();
        } else
            super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        if (listener != null)
            databaseReference.removeEventListener(listener);
        super.onDestroy();
    }
}
