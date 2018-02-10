package io.github.hyuwah.refactorymobiletask1.Network;

import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hyuwah on 07/02/18.
 */

public final class ServiceGenerator {


  public static final String BASE_URL = "https://jsonplaceholder.typicode.com/";

  // No need to instantiate this class
  private ServiceGenerator(){}

  // Configure the retrofit builder singleton
 private static Retrofit.Builder builder =
      new Retrofit.Builder()
          .baseUrl(BASE_URL)
          .client(new OkHttpClient.Builder().build())
          .addConverterFactory(GsonConverterFactory.create());

  // Build the retrofit singleton
  private static Retrofit retrofit = builder.build();


  public static <S> S createService(Class<S> serviceClass) {
    return retrofit.create(serviceClass);
  }

}
