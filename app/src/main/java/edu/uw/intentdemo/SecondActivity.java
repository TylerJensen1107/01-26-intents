package edu.uw.intentdemo;

import android.support.v4.widget.CursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.SimpleCursorAdapter;

public class SecondActivity extends AppCompatActivity {

    private static final String TAG = "**DEMO 2**";

    //for listview
    private SimpleCursorAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Bundle bundle = getIntent().getExtras();
        String message = bundle.getString("MASSAGE");

        //action bar "back"
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //TODO: fill in the link between columns and ids!
        String[] cols = new String[]{};
        int[] ids = new int[]{};

        adapter = new SimpleCursorAdapter(
                this,
                R.layout.list_item,
                null,
                cols, ids,
                0 //no flag
        );
        //set the adapter
        AdapterView listView = (AdapterView)findViewById(R.id.listSecond);
        listView.setAdapter(adapter);
    }
}
