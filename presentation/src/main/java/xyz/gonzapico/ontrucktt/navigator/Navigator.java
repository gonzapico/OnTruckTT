package xyz.gonzapico.ontrucktt.navigator;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import xyz.gonzapico.ontrucktt.LoginActivity;
import xyz.gonzapico.ontrucktt.ShowLoadsActivity;
import xyz.gonzapico.ontrucktt.ShowUsersActivity;

/**
 * Created by gfernandez on 8/12/16.
 */

public class Navigator {

  public Navigator() {

  }

  public void navigateToUsers(Context context) {
    if (context != null) {
      Intent intentToLaunch = ShowUsersActivity.getCallingIntent(context);
      context.startActivity(intentToLaunch);
    }
  }

  public void navigateToLoads(Context context) {
    if (context != null) {
      Intent intentToLaunch = ShowLoadsActivity.getCallingIntent(context);
      context.startActivity(intentToLaunch);
    }
  }

  public void navigateToLogin(Context context, boolean comeFromHome) {
    if (context != null) {
      Intent intentToLaunch = LoginActivity.getCallingIntent(context);
      intentToLaunch.putExtra(LoginActivity.COME_FROM, comeFromHome);
      context.startActivity(intentToLaunch);
    }
  }
}
