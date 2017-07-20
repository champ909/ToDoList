package com.sargent.mark.todolist;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.sargent.mark.todolist.data.Contract;

/**
 * Created by mark on 7/4/17.
 */

public class ToDoListAdapter extends RecyclerView.Adapter<ToDoListAdapter.ItemHolder> {

    private Cursor cursor;
    private ItemClickListener listener;
    private String TAG = "todolistadapter";

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.item, parent, false);

        ItemHolder holder = new ItemHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        holder.bind(holder, position);
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }



    public interface ItemClickListener {
        void onItemClick(int pos, String description, String category, String duedate, long id);

        void onCheckClick(int pos, boolean status, long id);
    }

    public ToDoListAdapter(Cursor cursor, ItemClickListener listener) {
        this.cursor = cursor;
        this.listener = listener;
    }

    public void swapCursor(Cursor newCursor) {
        if (cursor != null) cursor.close();
        cursor = newCursor;
        if (newCursor != null) {
            // Force the RecyclerView to refresh
            this.notifyDataSetChanged();
        }
    }

    //ViewHolder Extending default recylcerview(Setting onclicklistener here)

    class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView information;
        TextView dueDate;
        TextView foodCategory;
        TextView s;
        CheckBox checkStatus;
        String category;
        String status;
        String duedate;
        String description;

        long id;

        ItemHolder(View view) {
            super(view);
            foodCategory = (TextView) view.findViewById(R.id.foodCategory);
            checkStatus = (CheckBox) view.findViewById(R.id.checkStatus);
            //s = (TextView) view.findViewById(R.id.rishabh);
            information = (TextView) view.findViewById(R.id.information);
            dueDate = (TextView) view.findViewById(R.id.dueDate);
            view.setOnClickListener(this);
        }


        //We're binding the List Adapter to the view
        public void bind(ItemHolder holder, int pos) {
            cursor.moveToPosition(pos);
            id = cursor.getLong(cursor.getColumnIndex(Contract.TABLE_TODO._ID));
            Log.d(TAG, "deleting id: " + id);

            category = cursor.getString(cursor.getColumnIndex(Contract.TABLE_TODO.COLUMN_NAME_CATEGORY));
            status = cursor.getString(cursor.getColumnIndex(Contract.TABLE_TODO.COLUMN_NAME_STATUS));

            duedate = cursor.getString(cursor.getColumnIndex(Contract.TABLE_TODO.COLUMN_NAME_DUE_DATE));
            description = cursor.getString(cursor.getColumnIndex(Contract.TABLE_TODO.COLUMN_NAME_DESCRIPTION));
            information.setText(description);
            dueDate.setText(duedate);
            foodCategory.setText(category);

            if (status.equals("1")) {

                checkStatus.setChecked(true);
            }


            //OnClickListener called to check if checkbox has been clicked or not.
            checkStatus.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (((CheckBox) v).isChecked()) {

                        Log.d(TAG, "checked");
                        final int pos = getAdapterPosition();
                        listener.onCheckClick(pos, true, id);
                    } else {
                        Log.d(TAG, "unchecked");
                        final int pos = getAdapterPosition();
                        listener.onCheckClick(pos, false, id);

                    }
                    //case 2

                }
            });
            holder.itemView.setTag(id);
        }


        @Override
        public void onClick(View v) {
            final int pos = getAdapterPosition();
            listener.onItemClick(pos, description, category, duedate, id);
        }
    }

}
