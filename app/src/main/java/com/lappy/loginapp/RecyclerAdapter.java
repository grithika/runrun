package com.lappy.loginapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter {

    private ArrayList<RunActivityRetriever> arrayList;
    private AdapterView.OnItemClickListener deleteListener;

    public interface OnItemClickListener {
        void onDeleteClick(int position);
    }
//    public interface SetItemListener(OnItemClickListener listener) {
//        deleteListener = listener;
//    }
    public RecyclerAdapter(ArrayList<RunActivityRetriever> arrayList){
        this.arrayList = arrayList;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_run_history, parent, false);
        RecyclerViewHolder RVH = new RecyclerViewHolder(view, (OnItemClickListener) deleteListener, context, arrayList);

        return RVH;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
//        RunActivityRetriever retrieveRunnerActivity = arrayList.get(position);
//        holder.Duration_column.setText(String.valueOf(retrieveRunnerActivity.getElapsed_time()));
//        holder.Distance_column.setText(String.valueOf(retrieveRunnerActivity.getTotal_distance()));
//        holder.date_heading.setText(String.valueOf(retrieveRunnerActivity.getDate()));
//        holder.Entry_column.setText(String.valueOf(retrieveRunnerActivity.getId()));
//        holder.deleteButton.getContext();

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        ImageView deleteButton;
        TextView Duration_column, Distance_column, date_heading, Entry_column;
        RunDatabaseHelper RunDatabase;


        RecyclerViewHolder(final View view, final OnItemClickListener listener, final Context context, final ArrayList arrayList) {

            super(view);
            Duration_column = view.findViewById(R.id.runHistoryTimeDisplay);
            Distance_column = view.findViewById(R.id.runHistoryDistanceDisplay);
            date_heading = view.findViewById(R.id.dateDetails);
            Entry_column = view.findViewById(R.id.runHistoryEntryDisplay);
            deleteButton = view.findViewById(R.id.deleteButton);
            deleteButton.setTag(deleteListener);
            final RecyclerAdapter adapter = new RecyclerAdapter(arrayList);
            RunDatabase = new RunDatabaseHelper(context);


            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        AlertDialog.Builder dialogbuilder = new AlertDialog.Builder(context);
                        dialogbuilder.setMessage("Delete activity permanently")
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(final DialogInterface dialog, int
                                            which) {
                                        RunDatabase.DeleteData(Entry_column.getText().toString());
                                        adapter.notifyDataSetChanged();
                                        listener.onDeleteClick(getAdapterPosition());

                                    }
                                })
                                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });
                        AlertDialog alertDialog = dialogbuilder.create();
                        alertDialog.show();
                    }
                }
            });
        }
    }
}
