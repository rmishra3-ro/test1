package com.example.gymbudd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FindFriendActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private Button searchButton;
    private EditText searchInputText;

    private RecyclerView searchResult;
    private TextView fullName;
    private TextView Age;

    FirebaseFirestore dataStore = FirebaseFirestore.getInstance();
    FirebaseAuth auth = FirebaseAuth.getInstance();
    private static final String TAG = "FirestoreSearchActivity";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_friend);

        //mToolbar =  findViewById(R.id.se)
        searchResult = findViewById(R.id.search_result_list);
        searchResult.setHasFixedSize(true);
        searchResult.setLayoutManager(new LinearLayoutManager(this));

        searchButton = findViewById(R.id.btn_Search);
        searchInputText = findViewById(R.id.searchView);
        fullName = findViewById(R.id.userNameProfile);
        Age = findViewById(R.id.user_profile_age);


        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchInput = searchInputText.getText().toString();
                SearchNewPeople(searchInput);
                


            }
        });

        searchInputText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.d(TAG, "SearchBox has changed to: " + s.toString());




            }
        });

    }

    private void SearchNewPeople(String searchInput) {
        dataStore.collection("Data").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    List<DocumentSnapshot> list = task.getResult().getDocuments();
                    for (DocumentSnapshot s: list) {
                        Map<String, User> map = s.toObject(Map.class);
                    }
//                    for (DocumentSnapshot s : task.getResult().getDocuments()) {
//                        Map map = s.getData();
//                        for (Object x: map.values()){
//                            Log.d("TAG", x.toString());
//                        }
                        //if(searchInput.getBytes(auth))


//                        if (map.containsKey(auth.getCurrentUser().getUid())) {
//                            HashMap<String, String> userInfo = (HashMap) map.get(auth.getCurrentUser().getUid());
//                            Toast.makeText(FindFriendActivity.this, "FOUND CURRENT USER", Toast.LENGTH_SHORT).show();
//                            fullName.setText(userInfo.get("name"));
//                            Age.setText(userInfo.get("age"));
//
//                        }
 //                   }

                } else {

                }
            }
        });
    }
}
