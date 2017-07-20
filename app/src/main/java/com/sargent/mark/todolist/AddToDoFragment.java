package com.sargent.mark.todolist;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Calendar;

/**
 * Created by mark on 7/4/17.
 */

public class AddToDoFragment extends DialogFragment{

    private EditText enterItem;
    private DatePicker datePicker;
    private Button addButton;
    private Spinner foodSpinner;
    private final String TAG = "addtodofragment";

    public AddToDoFragment() {
    }

    //To have a way for the activity to get the data from the dialog
    public interface OnDialogCloseListener {
        void closeDialog(int year, int month, int day, String description, String category);
    }

    //added only spinner over here for select category
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_to_do_adder, container, false);
        enterItem = (EditText) view.findViewById(R.id.enterItem);
        datePicker = (DatePicker) view.findViewById(R.id.datePicker);
        addButton = (Button) view.findViewById(R.id.addButton);
        foodSpinner =(Spinner) view.findViewById(R.id.foodSpinner);

        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        datePicker.updateDate(year, month, day);


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnDialogCloseListener activity = (OnDialogCloseListener) getActivity();
                activity.closeDialog(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth(), enterItem.getText().toString(), foodSpinner.getSelectedItem().toString());
                AddToDoFragment.this.dismiss();
            }
        });

        return view;
    }
}



