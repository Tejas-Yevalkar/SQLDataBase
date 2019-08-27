package com.example.tejasyevalkar.listview;

import android.app.AlertDialog;
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

public class DeleteFruit extends AppCompatActivity {
    DataBaseHelper myDataBaseHelper;
    private EditText delete_fruit_name;
    private Button delete_button;
    private FruitPOJO fruitPOJO;
    private String fruit_name;
    private Bundle bundle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.delete_fruit );
        Init();
    }

    private void Init() {

        bundle = getIntent().getExtras();
        fruitPOJO = bundle.getParcelable( "delete_fruit" );

        myDataBaseHelper = new DataBaseHelper( this );
        delete_fruit_name = (EditText) findViewById( R.id.delete_fruit_name );
        delete_button = (Button) findViewById( R.id.delete_button );
        delete_button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initialize();
                if (!validate()) {
                } else {
                    onSuccess();
                }
            }
        } );
    }

    private void onSuccess() {
        AlertDialog.Builder dialog = new AlertDialog.Builder( DeleteFruit.this );
        dialog.setTitle( "Delete Fruit" );
        dialog.setMessage( getResources().getString( R.string.delete_fruit_dialod_msg ) );
        dialog.setPositiveButton( "Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                boolean isDelete = myDataBaseHelper.deleteData( fruitPOJO.getId() );
                if (!isDelete)
                    Toast.makeText( DeleteFruit.this, "Delete Fruit in db", Toast.LENGTH_SHORT ).show();
                else
                    Toast.makeText( DeleteFruit.this, "Fruit is not delete in db", Toast.LENGTH_SHORT ).show();

                Intent intent = new Intent( DeleteFruit.this, MainActivity.class );
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

    private boolean validate() {
        boolean valid = true;
        if (fruit_name.isEmpty()) {
            delete_fruit_name.setError( "!valid fruit name" );
            valid = false;
        }
        return valid;
    }

    private void initialize() {
        fruit_name = delete_fruit_name.getText().toString().trim();
    }
}
