package com.example.assigment3.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assigment3.ModelClass.ModelClass;
import com.example.assigment3.R;

import java.util.List;

public class Student extends AppCompatActivity implements View.OnClickListener {
    Button btnSubmit;
    EditText etName;
    EditText etClass;
    EditText etRollNo;
    int flag=0;
    public List<ModelClass> list;
    private TextView v1;
    private Button btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        initUi();
        setToolBar();
        setStudentView();
    }


    private void initUi()
    {
        btnSubmit = findViewById(R.id.btn_submit);
        etName = findViewById(R.id.et_student_name);
        etClass = findViewById(R.id.et_class);
        etRollNo = findViewById(R.id.et_roll_no);
        btnSubmit.setOnClickListener(this);

    }

    private void setToolBar()
    {
        /* Toolbar component */

        v1 = findViewById(R.id.tv_tool_bar_title);
        TextView tvSort = findViewById(R.id.tv_img_sort);
        TextView tvGrid = findViewById(R.id.tv_img_grid);
        v1.setText("Add Student");
        tvSort.setVisibility(View.INVISIBLE);
        tvGrid.setVisibility(View.INVISIBLE);

    }


    private void saveData()
    {
        String name = etName.getText().toString().trim();
        int mclass = Integer.parseInt(etClass.getText().toString().trim());
        int roll = Integer.parseInt(etRollNo.getText().toString().trim());
        if(mclass>0 && mclass<13) {
            MainActivity.modelClasslist.add(new ModelClass(name, mclass, roll));
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        else{
            Toast.makeText(getApplicationContext(),"enter class value between 1 to 12",Toast.LENGTH_SHORT).show();
        }
    }

    public void setStudentView()
    {
        if(getIntent().getExtras()!=null)
        {
            if(getIntent().getBooleanExtra("is View",false))
            {
                Intent intent = getIntent();
                v1=findViewById(R.id.tv_tool_bar_title);
                v1.setText("View Student");
                ModelClass std=intent.getParcelableExtra("Data");//use for class object
                etName.setText(std.getMname());
                etName.setFocusable(false);
                etName.setClickable(false);
                etName.setBackgroundResource(R.color.colorGrayDisable);
                etRollNo.setText(Integer.toString(std.getMroll()));
                etRollNo.setFocusable(false);
                etRollNo.setClickable(false);
                etRollNo.setBackgroundResource(R.color.colorGrayDisable);
                etClass.setText(Integer.toString(std.getMclass()));
                etClass.setFocusable(false);
                etClass.setClickable(false);
                etClass.setBackgroundResource(R.color.colorGrayDisable);
                btnSubmit.setVisibility(View.INVISIBLE);



            }
            else  if(getIntent().getBooleanExtra("isUpdate",false)){
                Toast.makeText(this, "helll", Toast.LENGTH_SHORT).show();
                v1=findViewById(R.id.tv_tool_bar_title);
                v1.setText("update");
                Intent intent=getIntent();
                ModelClass std=intent.getParcelableExtra("Data");
                etName.setText(std.getMname());
                etRollNo.setText(Integer.toString(std.getMroll()));
                etRollNo.setFocusable(false);
                etRollNo.setClickable(false);
                etRollNo.setBackgroundResource(R.color.colorGrayDisable);
                etClass.setText(Integer.toString(std.getMclass()));
                btnSubmit.setText("update");
                btnSubmit.setBackgroundResource(R.drawable.stoke_button1);
                int x=getResources().getColor(R.color.colorGray);
                btnSubmit.setTextColor(x);
                float defaultValue = getResources().getDimension(R.dimen.btn_update_size);
                btnSubmit.setTextSize(defaultValue);
                btnUpdate=findViewById(R.id.btn_submit);
                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(validation()){
                            Intent intent=getIntent();
                            intent.putExtra("mRoll", etRollNo.getText().toString());
                            intent.putExtra("name", etName.getText().toString());
                            intent.putExtra("class", etClass.getText().toString());
                            setResult(RESULT_OK,intent);
                            finish();

                        }
                    }
                });
            }
        }
    }

    private boolean validation() {
        String namePattern = "[A-Za-z ]+";
        String intPattern = "[0-9]+";
        if(etName.getText().toString().trim().length()==0
                || !etName.getText().toString().trim().matches(namePattern)) {
            Toast.makeText(this,"Enter Correct NAme",Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(etClass.getText().toString().trim().length()==0
                || !etClass.getText().toString().trim().matches(intPattern)|| !(etClass.getText().toString().trim().length() <3)) {
            Toast.makeText(this,"Enter correct Class between 1 to 12",Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(etRollNo.getText().toString().trim().length()==0
                || !etRollNo.getText().toString().trim().matches(intPattern)) {
            Toast.makeText(this,"Enter correct Roll number",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.btn_submit:
                if(validation()){
                    saveData();}
                break;

        }
    }
}
