package com.mohit.ipsians_diary.ui.tools;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import com.mohit.ipsians_diary.R;
import com.mohit.ipsians_diary.datamodels.SaveSharedPreference;
import com.mohit.ipsians_diary.ui.event.UpcomingEvents;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ToolsFragment extends Fragment {
    BottomNavigationView bottomNavigationView;
    TextView tv;
    CardView roomLocator, events;

    private static final String ALMA_MATER = "Bharati Vidyapeeth's College of Engineering, New Delhi";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tools, container, false);
        roomLocator = view.findViewById(R.id.roomLocator);
        events = view.findViewById(R.id.events);

        if (ALMA_MATER.equals(SaveSharedPreference.getCollege(getContext()))) {
            roomLocator.setVisibility(View.VISIBLE);
        } else {
            roomLocator.setVisibility(View.GONE);
        }

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getActivity() != null)
            bottomNavigationView = getActivity().findViewById(R.id.bottomNav);
        tv = getActivity().findViewById(R.id.navTitle);
        tv.setText("TOOLS");

        roomLocator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new RoomLocFragment();

                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragmentContainer, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), UpcomingEvents.class));
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        bottomNavigationView.getMenu().findItem(R.id.nav_tools).setChecked(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        bottomNavigationView.getMenu().findItem(R.id.nav_tools).setChecked(true);
    }
}
