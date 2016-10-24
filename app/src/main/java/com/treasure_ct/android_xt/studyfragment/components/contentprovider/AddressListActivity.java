package com.treasure_ct.android_xt.studyfragment.components.contentprovider;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.treasure_ct.android_xt.R;

public class AddressListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {
    private ListView listView;
    private SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_components_contentprovider_addresslist);
        listView = (ListView) findViewById(R.id.addresslist_btn);
        ContentResolver resolver = getContentResolver();
        Cursor cursor = resolver.query(ContactsContract.Contacts.CONTENT_URI, new String[]{ContactsContract.Contacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME}, null, null, null);
        adapter = new SimpleCursorAdapter(this,android.R.layout.simple_list_item_1,cursor,new String[]{ContactsContract.Contacts.DISPLAY_NAME},
                new int[]{android.R.id.text1}, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ContentResolver resolver = getContentResolver();
        Cursor cursor = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=?",
                new String[]{Long.toString(id)}, null);
        if (cursor != null){
            while (cursor.moveToNext()){
                int index = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                if (index != -1) {
                    String phone = cursor.getString(index);
                    Toast.makeText(AddressListActivity.this,"手机号:"+phone, Toast.LENGTH_SHORT).show();
                }
            }
            cursor.close();
        }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        ContentResolver resolver = getContentResolver();
        int delete = resolver.delete(ContactsContract.RawContacts.CONTENT_URI, ContactsContract.RawContacts.CONTACT_ID + "=?",
                new String[]{Long.toString(id)});
        if (delete > 0) {
            ContentResolver resolver1 = getContentResolver();
            Cursor cursor = resolver1.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
            adapter.changeCursor(cursor);
        }
        return false;
    }
}
