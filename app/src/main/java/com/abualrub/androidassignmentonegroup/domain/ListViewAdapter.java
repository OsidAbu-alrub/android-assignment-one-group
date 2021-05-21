package com.abualrub.androidassignmentonegroup.domain;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.abualrub.androidassignmentonegroup.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ListViewAdapter extends BaseAdapter {

    Context mContext;
    LayoutInflater inflater;
    List<Course> coursesList;
    ArrayList<Course> arrayList;


    public ListViewAdapter(Context context, List<Course> coursesList) {
        mContext = context;
        this.coursesList = coursesList;
        inflater = LayoutInflater.from(mContext);
        this.arrayList = new ArrayList<Course>();
        this.arrayList.addAll(coursesList);
    }
    public class ViewHolder{
        TextView cTitle,cDesc;
        ImageView cIcon;
    }

    @Override
    public int getCount() {
        return coursesList.size();
    }

    @Override
    public Object getItem(int position) {
        return coursesList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if(view==null){
            holder=new ViewHolder();
            view=inflater.inflate(R.layout.row,null);

            holder.cTitle=view.findViewById(R.id.mainTitle);
            holder.cDesc=view.findViewById(R.id.mainDesc);
            holder.cIcon=view.findViewById(R.id.mainIcon);

            view.setTag(holder);
        }
        else{

            holder=(ViewHolder) view.getTag();
        }

        holder.cTitle.setText(coursesList.get(position).getTitle());
        holder.cDesc.setText(coursesList.get(position).getShortDesc());
        holder.cIcon.setImageResource(coursesList.get(position).getIcon());
        view.setOnClickListener(new View.OnClickListener(){


            //banan work...
            @Override
            public void onClick(View view) {
               /* if(coursesList.get(position).getTitle().equals("Java")){
                    Intent intent=new Intent(mContext, MainActivity2.class);
                    intent.putExtra("actionBarTitle", "Java");
                    intent.putExtra("content","This is java description");
                    mContext.startActivity(intent);
                }
                if(coursesList.get(position).getTitle().equals("Python")){
                    Intent intent=new Intent(mContext, MainActivity2.class);
                    intent.putExtra("actionBarTitle", "Python");
                    intent.putExtra("content","This is Python description");
                    mContext.startActivity(intent);
                } if(coursesList.get(position).getTitle().equals("Ai")){
                    Intent intent=new Intent(mContext, MainActivity2.class);
                    intent.putExtra("actionBarTitle", "Ai");
                    intent.putExtra("content","This is Ai description");
                    mContext.startActivity(intent);
                }
                if(coursesList.get(position).getTitle().equals("Android")){
                    Intent intent=new Intent(mContext, MainActivity2.class);
                    intent.putExtra("actionBarTitle", "Android");
                    intent.putExtra("content","This is Android description");
                    mContext.startActivity(intent);
                }
                if(coursesList.get(position).getTitle().equals("Java")){
                    Intent intent=new Intent(mContext, MainActivity2.class);
                    intent.putExtra("actionBarTitle", "Java");
                    intent.putExtra("content","This is java description");
                    mContext.startActivity(intent);
                }
                if(coursesList.get(position).getTitle().equals("Security")){
                    Intent intent=new Intent(mContext, MainActivity2.class);
                    intent.putExtra("actionBarTitle", "Security");
                    intent.putExtra("content","This is Security description");
                    mContext.startActivity(intent);
                }
                if(coursesList.get(position).getTitle().equals("C")){
                    Intent intent=new Intent(mContext, MainActivity2.class);
                    intent.putExtra("actionBarTitle", "C");
                    intent.putExtra("content","This is C description");
                    mContext.startActivity(intent);
                }
                if(coursesList.get(position).getTitle().equals("C#")){
                    Intent intent=new Intent(mContext, MainActivity2.class);
                    intent.putExtra("actionBarTitle", "C#");
                    intent.putExtra("content","This is C# description");
                    mContext.startActivity(intent);
                }
                if(coursesList.get(position).getTitle().equals("ASP.Net")){
                    Intent intent=new Intent(mContext, MainActivity2.class);
                    intent.putExtra("actionBarTitle", "ASP.Net");
                    intent.putExtra("content","This is ASP.Net description");
                    mContext.startActivity(intent);
                }
                if(coursesList.get(position).getTitle().equals("C#")){
                    Intent intent=new Intent(mContext, MainActivity2.class);
                    intent.putExtra("actionBarTitle", "C#");
                    intent.putExtra("content","This is C# description");
                    mContext.startActivity(intent);
                }
                if(coursesList.get(position).getTitle().equals("JavaScript")){
                    Intent intent=new Intent(mContext, MainActivity2.class);
                    intent.putExtra("actionBarTitle", "JavaScript");
                    intent.putExtra("content","This is JavaScript description");
                    mContext.startActivity(intent);
                }
                if(coursesList.get(position).getTitle().equals("Html")){
                    Intent intent=new Intent(mContext, MainActivity2.class);
                    intent.putExtra("actionBarTitle", "Html");
                    intent.putExtra("content","This is Html description");
                    mContext.startActivity(intent);
                }*/


            }
        });




        return view;
    }
    //filter

    public void filter(String charText){
        charText=charText.toLowerCase(Locale.getDefault());
        coursesList.clear();
    if(charText.length()==0){
        coursesList.addAll(arrayList);
    }
    else{
        for (Course course: arrayList){
            if(course.getTitle().toLowerCase(Locale.getDefault())
                .contains(charText)){
                coursesList.add(course);
            }

        }
    }
        notifyDataSetChanged();
    }
}
