package xyz.gonzapico.ontrucktt;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import butterknife.BindView;
import butterknife.ButterKnife;
import javax.inject.Inject;
import xyz.gonzapico.ontrucktt.di.components.ApplicationComponent;
import xyz.gonzapico.ontrucktt.di.components.DaggerDatabaseComponent;
import xyz.gonzapico.ontrucktt.di.components.DatabaseComponent;
import xyz.gonzapico.ontrucktt.di.modules.ActivityModule;
import xyz.gonzapico.ontrucktt.navigator.Navigator;

/**
 * Created by gfernandez on 6/12/16.
 */

public abstract class BaseOTActivity extends AppCompatActivity {
  @Inject public Navigator mNavigator;
  protected DatabaseComponent mDatabaseComponent;
  @BindView(R.id.toolbar) Toolbar mToolbar;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(getLayoutResource());

    initializeDatabaseComponent();
    this.getApplicationComponent().inject(this);

    ButterKnife.bind(this);
    setUpToolbar();
  }

  protected void setUpInternalToolbar(String toolbarTitle){
    setUpBackArrow();
    setUpToolbarTitle(toolbarTitle);
  }

  private void setUpToolbar() {
    setSupportActionBar(mToolbar);
    getSupportActionBar().setTitle(getResources().getString(R.string.app_name));
  }

  private void initializeDatabaseComponent() {
    this.mDatabaseComponent = DaggerDatabaseComponent.builder()
        .applicationComponent(getApplicationComponent())
        .activityModule(getActivityModule())
        .build();
  }

  protected void setUpBackArrow() {
    if (getSupportActionBar() != null) {

      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
  }

  protected abstract int getLayoutResource();

  public void setUpRecyclerView(RecyclerView recyclerView) {
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
  }

  /**
   * Get the Main Application component for dependency injection.
   *
   * @return {@link ApplicationComponent}
   */
  protected ApplicationComponent getApplicationComponent() {
    return ((BaseOTApplication) getApplication()).getApplicationComponent();
  }

  /**
   * Get an Activity module for dependency injection.
   *
   * @return {@link ActivityModule}
   */
  protected ActivityModule getActivityModule() {
    return new ActivityModule(this);
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        finish();
        return true;

      default:
        return super.onOptionsItemSelected(item);
    }
  }

  protected void setUpToolbarTitle(String title){
    if (mToolbar != null){
      mToolbar.setTitle(title);
    }
  }
}
