package xyz.gonzapico.ontrucktt;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import butterknife.BindView;
import java.util.List;
import xyz.gonzapico.ontrucktt.showLoads.Load;
import xyz.gonzapico.ontrucktt.showLoads.ShowLoadsPresenter;
import xyz.gonzapico.ontrucktt.showLoads.ShowLoadsView;
import xyz.gonzapico.ontrucktt.showLoads.adapter.LoadsAdapter;
import xyz.gonzapico.ontrucktt.showUsers.ShowUsersPresenter;
import xyz.gonzapico.ontrucktt.showUsers.ShowUsersView;
import xyz.gonzapico.ontrucktt.showUsers.User;
import xyz.gonzapico.ontrucktt.showUsers.adapter.UsersAdapter;

public class MainActivity extends BaseOTActivity implements ShowUsersView, ShowLoadsView {

  @BindView(R.id.rvUsers) RecyclerView rvUsers;
  @BindView(R.id.rvLoads) RecyclerView rvLoads;
  private ShowUsersPresenter showUsersPresenter;
  private ShowLoadsPresenter showLoadsPresenter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    showUsersPresenter = new ShowUsersPresenter(this);
    showLoadsPresenter = new ShowLoadsPresenter(this);
    setUpRecyclerView(rvUsers);
    setUpRecyclerView(rvLoads);
  }

  private void setUpRecyclerView(RecyclerView recyclerView) {
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
  }

  @Override protected void onStart() {
    super.onStart();

    showUsersPresenter.onViewAttached();
    showLoadsPresenter.onViewAttached();
  }

  @Override protected int getLayoutResource() {
    return R.layout.activity_main;
  }

  @Override public void showLoading() {

  }

  @Override public void hideLoading() {

  }

  @Override public void renderUsers(List<User> userList) {
    UsersAdapter usersAdapter = new UsersAdapter(this);
    usersAdapter.setListOfUsers(userList);

    rvUsers.setAdapter(usersAdapter);
    usersAdapter.notifyDataSetChanged();
  }

  @Override public void renderLoads(List<Load> loadsList) {
    LoadsAdapter loadsAdapter = new LoadsAdapter(this);
    loadsAdapter.setListOfLoads(loadsList);

    rvLoads.setAdapter(loadsAdapter);
    loadsAdapter.notifyDataSetChanged();

  }
}
