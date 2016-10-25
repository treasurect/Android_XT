package com.treasure_ct.android_xt.welcomfragment.chat;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMContactManager;
import com.hyphenate.exceptions.HyphenateException;
import com.treasure_ct.android_xt.R;

import java.util.ArrayList;
import java.util.List;

public class ChatContactListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 200:
                    Object obj = msg.obj;
                    if (obj != null){
                        if (obj instanceof List){
                            List list = (List) obj;
                            arratlist.clear();
                            for (Object o : list) {
                             arratlist.add((String) o);
                            }
                            adapter.notifyDataSetChanged();
                        }
                    }
                    break;
            }
        }
    };
    private ArrayAdapter adapter;
    private ArrayList<String> arratlist;
    private EditText editAddContact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_contact_list);
        String userName = getIntent().getStringExtra("user");
        if (userName == null){
            finish();
        }else {
            ListView userListView = (ListView) findViewById(R.id.chat_userList);
            if (userListView != null){
                arratlist = new ArrayList<>();
                adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,arratlist);
                userListView.setAdapter(adapter);
                userListView.setOnItemClickListener(this);
            }
            ContactListThread thread = new ContactListThread();
            thread.start();
            editAddContact = (EditText) findViewById(R.id.chat_userList_addContactName);
        }
    }

    public void btnAddContacts(View view) {
        String name = editAddContact.getText().toString().trim();
        //TODO:好友的添加
        AddContactThread thread = new AddContactThread(name);
        thread.start();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String name = arratlist.get(position);
        Intent intent = new Intent(this, ChatPagerActivity.class);
        intent.putExtra("name",name);
        intent.putExtra("id",id);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.welcome_chat_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.chat_quit:
                EMClient.getInstance().logout(true);
                Toast.makeText(this, "退出成功", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
        return true;
    }

    private class AddContactThread extends Thread{
        private String account;

        public AddContactThread(String account) {
            this.account = account;
        }

        @Override
        public void run() {
            try {
                EMClient.getInstance().contactManager().addContact(account,"我是你老友");
            } catch (HyphenateException e) {
                e.printStackTrace();
            }

            //刷新列表
            new ContactListThread().start();
        }
    }
    private class ContactListThread extends Thread{
        @Override
        public void run() {
            try {
                EMContactManager manager = EMClient.getInstance().contactManager();
                List<String> userList = manager.getAllContactsFromServer();
                Message message = mHandler.obtainMessage(200);
                message.obj = userList;
                mHandler.sendMessage(message);
            } catch (HyphenateException e) {
                e.printStackTrace();
            }
        }
    }
}
