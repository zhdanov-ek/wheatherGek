package com.example.gek.weahtergek;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by gek on 03.06.17.
 */

public class SearchDialog extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setMessage(R.string.mes_input_city);

        builder.setView(R.layout.edit_text_search);

        builder.setPositiveButton(R.string.search, (dialog, id) -> {

                })
                .setNegativeButton(R.string.cancel, (dialog, id) -> {
                    // User cancelled the dialog
                });

        // Create the AlertDialog object and return it
        return builder.create();
    }
}
