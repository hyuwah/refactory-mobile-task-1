package io.github.hyuwah.refactorymobiletask1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.squareup.picasso.Picasso;
import io.github.hyuwah.refactorymobiletask1.Model.Photo;
import io.github.hyuwah.refactorymobiletask1.Network.JsonTypicodeService;
import io.github.hyuwah.refactorymobiletask1.Network.ServiceGenerator;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotoDetailActivity extends AppCompatActivity {

  public static String KEY_ID = "PHOTO_ID";

  JsonTypicodeService jsonTypicodeService;

  // View
  ImageView ivDetailPhoto;
  TextView tvDetailId, tvDetailAlbumId, tvDetailTitle, tvDetailUrl;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_photo_detail);

    Bundle bundle = getIntent().getExtras();
    int id = Integer.parseInt(bundle.getString(KEY_ID));

    setTitle("Photo ID: "+id);

    setupView();
    fetchPhoto(id);

  }

  private void setupView(){
    ivDetailPhoto = findViewById(R.id.photo_detail_image);
    tvDetailAlbumId = findViewById(R.id.photo_detail_album_id);
    tvDetailId = findViewById(R.id.photo_detail_id);
    tvDetailTitle = findViewById(R.id.photo_detail_title);
    tvDetailUrl = findViewById(R.id.photo_detail_url);
  }

  private void fetchPhoto(final int id){
    // Fetching API with Retrofit
    // Create REST adapter yang nunjuk ke JsonTypicode API endpoint
    jsonTypicodeService = ServiceGenerator.createService(JsonTypicodeService.class);

    // Execute call asynchronously
    jsonTypicodeService.getPhotoById(id).enqueue(new Callback<Photo>() {
      @Override
      public void onResponse(Call<Photo> call, Response<Photo> response) {
        // Kalo dapet response

        // Kalo sukses
        if(response.isSuccessful()){
          // Isi elemen view
          Photo photo = response.body();

          tvDetailTitle.setText(photo.getTitle());
          tvDetailAlbumId.setText("Album ID: " + photo.getAlbumId().toString());
          tvDetailId.setText("ID: " + photo.getId().toString());
          tvDetailUrl.setText("Image URL: "+photo.getUrl());

          Picasso.with(getBaseContext())
              .load(photo.getUrl())
              .placeholder(R.drawable.placeholder_img)
              .into(ivDetailPhoto);

        }
      }

      @Override
      public void onFailure(Call<Photo> call, Throwable t) {
        // Kalo gagal
        Toast.makeText(getBaseContext(), "Gagal ambil data dari API Photo with ID: "+id,Toast.LENGTH_SHORT).show();

        // log gagalnya
        Log.d("PhotosApiActivity", "Failed to fetch data : "+t.getMessage());
      }
    });
  }

}
