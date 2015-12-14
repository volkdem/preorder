package com.preorder.preorder;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.preorder.preorder.model.ProductBin;
import com.preorder.preorder.model.ProductsCatalog;
import com.preorder.preorder.model.Restraint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Evgeny on 01.12.2015.
 */
public class RestraintMenuPageAdapter extends FragmentStatePagerAdapter {
    private List<ProductsCatalog> menu;
    private ProductBin productBin;

    public RestraintMenuPageAdapter(FragmentManager fm, List<ProductsCatalog> menu, ProductBin prodcutBin) {
        super(fm);
        this.menu = new ArrayList<>( menu );
        this.productBin = prodcutBin;
    }

    @Override
    public Fragment getItem(int position) {
        RestraintMenuFragment menuFragment = new RestraintMenuFragment();
        menuFragment.setCatalog( menu.get( position ) );
        menuFragment.setProductBin( productBin );
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
