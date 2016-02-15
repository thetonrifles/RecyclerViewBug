package com.thetonrifles.recyclergrid.factories;

import com.thetonrifles.recyclergrid.FragmentA;
import com.thetonrifles.recyclergrid.FragmentC;
import com.thetonrifles.recyclergrid.enums.FragmentEnums;
import com.thetonrifles.recyclergrid.fragments.BaseFragment;

public class FragmentFactory
{
	public static BaseFragment getFragment(String type)
	{
		switch (type)
		{
			case FragmentEnums.FRAGMENT_A:
				return new FragmentA();

			case FragmentEnums.FRAGMENT_C:
				return new FragmentC();

			default:
				return null;
		}
	}
	public FragmentFactory()
	{
	}
}
