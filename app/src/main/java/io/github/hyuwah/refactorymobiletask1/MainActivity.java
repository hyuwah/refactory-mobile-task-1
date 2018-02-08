package io.github.hyuwah.refactorymobiletask1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

  Button btnApiPhoto;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    setTitle("Profile");

    btnApiPhoto = findViewById(R.id.btn_api_photo);
    btnApiPhoto.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent intent = new Intent(getBaseContext(),ApiPhotoActivity.class);
        startActivity(intent);
      }
    });

  }
}
