package xyz.gonzapico.ontrucktt;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(getLayoutResource());

    initializeDatabaseComponent();
    this.getApplicationComponent().inject(this);

    ButterKnife.bind(this);
  }

  private void initializeDatabaseComponent() {
    this.mDatabaseComponent = DaggerDatabaseComponent.builder()
        .applicationComponent(getApplicationComponent())
        .activityModule(getActivityModule())
        .build();
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
}
