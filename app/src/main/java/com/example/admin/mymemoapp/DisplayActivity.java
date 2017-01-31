package com.example.admin.mymemoapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class DisplayActivity extends AppCompatActivity {
    private ListView listView;
    DBController dbController;
    FloatingActionButton btn_add;
    ListView myList;
    Menu menu;
    AlertDialog.Builder alertDialogBuilder;
    AlertDialog alertDialog;
    Context context=this;
    CoordinatorLayout coordinatorLayout;
    SimpleCursorAdapter adapter;
    Snackbar snackbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        coordinatorLayout=(CoordinatorLayout)findViewById(R.id.coordinatorLayout);
        dbController=new DBController(this);
        btn_add=(FloatingActionButton)findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle dataBundle = new Bundle();
                dataBundle.putInt("id", 0);
                Intent intent = new Intent(getApplicationContext(),
                        EditActivity.class);
                intent.putExtras(dataBundle);
                startActivity(intent);
                finish();

            }
        });
        Cursor c = dbController.fetchAll();
        String[] fieldNames = new String[] { Constants._NAME, Constants._ID, Constants._CREATED_TIME, Constants._TITLE };
        int[] display = new int[] { R.id.txtnamerow, R.id.txtidrow,
                R.id.txtdate,R.id.txtremark };
        adapter = new SimpleCursorAdapter(this, R.layout.content_main, c, fieldNames,
                display, 0);
        snackbar = Snackbar
                .make(coordinatorLayout, "You are awesome I think!", Snackbar.LENGTH_LONG);
        snackbar.show();
        myList = (ListView) findViewById(R.id.listView1);
        myList.setAdapter(adapter);
        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                LinearLayout linearLayoutParent = (LinearLayout) arg1;
                LinearLayout linearLayoutChild = (LinearLayout) linearLayoutParent
                        .getChildAt(0);
                TextView m = (TextView) linearLayoutChild.getChildAt(1);
                Bundle dataBundle = new Bundle();
                dataBundle.putInt("id",
                        Integer.parseInt(m.getText().toString()));
                Intent intent = new Intent(getApplicationContext(),
                        EditActivity.class);
                intent.putExtras(dataBundle);
                startActivity(intent);
                finish();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        this.menu = menu;
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.add:
                Bundle dataBundle = new Bundle();
                dataBundle.putInt("id", 0);
                Intent intent = new Intent(getApplicationContext(),
                        EditActivity.class);
                intent.putExtras(dataBundle);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

