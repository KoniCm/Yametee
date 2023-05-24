package com.praticing.yametee.Dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.praticing.yametee.R;

public class DialogHelper extends AppCompatDialogFragment {

    Button account_help,borrow_help,return_help;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_helper_center, null);

        builder.setView(view);
        builder.setTitle("Help Center");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                
            }
        });

        account_help = view.findViewById(R.id.account_help);
        borrow_help = view.findViewById(R.id.borrow_help);
        return_help = view.findViewById(R.id.return_help);


        account_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "How to create account executed", Toast.LENGTH_SHORT).show();
            }
        });
        borrow_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "How to borrow the book executed", Toast.LENGTH_SHORT).show();
            }
        });
        return_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "How to return the book executed", Toast.LENGTH_SHORT).show();
            }
        });
        return builder.create();
    }
}