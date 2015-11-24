package ozne.td2;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import ozne.td2.User;

/**
 * Created by Enzo on 17/11/15.
 */
public interface GitHubService {

    @GET("/users/{username}")
    Call<User> getUser(@Path("username") String username);

}
