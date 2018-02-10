package io.github.hyuwah.refactorymobiletask1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;
import io.github.hyuwah.refactorymobiletask1.Adapter.PhotosAdapter;
import io.github.hyuwah.refactorymobiletask1.Model.Photo;
import io.github.hyuwah.refactorymobiletask1.Network.ServiceGenerator;
import io.github.hyuwah.refactorymobiletask1.Network.JsonTypicodeService;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiPhotoActivity extends AppCompatActivity {

  private JsonTypicodeService jsonTypicodeService;

  // Buat recyclerview + adapter
  private List<Photo> mPhotoList = new ArrayList<>();
  private RecyclerView rvPhoto;
  private PhotosAdapter photosAdapter;

  //Spinner
  private ProgressBar pbLoading;

  //Button
  private Button btnReload;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_api_photo);

    setupView();
    fetchPhotos();

  }

  private void setupView(){
    // progress bar muncul dulu diawal
    pbLoading = findViewById(R.id.pb_loading);
    pbLoading.setVisibility(View.VISIBLE);

    btnReload = findViewById(R.id.btn_reload);
    btnReload.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        fetchPhotos();
        btnReload.setVisibility(View.GONE);
        pbLoading.setVisibility(View.VISIBLE);
      }
    });

    rvPhoto = findViewById(R.id.rv_photo);

    photosAdapter = new PhotosAdapter(this,mPhotoList);

    /// Setting RV
    // Define Layout Manager
    RecyclerView.LayoutManager lmPhoto = new GridLayoutManager(this,2);
    // Apply layout ke RV
    rvPhoto.setLayoutManager(lmPhoto);
    rvPhoto.setItemAnimator(new DefaultItemAnimator());
    // Pasang adapter ke RV
    rvPhoto.setAdapter(photosAdapter);

  }

  private void fetchPhotos(){
    // Biar ga nambah tiap kali fetch
    mPhotoList.clear();

    // Fetching API with Retrofit
    // Create REST adapter yang nunjuk ke JsonTypicode API endpoint
    jsonTypicodeService = ServiceGenerator.createService(JsonTypicodeService.class);

    // Execute call asynchronously
    jsonTypicodeService.getAllPhoto().enqueue(new Callback<List<Photo>>() {
      @Override
      public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
        // Kalo dapet response
        pbLoading.setVisibility(View.GONE);

        // Kalo sukses
        if(response.isSuccessful()){
          // Isi list photo
          mPhotoList.addAll(response.body());
        }
        // Kasi tau adapter ada data berubah
        photosAdapter.notifyDataSetChanged();
      }

      @Override
      public void onFailure(Call<List<Photo>> call, Throwable t) {
        // Kalo gagal
        pbLoading.setVisibility(View.GONE);
        Toast.makeText(getBaseContext(), "Gagal ambil data dari API Photos",Toast.LENGTH_SHORT).show();

        btnReload.setVisibility(View.VISIBLE);


        // log gagalnya
        Log.d("PhotosApiActivity", "Failed to fetch data : "+t.getMessage());
      }
    });
  }

}
