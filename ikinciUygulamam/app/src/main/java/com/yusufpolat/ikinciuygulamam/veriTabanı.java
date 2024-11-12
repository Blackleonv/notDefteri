package com.yusufpolat.ikinciuygulamam;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yusufpolat.ikinciuygulamam.databinding.ActivitySeconScreenBinding;

import java.util.Scanner;

public class veriTabanı extends RecyclerView.Adapter <veriTabanı.holder>{

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ActivitySeconScreenBinding activitySeconScreenBinding = ActivitySeconScreenBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new holder(activitySeconScreenBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class holder extends RecyclerView.ViewHolder{

        private ActivitySeconScreenBinding binding;
        public holder(ActivitySeconScreenBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}

