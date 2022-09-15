package com.example.digitalturbinedtwalltest;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.preference.PreferenceManager;

import com.example.digitalturbinedtwalltest.databinding.FragmentFirstBinding;

import java.util.Objects;

public class FirstFragment extends Fragment {


    private static String app_id;
    private static String user_id;
    private static String security_token;
    Animation shake;
    Animation gone;
    TextView textView;
    SharedPreferences sp;
    SharedPreferences.OnSharedPreferenceChangeListener listener;
    private final String TAG = "FirstFragment";
    private FragmentFirstBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        Context context = getActivity().getApplicationContext();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        sp = PreferenceManager.getDefaultSharedPreferences(getContext());
        binding = FragmentFirstBinding.inflate(inflater, container, false);

        textView = binding.textviewFirst;
        //Animations
        shake = AnimationUtils.loadAnimation(getContext(), R.anim.shake_animatation);
        gone = AnimationUtils.loadAnimation(getContext(), R.anim.bottom_animation);

        return binding.getRoot();

    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Boolean wall;

        listener = new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                if (Objects.equals(key, "switch_dt")) {
                    if (sharedPreferences.getBoolean(key, false)) {
                        Log.d(TAG, "Enabled DT Wall");
                    } else {
                        Log.d(TAG, "Disabled DT Wall");
                    }
                }
            }
        };


        if (sp.getBoolean("switch_dt", false)) {
            binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    binding.buttonFirst.startAnimation(shake);
                    NavHostFragment.findNavController(FirstFragment.this)
                            .navigate(R.id.action_FirstFragment_to_SecondFragment);
                }
            });
        } else {
            Log.d(TAG, "Wall Disabled!");
            binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    textView.setText("Enable DT Wall from Settings!");
                    binding.buttonFirst.startAnimation(gone);
                    binding.buttonFirst.setVisibility(View.INVISIBLE);
                    Toast.makeText(getContext(), "Wall is disabled! Enable DT Wall from Settings", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}