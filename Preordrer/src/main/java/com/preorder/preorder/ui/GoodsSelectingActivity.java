package com.preorder.preorder.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


import org.prototype.model.Order;

import com.preorder.preorder.OrderSinglton;
import com.preorder.preorder.R;
import com.preorder.preorder.stubs.StubFactory;

public class GoodsSelectingActivity extends AppCompatActivity {
    private static final long DEFAULT_RESTRAINT_ID = 0L;
    private OrderWrapper orderWrapper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_goods_selecting );

        Toolbar toolbar = ( Toolbar ) findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );



        Long restraintId = getIntent().getLongExtra( RestraintListActivity.RESTRAINT_ID_KEY, DEFAULT_RESTRAINT_ID );
        if (restraintId == DEFAULT_RESTRAINT_ID) {
            throw new RuntimeException( "There is not input restraint id parameter '" + RestraintListActivity.RESTRAINT_ID_KEY + "'." );
        }

        orderWrapper = new OrderWrapper( OrderSinglton.newOrder( restraintId ) );

        RestraintMenuPageAdapter restraintMenuPageAdapter = new RestraintMenuPageAdapter(getSupportFragmentManager(), StubFactory.getProductCatalogs( restraintId ), orderWrapper );

        ViewPager pager = (ViewPager) findViewById(R.id.menuPager);
        pager.setAdapter(restraintMenuPageAdapter);

        /*ProductBinFragment binFragment = (ProductBinFragment) getFragmentManager().findFragmentById( R.id.product_bin );
        binFragment.setProductBin( orderWrapper );*/
    }

    @Override
    protected void onStart() {
        super.onStart();

        ViewPager pager = (ViewPager) findViewById(R.id.menuPager);
        RestraintMenuPageAdapter restraintMenuPageAdapter = ( RestraintMenuPageAdapter ) pager.getAdapter();
        restraintMenuPageAdapter.notifyDataSetChanged();

    }

    @Override
    public boolean onCreateOptionsMenu( Menu menu ) {
        getMenuInflater().inflate( R.menu.menu_goods, menu );
        return super.onCreateOptionsMenu( menu );
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item ) {
        switch ( item.getItemId() ) {
            case R.id.bin: {
                Intent startOrderConfirmationActivityIntent = new Intent( GoodsSelectingActivity.this.getApplicationContext(), OrderConfirmationActivity.class );
                GoodsSelectingActivity.this.startActivity( startOrderConfirmationActivityIntent );
                break;
            }
        }
        return super.onOptionsItemSelected( item );
    }
}
