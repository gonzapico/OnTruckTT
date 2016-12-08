package xyz.gonzapico.ontrucktt.showUsers;

import android.util.Log;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gfernandez on 6/12/16.
 */

public class ShowUsersPresenter {

  private final static String TAG = "ShowUsersPresenter";

  private ShowUsersView mShowUsersView;

  public ShowUsersPresenter(ShowUsersView showUsersView) {
    this.mShowUsersView = showUsersView;
  }

  public void onViewAttached() {
    getUsers();
  }

  private void getUsers() {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("usersData");

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
      }

      @Override public void onCancelled(DatabaseError error) {
        // Failed to read value
        Log.w(TAG, "Failed to read value.", error.toException());
      }
    });
  }

  private void onViewDetached() {

  }
}
