package com.example.digitalturbinedtwalltest;

import android.app.ProgressDialog;
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
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.digitalturbinedtwalltest.Adapter.WallAdapter;
import com.example.digitalturbinedtwalltest.Interface.DTWallApi;
import com.example.digitalturbinedtwalltest.Model.DTWall;
import com.example.digitalturbinedtwalltest.Model.Information;
import com.example.digitalturbinedtwalltest.Model.Offer;
import com.example.digitalturbinedtwalltest.databinding.FragmentSecondBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SecondFragment extends Fragment {

    private static final String TAG = "SecondFragment";

    RecyclerView recyclerView;
    DTWallApi dtWallApi;
    Animation shake;
    Animation gone;
    SharedPreferences sharedPreferences;
    String endpoint = "offers.json";
    ProgressDialog progressdialog;
    TextView textView;
    private FragmentSecondBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        binding = FragmentSecondBinding.inflate(inflater, container, false);
        recyclerView = binding.recyclerView;
        Context context = getActivity().getApplicationContext();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

        textView = binding.textView;

        actionViews();
        //animations
        shake = AnimationUtils.loadAnimation(getContext(), R.anim.shake_animatation);
        gone = AnimationUtils.loadAnimation(getContext(), R.anim.bottom_animation);
        return binding.getRoot();

    }


    private void actionViews() {
        dtWallApi = APIClient.getClient().create(DTWallApi.class);
        endpoint = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext()).getString("end", "defaultStringIfNothingFound");

    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        if (sharedPreferences.getBoolean("switch_dt", false)) {
            binding.buttonSecond.setOnClickListener(v -> {
                binding.buttonSecond.startAnimation(shake);
                progressdialog = new ProgressDialog(getContext());
                progressdialog.setMessage("Please Wait....");
                progressdialog.show();
                callApiForReal();
            });
        } else {
            binding.buttonSecond.setOnClickListener(v -> {
                binding.buttonSecond.startAnimation(gone);
                binding.buttonSecond.setVisibility(View.INVISIBLE);
                textView.setText("I told you, Enable DT Wall!");
                Toast.makeText(getContext(), "Wall is disabled! Enable DT Wall from Settings", Toast.LENGTH_SHORT).show();
            });
        }
    }

    private void callApiForReal() {
        Call<DTWall> drCall = dtWallApi.getData(endpoint);

        drCall.enqueue(new Callback<DTWall>() {
            @Override
            public void onResponse(Call<DTWall> call, Response<DTWall> response) {
                if (response.isSuccessful()) {
                    progressdialog.dismiss();
                    Log.d(TAG, "onResponse: response message :" + response.message());
                    Log.d(TAG, "onResponse: get code :" + response.body().getCode());
                    Log.d(TAG, "onResponse: message : " + response.body().getMessage());

                    try {
                        DTWall dtWall = response.body();
                        List<Offer> offers = dtWall.getOffers();

                        Information information = new Information();
                        information = dtWall.getInformation();

                        for (int i = 0; i < offers.size(); i++) {
                            Log.d(TAG, "onResponse: Titles: " + offers.get(i).getTitle());
                        }

                        Log.d(TAG, "onResponse: INFORMATION COUNTRY : " + information.getCountry());

                        Log.d(TAG, "onResponse: OFFERS LINK  : " + offers.get(0).getLink());

                        Log.d(TAG, "onResponse: BASE OBJECT CODE : " + dtWall.getCode());

                        if (offers.size() > 0) {
                            passDataToRecyclerView(offers);
                        }

                    } catch (Exception e) {
                        e.getLocalizedMessage();
                        e.printStackTrace();
                    }


                }
            }

            @Override
            public void onFailure(Call<DTWall> call, Throwable t) {
                Log.d(TAG, "onFailure: D CALL FAILED DUE TO  - " + t.getLocalizedMessage());
            }
        });
    }


    private void passDataToRecyclerView(List<Offer> offers) {
        WallAdapter wallAdapter = new WallAdapter(getContext(), offers);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(wallAdapter);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}