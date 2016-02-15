package com.thetonrifles.recyclergrid.managers;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import com.thetonrifles.recyclergrid.factories.FragmentFactory;
import com.thetonrifles.recyclergrid.fragments.BaseFragment;

import java.util.ArrayList;

public class SimpleFragmentManager
{
	private static ArrayList<BaseFragment> fragments = new ArrayList<BaseFragment>();
	private static int _containerId;
	private static AppCompatActivity _activity;

	public static BaseFragment getFragment(String type)
	{
		for(BaseFragment fragment : fragments)
		{
			if(fragment.getType() == type) return fragment;
		}

		BaseFragment fragment = FragmentFactory.getFragment(type);

		fragments.add(fragment);

		return fragment;
	}

	public static BaseFragment getCurrentFragment()
	{
		FragmentManager fragmentManager = _activity.getFragmentManager();

		return (BaseFragment) fragmentManager.findFragmentById(_containerId);
	}

	/**Static methods**/
	public static void init(AppCompatActivity activity, int containerId)
	{
		_activity = activity;
		_containerId = containerId;
	}

	public static BaseFragment changeFragment(String type)
	{
		BaseFragment fragment = getFragment(type);
		moveTo(fragment);

		return fragment;
	}

	private static void moveTo(BaseFragment fragment)
	{
		if(fragment != null)
		{
			FragmentManager fragmentManager = _activity.getFragmentManager();
			FragmentTransaction ft = fragmentManager.beginTransaction();
			ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);

			BaseFragment currentFragment = getCurrentFragment();

			if(fragment != currentFragment)
			{
				ft.replace(_containerId, fragment, fragment.getType());

				if (currentFragment != null)
				{
					ft.addToBackStack(null);
				}
				ft.commit();
			}
		}
	}

	public SimpleFragmentManager()
	{
	}
}
