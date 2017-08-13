package notepad.xdream.cf.notepad.tools;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import notepad.xdream.cf.notepad.R;
import notepad.xdream.cf.notepad.bean.Notes;

/**
 * Created by kwinter on 2017/8/13.
 */

/*
 * 采集器列表数据适配器
 * @author guopeng
 * @created 2015年12月4日
 */
public class CollectorListAdapter extends BaseAdapter {

    private List<Notes> listItems;//数据集合
    private LayoutInflater layoutinflater;//视图容器，用来导入布局

    static class ViewHolder
    {
        private TextView tv_writeTime;
        private TextView tv_content;
        private ImageView image;
    }

    /*
     * 实例化Adapter
     */
    public CollectorListAdapter(Context context, List<Notes> dataSet)
    {
        this.listItems = dataSet;
        this.layoutinflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public Object getItem(int position) {
        return listItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Notes notes = listItems.get(position);
        ViewHolder holder;
        View view;
        if(convertView == null)
        {
            holder= new ViewHolder();
            //获取listitem布局文件
            view = layoutinflater.inflate(R.layout.list_items, null);

            //获取控件对象
            holder.tv_writeTime = (TextView) view.findViewById(R.id.rtime);
            holder.tv_content = (TextView) view.findViewById(R.id.itemText);
            holder.image = (ImageView) view.findViewById(R.id.itemPic);

            view.setTag(holder);
        }
        else
        {
            view = convertView;
            holder = (ViewHolder) view.getTag();
        }
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //设置图片和文字
        holder.tv_writeTime.setText(format1.format(notes.getDate()));
        holder.tv_content.setText(notes.getContent());

        return view;
    }

}