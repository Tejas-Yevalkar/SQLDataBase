package com.example.tejasyevalkar.listview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.tejasyevalkar.listview.POJO.FruitPOJO;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * Created by tejas yevalkar on 18/12/2017.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    List<FruitPOJO> fruitPOJOArrayList;
    Context context;

    public static final String rs = "\u20B9";
    public CustomAdapter(Context context,List<FruitPOJO> fruitPOJOArrayList) {
        this.context = context;
        this.fruitPOJOArrayList = fruitPOJOArrayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from( parent.getContext() ).inflate( R.layout.card_view, parent, false );
        MyViewHolder vh = new MyViewHolder( v );
        return vh;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final FruitPOJO fruitPOJO=fruitPOJOArrayList.get( position );

       holder.tv_fname.setText( fruitPOJO.getmFName() );
       holder.tv_fPkg.setText( "PKG Date :"+fruitPOJO.getmFPkgDate());
       holder.tv_fExp.setText( "EXP Date :"+fruitPOJO.getmFExpDate() );
       holder.tv_fPrice.setText(""+rs+" "+fruitPOJO.getmFPrice()+"/kg");


       holder.itemView.setOnClickListener( new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               PopupMenu popupMenu = new PopupMenu(context ,holder.itemView );
               popupMenu.inflate( R.menu.menu_list_view );
               popupMenu.setOnMenuItemClickListener( new PopupMenu.OnMenuItemClickListener() {
                   @Override
                   public boolean onMenuItemClick(MenuItem item) {
                       switch (item.getItemId()){
                           case R.id.update:
                               Bundle bundle =new Bundle(  );
                               bundle.putParcelable( "fruit",fruitPOJO );
                               context.startActivity( new Intent( context,UpdateFruit.class ).putExtras( bundle ) );
                               break;
                           case R.id.delete:
                               Bundle bundle1 = new Bundle(  );
                               bundle1.putParcelable( "delete_fruit",fruitPOJO );
                               context.startActivity( new Intent( context,DeleteFruit.class ).putExtras( bundle1 ) );
                               break;
                       }
                       return false;
                   }
               } );
               popupMenu.show();
           }
       } );
    }

    @Override
    public int getItemCount() {
        return fruitPOJOArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_fname, tv_fPkg, tv_fExp, tv_fPrice;

        public MyViewHolder(View itemView) {
            super( itemView );

            tv_fname = (TextView) itemView.findViewById( R.id.tv_fname );
            tv_fPkg = (TextView) itemView.findViewById( R.id.tv_fPkg );
            tv_fExp = (TextView) itemView.findViewById( R.id.tv_fExp );
            tv_fPrice = (TextView) itemView.findViewById( R.id.tv_fPrice );
        }


    }

}
