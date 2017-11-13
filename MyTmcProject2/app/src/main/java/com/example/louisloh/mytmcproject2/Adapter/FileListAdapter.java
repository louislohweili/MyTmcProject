//package com.example.louisloh.mytmcproject2.Adapter;
//
//import android.app.Activity;
//import android.support.annotation.LayoutRes;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.bumptech.glide.Glide;
//import com.example.louisloh.mytmcproject2.R;
//import com.example.louisloh.mytmcproject2.Methods.UploadFile;
//
//import java.util.List;
//
///**
// * Created by louisloh on 5/5/2017.
// */
//
//public class FileListAdapter extends ArrayAdapter<UploadFile> {
//    private Activity context;
//    private int resource;
//    private List<UploadFile> listImage;
//
//    public FileListAdapter(@NonNull Activity context, @LayoutRes int resource, @NonNull List<UploadFile> objects) {
//        super(context, resource, objects);
//        this.context = context;
//        this.resource = resource;
//        listImage = objects;
//    }
//
//    @NonNull
//    @Override
//    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        LayoutInflater inflater = context.getLayoutInflater();
//
//        View v = inflater.inflate(resource, null);
//        TextView tvName = (TextView) v.findViewById(R.id.tvImageName);
//        ImageView img = (ImageView) v.findViewById(R.id.imgView);
//
//        tvName.setText(listImage.get(position).getFileName());
//        Glide.with(context).load(listImage.get(position).getUrl()).into(img);
//
//        return v;
//
//    }
//}


