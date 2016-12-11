package xyz.gonzapico.ontrucktt.login;

import android.widget.EditText;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by gfernandez on 8/12/16.
 */

public interface LoginView {

  void showLoading();

  void hideLoading();

  void userLoggedIn(FirebaseUser user);

  void userLoggedOut();

  void showAuthFailed();

  void showStatusAuthFailed();
}
