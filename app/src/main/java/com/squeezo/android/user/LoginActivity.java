/*
package com.squeezo.android.user;

import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;


public class LoginActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    private static final int RC_SIGN_IN = 0;
    private static final String TAG = "LoginActivity";
    private static final String[] permission = {"public_profile", "email"};
    private JSONObject fbResponse;
    private GoogleApiClient mGoogleApiClient;
    private boolean mIntentInProgress;
    private boolean mSignInClicked;
    private ConnectionResult mConnectionResult;
    private CallbackManager callbackManager;

    private String whichSocialSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.activity_login);

        TextView textViewForgotPassword = (TextView) findViewById(R.id.textViewForgotPasswordLogin);
        SpannableString forgotPassword = new SpannableString("Forgot Password?");
        forgotPassword.setSpan(new UnderlineSpan(), 0, forgotPassword.length(), 0);
        textViewForgotPassword.setText(forgotPassword);

        ImageButton btnSignIn = (ImageButton) findViewById(R.id.google_plus_login_button);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this).addApi(Plus.API)
                .addScope(Plus.SCOPE_PLUS_LOGIN).build();

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                whichSocialSignIn = "g_plus";

                if (mGoogleApiClient.isConnected()) {
                    Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
                    mGoogleApiClient.disconnect();
                    mGoogleApiClient.connect();
                    revokeGooglePlusAccess();
                    Toast.makeText(getApplicationContext(), "Successfully logged out", Toast.LENGTH_SHORT).show();
                } else
                    signInWithGplus();
            }
        });

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        System.out.println("Facebook Login Successful!");
                        System.out.println("Logged in user Details : ");
                        System.out.println("--------------------------");
                        System.out.println("User ID  : " + loginResult.getAccessToken().getUserId());
                        System.out.println("Authentication Token : " + loginResult.getAccessToken().getToken());

                        AccessToken token = loginResult.getAccessToken();

                        Toast.makeText(LoginActivity.this, "Login Successful!", Toast.LENGTH_LONG).show();

                        GraphRequest request = new GraphRequest(token, "/me", null, HttpMethod.GET, new GraphRequest.Callback() {
                            @Override
                            public void onCompleted(GraphResponse graphResponse) {
                                System.out.println("Facebook response " + graphResponse);
                                fbResponse = graphResponse.getJSONObject();
                                try {
                                    Toast.makeText(getApplicationContext(), fbResponse.getString("name") + "\n" + fbResponse.getString("email"), Toast.LENGTH_SHORT).show();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        request.executeAsync();
                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(LoginActivity.this, "Login cancelled by user!", Toast.LENGTH_LONG).show();
                        System.out.println("Facebook Login failed!!");
                    }

                    @Override
                    public void onError(FacebookException e) {
                        Toast.makeText(LoginActivity.this, "Login unsuccessful!", Toast.LENGTH_LONG).show();
                        System.out.println("Facebook Login failed!!");
                    }
                });

        ImageButton loginButton = (ImageButton) findViewById(R.id.facebook_login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                whichSocialSignIn = "fb";
                LoginManager.getInstance().logInWithReadPermissions(LoginActivity.this, Arrays.asList(permission));
            }
        });

        */
/*float fbIconScale = 1.8F;
        Drawable drawable = getResources().getDrawable(
                com.facebook.R.drawable.com_facebook_button_icon, null);
        if (drawable != null) {
            drawable.setBounds(0, 0, (int)(drawable.getIntrinsicWidth()*fbIconScale),
                    (int)(drawable.getIntrinsicHeight()*fbIconScale));

            loginButton.setCompoundDrawables(drawable, null, null, null);
            loginButton.setCompoundDrawablePadding(getResources().
                    getDimensionPixelSize(R.dimen.fb_margin_override_textpadding));
            loginButton.setPadding(
                    loginButton.getResources().getDimensionPixelSize(
                            R.dimen.fb_margin_override_lr),
                    loginButton.getResources().getDimensionPixelSize(
                            R.dimen.fb_margin_override_top),
                    0,
                    loginButton.getResources().getDimensionPixelSize(
                            R.dimen.fb_margin_override_bottom));
        }*//*


        final EditText txtEmail = (EditText) findViewById(R.id.editTextEmailLogin);
        final EditText txtPassword = (EditText) findViewById(R.id.editTextPasswordLogin);

        Button btnLogin = (Button) findViewById(R.id.buttonLoginLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    if (!(txtEmail.getText().toString().length() == 0) && !(txtPassword.getText().toString().length() == 0))
                        Toast.makeText(getApplicationContext(), "Login", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getApplicationContext(), "Enter all details", Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    */
