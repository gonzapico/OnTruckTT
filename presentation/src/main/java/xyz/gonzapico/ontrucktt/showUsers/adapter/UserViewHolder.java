package xyz.gonzapico.ontrucktt.showUsers.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.gonzapico.ontrucktt.R;

/**
 * Created by gfernandez on 6/12/16.
 */

public class UserViewHolder extends RecyclerView.ViewHolder {

  @BindView(R.id.tvFullName) TextView tvFullName;
  @BindView(R.id.tvId) TextView tvId;
  @BindView(R.id.tvToken) TextView tvToken;

  public UserViewHolder(View itemView) {
    super(itemView);

    ButterKnife.bind(this, itemView);
  }
}
