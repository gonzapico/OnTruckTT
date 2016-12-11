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
import xyz.gonzapico.ontrucktt.showLoads.Load;
import xyz.gonzapico.ontrucktt.showLoads.ShowLoadsPresenter;
import xyz.gonzapico.ontrucktt.showLoads.ShowLoadsView;
import xyz.gonzapico.ontrucktt.showLoads.adapter.LoadsAdapter;

public class ShowLoadsActivity extends BaseOTActivity implements ShowLoadsView {
  @BindView(R.id.rvLoads) RecyclerView rvLoads;
  @BindView(R.id.pbLoading) ProgressBar pbLoading;
  @BindView(R.id.llGlobalShowLoads) LinearLayout llGlobalLoads;
  @Inject ShowLoadsPresenter showLoadsPresenter;

  public static Intent getCallingIntent(Context context) {
    return new Intent(context, ShowLoadsActivity.class);
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mDatabaseComponent.inject(this);
    showLoadsPresenter.setUpView(this);
    setUpRecyclerView(rvLoads);
  }

  @Override protected int getLayoutResource() {
    return R.layout.activity_show_loads;
  }

  @Override protected void onStart() {
    super.onStart();

    showLoadsPresenter.onViewAttached();
  }

  @Override protected void onStop() {
    super.onStop();

    showLoadsPresenter.onViewDetached();
  }

  @Override public void renderLoads(List<Load> loadsList) {
    LoadsAdapter loadsAdapter = new LoadsAdapter(this);
    loadsAdapter.setListOfLoads(loadsList);

    rvLoads.setAdapter(loadsAdapter);
    loadsAdapter.notifyDataSetChanged();
  }

  @Override public void showLoading() {
    pbLoading.setVisibility(View.VISIBLE);
  }

  @Override public void hideLoading() {
    pbLoading.setVisibility(View.GONE);
  }

  @Override public void showErrorMessage(String msgError) {
    Snackbar.make(llGlobalLoads, msgError, Snackbar.LENGTH_SHORT).show();
  }

  @Override public void showPermissionDeniedError() {
    Snackbar.make(llGlobalLoads, getResources().getString(R.string.login_required),
        Snackbar.LENGTH_LONG)
        .setAction(getResources().getText(R.string.login_button), showLoadsPresenter)
        .show();
  }
}
