package xyz.gonzapico.ontrucktt.showUsers;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by gfernandez on 30/11/16.
 */
@IgnoreExtraProperties public class User {

  public String fullName;
  public String id;
  public String token;

  public User() {
    // Default constructor required for calls to DataSnapshot.getValue(User.class)
  }

  public User(String fullName, String id, String token) {
    this.fullName = fullName;
    this.id = id;
    this.token = token;
  }

  @Override public String toString() {
    return "id -> " + this.id + " fullName -> " + this.fullName + " token -> " + this.token;
  }
}
