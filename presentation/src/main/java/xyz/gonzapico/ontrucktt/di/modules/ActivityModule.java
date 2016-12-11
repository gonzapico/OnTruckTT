package xyz.gonzapico.ontrucktt.di.modules;

/**
 * Created by gfernandez on 10/12/16.
 */

import android.app.Activity;
import dagger.Module;
import dagger.Provides;
import xyz.gonzapico.ontrucktt.di.PerActivity;

/**
 * A module to wrap the Activity state and expose it to the graph.
 */
@Module public class ActivityModule {
  private final Activity activity;

  public ActivityModule(Activity activity) {
    this.activity = activity;
  }

  /**
   * Expose the activity to dependents in the graph.
   */
  @Provides @PerActivity Activity activity() {
    return this.activity;
  }
}
