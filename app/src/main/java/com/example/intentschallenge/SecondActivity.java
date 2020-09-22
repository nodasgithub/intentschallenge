package com.example.intentschallenge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    private EditText etName, etPhone, etWeb, etLocation;
    private ImageView ivSmile, ivNormal, ivBad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        etName = findViewById(R.id.etName);
        etPhone = findViewById(R.id.etPhone);
        etWeb = findViewById(R.id.etWeb);
        etLocation = findViewById(R.id.etLocation);

        ivSmile = findViewById(R.id.ivSmile);
        ivNormal = findViewById(R.id.ivNormal);
        ivBad = findViewById(R.id.ivBad);

        ivSmile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageClicked("smile");
            }
        });

        ivNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageClicked("normal");
            }
        });

        ivBad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageClicked("bad");
            }
        });
    }

    private void imageClicked(String image) {
        if (etName.getText().toString().isEmpty() ||
                etPhone.getText().toString().isEmpty() ||
                etWeb.getText().toString().isEmpty() ||
                etLocation.getText().toString().isEmpty()) {

            Toast.makeText(SecondActivity.this, "Please enter all fields!", Toast.LENGTH_SHORT).show();
        } else {
            String name = etName.getText().toString().trim();
            String phone = etPhone.getText().toString().trim();
            String web = etWeb.getText().toString().trim();
            String location = etLocation.getText().toString().trim();

            Intent intent = new Intent();
            intent.putExtra("name", name);
            intent.putExtra("phone", phone);
            intent.putExtra("web", web);
            intent.putExtra("location", location);
            intent.putExtra("image", image);

            setResult(RESULT_OK, intent);
            SecondActivity.this.finish();
        }
    }
}