package com.treasure_ct.android_xt.studyfragment.loadingways;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.treasure_ct.android_xt.R;
import com.treasure_ct.android_xt.studyfragment.loadingways.Loader.LoaderContacts;
import com.treasure_ct.android_xt.studyfragment.loadingways.Loader.LoaderDuanzi;

public class LoaderActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loadingways_loader);
        Button contacts = (Button) findViewById(R.id.loader_contacts);
        Button duanzi = (Button) findViewById(R.id.loader_duanzi);
        contacts.setOnClickListener(this);
        duanzi.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loader_contacts:
                startActivity(new Intent(this,LoaderContacts.class));
                break;
            case R.id.loader_duanzi:
                startActivity(new Intent(this,LoaderDuanzi.class));
                break;
        }
    }
}
