package com.travelfoots.ntitreetravelfoots;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.travelfoots.ntitreetravelfoots.util.MyDialogListener;

import java.util.Date;

public class CustomDialog extends Dialog implements View.OnClickListener {
    private MyDialogListener dialogListener;
    private static final int LAYOUT = R.layout.travelrecord_dialog;
    private Context context;
private Date startDate;
private Date endDate;
    private TextInputLayout ti;
    private TextInputEditText nameEt;
    private TextView tv_startDate;
    private TextView tv_endDate;
    private String name;
    private Button cencel_dialogbtn;
    private Button submin_dialogbtn;


    public CustomDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    public CustomDialog(Context context, Date startDate,Date endDate) {
        super(context);
        this.context = context;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void setDialogListener(MyDialogListener dialogListener){
        this.dialogListener = dialogListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);

        ti = findViewById(R.id.til_1);
        nameEt = findViewById(R.id.tiet_1);

        tv_startDate = findViewById(R.id.tv_startDate);
        tv_endDate = findViewById(R.id.tv_endDate);
        tv_startDate.setText(String.valueOf(startDate));
        tv_startDate.setText(String.valueOf(endDate));
        ti.setCounterEnabled(true);
        ti.setCounterMaxLength(16);

        submin_dialogbtn = findViewById(R.id.submit_dialogbtn);
        cencel_dialogbtn = findViewById(R.id.cancel_dialogbtn);
        submin_dialogbtn.setOnClickListener(this);
        cencel_dialogbtn.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.submit_dialogbtn:
                String title = nameEt.getText().toString();
                dialogListener.onPositiveClicked(title);
                dismiss();
                break;
            case R.id.cancel_dialogbtn:
                cancel();
                break;

        }
    }
}
