package com.example.gek.weahtergek;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnSearch).setOnClickListener(v -> {
            openSearchDialog();
        });
    }

    private void openSearchDialog(){
        SearchDialog searchDialog = new SearchDialog();
        searchDialog.show(getFragmentManager(), "dialog search");
    }
}
