package com.travelfoots.ntitreetravelfoots;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.TextView;

public class CustomDialog extends Dialog implements View.OnClickListener {
    private static final int LAYOUT = R.layout.travelrecord_dialog;
    private Context context;

    private TextInputEditText nameEt;

    private TextInputEditText emailEt;
    private TextView cancelTv;

    private TextView searchTv;
    private String name;


    public CustomDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    public CustomDialog(Context context,String name) {
        super(context);
        this.context = context;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onClick(View view) {

    }
}
