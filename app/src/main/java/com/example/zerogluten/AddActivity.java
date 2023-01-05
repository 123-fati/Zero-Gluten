package com.example.zerogluten;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;


public class AddActivity extends AppCompatActivity {

    Spinner spinner;
    EditText name_input, description_input, price_input, p_input;
    Button add_button;
    String category;


    private static final int REQUEST_CODE_CHOOSE_IMAGE = 1;

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


        Button chooseImageButton = findViewById(R.id.choose_image_button);
        chooseImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the image gallery app
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_CHOOSE_IMAGE);
            }
        });



        add_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                // Get the product name and image from the form
                ImageView imageView = findViewById(R.id.image_view);

                // Convert the image to a byte array
                Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
                byte[] imageByteArray = outputStream.toByteArray();



                MyDbHelper myDB = new MyDbHelper(AddActivity.this);
                myDB.addProducts(
                        name_input.getText().toString().trim(),
                        description_input.getText().toString().trim(),
                        Integer.valueOf(price_input.getText().toString().trim()),
                        Integer.valueOf(p_input.getText().toString().trim()),
                        category,
                        imageByteArray
                        );
            }

        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Check if the request code is the same as the one we used
        if (requestCode == REQUEST_CODE_CHOOSE_IMAGE && resultCode == RESULT_OK && data != null) {
            // Get the URI of the selected image
            Uri selectedImageUri = data.getData();

            // Set the image in the image view
            ImageView imageView = findViewById(R.id.image_view);
            try {
                // Get the input stream of the image
                InputStream inputStream = getContentResolver().openInputStream(selectedImageUri);

                // Convert the input stream to a bitmap
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}

