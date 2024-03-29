package com.mmuhamadamirzaidi.firebaserecyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mmuhamadamirzaidi.firebaserecyclerview.Adapter.CategoryAdapter;
import com.mmuhamadamirzaidi.firebaserecyclerview.Model.Category;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView categorypage, subcategorypage, endpage;

    DatabaseReference reference;
    RecyclerView listcategory;
    ArrayList<Category> category;
    CategoryAdapter categoryAdapter;

//    FloatingActionButton MemoFabCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        categorypage = findViewById(R.id.categorypage);
        subcategorypage = findViewById(R.id.subcategorypage);
        endpage = findViewById(R.id.endpage);

//        //Floating Action Button
//        MemoFabCreate = (FloatingActionButton) findViewById(R.id.memo_fab_create);
//
//        MemoFabCreate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent createIntent = new Intent(MemoActivity.this, CreateMemoActivity.class);
//                startActivity(createIntent);
//            }
//        });

        //Working with data
        listcategory = findViewById(R.id.listcategory);
        listcategory.setLayoutManager(new LinearLayoutManager(this));
        category = new ArrayList<Category>();

        reference = FirebaseDatabase.getInstance().getReference().child("Category");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Set code to retrieve data and replace layout
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Category p = dataSnapshot1.getValue(Category.class);
                    category.add(p);
                }
                categoryAdapter = new CategoryAdapter(MainActivity.this, category);
                listcategory.setAdapter(categoryAdapter);
                categoryAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //Set code to show an error
                Toast.makeText(getApplicationContext(), "No Data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
