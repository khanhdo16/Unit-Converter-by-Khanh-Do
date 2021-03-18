package com.example.unitconverterbykhanhdo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import static android.text.TextUtils.isEmpty;
import static java.lang.Double.*;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner unitMenu;
    private EditText inputNumber;
    private TextView labelResult1;
    private TextView labelResult2;
    private TextView labelResult3;
    private TextView result1;
    private TextView result2;
    private TextView result3;
    private static DecimalFormat df = new DecimalFormat("0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        unitMenu = findViewById(R.id.unitMenu);
        inputNumber = findViewById(R.id.inputNumber);
        labelResult1 = findViewById(R.id.labelResult1);
        labelResult2 = findViewById(R.id.labelResult2);
        labelResult3 = findViewById(R.id.labelResult3);
        result1 = findViewById(R.id.result1);
        result2 = findViewById(R.id.result2);
        result3 = findViewById(R.id.result3);

        unitMenu.setOnItemSelectedListener(this);

        inputNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                converterSwitch(unitMenu, unitMenu.getSelectedItemPosition());
            }
        });

        String[] unitSelection = getResources().getStringArray(R.array.unitSelection);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, unitSelection);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        unitMenu.setAdapter(adapter);
    }

    public void convertLength(View view) {
        if (unitMenu.getSelectedItemPosition() != 0) unitMenu.setSelection(0);
        Toast.makeText(this,"Convert Length", Toast.LENGTH_SHORT).show();

        labelResult1.setText("Centimeter");
        labelResult2.setText("Foot");
        labelResult3.setText("Inch");

        if (!isEmpty(inputNumber.getText().toString())) {
            double input = Double.parseDouble(inputNumber.getText().toString());
            double temp = input * 100;
            result1.setText(df.format(temp));
            temp = input * 3.28;
            result2.setText(df.format(temp));
            temp = input * 39.37;
            result3.setText(df.format(temp));
        }
    }

    public void convertTemp(View view) {
        if (unitMenu.getSelectedItemPosition() != 1) unitMenu.setSelection(1);
        Toast.makeText(this,"Convert Temperature", Toast.LENGTH_SHORT).show();

        labelResult1.setText("Fahrenheit");
        labelResult2.setText("Kelvin");
        labelResult3.setText("");

        if (!isEmpty(inputNumber.getText().toString())) {
            double input = Double.parseDouble(inputNumber.getText().toString());
            double temp = (input * 9/5) + 32;
            result1.setText(df.format(temp));
            temp = input + 273.15;
            result2.setText(df.format(temp));
            result3.setText("");
        }
    }

    public void convertWeight(View view) {
        if (unitMenu.getSelectedItemPosition() != 2) unitMenu.setSelection(2);
        Toast.makeText(this,"Convert Weight", Toast.LENGTH_SHORT).show();

        labelResult1.setText("Gram");
        labelResult2.setText("Ounce");
        labelResult3.setText("Pound");

        if (!isEmpty(inputNumber.getText().toString())) {
            double input = Double.parseDouble(inputNumber.getText().toString());
            double temp = input * 1000;
            result1.setText(df.format(temp));
            temp = input * 35.274;
            result2.setText(df.format(temp));
            temp = input * 2.205;
            result3.setText(df.format(temp));
        }
    }

    public void converterSwitch (View view, int position)
    {
        switch (position) {
            case 0:
                convertLength(view);
                break;
            case 1:
                convertTemp(view);
                break;
            case 2:
                convertWeight(view);
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId() == R.id.unitMenu) {
            converterSwitch(view, position);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}