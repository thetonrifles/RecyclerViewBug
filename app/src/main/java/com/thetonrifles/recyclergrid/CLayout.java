package com.thetonrifles.recyclergrid;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.widget.RelativeLayout;
import com.thetonrifles.recyclergrid.adapter.Adapter;
import com.thetonrifles.recyclergrid.adapter.ListItem;

import java.util.ArrayList;

public class CLayout extends RelativeLayout implements SearchView.OnQueryTextListener
{
	private static final int SEARCH_VIEW_ID = 1;

	private ArrayList<ListItem> items;
	private SearchView searchView;
	private Adapter adapter;
	private RecyclerView recyclerView;

	public CLayout(Context context)
	{
		super(context);

		createRecyclerView();
	}

	private void createRecyclerView()
	{
		searchView = new SearchView(getContext());
		searchView.setId(SEARCH_VIEW_ID);
		searchView.setQueryHint("Search...");
		searchView.setIconifiedByDefault(false);
		searchView.onActionViewExpanded();
		searchView.setOnQueryTextListener(this);

		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		params.addRule(ALIGN_PARENT_TOP);
		addView(searchView, params);

		items = new ArrayList<>();
		items.add(new ListItem("Francesco"));
		items.add(new ListItem("Daniele"));
		items.add(new ListItem("Kevin"));
		items.add(new ListItem("Radja"));
		items.add(new ListItem("Miralem"));
		items.add(new ListItem("Kostas"));
		items.add(new ListItem("Alessandro"));
		items.add(new ListItem("Diego"));
		items.add(new ListItem("Stephan"));
		items.add(new ListItem("Mohamed"));
		items.add(new ListItem("William"));
		items.add(new ListItem("Douglas"));
		items.add(new ListItem("Seydou"));

		adapter = new Adapter(getContext(), items);

		recyclerView = new RecyclerView(getContext());
		recyclerView.setHasFixedSize(true);
		recyclerView.setAdapter(adapter);
		recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

		params = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		params.addRule(BELOW, SEARCH_VIEW_ID);
		addView(recyclerView, params);
	}

	@Override
	public boolean onQueryTextSubmit(String query)
	{
		return false;
	}

	@Override
	public boolean onQueryTextChange(String newText)
	{
		if(adapter != null)
		{
			adapter.filter(newText);

			//if it is commented out, bug will not be!
			recyclerView.scrollToPosition(0);
		}

		return true;
	}

	public void onStart()
	{
		if(searchView != null)
		{
			searchView.setIconified(false);
		}
	}

	public void onStop()
	{
		if(searchView != null)
		{
			searchView.setQuery("", false);
			//searchView.clearFocus();
		}
	}
}
