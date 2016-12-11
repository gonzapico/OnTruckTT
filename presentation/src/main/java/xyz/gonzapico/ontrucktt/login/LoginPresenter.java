package xyz.gonzapico.ontrucktt.login;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import javax.inject.Inject;
import xyz.gonzapico.ontrucktt.LoginActivity;
import xyz.gonzapico.ontrucktt.di.PerActivity;

/**
 * Created by gfernandez on 8/12/16.
 */
@PerActivity public class LoginPresenter implements FirebaseAuth.AuthStateListener {
  private static final String TAG = "LoginPresenter";
  private FirebaseAuth mAuth;
  private FirebaseAuth.AuthStateListener mAuthListener;

  private LoginView mLoginView;

  @Inject public LoginPresenter(FirebaseAuth firebaseAuth) {
    mAuth = firebaseAuth;

    mAuthListener = this;
  }

  public void setUpView(LoginView loginView) {
    this.mLoginView = loginView;
  }

  public void onViewAttached() {
    mAuth.addAuthStateListener(mAuthListener);
  }

  public void onViewDetached() {
    if (mAuthListener != null) {
      mAuth.removeAuthStateListener(mAuthListener);
    }
  }

  @Override public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
    FirebaseUser user = firebaseAuth.getCurrentUser();
    if (user != null) {
      // User is signed in
      Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
    } else {
      // User is signed out
      Log.d(TAG, "onAuthStateChanged:signed_out");
    }
    // [START_EXCLUDE]
    updateUI(user);
    // [END_EXCLUDE]
  }

  private void updateUI(FirebaseUser user) {
    //hideProgressDialog();
    if (user != null) {
      mLoginView.userLoggedIn(user);
    } else {
      mLoginView.userLoggedOut();
    }
  }

  public void signOut() {
    mAuth.signOut();
    updateUI(null);
  }

  public void signIn(EditText etEmail, EditText etPassword) {
    Log.d(TAG, "signIn:" + etEmail.getText().toString());
    if (!validateForm(etEmail, etPassword)) {
      return;
    }

    mLoginView.showLoading();

    mAuth.signInWithEmailAndPassword(etEmail.getText().toString(), etPassword.getText().toString())
        .addOnCompleteListener(((LoginActivity) mLoginView), new OnCompleteListener<AuthResult>() {
          @Override public void onComplete(@NonNull Task<AuthResult> task) {
            Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());

            // If sign in fails, display a message to the user. If sign in succeeds
            // the auth state listener will be notified and logic to handle the
            // signed in user can be handled in the listener.
            if (!task.isSuccessful()) {
              Log.w(TAG, "signInWithEmail:failed", task.getException());
              mLoginView.showAuthFailed();
            }

            if (!task.isSuccessful()) {
              mLoginView.showStatusAuthFailed();
            }
            mLoginView.hideLoading();
          }
        });
  }

  private boolean validateForm(EditText etEmail, EditText etPassword) {
    boolean valid = true;

    String email = etEmail.getText().toString();
    if (TextUtils.isEmpty(email)) {
      etEmail.setError("Required.");
      valid = false;
    } else {
      etEmail.setError(null);
    }

    String password = etPassword.getText().toString();
    if (TextUtils.isEmpty(password)) {
      etPassword.setError("Required.");
      valid = false;
    } else {
      etPassword.setError(null);
    }

    return valid;
  }

  public void createAccount(EditText etEmail, EditText etPassword) {
    String email = etEmail.getText().toString();
    String password = etPassword.getText().toString();
    Log.d(TAG, "createAccount:" + email);
    if (!validateForm(etEmail, etPassword)) {
      return;
    }

    mLoginView.showLoading();

    mAuth.createUserWithEmailAndPassword(email, password)
        .addOnCompleteListener((LoginActivity) mLoginView, new OnCompleteListener<AuthResult>() {
          @Override public void onComplete(@NonNull Task<AuthResult> task) {
            Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

            // If sign in fails, display a message to the user. If sign in succeeds
            // the auth state listener will be notified and logic to handle the
            // signed in user can be handled in the listener.
            if (!task.isSuccessful()) {
              mLoginView.showAuthFailed();
            }

            mLoginView.hideLoading();
          }
        });
  }
}
