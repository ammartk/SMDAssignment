package com.example.smdassignment;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Filterable;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;

public class SetsActivity extends AppCompatActivity implements SetsAdapter.SetsItemClickListener {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    ArrayList<Sets> dataset = new ArrayList<Sets>();
    ActivityResultLauncher<Intent> CardsLauncher;
    ActivityResultLauncher<Intent> NameLauncher;
    Sets currentSet;
    EditText search;
    Filterable filterable;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sets);

        search = (EditText) findViewById(R.id.search);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                filterable.getFilter().filter(search.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.SetsList);
        recyclerView.setHasFixedSize(true);

        CardsLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {

                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode() == RESULT_OK){
                            Intent data = result.getData();
                            int id = data.getIntExtra("pos", 0);
                            ArrayList<Cards> temp = (ArrayList<Cards>) data.getSerializableExtra("cards");
                            Sets t = dataset.get(id);
                            t.setDataset(temp);
                            dataset.set(id, t);
                            mAdapter.notifyDataSetChanged();
                            // handle incoming data from child
                            // handle incoming data from child
                        }
                    }
                });

        NameLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {

                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode() == RESULT_OK){
                            Intent data = result.getData();
                            int id = data.getIntExtra("id", 0);
                            String text = data.getStringExtra("content");
                            Sets set = dataset.get(id);
                            set.setTitle(text);
                            dataset.set(id, set);
                            mAdapter.notifyDataSetChanged();
                            // handle incoming data from child
                            // handle incoming data from child
                        }
                    }
                });
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        SetsAdapter adp = new SetsAdapter(dataset, this);
        mAdapter = adp;
        filterable = adp;
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);

    }
    public void clickHandlerSet(View v){
        if (v.getId() == R.id.setsButton){
            ArrayList<Cards> sets = new ArrayList<Cards>();
            Sets set = new Sets("FEE",sets);

            dataset.add(set);
            ArrayList<Cards> test = dataset.get(dataset.size() - 1).getDataset();
            mAdapter.notifyDataSetChanged();
            Intent intent = new Intent(this, InputSetActivity.class);
            intent.putExtra("id", dataset.size() - 1);
            NameLauncher.launch(intent);
        }
    }
    @Override
    public void onClick(Sets n, int position) {
        Intent intent = new Intent(this,CardsActivity.class);
        ArrayList<Cards> test = dataset.get(position).getDataset();
        intent.putExtra("id", position);
        intent.putExtra("cards", (Serializable) test);
        CardsLauncher.launch(intent);

    }
}
