package com.preorder.preorder.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;


import org.prototype.model.Order;

import com.preorder.preorder.OrderSinglton;
import com.preorder.preorder.R;
import com.preorder.preorder.stubs.StubFactory;

public class GoodsSelectingActivity extends FragmentActivity {
    private static final long DEFAULT_RESTRAINT_ID = 0L;
    private OrderWrapper orderWrapper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_goods_selecting );

        Long restraintId = getIntent().getLongExtra( RestraintListActivity.RESTRAINT_ID_KEY, DEFAULT_RESTRAINT_ID );
        if (restraintId == DEFAULT_RESTRAINT_ID) {
            throw new RuntimeException( "There is not input restraint id parameter '" + RestraintListActivity.RESTRAINT_ID_KEY + "'." );
        }

        orderWrapper = new OrderWrapper( OrderSinglton.newOrder( restraintId ) );

        RestraintMenuPageAdapter restraintMenuPageAdapter = new RestraintMenuPageAdapter(getSupportFragmentManager(), StubFactory.getProductCatalogs( restraintId ), orderWrapper );

        ViewPager pager = (ViewPager) findViewById(R.id.menuPager);
        pager.setAdapter(restraintMenuPageAdapter);

        ProductBinFragment binFragment = (ProductBinFragment) getFragmentManager().findFragmentById( R.id.product_bin );
        binFragment.setProductBin( orderWrapper );
    }

    @Override
    protected void onStart() {
        super.onStart();

        ViewPager pager = (ViewPager) findViewById(R.id.menuPager);
        RestraintMenuPageAdapter restraintMenuPageAdapter = ( RestraintMenuPageAdapter ) pager.getAdapter();
        restraintMenuPageAdapter.notifyDataSetChanged();

    }
}
