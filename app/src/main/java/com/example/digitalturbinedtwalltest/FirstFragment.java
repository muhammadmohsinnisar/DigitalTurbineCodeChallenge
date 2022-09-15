package com.example.digitalturbinedtwalltest;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.preference.PreferenceManager;

import com.example.digitalturbinedtwalltest.Interface.DTWallApi;
import com.example.digitalturbinedtwalltest.Model.DTWall;
import com.example.digitalturbinedtwalltest.Model.Information;
import com.example.digitalturbinedtwalltest.Model.Offer;
import com.example.digitalturbinedtwalltest.databinding.FragmentFirstBinding;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FirstFragment extends Fragment {


    private String TAG = "FirstFragment";
    private static String app_id;
    private static String user_id;
    private static String security_token;

    SharedPreferences sp;
    SharedPreferences.OnSharedPreferenceChangeListener listener;

    private FragmentFirstBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        Context context = getActivity().getApplicationContext();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        app_id = sharedPreferences.getString("edp_appId", "");
        user_id = sharedPreferences.getString("edp_userId", "");
        security_token = sharedPreferences.getString("edp_token", "");

        sp = PreferenceManager.getDefaultSharedPreferences(getContext());

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Boolean wall;

        listener = new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                if(Objects.equals(key,"switch_dt")) {
                    if(sharedPreferences.getBoolean(key,false)){
                        Log.d(TAG,"Enabled DT Wall");
                    } else {
                        Log.d(TAG,"Disabled DT Wall");
                    }
                }
            }
        };


        if(sp.getBoolean("switch_dt", false)) {
            binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                        NavHostFragment.findNavController(FirstFragment.this)
                                .navigate(R.id.action_FirstFragment_to_SecondFragment);
                }
            });
        } else {
            Log.d(TAG, "Wall Disabled!");
            binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
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