/**
     * Sign-in into google
     *//*

    private void signInWithGplus() {
        if (!mGoogleApiClient.isConnecting()) {
            mSignInClicked = true;
            resolveSignInError();
        }
    }

    */
/**
     * Method to resolve any signin errors
     *//*

    private void resolveSignInError() {
        if (mConnectionResult.hasResolution()) {
            try {
                mIntentInProgress = true;
                mConnectionResult.startResolutionForResult(this, RC_SIGN_IN);
            } catch (IntentSender.SendIntentException e) {
                mIntentInProgress = false;
                mGoogleApiClient.connect();
            }
        }
    }

    */
/**
     * Revoking access from google
     *//*

    private void revokeGooglePlusAccess() {
        if (mGoogleApiClient.isConnected()) {
            Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
            Plus.AccountApi.revokeAccessAndDisconnect(mGoogleApiClient)
                    .setResultCallback(new ResultCallback<Status>() {
                        @Override
                        public void onResult(Status arg0) {
                            Log.e(TAG, "User access revoked!");
                            mGoogleApiClient.connect();
                        }

                    });
        }
    }


    @Override
    protected void onActivityResult(int reqCode, int resCode, Intent i) {
        super.onActivityResult(reqCode, resCode, i);

        if (whichSocialSignIn.equals("fb"))
            callbackManager.onActivityResult(reqCode, resCode, i);

        if (reqCode == RC_SIGN_IN) {
            if (reqCode != RESULT_OK) {
                mSignInClicked = false;
            }

            mIntentInProgress = false;

            if (!mGoogleApiClient.isConnecting()) {
                mGoogleApiClient.connect();
            }
        }
    }

    */
/**
     * Fetching user's information name, email, profile pic
     *//*

    private void getProfileInformation() {
        try {
            if (Plus.PeopleApi.getCurrentPerson(mGoogleApiClient) != null) {
                Person currentPerson = Plus.PeopleApi
                        .getCurrentPerson(mGoogleApiClient);
                String personName = currentPerson.getDisplayName();
                String personPhotoUrl = currentPerson.getImage().getUrl();
                String personGooglePlusProfile = currentPerson.getUrl();
                String email = Plus.AccountApi.getAccountName(mGoogleApiClient);

                Log.e(TAG, "Name: " + personName + ", plusProfile: "
                        + personGooglePlusProfile + ", email: " + email
                        + ", Image: " + personPhotoUrl);

                Toast.makeText(getApplication(), personName + "\n" + email, Toast.LENGTH_SHORT).show();

                */
/*txtName.setText(personName);
                txtEmail.setText(email);*//*


                // by default the profile url gives 50x50 px image only
                // we can replace the value with whatever dimension we want by
                // replacing sz=X
                */
/*personPhotoUrl = personPhotoUrl.substring(0,
                        personPhotoUrl.length() - 2)
                        + PROFILE_PIC_SIZE;

                new LoadProfileImage(imgProfilePic).execute(personPhotoUrl);*//*


            } else {
                Toast.makeText(getApplicationContext(),
                        "Person information is null", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onConnected(Bundle bundle) {
//        Toast.makeText(this, "User is connected!", Toast.LENGTH_LONG).show();

        // Get user's information
        getProfileInformation();
    }

    @Override
    public void onConnectionSuspended(int i) {
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        if (!connectionResult.hasResolution()) {
            GooglePlayServicesUtil.getErrorDialog(connectionResult.getErrorCode(), this, 0).show();
            return;
        }

        if (!mIntentInProgress) {
            // Store the ConnectionResult for later usage
            mConnectionResult = connectionResult;

            if (mSignInClicked) {
                // The user has already clicked 'sign-in' so we attempt to
                // resolve all
                // errors until the user is signed in, or they cancel.
                resolveSignInError();
            }
        }
    }

}
*/
