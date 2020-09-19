package com.roqua;

import android.app.Activity;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends BaseActivity {

    @BindView(R.id.home_gv_authors_list)
    RecyclerView homeGvAuthorsList;
    ArrayList<AuthorItem> list;
    AuthorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme2);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        addAuthors();
    }

    private void addAuthors() {
        homeGvAuthorsList.setLayoutManager(new GridLayoutManager(HomeActivity.this , 2));
        list=new ArrayList<>();
        list.add(new AuthorItem("عبد الباسط عبد الصمد" , R.drawable.baset , "" , ""));
        list.add(new AuthorItem("ياسر الدوسري" , R.drawable.dusry , "https://ia803009.us.archive.org/11/items/RuqiaDosary.mp3_530/RuqiaDosary.mp3" , ""));
        list.add(new AuthorItem("ماهر المعيقلي" , R.drawable.maher , "https://9wtquran.com/up/uploads/1467172156351.mp3" , "https://9wtquran.com/up/uploads/1467172156351.mp3"));
        list.add(new AuthorItem("محمد المحسيني" , R.drawable.mhsny , "" , ""));
        list.add(new AuthorItem("عبد الرحمن السديس" , R.drawable.sdis , "" , ""));
        adapter=new AuthorAdapter(list , HomeActivity.this , HomeActivity.this);
        homeGvAuthorsList.setAdapter(adapter);

    }

    @Override
    public void onBackPressed() {
        super.superBackPressed();
    }
}