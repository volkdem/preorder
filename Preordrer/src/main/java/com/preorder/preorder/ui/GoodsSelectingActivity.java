package com.preorder.preorder.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;


import org.prototype.model.Order;

import com.preorder.preorder.R;
import com.preorder.preorder.stubs.StubFactory;

public class GoodsSelectingActivity extends FragmentActivity {
    private static final long restraintId = 1;
    private OrderWrapper orderWrapper = new OrderWrapper( new Order( restraintId ) );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_goods_selecting);

        RestraintMenuPageAdapter restraintMenuPageAdapter = new RestraintMenuPageAdapter(getSupportFragmentManager(), StubFactory.getProductCatalogs( restraintId ), orderWrapper );

        ViewPager pager = (ViewPager) findViewById(R.id.menuPager);
        pager.setAdapter(restraintMenuPageAdapter);

        ProductBinFragment binFragment = (ProductBinFragment) getFragmentManager().findFragmentById( R.id.product_bin );
        binFragment.setProductBin( orderWrapper );

    }

}
