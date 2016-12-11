package xyz.gonzapico.ontrucktt;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import butterknife.BindView;
import java.util.List;
import javax.inject.Inject;
import xyz.gonzapico.ontrucktt.showUsers.ShowUsersPresenter;
import xyz.gonzapico.ontrucktt.showUsers.ShowUsersView;
import xyz.gonzapico.ontrucktt.showUsers.User;
import xyz.gonzapico.ontrucktt.showUsers.adapter.UsersAdapter;

public class ShowUsersActivity extends BaseOTActivity implements ShowUsersView {
  @BindView(R.id.rvUsers) RecyclerView rvUsers;
  @BindView(R.id.pbLoading) ProgressBar pbLoading;
  @BindView(R.id.llGlobalShowUsers) LinearLayout llGlobalUsers;
  @Inject ShowUsersPresenter showUsersPresenter;

  public static Intent getCallingIntent(Context context) {
    return new Intent(context, ShowUsersActivity.class);
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mDatabaseComponent.inject(this);
    showUsersPresenter.setUpView(this);
    setUpRecyclerView(rvUsers);

    super.setUpInternalToolbar(getResources().getString(R.string.title_activity_show_users));
  }

  @Override protected int getLayoutResource() {
    return R.layout.activity_show_users;
  }

  @Override public void showLoading() {
    pbLoading.setVisibility(View.VISIBLE);
  }

  @Override public void hideLoading() {
    pbLoading.setVisibility(View.GONE);
  }

  @Override protected void onStart() {
    super.onStart();

    showUsersPresenter.onViewAttached();
  }

  @Override protected void onStop() {
    super.onStop();

    showUsersPresenter.onViewDetached();
  }

  @Override public void renderUsers(List<User> userList) {
    UsersAdapter usersAdapter = new UsersAdapter(this);
    usersAdapter.setListOfUsers(userList);

    rvUsers.setAdapter(usersAdapter);
    usersAdapter.notifyDataSetChanged();
  }

  @Override public void showErrorMessage(String msgError) {
    Snackbar.make(llGlobalUsers, msgError, Snackbar.LENGTH_SHORT).show();
  }
}
