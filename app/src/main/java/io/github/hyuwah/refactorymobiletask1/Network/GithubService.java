package io.github.hyuwah.refactorymobiletask1.Network;

import io.github.hyuwah.refactorymobiletask1.Model.AccessToken;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by hyuwah on 12/02/18.
 */

public interface GithubService {

  @Headers("Accept: application/json") // Kita ingin balikan dalam bentuk json
  @POST("login/oauth/access_token")
  @FormUrlEncoded // Kita ingin kirim data dengan format urlencoded
  Call<AccessToken> getAccessToken(
      @Field("client_id") String clientId,
      @Field("client_secret") String clientSecret,
      @Field("code") String code
  );

}
