package io.github.hyuwah.refactorymobiletask1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

  // View
  Button btnApiPhoto;

  private static final int TIME_INTERVAL = 2000; // # milliseconds, desired time passed between two back presses.
  private long mBackPressed;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    setTitle("Profile");

    btnApiPhoto = findViewById(R.id.btn_api_photo);
    btnApiPhoto.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent intent = new Intent(getBaseContext(), PhotoListActivity.class);
        startActivity(intent);
      }
    });

  }

  @Override
  public void onBackPressed() {
    if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis()) {
      super.onBackPressed();
      return;
    } else {
      Toast.makeText(getBaseContext(), "Tap back again to exit", Toast.LENGTH_SHORT).show();
    }

    mBackPressed = System.currentTimeMillis();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.action_logout:
              showLogoutConfirmationDialog();
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }

  }

  private void showLogoutConfirmationDialog() {
    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivity.this);
    dialogBuilder
        .setTitle("Wait...")
        .setMessage("Are you sure you want to logout?")
        .setNegativeButton("Yes", new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialogInterface, int i) {
            Logout();
          }
        })
        .setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialogInterface, int i) {
            return;
          }
        });
    dialogBuilder.show();
  }

  private void Logout() {

    Intent intent = new Intent(this, LoginActivity.class);
    startActivity(intent);
    finish();

  }

}
