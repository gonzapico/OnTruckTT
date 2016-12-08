package xyz.gonzapico.ontrucktt.showLoads.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.Collections;
import java.util.List;
import xyz.gonzapico.ontrucktt.R;
import xyz.gonzapico.ontrucktt.showUsers.User;

/**
 * Created by gfernandez on 6/12/16.
 */

public class LoadsAdapter extends RecyclerView.Adapter<UserViewHolder> {
  private final LayoutInflater layoutInflater;
  private List<User> listOfUsers;

  public LoadsAdapter(Context context) {
    this.layoutInflater =
        (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    this.listOfUsers = Collections.emptyList();
  }

  @Override public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    final View view = this.layoutInflater.inflate(R.layout.row_user, parent, false);
    return new UserViewHolder(view);
  }

  @Override public void onBindViewHolder(UserViewHolder holder, int position) {
    holder.tvFullName.setText(listOfUsers.get(position).fullName);
    holder.tvId.setText(listOfUsers.get(position).id);
    holder.tvToken.setText(listOfUsers.get(position).token);
  }

  @Override public int getItemCount() {
    return listOfUsers.size();
  }

  public void setListOfUsers(List<User> userList) {
    this.listOfUsers = userList;
  }
}
