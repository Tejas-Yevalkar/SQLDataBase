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

/**
 * Created by tejas yevalkar on 16/12/2017.
 */

public class UpdateFruit extends AppCompatActivity {
    DataBaseHelper myDataBaseHelper;
    private EditText et_update_fruit_name,et_new_fruit_price;
    private Button btn_update_fruit_price;
    private String update_fruit_name,new_fruit_price;
    private FruitPOJO fruitPOJO;
    private Bundle bundle;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.update_fruit );
        Init();
    }

    private void Init() {

        bundle=getIntent().getExtras();
        fruitPOJO=bundle.getParcelable( "fruit" );


        myDataBaseHelper = new DataBaseHelper( this );
        et_update_fruit_name = (EditText) findViewById( R.id.et_update_fruit_name );
        et_new_fruit_price = (EditText) findViewById( R.id.et_old_fruit_price );
        btn_update_fruit_price = (Button) findViewById(R.id.btn_update_fruit_price );
        btn_update_fruit_price.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initialize();
                if(!validate()){

                }else {
                    onSuccess();
                }
            }
        } );
    }
    private  void onSuccess(){
        AlertDialog.Builder dialog = new AlertDialog.Builder( UpdateFruit.this );
        dialog.setTitle( "UpDate Fruit" );
        dialog.setMessage( getResources().getString( R.string.update_fruit_dilog_msg ) );
        dialog.setPositiveButton( "ADD", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                boolean isUpdate = myDataBaseHelper.updateData( new FruitPOJO(fruitPOJO.getId(),et_update_fruit_name.getText().toString(),0,0,
                             Integer.parseInt(et_new_fruit_price.getText().toString())));
                if(isUpdate == true)
                    Toast.makeText( UpdateFruit.this, "Update Fruit in db", Toast.LENGTH_SHORT ).show();
                else
                    Toast.makeText( UpdateFruit.this, "Fruit is Not Updated", Toast.LENGTH_SHORT ).show();

                Intent intent = new Intent( UpdateFruit.this,MainActivity.class );
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
    private boolean validate(){
        boolean valid = true;
        if(update_fruit_name.isEmpty()){
            et_update_fruit_name.setError( "Enter valid name" );
            valid = false;
        }
        if(new_fruit_price.isEmpty()){
            et_new_fruit_price.setError( "Enter new fruit price" );
            valid = false;
        }
        return valid;
    }

    private void initialize() {
        update_fruit_name = et_update_fruit_name.getText().toString().trim();
        new_fruit_price = et_new_fruit_price.getText().toString().trim();
    }
}
