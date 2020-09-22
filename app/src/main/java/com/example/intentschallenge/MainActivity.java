package com.example.intentschallenge;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnCreate;
    private ImageView ivFace, ivCall, ivWeb, ivLocation;

    private String phone, web, location;

    final int SECONDACTIVITY = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCreate = findViewById(R.id.btnCreate);

        ivFace = findViewById(R.id.ivFace);
        ivCall = findViewById(R.id.ivCall);
        ivWeb = findViewById(R.id.ivWeb);
        ivLocation = findViewById(R.id.ivLocation);

        ivFace.setVisibility(View.GONE);
        ivCall.setVisibility(View.GONE);
        ivWeb.setVisibility(View.GONE);
        ivLocation.setVisibility(View.GONE);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,
                        com.example.intentschallenge.SecondActivity.class);

                startActivityForResult(intent, SECONDACTIVITY);
            }
        });

        ivCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
                startActivity(intent);
            }
        });

        ivWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://" + web));
                startActivity(intent);
            }
        });

        ivLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0.0?q=" + location));
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SECONDACTIVITY) {
            if (resultCode == RESULT_OK) {
                String image = data.getStringExtra("image").toString();

                phone = data.getStringExtra("phone").toString();
                web = data.getStringExtra("web").toString();
                location = data.getStringExtra("location").toString();

                switch(image) {
                    case "smile":
                        ivFace.setImageResource(R.drawable.smile);
                        break;

                    case "normal":
                        ivFace.setImageResource(R.drawable.nomal);
                        break;

                    case "bad":
                        ivFace.setImageResource(R.drawable.bad);
                        break;

                    default:
                        break;
                }

                ivFace.setVisibility(View.VISIBLE);
                ivCall.setVisibility(View.VISIBLE);
                ivWeb.setVisibility(View.VISIBLE);
                ivLocation.setVisibility(View.VISIBLE);
            }

            if (resultCode == RESULT_CANCELED) {
                Toast.makeText(MainActivity.this, "No data received!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}