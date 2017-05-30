package notepad.xdream.cf.notepad;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.a520wcf.yllistview.YLListView;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import notepad.xdream.cf.notepad.bean.Notes;
import notepad.xdream.cf.notepad.tools.BackHandledFragment;
import notepad.xdream.cf.notepad.tools.MyAdapter;


public class BlankFragment   extends BackHandledFragment {
    private YLListView listView;
    private int lastPress = 0;
    private boolean delState = false;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         final View root=inflater.inflate(R.layout.add,container,false);

        final List<Notes> notesList = DataSupport.findAll(Notes.class);

       // String a[]={"a","b","c"};
     // DataSupport.deleteAll(Notes.class);

        listView = (YLListView)root.findViewById(R.id.listView);
        // 不添加也有默认的头和底
        View topView=View.inflate(root.getContext(),R.layout.top,null);
        listView.addHeaderView(topView);
        View bottomView=new View(root.getContext());
        listView.addFooterView(bottomView);

        // 顶部和底部也可以固定最终的高度 不固定就使用布局本身的高度
        //listView.setFinalBottomHeight(100);
        listView.setFinalTopHeight(100);

        listView.setAdapter(new MyAdapter(root.getContext(), (ArrayList<Notes>) notesList));

        //YLListView默认有头和底  处理点击事件位置注意减去
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                position=position-listView.getHeaderViewsCount();
                getFragmentManager().beginTransaction().replace(R.id.container,AnotherFRG.newInstance(notesList.get(position))).commit();


            }
        });
      /*  List<Notes> notesList = DataSupport.findAll(Notes.class);
        LinearLayout ly=(LinearLayout) root.findViewById(R.id.llayout);
       for (Notes notes:notesList){
           TextView tv=new TextView(root.getContext());
           tv.setText(notes.getContent());
           //tv.setId(Integer.parseInt(notes.getId().toString()));
           tv.setBackgroundColor(Color.LTGRAY);
         *//*  tv.setHeight(90);
           tv.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);*//*
           LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,90);
           lp.setMargins(10, 20, 30, 40);
           tv.setLayoutParams(lp);
           // tv.setGravity(Gravity.TOP);
           ly.addView(tv);

       }*/
        root.findViewById(R.id.addNotes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.container,new AnotherFRG()).commit();
            }
        });
        return root;
    }


    @Override
    public boolean onBackPressed() {
        return false;
    }
}
