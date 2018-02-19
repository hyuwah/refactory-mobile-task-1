package io.github.hyuwah.refactorymobiletask1.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hyuwah on 12/02/18.
 */

public class AccessToken {

  @SerializedName("access_token") //key di jsonnya
  private String accessToken;

  @SerializedName("token_type") //key di jsonnya
  private String tokenType;

  public String getAccessToken() {
    return accessToken;
  }

  public String getTokenType() {

    if (! Character.isUpperCase(tokenType.charAt(0))) {
      tokenType =
          Character
              .toString(tokenType.charAt(0))
              .toUpperCase() + tokenType.substring(1);
    }
    return tokenType;
  }
}
