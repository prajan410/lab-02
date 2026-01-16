package com.example.listycity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    int selectedIndex = -1;
    TextInputEditText inputCity;
    TextInputLayout inputContainer;
    Button confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);
        String [] cities = {"Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin", "Vienna", "Tokyo", "Beijing", "Osaka", "New Delhi"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        cityList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        cityList.setOnItemClickListener((parent, view, position, id) -> {
            selectedIndex = position;
        });
        inputContainer = findViewById(R.id.input_container);
        inputCity = findViewById(R.id.input_city);
        confirm = findViewById(R.id.confirm);

    }


    public void delete(View view) {
        if (selectedIndex!=-1) {
            dataList.remove(selectedIndex);
            cityAdapter.notifyDataSetChanged();
            selectedIndex=-1;
        }
    }

    public void onClick(View view) {
        inputContainer.setVisibility(View.VISIBLE);
        confirm.setVisibility(View.VISIBLE);
    }

    public void add_city(View view) {
        String city = inputCity.getText().toString().trim();
        if (!city.isEmpty()) {
            dataList.add(city);
            cityAdapter.notifyDataSetChanged();
            inputCity.setText("");
            inputContainer.setVisibility(View.GONE);
            confirm.setVisibility(View.GONE);
        }
    }
}