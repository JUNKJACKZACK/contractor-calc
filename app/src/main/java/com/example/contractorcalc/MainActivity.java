package com.example.contractorcalc;
import static java.lang.Float.parseFloat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget. EditText;
import android.widget.TextView;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contractorCalc();
    }

    private void contractorCalc() {
        Button calculateButton = findViewById(R.id.buttonCalculate);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences taxRatePref = getApplicationContext().getSharedPreferences("taxRatePref", Context.MODE_PRIVATE);
                String tr = taxRatePref.getString("taxrate", "");
                final DecimalFormat decimalFormat = new DecimalFormat("0.00");

                EditText editLaborCost = findViewById(R.id.editTextLaborCostInput);
                EditText editMaterialCost = findViewById(R.id.editTextMaterialCostInput);

                String laborCostString = editLaborCost.getText().toString();
                String materialCostString = editMaterialCost.getText().toString();
                double laborCostInt = Double.parseDouble(laborCostString);
                double materialCostInt = Double.parseDouble(materialCostString);

                TextView subTotal = findViewById(R.id.textViewSubTotalView);
                TextView taxRate = findViewById(R.id.textViewTaxRateView);
                TextView tax = findViewById(R.id.textViewTaxView);
                TextView total = findViewById(R.id.textViewTotalView);

                double subTotalCalc = Double.parseDouble(decimalFormat.format(laborCostInt + materialCostInt));
                double taxCalc = Double.parseDouble(decimalFormat.format(subTotalCalc * 0.05));
                double totalCalc = Double.parseDouble(decimalFormat.format(subTotalCalc + taxCalc));

                subTotal.setText(String.format("$" + subTotalCalc));
                taxRate.setText(tr);
                tax.setText(String.format("$" + taxCalc));
                total.setText(String.format("S" + totalCalc));
            }
        });


        Button taxRateButton = findViewById(R.id.buttonTaxRate);
        taxRateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TaxRateModify.class);
                startActivity(intent);
            }
        });

    }
}