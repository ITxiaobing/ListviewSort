package com.demo.it.listviewindex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {
    private String TAG = MainActivity.class.getSimpleName();
    private ArrayList<Person> contactLst = new ArrayList<>();
    private ListView mContact;
    private TextView mSelectIndex;
    private ListIndexView mListIndex;
    private int mScrollState = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListIndex = (ListIndexView) findViewById(R.id.lv);
        mContact = (ListView) findViewById(R.id.contact);
        mSelectIndex = (TextView) findViewById(R.id.select_index);
        mListIndex.setOnSelectIndex(new ListIndexView.SelectIndexListener() {
            @Override
            public void selectIndex(String index) {
                mSelectIndex.setVisibility(View.VISIBLE);
                mSelectIndex.setText(index);
                updateListView(index);
            }

            @Override
            public void hideSelect() {
                mSelectIndex.setVisibility(View.GONE);
            }
        });
        initData();
        mContact.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                Log.e(TAG, scrollState + "=========");
                mScrollState = scrollState;
                if (mScrollState == 1 || mScrollState == 2) {
                    mSelectIndex.setVisibility(View.VISIBLE);
                } else {
                    mSelectIndex.setVisibility(View.GONE);
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                mListIndex.setTouchIndex(contactLst.get(firstVisibleItem).getHeaderWord());
                if (mScrollState == 1 || mScrollState == 2) {
                    mSelectIndex.setVisibility(View.VISIBLE);
                    mSelectIndex.setText(contactLst.get(firstVisibleItem).getHeaderWord());
                }


            }
        });
    }

    private void updateIndex(String headerWord) {
        mSelectIndex.setVisibility(View.VISIBLE);
        mSelectIndex.setText(headerWord);
    }

    private void updateListView(String words) {
        for (int i = 0; i < contactLst.size(); i++) {
            String headerWord = contactLst.get(i).getHeaderWord();
            //将手指按下的字母与列表中相同字母开头的项找出来
            if (words.equals(headerWord)) {
                //将列表选中哪一个
                mContact.setSelection(i);
                //找到开头的一个即可
                return;
            }
        }
    }

    private void initData() {
        Person person = new Person("张三");
        Person person2 = new Person("李四");
        Person person3 = new Person("王二");
        Person person4 = new Person("张静初");
        Person person5 = new Person("赵丽颖");
        Person person6 = new Person("王二");
        Person person7 = new Person("张静初");
        Person person8 = new Person("赵丽颖");
        Person person9 = new Person("王二");
        Person person10 = new Person("张静初");
        Person person11 = new Person("赵丽颖");
        Person person12 = new Person("王二");
        Person person13 = new Person("张静初");
        Person person14 = new Person("赵丽颖");
        Person person15 = new Person("赵丽颖");
        contactLst.add(person);
        contactLst.add(person2);
        contactLst.add(person3);
        contactLst.add(person4);
        contactLst.add(person5);
        contactLst.add(person6);
        contactLst.add(person7);
        contactLst.add(person8);
        contactLst.add(person9);
        contactLst.add(person10);
        contactLst.add(person11);
        contactLst.add(person12);
        contactLst.add(person13);
        contactLst.add(person14);
        contactLst.add(person15);
        //对集合排序
        Collections.sort(contactLst, new Comparator<Person>() {
            @Override
            public int compare(Person lhs, Person rhs) {
                //根据拼音进行排序
                return lhs.getPinyin().compareTo(rhs.getPinyin());
            }
        });
        mContact.setAdapter(new ContactAdapter(contactLst, this));
    }
}
