package mardil.it.uniparthenope.easyeat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CustomListView extends ArrayAdapter<model> {

    public static ArrayList<model> piatti;
    public Integer imgid;
    private Activity context;



    public CustomListView(Activity context, ArrayList<model> piatti, Integer imgid) {
        super(context, R.layout.row,piatti);

        this.context=context;
        this.piatti=piatti;
        this.imgid=imgid;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {



        /*
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.row, null);
        }

        ImageView img = convertView.findViewById(R.id.imageView2);
        TextView text = convertView.findViewById(R.id.textView);
        CheckBox check = convertView.findViewById(R.id.checkBox);

        img.setImageResource(imgid.get(position));
        text.setText(piatti.get(position).getPiatto());

        return convertView;

*/

        final ViewHolder viewHolder;

        if(convertView==null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.row, null);
            viewHolder = new ViewHolder();

            viewHolder.tvw1 = convertView.findViewById(R.id.textView);
            viewHolder.ivw1 = convertView.findViewById(R.id.imageView2);
            viewHolder.cb1 = convertView.findViewById(R.id.checkBox);

            convertView.setTag(viewHolder);

        }
        else {
            viewHolder= (ViewHolder) convertView.getTag();
        }

        viewHolder.tvw1.setText(piatti.get(position).getPiatto());
        viewHolder.cb1.setChecked(piatti.get(position).getSelected());
        viewHolder.cb1.setTag(R.integer.btnplusview,convertView);
        viewHolder.cb1.setTag(position);
        viewHolder.ivw1.setImageResource(imgid);
        viewHolder.cb1.setFocusable(false);

        viewHolder.cb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View tempview= (View) viewHolder.cb1.getTag(R.integer.btnplusview);
                TextView tv=tempview.findViewById(R.id.textView);
                 Integer pos= (Integer) viewHolder.cb1.getTag();

                if(piatti.get(pos).getSelected()){
                    piatti.get(pos).setSelected(false);
                }
                else {
                    piatti.get(pos).setSelected(true);
                }


            }
        });


        return convertView;
    }

}



    class ViewHolder
    {
        TextView tvw1;
        ImageView ivw1;
        CheckBox cb1;

    }
