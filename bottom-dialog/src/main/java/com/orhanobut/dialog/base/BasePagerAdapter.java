package com.orhanobut.dialog.base;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * Created by Nevermore on 2017/5/3.
 *
 * BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT 懒加载。不会一启动就加载同时多个 Fragment个页面
 */

public class BasePagerAdapter extends FragmentPagerAdapter
{
    private List<Fragment> mFragments;
    private List<String> titls;


    public BasePagerAdapter(FragmentManager fm, List<Fragment> mFragment)
    {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mFragments=mFragment;
    }



    public BasePagerAdapter(FragmentManager fm, List<Fragment> mFragment, List<String> mTitleList )
    {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mFragments=mFragment;
        titls=mTitleList;
    }
    @Override
    public int getCount()
    {
        return mFragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
//        return super.getPageTitle(position);
        if (titls!=null&&titls.size()>0){
            return titls.get(position);
        }else {
            return null;
        }
    }

    @Override
    public Fragment getItem(int position)
    {
        return mFragments.get(position);
    }


}
