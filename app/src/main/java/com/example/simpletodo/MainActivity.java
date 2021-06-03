package com.example.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etElement;
    Button btnAdd;
    Button btnClear;
    Button btnDel;
    Spinner spinner;
    ListView lvTask;
    ArrayList<String> alTask;
    ArrayAdapter<String> aaTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etElement = findViewById(R.id.editTextIndex);
        btnAdd = findViewById(R.id.buttonAddItem);
        btnDel = findViewById(R.id.buttonDeleteItem);
        btnClear = findViewById(R.id.buttonClearItem);
        spinner = findViewById(R.id.spinner);
        lvTask = findViewById(R.id.listViewTask);

        alTask = new ArrayList<>();

        aaTask = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, alTask);

        lvTask.setAdapter(aaTask);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task = etElement.getText().toString();
                alTask.add(task);
                aaTask.notifyDataSetChanged();
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int delNum = Integer.parseInt(etElement.getText().toString());
                if (delNum > -1 && delNum < alTask.size()) {
                    alTask.remove(delNum);
                    aaTask.notifyDataSetChanged();
                } else {
                    Toast.makeText(MainActivity.this, "Invalid Index", Toast.LENGTH_SHORT).show();
                    aaTask.notifyDataSetChanged();
                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int taskNo = alTask.size();
                if (taskNo != 0) {
                    int size = alTask.size() - 1;
                    for (int i = size; -1 < i; i--) {
                        alTask.remove(i);
                    }
                    aaTask.notifyDataSetChanged();
                } else {
                    Toast.makeText(MainActivity.this, "Empty", Toast.LENGTH_SHORT).show();
                }
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    btnAdd.setEnabled(true);
                    btnDel.setEnabled(false);
                    etElement.setHint("Enter a new task");
                } else if (position == 1) {
                    btnDel.setEnabled(true);
                    btnAdd.setEnabled(false);
                    etElement.setHint("Enter the index of the task to be removed");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}