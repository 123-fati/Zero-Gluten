package com.example.zerogluten;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class AddActivity extends AppCompatActivity {

    Spinner spinner;
    EditText name_input, description_input, price_input, p_input;
    Button add_button;
    String category;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        spinner = findViewById(R.id.categorie);



        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.categories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        //String category = spinner.getSelectedItem().toString();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                category =spinner.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });



        name_input = findViewById(R.id.name_input);
        description_input = findViewById(R.id.description_input);
        price_input = findViewById(R.id.price_input);
        p_input = findViewById(R.id.p_input);

        add_button = findViewById(R.id.add_button);

        add_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                MyDbHelper myDB = new MyDbHelper(AddActivity.this);
                myDB.addProducts(
                        name_input.getText().toString().trim(),
                        description_input.getText().toString().trim(),
                        Integer.valueOf(price_input.getText().toString().trim()),
                        Integer.valueOf(p_input.getText().toString().trim()),
                        category
                        );
            }
        });
    }
}