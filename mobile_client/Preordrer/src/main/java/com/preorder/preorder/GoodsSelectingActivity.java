package com.preorder.preorder;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;


import com.preorder.preorder.model.ProductBin;
import com.preorder.preorder.stubs.StubFactory;

public class GoodsSelectingActivity extends FragmentActivity {
    private static final long restraintId = 1;
    private ProductBin productBin = new ProductBin( restraintId );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_selecting);

        RestraintMenuPageAdapter restraintMenuPageAdapter = new RestraintMenuPageAdapter(getSupportFragmentManager(), StubFactory.getProductCatalogs( restraintId ), productBin );

        ViewPager pager = (ViewPager) findViewById(R.id.menuPager);
        pager.setAdapter(restraintMenuPageAdapter);

        ProductBinFragment binFragment = (ProductBinFragment) getFragmentManager().findFragmentById( R.id.product_bin );
        binFragment.setProductBin( productBin );

    }

}
