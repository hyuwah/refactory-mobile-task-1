package io.github.hyuwah.refactorymobiletask1;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import io.github.hyuwah.refactorymobiletask1.Model.AccessToken;
import io.github.hyuwah.refactorymobiletask1.Network.GithubService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

  // Github OAuth
  private final String GITHUB_CLIENT_ID = "your-client-id";
  private final String GITHUB_CLIENT_SECRET = "your-client-secret";
  private final String GITHUB_REDIRECT_URL = "refactorymobiletask://callback"; // pastikan sama dengan callback url app yang terdaftar
  private final String GITHUB_BASE_AUTH_URL = "https://github.com/login/oauth/authorize";

  // View
  private Button btnLogin;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    btnLogin = findViewById(R.id.btn_login);
    btnLogin.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {

        getAuthorizationToken();

      }
    });
  }

  @Override
  protected void onResume() {
    super.onResume();

    getAccessToken();

  }

  // Ambil Auth Token (User login di github & auth app)
  private void getAuthorizationToken() {

    Uri GithubAuthUri = Uri.parse(GITHUB_BASE_AUTH_URL +
        "?client_id=" + GITHUB_CLIENT_ID +
        "&redirect_url=" + GITHUB_REDIRECT_URL
    );

    Intent intent = new Intent(Intent.ACTION_VIEW, GithubAuthUri);
    intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY
        | Intent.FLAG_ACTIVITY_CLEAR_TOP); // biar ga balik ke browser kalo back
    startActivity(intent);
  }

  private void getAccessToken() {
    // Check data callback dari OAuth
    Uri uri = getIntent().getData();
    if (uri != null && uri.toString().startsWith(GITHUB_REDIRECT_URL)) {

      Toast.makeText(this, uri.toString(), Toast.LENGTH_SHORT).show();
      // Get the authorization token / code
      String code = uri.getQueryParameter("code");

      // Kalo dapet authorization tokennya
      if (code != null) {

        // Bikin retrofit client dengan github service API
        Retrofit.Builder builder = new Builder()
            .baseUrl("https://github.com/")
            .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        GithubService githubService = retrofit.create(GithubService.class);

        // Request Access Token
        githubService.getAccessToken(GITHUB_CLIENT_ID, GITHUB_CLIENT_SECRET, code)
            .enqueue(new Callback<AccessToken>() {
              @Override
              public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {

                // Ambil objek accessToken
                AccessToken accessToken = response.body();

                Toast.makeText(LoginActivity.this,
                    accessToken.getTokenType() + "\n" + accessToken.getAccessToken(),
                    Toast.LENGTH_SHORT).show();

                // Go to profile / main activity
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

                finish();
              }

              @Override
              public void onFailure(Call<AccessToken> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Error getting Authorization Token",
                    Toast.LENGTH_SHORT).show();
              }
            });
      }
    }
  }

}
