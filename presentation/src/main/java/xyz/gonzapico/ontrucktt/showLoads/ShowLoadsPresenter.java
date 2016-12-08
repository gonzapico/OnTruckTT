package xyz.gonzapico.ontrucktt.showLoads;

import android.util.Log;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;
import xyz.gonzapico.ontrucktt.showUsers.User;

/**
 * Created by gfernandez on 6/12/16.
 */

public class ShowLoadsPresenter {

  private final static String TAG = "ShowUsersPresenter";

  private ShowLoadsView mShowLoadsView;

  public ShowLoadsPresenter(ShowLoadsView showLoadsView){
    this.mShowLoadsView = showLoadsView;
  }

  public void onViewAttached(){
    getLoads();
  }

  private void getLoads() {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("loads");

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

      }

      @Override public void onCancelled(DatabaseError error) {
        // Failed to read value
        Log.w(TAG, "Failed to read value.", error.toException());
      }
    });
  }

  private void onViewDetached(){

  }
}
