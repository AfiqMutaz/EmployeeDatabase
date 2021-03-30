package com.example.employeedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText inputName, inputSalary;
    Spinner inputSpinnerDept;
    Button buttonAdd, buttonView;
    DatabaseManager mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDatabase = new DatabaseManager(this);
        inputName = findViewById(R.id.etName);
        inputSalary = findViewById(R.id.etSalary);
        inputSpinnerDept = findViewById(R.id.spinnerDepartment);

        buttonAdd = findViewById(R.id.btAdd);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("BUTTON_ADD", "CLICKED");
                addEmployee();
            }
        });

        buttonView = findViewById(R.id.btView);
        buttonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("BUTTON_VIEW", "CLICKED");
                viewData();
            }
        });
    }

    private void addEmployee() {
        String name = inputName.getText().toString();
        String dept = inputSpinnerDept.getSelectedItem().toString();
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String joiningDate = sdf.format(cal.getTime());
        String salary = inputSalary.getText().toString();
        //validation
        if(name.isEmpty()) {
            inputName.setError("Name can't be empty");
            inputName.requestFocus();
            return;
        }
        if(salary.isEmpty()) {
            inputSalary.setError("Salary can't be empty");
            inputSalary.requestFocus();
            return;
        }
        //adding the employees with the databasemanager instance
        if(mDatabase.addEmployee(name, dept, joiningDate, Double.parseDouble(salary)))
            Toast.makeText(this, "Employee Added", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Could not add employee", Toast.LENGTH_SHORT).show();
    }

    public void viewData() {
        Intent intent = new Intent(this, EmployeeActivity.class);
        startActivity(intent);
    }
}