package com.example.contractorcalc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget. EditText;
import com.google.android.material.slider.Slider;

public class TaxRateModify extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tax_rate_modify);

        Slider slider = findViewById(R.id.normalContinuousSlider);
        slider.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                EditText taxRate = findViewById(R.id.editTextTaxRate);
                taxRate.setText(value + "%");
                String updatedValue = String.valueOf(value);

                SharedPreferences taxRatePref;
                taxRatePref = getSharedPreferences("TaxRatePreference", Context.MODE_PRIVATE);

                SharedPreferences.Editor editor = taxRatePref.edit();
                editor.putString("taxrate", updatedValue);
                editor.commit();
            }
        });

        Button saveButton = findViewById(R.id.buttonSave);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TaxRateModify.this, MainActivity.class);


                startActivity(intent);
            }
        });
    }
}