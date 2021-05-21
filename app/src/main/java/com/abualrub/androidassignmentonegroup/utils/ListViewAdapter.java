package com.abualrub.androidassignmentonegroup.utils;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.abualrub.androidassignmentonegroup.R;
import com.abualrub.androidassignmentonegroup.domain.Course;
import com.abualrub.androidassignmentonegroup.utils.ICategory;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
// *********************************
// MADE BY MOHAMMAD MUTAIR (1180907)
// ALSO OSID ABU-ALRUB (1183096)
// *********************************
public class ListViewAdapter extends BaseAdapter implements ICategory {

    private Context mContext;
    private LayoutInflater inflater;
    private List<Course> coursesList;
    private ArrayList<Course> arrayList;

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
        switch (coursesList.get(position).getCategory()){
            case PROGRAMMING:
                holder.cIcon.setImageResource(PROGRAMMING_ICON);
                break;
            case LANGUAGES:
                holder.cIcon.setImageResource(LANGUAGE_ICON);
                break;
            case COOKING:
                holder.cIcon.setImageResource(COOKING_ICON);
                break;
            case SCIENCE:
                holder.cIcon.setImageResource(SCIENCE_ICON);
                break;
            default:
                holder.cIcon.setImageResource(LIFE_ICON);
        }
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
