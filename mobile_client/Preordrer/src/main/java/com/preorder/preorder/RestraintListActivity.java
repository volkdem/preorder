package com.preorder.preorder;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.preorder.preorder.model.Restraint;
import com.preorder.preorder.stubs.StubFactory;

import java.util.ArrayList;
import java.util.List;

public class RestraintListActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restraint_list);

        RestraintViewAdapter restraintAdapter = new RestraintViewAdapter();
        setListAdapter( restraintAdapter );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_restraint_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


}

class RestraintViewAdapter extends BaseAdapter {


    private List<Restraint> restraints = null;

    public RestraintViewAdapter() {
        restraints = StubFactory.getRestraints();
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Object getItem(int position) {
        return restraints.get( position );
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate( R.layout.restraint_item, null );
        }

        final Restraint restraint = restraints.get( position );
        TextView nameTextView = (TextView) view.findViewById( R.id.restraint_name );
        nameTextView.setText(restraint.getName());

        TextView addressTextView = (TextView) view.findViewById( R.id.restraint_address );
        addressTextView.setText(restraint.getAddress());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent restraintIntent = new Intent();
                restraintIntent.setClass( v.getContext(), GoodsSelectingActivity.class );
                v.getContext().startActivity( restraintIntent );
            }
        });

        return view;
    }
}




