package xyz.gonzapico.ontrucktt.showUsers;

import android.util.Log;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import xyz.gonzapico.ontrucktt.di.PerActivity;

/**
 * Created by gfernandez on 6/12/16.
 */

@PerActivity public class ShowUsersPresenter {

  private final static String TAG = "ShowUsersPresenter";

  private ShowUsersView mShowUsersView;
  private FirebaseDatabase mFirebaseDatabase;

  @Inject public ShowUsersPresenter(FirebaseDatabase firebaseDatabase) {
    this.mFirebaseDatabase = firebaseDatabase;
  }

  public void setUpView(ShowUsersView showUsersView) {
    this.mShowUsersView = showUsersView;
  }

  public void onViewAttached() {
    mShowUsersView.showLoading();
    getUsers();
  }

  private void getUsers() {
    DatabaseReference myRef = mFirebaseDatabase.getReference("usersData");

    // Read from the database
    myRef.addValueEventListener(new ValueEventListener() {
      @Override public void onDataChange(DataSnapshot dataSnapshot) {
        // This method is called once with the initial value and again
        // whenever data at this location is updated.
        List<User> listOfUsers = new ArrayList<>();
        for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
          User user = childDataSnapshot.getValue(User.class);
          Log.d(TAG, "" + user.toString());
          listOfUsers.add(user);
        }
        mShowUsersView.renderUsers(listOfUsers);
        mShowUsersView.hideLoading();
      }

      @Override public void onCancelled(DatabaseError error) {
        // Failed to read value
        Log.w(TAG, "Failed to read value.", error.toException());
        mShowUsersView.showErrorMessage("Failed to read value: " + error.toException());
        mShowUsersView.hideLoading();
      }
    });
  }

  public void onViewDetached() {

  }
}
