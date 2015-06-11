package com.squeezo.android.user.intro;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.squeezo.android.user.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class SignInFragment extends android.support.v4.app.Fragment implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    protected static final int RC_SIGN_IN = 0;
    protected static String whichSocialSignIn;
    private final String TAG = "IntroActivity";
    private final String[] permission = {"public_profile", "email"};
    private JSONObject fbResponse;
    private GoogleApiClient mGoogleApiClient;
    private boolean mIntentInProgress;
    private boolean mSignInClicked;
    private ConnectionResult mConnectionResult;
    private CallbackManager callbackManager;
    private boolean fbLogin = false;

    private Context context;
    private Activity activity;

    private FragmentManager manager;
    private FragmentTransaction transaction;

    public SignInFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
        context = getActivity().getApplicationContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);

        FacebookSdk.sdkInitialize(getActivity());
        callbackManager = CallbackManager.Factory.create();
        manager = getFragmentManager();

        final EditText editEmail = (EditText) view.findViewById(R.id.editTextEmailLogin);
        final EditText editPassword = (EditText) view.findViewById(R.id.editTextPasswordLogin);

        Button btnLogin = (Button) view.findViewById(R.id.buttonLoginLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editEmail.getText().toString().length() == 0)
                    editEmail.setBackgroundResource(R.drawable.custom_edit_text_error);
                else if (editPassword.getText().toString().length() == 0)
                    editPassword.setBackgroundResource(R.drawable.custom_edit_text_error);
                else
                    Toast.makeText(activity, "Login Successful", Toast.LENGTH_SHORT).show();
            }
        });

        TextView txtForgotPassword = (TextView) view.findViewById(R.id.textViewForgotPasswordSignIn);
        txtForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ForgotPasswordFragment fragment = new ForgotPasswordFragment();
                transaction = manager.beginTransaction();
                transaction.replace(R.id.relativeLayoutIntro, fragment, "forgotPasswordFragment");
                transaction.addToBackStack("forgotPassword");
                transaction.commit();
            }
        });

        ImageButton btnSignIn = (ImageButton) view.findViewById(R.id.google_plus_login_button);

        mGoogleApiClient = new GoogleApiClient.Builder(context)
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
                    Toast.makeText(activity, "Successfully logged out", Toast.LENGTH_SHORT).show();
                } else
                    signInWithGplus();
            }
        });

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        fbLogin = true;
                        System.out.println("Facebook Login Successful!");
                        System.out.println("Logged in user Details : ");
                        System.out.println("--------------------------");
                        System.out.println("User ID  : " + loginResult.getAccessToken().getUserId());
                        System.out.println("Authentication Token : " + loginResult.getAccessToken().getToken());

                        AccessToken token = loginResult.getAccessToken();

                        Toast.makeText(activity, "Login Successful!", Toast.LENGTH_LONG).show();

                        GraphRequest request = new GraphRequest(token, "/me", null, HttpMethod.GET, new GraphRequest.Callback() {
                            @Override
                            public void onCompleted(GraphResponse graphResponse) {
                                System.out.println("Facebook response " + graphResponse);
                                fbResponse = graphResponse.getJSONObject();
                                try {
                                    Toast.makeText(context, fbResponse.getString("name") + "\n" + fbResponse.getString("email"), Toast.LENGTH_SHORT).show();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        request.executeAsync();
                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(activity, "Login cancelled by user!", Toast.LENGTH_LONG).show();
                        System.out.println("Facebook Login failed!!");
                    }

                    @Override
                    public void onError(FacebookException e) {
                        Toast.makeText(activity, "Login unsuccessful!", Toast.LENGTH_LONG).show();
                        System.out.println("Facebook Login failed!!");
                    }
                });

        ImageButton loginButton = (ImageButton) view.findViewById(R.id.facebook_login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                whichSocialSignIn = "fb";

                if (!fbLogin)
                    LoginManager.getInstance().logInWithReadPermissions(activity, Arrays.asList(permission));
                else {
                    LoginManager.getInstance().logOut();
                    Toast.makeText(context, "Successfully Logged Out", Toast.LENGTH_SHORT).show();
                }

            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    /**
     * Sign-in into google
     */
    private void signInWithGplus() {
        if (!mGoogleApiClient.isConnecting()) {
            mSignInClicked = true;
            resolveSignInError();
        }
    }

    /**
     * Method to resolve any signin errors
     */
    private void resolveSignInError() {
        if (mConnectionResult.hasResolution()) {
            try {
                mIntentInProgress = true;
                mConnectionResult.startResolutionForResult(activity, RC_SIGN_IN);
            } catch (IntentSender.SendIntentException e) {
                mIntentInProgress = false;
                mGoogleApiClient.connect();
            }
        }
    }

    /**
     * Revoking access from google
     */
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
    public void onActivityResult(int reqCode, int resCode, Intent i) {
        super.onActivityResult(reqCode, resCode, i);


        if (whichSocialSignIn.equals("fb")) {
            Log.d("activity result frag fb", "req code = " + reqCode + " rescode = " + resCode);
            callbackManager.onActivityResult(reqCode, resCode, i);
        }


        if (reqCode == RC_SIGN_IN) {
            Log.d("activity result frag gp", "req code = " + reqCode + " rescode = " + resCode);
            mIntentInProgress = false;

            if (!mGoogleApiClient.isConnecting()) {
                mGoogleApiClient.connect();
            }
        }
    }

    /**
     * Fetching user's information name, email, profile pic
     */
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

                Toast.makeText(context, personName + "\n" + email, Toast.LENGTH_SHORT).show();

                /*txtName.setText(personName);
                txtEmail.setText(email);*/

                // by default the profile url gives 50x50 px image only
                // we can replace the value with whatever dimension we want by
                // replacing sz=X
                /*personPhotoUrl = personPhotoUrl.substring(0,
                        personPhotoUrl.length() - 2)
                        + PROFILE_PIC_SIZE;

                new LoadProfileImage(imgProfilePic).execute(personPhotoUrl);*/

            } else {
                Toast.makeText(context,
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
            GooglePlayServicesUtil.getErrorDialog(connectionResult.getErrorCode(), activity, 0).show();
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

    private String passwordToMD5(String password) {

        String generatedPassword = null;

        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(password.getBytes());
            //Get the hash's bytes
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;

    }

}
