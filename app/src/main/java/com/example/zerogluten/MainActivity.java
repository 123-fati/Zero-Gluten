package com.example.zerogluten;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    SearchView searchView;
    FloatingActionButton add_button;

    MyDbHelper myDB;
    ArrayList<String> product_id, product_name, product_description, product_price, product_poid, product_category;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = findViewById(R.id.recycleView);
        add_button = findViewById(R.id.add_button);


        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        myDB = new MyDbHelper(MainActivity.this);
        product_id = new ArrayList<>();
        product_name = new ArrayList<>();
        product_description = new ArrayList<>();
        product_price = new ArrayList<>();
        product_poid = new ArrayList<>();
        product_category = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomAdapter(MainActivity.this,this, product_id, product_name, product_description, product_price, product_poid,
                product_category);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.arabe:
                Toast.makeText(this, "Arabe selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.français:
                Toast.makeText(this, "français selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.anglais:
                Toast.makeText(this, "anglais selected", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    void storeDataInArrays(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){
            //empty_imageview.setVisibility(View.VISIBLE);
            //no_data.setVisibility(View.VISIBLE);
            Toast.makeText(this, "no data", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                product_id.add(cursor.getString(0));
                product_name.add(cursor.getString(1));
                product_description.add(cursor.getString(2));
                product_price.add(cursor.getString(3));
                product_poid.add(cursor.getString(4));
                product_category.add(cursor.getString(5));
            }
            //empty_imageview.setVisibility(View.GONE);
            //no_data.setVisibility(View.GONE);
        }
    }
}