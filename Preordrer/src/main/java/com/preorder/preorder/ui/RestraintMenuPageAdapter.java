package com.preorder.preorder.ui;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import org.prototype.model.ProductsCatalog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Evgeny on 01.12.2015.
 */
public class RestraintMenuPageAdapter extends FragmentStatePagerAdapter {
    private List<ProductsCatalog> menu;
    private OrderWrapper orderWrapper;

    public RestraintMenuPageAdapter(FragmentManager fm, List<ProductsCatalog> menu, OrderWrapper prodcutBin) {
        super(fm);
        this.menu = new ArrayList<>( menu );
        this.orderWrapper = prodcutBin;
    }

    @Override
    public Fragment getItem(int position) {
        RestraintMenuFragment menuFragment = new RestraintMenuFragment();
        menuFragment.setCatalog( menu.get( position ) );
        menuFragment.setOrderWrapper( orderWrapper );
        return menuFragment;
    }

    @Override
    public int getCount() {
        return menu.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return menu.get( position ).getName();
    }
}
