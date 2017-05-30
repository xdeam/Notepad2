package notepad.xdream.cf.notepad.tools;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import notepad.xdream.cf.notepad.AnotherFRG;
import notepad.xdream.cf.notepad.R;
import notepad.xdream.cf.notepad.bean.Notes;

/**
 * Created by kwinter on 2017/5/29.
 */

public class MyAdapter extends BaseAdapter {
    private Context context = null;// 上下文  
    private ArrayList<Notes> list = null;// 数据源

    //private ImageView notesImage = null;
    private TextView notesText = null;
    private TextView dtime=null;



    // 适配器构造函数

    public MyAdapter(Context c, ArrayList<Notes> list) {
        this.context = c;//c是上下文，在UI编程中，一般这个参数都是必要的
        this.list = list;//list中是一个Notes数组，存放了所有要显示的Notes  
    }

    // 下面三个是实现抽象函数，可以无视  
    public int getCount() {
        return list.size();
    }
    public Object getItem(int position) {
        return list.get(position);
    }
    public long getItemId(int position) {
        return position;
    }

    // 根据参数，个性化自己的View  
    public View getView(final int position, View convertView, final ViewGroup parent) {
      convertView = LayoutInflater.from(context).inflate(R.layout.list_items,
                null);// 通过上下文获取一开始定义的menu_item布局，以这个布局文件为样式造出一个自定义View
        /*
        NotesImage = (ImageView) convertView.findViewById(R.id.item_image);//获取布局文件里的ImageView  
        NotesText = (TextView) convertView.findViewById(R.id.item_text);//获取布局文件里的TextView  

       NotesImage.setImageResource(list.get(position)
                .getNotesImageRscId());//对功能按钮的图标进行赋值
        NotesText.setText(list.get(position).getNotesTextRscId());//对功能按钮的名字进行赋值  */
        notesText=(TextView)convertView.findViewById(R.id.itemText);
        dtime=(TextView)convertView.findViewById(R.id.rtime);
        dtime.setText(list.get(position).getDate()==null?"没有便签":list.get(position).getDate().toString());
        notesText.setText(list.get(position).getContent());
     /*   convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Tips",list.get(position).getContent());

            }
        });*/
        //响应不同Notes的onClick事件，个人认为这行代码最精妙，仅仅一行代码省去了多少switch和case  

        return convertView;//最终返回一个View，这一个View就是一整个功能按钮，而且是个性化的功能按钮  
    }
}  
