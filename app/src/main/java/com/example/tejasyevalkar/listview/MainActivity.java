package com.example.tejasyevalkar.listview;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tejasyevalkar.listview.POJO.FruitPOJO;
import com.example.tejasyevalkar.listview.SQLiteDataBase.DataBaseHelper;

import java.util.ArrayList;
import java.util.List;

import static com.example.tejasyevalkar.listview.Constants.NAME;

public class MainActivity extends AppCompatActivity {

    DataBaseHelper myDataBaseHelper;

    Button btn_add, btn_cancle;
    RecyclerView recyclerView;

    SwipeRefreshLayout swipeRefreshLayout;

    CustomAdapter customAdapter;

    List<FruitPOJO> fruitPOJOlist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        Init();
    }

    private void Init() {
        myDataBaseHelper = new DataBaseHelper( this );

        fruitPOJOlist = myDataBaseHelper.getList();

        btn_add = (Button) findViewById( R.id.btn_add_list );
        btn_add.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MainActivity.this, AddFruit.class );
                startActivityForResult( intent, 2 );
            }
        } );
        btn_cancle = (Button) findViewById( R.id.btn_cancel );
        btn_cancle.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit( 0 );
            }
        } );

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById( R.id.swipe_bar );
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener( new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed( new Runnable() {
                    @Override
                    public void run() {
                        Init();
                        swipeRefreshLayout.setRefreshing( false );

                    }
                }, 1000 );
            }
        } );

        recyclerView = (RecyclerView) findViewById( R.id.recycler_view );
        //recyclerView.setHasFixedSize( true );

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager( MainActivity.this );
        recyclerView.setLayoutManager( linearLayoutManager );
        customAdapter = new CustomAdapter( MainActivity.this, fruitPOJOlist );
        recyclerView.setAdapter( customAdapter );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult( requestCode, resultCode, data );

        Log.d( "my_tag", "onActivityResult: " + data );
        if (requestCode == 2 && resultCode == RESULT_OK) {
            FruitPOJO fruitPOJO = data.getParcelableExtra( NAME );
            fruitPOJOlist.add( fruitPOJO );
            customAdapter.notifyDataSetChanged();
        }
    }
}