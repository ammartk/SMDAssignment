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

public class CardsActivity extends AppCompatActivity implements CardsAdapter.CardItemListener {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    ArrayList<Cards> dataset = new ArrayList<Cards>();
    ActivityResultLauncher<Intent> CardLauncher;
    private int currentSetID;
    private EditText search;
    Filterable filterable;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards);

        search = (EditText) findViewById(R.id.searchCard);
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


        recyclerView = (RecyclerView) findViewById(R.id.CardsList);
        recyclerView.setHasFixedSize(true);
        Intent intent = getIntent();
        currentSetID = intent.getIntExtra("id", 0);
        dataset = (ArrayList<Cards>) intent.getSerializableExtra("cards");
        CardLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {

                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode() == RESULT_OK){
                            Intent data = result.getData();
                            int id = data.getIntExtra("id", 0);
                            Cards temp = (Cards) data.getSerializableExtra("content");
                            dataset.set(id, temp);
                            mAdapter.notifyDataSetChanged();
                            // handle incoming data from child
                            // handle incoming data from child
                        }
                    }
                });
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        CardsAdapter adp = new CardsAdapter(dataset, this);
        mAdapter = adp;
        filterable = adp;
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);

    }
    @Override
    public void onBackPressed(){
        Intent i = new Intent();
        i.putExtra("pos", currentSetID);
        i.putExtra("cards", (Serializable) dataset);
        setResult(RESULT_OK, i);
        finish();

    }

    public void clickHandler(View v){
        if (v.getId() == R.id.push_button){
            Cards card = new Cards("FEE", "FI");
            dataset.add(card);
            Intent intent = new Intent(this,CardActivity.class);
            intent.putExtra("id", dataset.size() - 1);
            CardLauncher.launch(intent);
            mAdapter.notifyDataSetChanged();
        }
    }
    @Override
    public void onClickListener(Cards n, int position) {
        Intent intent = new Intent(this,CardActivity.class);
        intent.putExtra("id", position);
        Cards temp = dataset.get(position);
        intent.putExtra("card", (Serializable) temp);

        CardLauncher.launch(intent);

    }
}
