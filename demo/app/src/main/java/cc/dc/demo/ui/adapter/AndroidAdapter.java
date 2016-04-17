package cc.dc.demo.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import cc.dc.demo.R;
import cc.dc.demo.bean.AndroidBean;

/**
 * Created by dingcai on 2016/4/6.
 */
public class AndroidAdapter extends BaseAdapter{
    private Context context;
    private List<AndroidBean> infos;
    private LayoutInflater inflater;
    private ViewHolder viewHolder;

    public AndroidAdapter(Context context, List<AndroidBean> infos) {
        this.context = context;
        this.infos = infos;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return infos.size();
    }

    @Override
    public Object getItem(int position) {
        return infos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AndroidBean bean = infos.get(position);
        if (convertView == null) {
            convertView  =inflater.inflate(R.layout.item_list_only,null);
            viewHolder = new ViewHolder();
            viewHolder.autor = (TextView)convertView.findViewById(R.id.tv_item_list_autor);
            viewHolder.desc = (TextView)convertView.findViewById(R.id.tv_item_list_desc);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        if (bean != null) {
            viewHolder.autor.setText(bean.getWho());
            viewHolder.desc.setText(bean.getDesc());
        }
        return convertView;
    }

    private class ViewHolder{
        private TextView autor;
        private TextView desc;
    }
}
