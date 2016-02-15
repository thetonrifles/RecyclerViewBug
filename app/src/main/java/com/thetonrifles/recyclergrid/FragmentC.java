package com.thetonrifles.recyclergrid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.thetonrifles.recyclergrid.enums.FragmentEnums;
import com.thetonrifles.recyclergrid.fragments.BaseFragment;


public class FragmentC extends BaseFragment
{
	private RelativeLayout view;

	public FragmentC()
	{
		setType(FragmentEnums.FRAGMENT_C);
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		if(view == null)
		{
			view = new CLayout(container.getContext());
			view.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
		}

		return view;
	}

	@Override
	public void onStart()
	{
		super.onStart();

		if(view != null)
		{
			((CLayout)view).onStart();
		}
	}

	@Override
	public void onStop()
	{
		if(view != null)
		{
			((CLayout)view).onStop();
		}

		super.onStop();
	}
}
