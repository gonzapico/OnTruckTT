package xyz.gonzapico.ontrucktt.showLoads.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.gonzapico.ontrucktt.R;

/**
 * Created by gfernandez on 6/12/16.
 */

public class LoadViewHolder extends RecyclerView.ViewHolder {


  @BindView(R.id.tvName) TextView tvName;
  @BindView(R.id.tvOrigin) TextView tvOrigin;
  @BindView(R.id.tvDestination) TextView tvDestination;
  public LoadViewHolder(View itemView) {
    super(itemView);

    ButterKnife.bind(this, itemView);
  }
}
