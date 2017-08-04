package com.zebpay.demo.manohar.peswani;

import android.content.Context;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Manohar on 04-08-2017.
 */

public class UserPreference {

    public static final String VARIABLE_VALUE = "value";
    public static final String SAVED_VALUE = "current_value";
    public static final String VALUE_TYPE = "value_type";

    public static void showChangeLangDialog(Context context, String value) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        final View dialogView = inflater.inflate(R.layout.custom_dialog, null);
        dialogBuilder.setView(dialogView);
        dialogBuilder.setCancelable(false);
        final TextView marketValue = dialogView.findViewById(R.id.marketValue);
        marketValue.setText("Current market value = " + value + " INR");
        final EditText variableValue = dialogView.findViewById(R.id.varaition);
        final RadioButton percentage = dialogView.findViewById(R.id.percent);
        dialogBuilder.setTitle("Price Reminder");
        dialogBuilder.setMessage("Enter the variable value for which you want to be notify");
        dialogBuilder.setPositiveButton("Done", (dialog, whichButton) -> {

        });

        dialogBuilder.setNegativeButton("Cancel", (dialog, whichButton) -> dialog.dismiss());
        AlertDialog b = dialogBuilder.create();
        b.show();
        b.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (variableValue.getText().length() == 0) {
                    Toast.makeText(context, "Please enter the value.", Toast.LENGTH_SHORT).show();
                    return;
                }
                PreferenceManager.getDefaultSharedPreferences(context).edit()
                        .putString(SAVED_VALUE, value)
                        .putString(VARIABLE_VALUE, variableValue.getText().toString())
                        .putBoolean(VALUE_TYPE, percentage.isChecked()).apply();
                Toast.makeText(context, "Notification Activated.", Toast.LENGTH_SHORT).show();
                b.dismiss();
            }
        });
    }

}
