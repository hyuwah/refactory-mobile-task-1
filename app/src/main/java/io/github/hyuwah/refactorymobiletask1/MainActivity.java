package io.github.hyuwah.refactorymobiletask1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

  Button btnApiPhoto, btnLogout;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    setTitle("Profile");

    btnApiPhoto = findViewById(R.id.btn_api_photo);
    btnApiPhoto.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent intent = new Intent(getBaseContext(),PhotoListActivity.class);
        startActivity(intent);
      }
    });

    btnLogout = findViewById(R.id.btn_logout);
    btnLogout.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        Logout();
      }
    });

  }

  private void Logout(){

    Intent intent = new Intent(this, LoginActivity.class);
    startActivity(intent);
    finish();

  }

}
