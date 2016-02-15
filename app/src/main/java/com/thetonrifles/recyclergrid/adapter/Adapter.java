package com.thetonrifles.recyclergrid.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.thetonrifles.recyclergrid.R;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ListViewHolder> {

    protected static class ListViewHolder extends RecyclerView.ViewHolder {

        TextView txt_label;

        public ListViewHolder(View itemView) {
            super(itemView);
            txt_label = (TextView) itemView.findViewById(R.id.txt_label);
        }

    }

    private Context mContext;
    private LayoutInflater mLayoutInflater;

    private List<ListItem> mItems;
    private List<ListItem> mOriginalItems;

    public Adapter(Context context, List<ListItem> items) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mItems = items;
        mOriginalItems = new ArrayList<ListItem>(items);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.view_item, parent, false);
        return new ListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {
        ListItem item = mItems.get(position);
        holder.txt_label.setText(item.getLabel());
    }

    public List<ListItem> filter(String query)
    {
        query = query.toLowerCase();
        final ArrayList<ListItem> filteredModelList = new ArrayList<>();

        if (query.length() == 0)
        {
            filteredModelList.addAll(mOriginalItems);
        }
        else
        {
            for (ListItem model : mOriginalItems)
            {
                if (model.getLabel().toLowerCase().contains(query.toLowerCase()))
                {
                    filteredModelList.add(model);
                }
            }
        }

        animateTo(filteredModelList);

        return filteredModelList;
    }

    public void animateTo(ArrayList<ListItem> models)
    {
        applyAndAnimateRemovals(models);
        applyAndAnimateAdditions(models);
        applyAndAnimateMovedItems(models);
    }

    private void applyAndAnimateRemovals(ArrayList<ListItem> newModels)
    {
        for (int i = mItems.size() - 1; i >= 0; i--)
        {
            final ListItem model = mItems.get(i);
            if (!newModels.contains(model))
            {
                removeItem(i);
            }
        }
    }

    private void applyAndAnimateAdditions(ArrayList<ListItem> newModels)
    {
        for (int i = 0, count = newModels.size(); i < count; i++)
        {
            final ListItem model = newModels.get(i);
            if (!mItems.contains(model))
            {
                addItem(i, model);
            }
        }
    }

    private void applyAndAnimateMovedItems(ArrayList<ListItem> newModels)
    {
        for (int toPosition = newModels.size() - 1; toPosition >= 0; toPosition--)
        {
            final ListItem model = newModels.get(toPosition);
            final int fromPosition = mItems.indexOf(model);
            if (fromPosition >= 0 && fromPosition != toPosition)
            {
                moveItem(fromPosition, toPosition);
            }
        }
    }

    public ListItem removeItem(int position)
    {
        final ListItem model = mItems.remove(position);
        if (position == 0)
        {
            notifyDataSetChanged();
        }
        else
        {
            notifyItemRemoved(position);
        }
        return model;
    }

    public void addItem(int position, ListItem model)
    {
        mItems.add(position, model);
        notifyItemInserted(position);
    }

    public void moveItem(int fromPosition, int toPosition)
    {
        final ListItem model = mItems.remove(fromPosition);
        mItems.add(toPosition, model);
        notifyItemMoved(fromPosition, toPosition);
    }

}
