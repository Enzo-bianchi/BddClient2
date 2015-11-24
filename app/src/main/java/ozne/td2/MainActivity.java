package ozne.td2;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;

import retrofit.Call;
import retrofit.Callback;
import ozne.td2.User;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;


public class MainActivity extends Activity {
    SharedPreferences  mPrefs;
    SharedPreferences.Editor editor;
    Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPrefs = getPreferences(MODE_PRIVATE);

        /*
            On verifie si il y a deja un user dans les sharedpreferences
         */
        String lastresearch = mPrefs.getString("LastNameResearch", null);
        if (lastresearch != null){
            gson = new Gson();
            User LastUser = gson.fromJson(lastresearch, User.class);

            Log.i("MODEL SHAREDPREF : ", "Github Name :" + LastUser.getName() + "\nCompany :" + LastUser.getCompany() + "\nLogin :" + LastUser.getLogin()
                    + "\nMail :" + LastUser.getEmail() + "\nFollowers :" + LastUser.getFollowers() + "\nLocation :" + LastUser.getLocation());

        }
        /*
            Ensuite on initialise l'API
         */


        String API = "https://api.github.com";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GitHubService git = retrofit.create(GitHubService.class);
        Call call = git.getUser("enygmatik92");

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Response<User> response, Retrofit retrofit) {

                User model = response.body();

                if (model == null) {
                    //404 or the response cannot be converted to User.
                    ResponseBody responseBody = response.errorBody();
                    if (responseBody != null) {
                        try {
                            Log.i("ResponseBody", "responseBody = " + responseBody.string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Log.i("ResponseBody : ", "null");
                    }
                } else {
                    /* Reponse 200, tout est OK, on verifie dans la console*/
                    Log.i("MODEL : ", "Github Name :" + model.getName() +
                            "\nCompany :" + model.getCompany() +
                            "\nLogin :" + model.getLogin() +
                            "\nMail :" + model.getEmail() +
                            "\nFollowers :" + model.getFollowers() +
                            "\nLocation :" + model.getLocation());


                    /* On ajoute directement notre user avec le convertisseur dans les sharedPreferences */

                    editor = mPrefs.edit();
                    gson = new Gson();
                    String json = gson.toJson(model);
                    editor.putString("LastNameResearch", json);
                    editor.commit();


                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("Erreur", "une erreur est survenue" + t.getMessage());
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
