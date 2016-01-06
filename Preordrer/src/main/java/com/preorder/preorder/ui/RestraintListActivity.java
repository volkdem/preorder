package com.preorder.preorder.ui;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.prototype.model.Restraint;

import com.preorder.preorder.R;
import com.preorder.preorder.stubs.StubFactory;

import java.util.List;

public class RestraintListActivity extends ListActivity {
    public static final String RESTRAINT_ID_KEY = RestraintListActivity.class + ".RESTRAINT_ID_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_restraint_list);

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
                restraintIntent.putExtra( RestraintListActivity.RESTRAINT_ID_KEY, restraint.getId() );
                v.getContext().startActivity( restraintIntent );
            }
        });

        return view;
    }
}




