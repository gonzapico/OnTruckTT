package xyz.gonzapico.ontrucktt.showUsers;

import java.util.List;

/**
 * Created by gfernandez on 6/12/16.
 */

public interface ShowUsersView {

  void showLoading();

  void hideLoading();

  void renderUsers(List<User> userList);

  void showErrorMessage(String msgError);
}
