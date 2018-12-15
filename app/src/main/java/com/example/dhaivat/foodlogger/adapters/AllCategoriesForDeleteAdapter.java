package com.example.dhaivat.foodlogger.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dhaivat.foodlogger.R;
import com.example.dhaivat.foodlogger.UpdatecategoryActivity;
import com.example.dhaivat.foodlogger.database.MyDatabase;
import com.example.dhaivat.foodlogger.models.Categories;

import java.util.ArrayList;

public class AllCategoriesForDeleteAdapter extends RecyclerView
        .Adapter<AllCategoriesForDeleteAdapter
        .DataObjectHolder> {
    private static String LOG_TAG = "MyRecyclerViewAdapter";
    private ArrayList<Categories> categoriesArrayList;


    private Context context;
    private static MyClickListener myClickListener;

    public static class DataObjectHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {
        TextView tvName;
        private LinearLayout llDelete;



        public DataObjectHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tvName);

            llDelete= (LinearLayout) itemView.findViewById(R.id.llDelete);
            llDelete.setVisibility(View.VISIBLE);

//  *****for line on text********
//            cut_price.setPaintFlags(cut_price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            Log.i(LOG_TAG, "Adding Listener");
           // itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            //       myClickListener.onItemClick(getAdapterPosition(), v);
        }

    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    public AllCategoriesForDeleteAdapter(Context context, ArrayList<Categories> categoriesArrayList) {
        this.context = context;
        this.categoriesArrayList = categoriesArrayList;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_search_product, parent, false);


        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, final int position) {
//        holder.quote.setText(recommendedDishesArrayList.get(position).getQuote());
//
//
//
        holder.tvName.setText(categoriesArrayList.get(position).getCatName());


//        holder.totalWeight.setText(airGroundServiceArrayList.get(position).get());

        holder.llDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setMessage("Are you sure want to delete " + categoriesArrayList.get(position).getCatName() + " ?");
                        alertDialogBuilder.setPositiveButton("yes",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface arg0, int arg1) {
                                        MyDatabase myDatabase = new MyDatabase(context);
                                        SQLiteDatabase sqLiteDatabase = myDatabase.getWritableDatabase();

                                        myDatabase.deletecategory(categoriesArrayList.get(position));
                                        categoriesArrayList.remove(position);

                                        notifyDataSetChanged();
                                    }
                                });

                alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                dialogInterface.dismiss();
                            }
                        });

                        AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

                            // Create the AlertDialog object and return it



            }
        });


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent it = new Intent(context, UpdatecategoryActivity.class);
                it.putExtra("id", categoriesArrayList.get(position).getId());
                context.startActivity(it);
            }
        });

    }



    @Override
    public int getItemCount() {
        return categoriesArrayList.size();
    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);

    }
}