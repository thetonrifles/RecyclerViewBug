package com.thetonrifles.recyclergrid.fragments;

import android.app.Fragment;

public class BaseFragment extends Fragment
{
	private String type;

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public BaseFragment()
	{
	}
}
