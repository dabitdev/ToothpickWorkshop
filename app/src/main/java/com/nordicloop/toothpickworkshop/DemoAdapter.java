package com.nordicloop.toothpickworkshop;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DemoAdapter extends RecyclerView.Adapter<DemoAdapter.ViewHolder> {
    private Row[] mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    static class ViewHolder extends RecyclerView.ViewHolder {
      // each data item is just a string in this case
      View mViewRoot;
      TextView mPrimaryText;
      TextView mSecondaryText;
      ViewHolder(View v) {
        super(v);
        mViewRoot = v;
        mPrimaryText = v.findViewById(R.id.primaryText);
        mSecondaryText = v.findViewById(R.id.secondaryText);
      }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public DemoAdapter(Row[] dataset) {
      mDataset = dataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public DemoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
      // create a new view
      View viewGroup = LayoutInflater.from(parent.getContext())
          .inflate(R.layout.row_viewgroup_cell, parent, false);

      return new ViewHolder(viewGroup);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
      // - get element from your dataset at this position
      // - replace the contents of the view with that element
      final Row row = mDataset[position];
      holder.mPrimaryText.setText(row.clazz.getSimpleName());
      holder.mSecondaryText.setText(row.summary);
      holder.mViewRoot.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          Context context = v.getContext();
          context.startActivity(new Intent(context, row.clazz));
        }
      });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
      return mDataset.length;
    }
  }