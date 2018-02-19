package io.github.hyuwah.refactorymobiletask1.Network;

import io.github.hyuwah.refactorymobiletask1.Model.Photo;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by hyuwah on 07/02/18.
 */

public interface JsonTypicodeService {

  String BASE_URL = "https://jsonplaceholder.typicode.com/";

  // Photo Endpoint

  @GET("photos")
  Call<List<Photo>> getAllPhoto();

  @GET("photos/{id}")
  Call<Photo> getPhotoById(@Path("id") int id);

}
