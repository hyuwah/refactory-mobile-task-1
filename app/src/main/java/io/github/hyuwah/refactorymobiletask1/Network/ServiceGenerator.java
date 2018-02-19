package io.github.hyuwah.refactorymobiletask1.Network;

import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hyuwah on 07/02/18.
 */

public final class ServiceGenerator {


  public static final String BASE_URL = "https://jsonplaceholder.typicode.com/";

  // No need to instantiate this class
  private ServiceGenerator(){}


  private static HttpLoggingInterceptor logging = new HttpLoggingInterceptor().setLevel(Level.BODY);

  private static OkHttpClient.Builder httpClient = new Builder().addInterceptor(logging);


  // Configure the retrofit builder singleton
 private static Retrofit.Builder builder =
      new Retrofit.Builder()
          .baseUrl(BASE_URL)
          .client(httpClient.build())
          .addConverterFactory(GsonConverterFactory.create());

  // Build the retrofit singleton
  private static Retrofit retrofit = builder.build();


  public static <S> S createService(Class<S> serviceClass) {
    return retrofit.create(serviceClass);
  }

}
