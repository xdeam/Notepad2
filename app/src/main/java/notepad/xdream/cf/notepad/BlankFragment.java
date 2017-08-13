package notepad.xdream.cf.notepad;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import org.litepal.crud.DataSupport;

import java.util.List;

import notepad.xdream.cf.notepad.bean.Notes;
import notepad.xdream.cf.notepad.tools.BackHandledFragment;
import notepad.xdream.cf.notepad.tools.CollectorListAdapter;


public class BlankFragment   extends BackHandledFragment {

    private int lastPress = 0;
    private boolean delState = false;
    private SwipeMenuListView listView;

    //初始化ListView数据，在OnCreate方法中调用
    private void initData(Context context)
    {
        final List<Notes> notesList = DataSupport.findAll(Notes.class);
       final CollectorListAdapter adapter = new CollectorListAdapter(context,notesList);
       Log.d("nuul", "initData: "+adapter==null?"null":"!！！！");
        listView.setAdapter(adapter);
        listView.setEmptyView(getActivity().findViewById(R.id.collector_listview_empty));


        // step 1. create a MenuCreator
        SwipeMenuCreator creator = new SwipeMenuCreator()
        {
            @Override
            public void create(SwipeMenu menu)
            {
                // create "open" item
                SwipeMenuItem openItem = new SwipeMenuItem(getActivity().getApplicationContext());
                // set item background
                openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9, 0xCE)));
                // set item width
                openItem.setWidth(dp2px(90));
                // set item title
                openItem.setTitle("打开");
                // set item title fontsize
                openItem.setTitleSize(18);
                // set item title font color
                openItem.setTitleColor(Color.WHITE);
                // add to menu
                menu.addMenuItem(openItem);

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(getActivity().getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9, 0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(dp2px(90));
                // set a icon
                deleteItem.setIcon(R.drawable.ic_action_discard);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };

        // set creator
        listView.setMenuCreator(creator);

        // step 2. listener item click event
        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener()
        {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index)
            {
                Notes notes = notesList.get(position);

                switch (index)
                {
                    case 0:
                        // open
                        open(notes);
                        break;
                    case 1:
                        // delete
                        delete(notes);
                        notesList.remove(position);
                        adapter.notifyDataSetChanged();
                        break;
                }
                return true;
            }
        });

        // set SwipeListener
        listView.setOnSwipeListener(new SwipeMenuListView.OnSwipeListener()
        {
            @Override
            public void onSwipeStart(int position)
            {
                // swipe start
            }

            @Override
            public void onSwipeEnd(int position)
            {
                // swipe end
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                getFragmentManager().beginTransaction().replace(R.id.container,AnotherFRG.newInstance(notesList.get(position))).commit();

            }
        });
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }

    /**
     * 打开
     * @param
     */
    private void open(Notes notes)
    {
        getFragmentManager().beginTransaction().replace(R.id.container,AnotherFRG.newInstance(notes)).commit();

    }

    /**
     * 删除
     * @param
     */
    private void delete(Notes notes)
    {
        notes.delete();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         final View root=inflater.inflate(R.layout.add,container,false);
        listView= (SwipeMenuListView) root.findViewById(R.id.collector_listview);
        initData(root.getContext());


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
