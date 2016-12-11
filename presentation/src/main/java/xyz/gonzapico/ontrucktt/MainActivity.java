package xyz.gonzapico.ontrucktt;

import android.os.Bundle;
import butterknife.OnClick;
import xyz.gonzapico.ontrucktt.navigator.Navigator;

public class MainActivity extends BaseOTActivity {

  @OnClick(R.id.btnViewUsers) void viewUsers() {
    mNavigator.navigateToUsers(this);
  }

  @OnClick(R.id.btnViewLoads) void viewLoads() {
    mNavigator.navigateToLoads(this);
  }

  @OnClick(R.id.btnLogin) void login() {
    mNavigator.navigateToLogin(this, true);
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override protected int getLayoutResource() {
    return R.layout.activity_main;
  }
}
