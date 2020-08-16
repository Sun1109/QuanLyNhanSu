package com.example.quanlynhansu.view_models;

import android.view.View;
import android.widget.RadioButton;

import java.util.ArrayList;

public class MyRadioButtonGroup {
    private ArrayList<RadioButton> radioButtons;
    public MyRadioButtonGroup(RadioButton... radioButtons) {
        this.radioButtons = new ArrayList<RadioButton>();
        for(RadioButton rad: radioButtons){
            rad.setOnClickListener(onClick);
            this.radioButtons.add(rad);
        }
    }


    View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            for(RadioButton rad: radioButtons){
                rad.setChecked(false);
            }
            ((RadioButton) v).setChecked(true);
        }
    };
}
