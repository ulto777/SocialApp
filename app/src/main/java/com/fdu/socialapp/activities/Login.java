package com.fdu.socialapp.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.amazon.identity.auth.device.authorization.api.AmazonAuthorizationManager;
import com.facebook.Session;
import com.fdu.socialapp.R;
import com.fdu.socialapp.custom.CognitoSyncClientManager;
import com.fdu.socialapp.custom.DeveloperAuthenticationProvider;
import com.fdu.socialapp.custom.User;
import com.fdu.socialapp.devauth.client.AmazonSharedPreferencesWrapper;

public class Login extends Activity {
    private static final String TAG = "Login";
    private AmazonAuthorizationManager mAuthManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (User.getMyUser().isLogin()){
            Intent intent = new Intent(this, Main.class);
            startActivity(intent);
            finish();
        }
        setContentView(R.layout.activity_login);

        /**
         * Initializes the sync client. This must be call before you can use it.
         */
        CognitoSyncClientManager.init(this);
    /*    final Session session = Session
                .openActiveSessionFromCache(Login.this);
*/


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
        if (id == R.id.logOut) {
            Log.i("shit", "退出1");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void login(View view){
        final TextView txtUsername = (TextView) findViewById(R.id.userName);
        final TextView txtPassword = (TextView) findViewById(R.id.pwd);
        if (txtUsername.getText().toString().isEmpty()
                || txtPassword.getText().toString().isEmpty()) {
            new AlertDialog.Builder(Login.this)
                    .setTitle("Login error")
                    .setMessage("Username or password cannot be empty!!")
                    .show();
        }
        else {
            // Clear the existing credentials
            CognitoSyncClientManager.credentialsProvider
                    .clearCredentials();
            // Initiate user authentication against the
            // developer backend in this case the sample Cognito
            // developer authentication application.
            ((DeveloperAuthenticationProvider) CognitoSyncClientManager.credentialsProvider
                    .getIdentityProvider()).login(
                    txtUsername.getText().toString(),
                    txtPassword.getText().toString(),
                    Login.this);
        }
    }

    public void loginDone(){
        Toast.makeText(Login.this, "登录成功", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, Main.class);
        startActivity(intent);
        finish();
    }

    public void toSignUp(View view){
        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
    }

    public void logOut(View view){
        // wipe data
        CognitoSyncClientManager.getInstance()
                .wipeData();

        // Wipe shared preferences
        AmazonSharedPreferencesWrapper.wipe(PreferenceManager
                .getDefaultSharedPreferences(Login.this));
        Log.i("shit", "退出1");
        finish();
    }
}
