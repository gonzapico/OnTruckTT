package xyz.gonzapico.ontrucktt.showLoads;

import android.util.Log;
import android.view.View;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import xyz.gonzapico.ontrucktt.BaseOTActivity;
import xyz.gonzapico.ontrucktt.ShowLoadsActivity;
import xyz.gonzapico.ontrucktt.di.PerActivity;
import xyz.gonzapico.ontrucktt.navigator.Navigator;

/**
 * Created by gfernandez on 6/12/16.
 */

@PerActivity public class ShowLoadsPresenter implements View.OnClickListener {

  private final static String TAG = "ShowUsersPresenter";

  private ShowLoadsView mShowLoadsView;
  private FirebaseDatabase mFirebaseDatabase;

  @Inject public ShowLoadsPresenter(FirebaseDatabase firebaseDatabase) {
    this.mFirebaseDatabase = firebaseDatabase;
  }

  public void setUpView(ShowLoadsView showLoadsView){
    this.mShowLoadsView = showLoadsView;
  }

  public void onViewAttached() {
    getLoads();
    mShowLoadsView.showLoading();
  }

  private void getLoads() {

    DatabaseReference myRef = mFirebaseDatabase.getReference("loads");

    // Read from the database
    myRef.addValueEventListener(new ValueEventListener() {
      @Override public void onDataChange(DataSnapshot dataSnapshot) {
        // This method is called once with the initial value and again
        // whenever data at this location is updated.
        List<Load> listOfLoads = new ArrayList<>();
        for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
          Load load = childDataSnapshot.getValue(Load.class);
          Log.d(TAG, "" + load.toString());
          listOfLoads.add(load);
        }
        mShowLoadsView.renderLoads(listOfLoads);
        mShowLoadsView.hideLoading();
      }

      @Override public void onCancelled(DatabaseError error) {
        // Failed to read value
        Log.w(TAG, "Failed to read value.", error.toException());
        mShowLoadsView.hideLoading();
        switch (error.getCode()) {
          case DatabaseError.PERMISSION_DENIED:
            mShowLoadsView.showPermissionDeniedError();
            break;
          default:
            mShowLoadsView.showErrorMessage(error.getMessage());
            break;
        }
      }
    });
  }

  public void onViewDetached() {

  }

  @Override public void onClick(View view) {
    if ((view != null) && (view.getContext() != null)) {
      if (mShowLoadsView instanceof ShowLoadsActivity){
        ((ShowLoadsActivity) mShowLoadsView).finish();
      }
      if (mShowLoadsView instanceof BaseOTActivity)
        ((BaseOTActivity) mShowLoadsView).mNavigator.navigateToLogin(view.getContext(), false);
    }
  }
}
