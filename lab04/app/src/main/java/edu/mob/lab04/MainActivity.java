package edu.mob.lab04;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    protected CDBMaterial dbMaterial;
    protected RecyclerView recyclerView;
    protected CRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        dbMaterial = new CDBMaterial(this);
        dbMaterial.open();
        findViewById(R.id.buttonAdd).setOnClickListener(v-> {
            dbMaterial.addMaterial(
                    ((EditText) findViewById(R.id.editType)).getText().toString(),
                    ((EditText) findViewById(R.id.editStandard)).getText().toString());
            adapter.swapCursor(dbMaterial.getMaterials());
            //runLayoutAnimation();
        });

        adapter = new CRecyclerViewAdapter(this,dbMaterial.getMaterials());
        recyclerView = findViewById(R.id.viewList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        }
    }
