package xyz.gonzapico.ontrucktt.showLoads.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.Collections;
import java.util.List;
import xyz.gonzapico.ontrucktt.R;
import xyz.gonzapico.ontrucktt.showLoads.Load;

/**
 * Created by gfernandez on 6/12/16.
 */

public class LoadsAdapter extends RecyclerView.Adapter<LoadViewHolder> {
  private final LayoutInflater layoutInflater;
  private List<Load> listOfLoads;

  public LoadsAdapter(Context context) {
    this.layoutInflater =
        (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    this.listOfLoads = Collections.emptyList();
  }

  @Override public LoadViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    final View view = this.layoutInflater.inflate(R.layout.row_load, parent, false);
    return new LoadViewHolder(view);
  }

  @Override public void onBindViewHolder(LoadViewHolder holder, int position) {
    Load currentLoad = listOfLoads.get(position);
    holder.tvOrigin.setText(currentLoad.getOrigin());
    holder.tvName.setText(currentLoad.name);
    holder.tvDestination.setText(currentLoad.getDestination());
  }

  @Override public int getItemCount() {
    return listOfLoads.size();
  }

  public void setListOfLoads(List<Load> loadList) {
    this.listOfLoads = loadList;
  }
}
