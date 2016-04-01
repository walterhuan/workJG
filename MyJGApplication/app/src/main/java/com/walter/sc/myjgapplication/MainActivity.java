package com.walter.sc.myjgapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.walter.sc.okhttp.OKHttpActivity;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import butterknife.OnLongClick;

public class MainActivity extends AppCompatActivity {
    private static final ButterKnife.Action<View> ALPHA_FADE = new ButterKnife.Action<View>() {

        @Override
        public void apply(View view, int index) {
            AlphaAnimation alphaAnimation = new AlphaAnimation(0,1);
            alphaAnimation.setFillBefore(true);
            alphaAnimation.setDuration(500);
            alphaAnimation.setStartOffset(index * 100);
            view.startAnimation(alphaAnimation);
        }
    };

    @Bind(R.id.title)
    TextView myTitle;

    @Bind(R.id.subtitle)
    TextView mySubTitle;

    @Bind(R.id.hello)
    Button myButton;

    @Bind(R.id.list_of_things)
    ListView myListOfThings;

    @Bind(R.id.footer)
    TextView myFooter;

    @Bind({R.id.title,R.id.subtitle,R.id.hello})
    List<View> headerViews;

    private MyButterKnifeAdapter adapter;
    
    @OnClick(R.id.hello)
    void sayHello(){
        Toast.makeText(this,"Hello views!",Toast.LENGTH_SHORT).show();
        ButterKnife.apply(headerViews,ALPHA_FADE);
    }

    @OnLongClick(R.id.hello)
    boolean sayGetOffMe(){
        Toast.makeText(this,"get off me!",Toast.LENGTH_SHORT).show();
        return true;
    }

    @OnItemClick(R.id.list_of_things)
    void OnItemClickList(int position){
        Toast.makeText(this,adapter.getItem(position).toString(),Toast.LENGTH_SHORT).show();
        switch (position){
            case 0:
                Intent intent = new Intent(this, OKHttpActivity.class);
                startActivity(intent);
                break;

        }

    }






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        init();

    }

    private void init() {
        myTitle.setText("Butter Knife");
        mySubTitle.setText("Field and method binding for Android views.");
        myFooter.setText("by Jake Wharton");
        myButton.setText("Say Hello");

        adapter = new MyButterKnifeAdapter(this);
        myListOfThings.setAdapter(adapter);


    }
}
