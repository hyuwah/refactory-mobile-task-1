package io.github.hyuwah.refactorymobiletask1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.squareup.picasso.Picasso;
import de.hdodenhof.circleimageview.CircleImageView;
import io.github.hyuwah.refactorymobiletask1.Model.GithubUser;
import io.github.hyuwah.refactorymobiletask1.Network.GithubService;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileActivity extends AppCompatActivity {

  private String TAG = getClass().getSimpleName();

  // View
  Button btnApiPhoto;
  CircleImageView ivProfilePic;
  TextView tvProfileName, tvProfileGithubUrl, tvProfileGithubFollowers, tvProfileGithubRepo;

  private static final int TIME_INTERVAL = 2000; // # milliseconds, desired time passed between two back presses.
  private long mBackPressed;

  Bundle bundle;

  private String ACCESS_TOKEN_GITHUB;
  private String TOKEN_TYPE_GITHUB;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_profile);

    setTitle("Profile");

    bundle = getIntent().getExtras();

    ivProfilePic = findViewById(R.id.iv_profile_picture);
    tvProfileName = findViewById(R.id.tv_profile_name);
    tvProfileGithubUrl = findViewById(R.id.tv_profile_github_url);
    tvProfileGithubFollowers = findViewById(R.id.tv_profile_github_followers);
    tvProfileGithubRepo = findViewById(R.id.tv_profile_github_repo);

    btnApiPhoto = findViewById(R.id.btn_api_photo);
    btnApiPhoto.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent intent = new Intent(getBaseContext(), PhotoListActivity.class);
        startActivity(intent);
      }
    });

    if(bundle!=null){
      Log.i(TAG, "onCreate: bundle not null");
      ACCESS_TOKEN_GITHUB = bundle.getString("accesstoken");
      TOKEN_TYPE_GITHUB = bundle.getString("tokentype");

      fetchGithubData();


    }


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
    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(ProfileActivity.this);
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

  private void fetchGithubData(){

    Retrofit.Builder builder = new Builder()
        .baseUrl("https://api.github.com/")
        .client(new OkHttpClient.Builder()
            .addInterceptor(new HttpLoggingInterceptor().setLevel(Level.BODY))
            .build())
        .addConverterFactory(GsonConverterFactory.create());

    Retrofit retrofit = builder.build();
    final GithubService githubService = retrofit.create(GithubService.class);

    githubService.getUserDetail(ACCESS_TOKEN_GITHUB).enqueue(new Callback<GithubUser>() {
      @Override
      public void onResponse(Call<GithubUser> call, Response<GithubUser> response) {
        if(response.isSuccessful()){
          Log.i(TAG, "onResponse:"+response.message());
          GithubUser githubUser = response.body();

          Picasso.with(ProfileActivity.this).load(githubUser.getAvatarUrl()).into(ivProfilePic);
          tvProfileName.setText(githubUser.getName());

          tvProfileGithubUrl.setText(githubUser.getHtmlUrl());
          tvProfileGithubFollowers.setText("Followers: "+githubUser.getFollowers());
          tvProfileGithubRepo.setText("Repository: "+githubUser.getPublicRepos());

        }
      }

      @Override
      public void onFailure(Call<GithubUser> call, Throwable t) {

      }
    });



  }

}
