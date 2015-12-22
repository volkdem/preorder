package com.preorder.preorder;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;


import com.preorder.preorder.model.ProductBin;
import com.preorder.preorder.stubs.StubFactory;

public class GoodsSelectingActivity extends FragmentActivity {
    private static final long restraintId = 1;
    private ProductBinWrapper productBinWrapper = new ProductBinWrapper( new ProductBin( restraintId ) );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_selecting);

        RestraintMenuPageAdapter restraintMenuPageAdapter = new RestraintMenuPageAdapter(getSupportFragmentManager(), StubFactory.getProductCatalogs( restraintId ), productBinWrapper );

        ViewPager pager = (ViewPager) findViewById(R.id.menuPager);
        pager.setAdapter(restraintMenuPageAdapter);

        ProductBinFragment binFragment = (ProductBinFragment) getFragmentManager().findFragmentById( R.id.product_bin );
        binFragment.setProductBin( productBinWrapper );

    }

}
