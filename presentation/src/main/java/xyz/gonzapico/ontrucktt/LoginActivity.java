/**
 * Copyright 2016 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package xyz.gonzapico.ontrucktt;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.OnClick;
import com.google.firebase.auth.FirebaseUser;
import xyz.gonzapico.ontrucktt.login.LoginPresenter;
import xyz.gonzapico.ontrucktt.login.LoginView;

public class LoginActivity extends BaseOTActivity implements LoginView {

  public static String COME_FROM = "come_from";
  @BindView(R.id.status) TextView mStatusTextView;
  @BindView(R.id.detail) TextView mDetailTextView;
  @BindView(R.id.email_sign_in_button) Button btnSignIn;
  @BindView(R.id.email_create_account_button) Button btnCreateAccount;
  @BindView(R.id.sign_out_button) Button btnSignOut;
  @BindView(R.id.email_password_buttons) LinearLayout llEmailPasswordButtons;
  @BindView(R.id.email_password_fields) LinearLayout llEmailPasswordFields;
  @BindView(R.id.field_email) EditText mEmailField;
  @BindView(R.id.field_password) EditText mPasswordField;
  private LoginPresenter mLoginPresenter;
  private boolean comeFromHome = false;

  public static Intent getCallingIntent(Context context) {
    return new Intent(context, LoginActivity.class);
  }

  @OnClick(R.id.email_sign_in_button) void signIn() {
    mLoginPresenter.signIn(mEmailField, mPasswordField);
  }

  @OnClick(R.id.email_create_account_button) void createAccount() {
    mLoginPresenter.createAccount(mEmailField, mPasswordField);
  }

  @OnClick(R.id.sign_out_button) void signOut() {
    mLoginPresenter.signOut();
  }

  @Override protected int getLayoutResource() {
    return R.layout.activity_emailpassword;
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    mLoginPresenter = new LoginPresenter(this);
    comeFromHome = getIntent().getBooleanExtra(COME_FROM, false);
  }

  @Override public void onStart() {
    super.onStart();
    mLoginPresenter.onViewAttached();
  }

  @Override public void onStop() {
    super.onStop();
    mLoginPresenter.onViewDetached();
  }

  @Override public void showLoading() {

  }

  @Override public void hideLoading() {

  }

  @Override public void userLoggedIn(FirebaseUser user) {
    mStatusTextView.setText(getString(R.string.emailpassword_status_fmt, user.getEmail()));
    mDetailTextView.setText(getString(R.string.firebase_status_fmt, user.getUid()));

    llEmailPasswordButtons.setVisibility(View.GONE);
    llEmailPasswordFields.setVisibility(View.GONE);
    btnSignOut.setVisibility(View.VISIBLE);

    if (!comeFromHome) this.finish();
  }

  @Override public void userLoggedOut() {
    mStatusTextView.setText(R.string.signed_out);
    mDetailTextView.setText(null);

    llEmailPasswordButtons.setVisibility(View.VISIBLE);
    llEmailPasswordFields.setVisibility(View.VISIBLE);
    btnSignOut.setVisibility(View.GONE);
  }

  @Override public void showAuthFailed() {
    Toast.makeText(this, R.string.auth_failed, Toast.LENGTH_SHORT).show();
  }

  @Override public void showStatusAuthFailed() {
    mStatusTextView.setText(R.string.auth_failed);
  }
}