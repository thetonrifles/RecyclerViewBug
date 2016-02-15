package com.thetonrifles.recyclergrid;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.thetonrifles.recyclergrid.enums.FragmentEnums;
import com.thetonrifles.recyclergrid.fragments.BaseFragment;

public class FragmentA extends BaseFragment
{
    private Callback mCallback;

    public FragmentA()
    {
        setType(FragmentEnums.FRAGMENT_A);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_a, container, false);
        View btn = layout.findViewById(R.id.btn_open_list);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCallback != null) {
                    mCallback.onButtonClick();
                }
            }
        });
        return layout;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Callback) {
            mCallback = (Callback) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallback = null;
    }

    public interface Callback {

        void onButtonClick();

    }

}
