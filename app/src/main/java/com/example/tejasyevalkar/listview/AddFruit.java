package com.example.tejasyevalkar.listview;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tejasyevalkar.listview.POJO.FruitPOJO;
import com.example.tejasyevalkar.listview.SQLiteDataBase.DataBaseHelper;

import static com.example.tejasyevalkar.listview.Constants.NAME;

/**
 * Created by tejas yevalkar on 16/12/2017.
 */

public class AddFruit extends AppCompatActivity {
    DataBaseHelper myDataBaseHelper;
    private EditText etFName, etFPkg, etFExp, etFPrice;
    private Button btnAdd;
    private Context context;
    private String fname, fpkg, fxp, fprice;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.add_fruit );
        Init();
    }

    private void Init() {
        myDataBaseHelper = new DataBaseHelper( this );
        etFName = (EditText) findViewById( R.id.et_add_fruit_name );
        etFPkg = (EditText) findViewById( R.id.et_add_fruit_pkg );
        etFExp = (EditText) findViewById( R.id.et_add_fruit_exp );
        etFPrice = (EditText) findViewById( R.id.et_add_fruit_price );
        btnAdd = (Button) findViewById( R.id.btn_add_fruit );
        btnAdd.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initialize();
                if (!Validate()) {

                } else {
                    onAddFruit();
                }
            }
        } );
    }

    private void onAddFruit() {
        AlertDialog.Builder dialog = new AlertDialog.Builder( AddFruit.this );
        dialog.setTitle( "ADD Fruit" );
        dialog.setMessage( getResources().getString( R.string.add_fruit_dilog_msg ) );
        dialog.setPositiveButton( "ADD", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                final FruitPOJO fruitPOJO = new FruitPOJO( etFName.getText().toString(),
                        Integer.parseInt( etFPkg.getText().toString() ),
                        Integer.parseInt( etFExp.getText().toString() ),
                        Integer.parseInt( etFPrice.getText().toString() ) );

                if (fruitPOJO != null) {
                    boolean isInserted = myDataBaseHelper.insertData( fruitPOJO );
                    if (!isInserted)
                        Toast.makeText( AddFruit.this, "Fruit Not inserted in db", Toast.LENGTH_SHORT ).show();
                    else
                       Toast.makeText( AddFruit.this, "Fruit is Inserted in db", Toast.LENGTH_SHORT ).show();
                }
                Intent intent = new Intent(AddFruit.this,MainActivity.class);
                /*intent.putExtra( NAME, fruitPOJO );
                setResult( RESULT_OK, intent );*/
                finish();
            }
        } );
        dialog.setNegativeButton( "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        } );
        dialog.show();
    }

    public boolean Validate() {
        boolean valid = true;
        if (fname.isEmpty() || fname.length() > 10) {
            etFName.setError( "Valid Fruit Name" );
            valid = false;
        }
        if (fpkg.isEmpty()) {
            etFPkg.setError( "Field required" );
            valid = false;
        }
        if (fxp.isEmpty()) {
            etFExp.setError( "Field required" );
            valid = false;
        }
        if (fprice.isEmpty()) {
            etFPrice.setError( "Enter Valid Price" );
            valid = false;
        }
        return valid;
    }

    private void initialize() {
        fname = etFName.getText().toString().trim();
        fpkg = etFPkg.getText().toString().trim();
        fxp = etFExp.getText().toString().trim();
        fprice = etFPrice.getText().toString().trim();
    }
}
