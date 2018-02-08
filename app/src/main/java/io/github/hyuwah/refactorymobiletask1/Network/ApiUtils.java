package io.github.hyuwah.refactorymobiletask1.Network;

/**
 * Created by hyuwah on 07/02/18.
 */

public class ApiUtils {

  public static final String BASE_URL = "https://jsonplaceholder.typicode.com/";

  public static IPhotoService getAllPhoto(){
    return RetrofitClient.getClient(BASE_URL).create(IPhotoService.class);
  }

  public static IPhotoService getPhoto(int id){
    return RetrofitClient.getClient(BASE_URL).create(IPhotoService.class);
  }

}
