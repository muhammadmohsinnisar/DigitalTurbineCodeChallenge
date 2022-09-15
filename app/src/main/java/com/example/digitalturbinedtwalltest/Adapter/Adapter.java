package com.example.digitalturbinedtwalltest.Adapter;

import android.content.Context;

import com.example.digitalturbinedtwalltest.Model.DWTestModel;

import java.util.List;


public class Adapter  {


    private Context context;
    private List<DWTestModel> dwTestModelList;



    /**
     * Initialize the dataset of the Adapter.
     *
     * dataSet String[] containing the data to populate views to be used
     * by RecyclerView.
     */
//    public Adapter(Context context, List<DWTestModel> dwTestModelList){
//        this.context = context;
//        this.dwTestModelList = dwTestModelList;
//    }

//    public static class MyViewholder extends RecyclerView.ViewHolder{
//
//        TextView title;
//        ImageView imageView;
//
//        public MyViewholder(@NonNull View itemView){
//            super(itemView);
//            title = itemView.getRootView().findViewById(R.id.title_text);
//            imageView = itemView.getRootView().findViewById(R.id.image_text);
//        }
//    }

    // Create new views (invoked by the layout manager)
//    @NonNull
//    @Override
//    public WallAdapter.MyViewholder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
//        // Create a new view, which defines the UI of the list item
//        View view = LayoutInflater.from(viewGroup.getContext())
//                .inflate(R.layout.item_dtwall, viewGroup, false);
//        return new WallAdapter.MyViewholder(view);
//    }
//
//
//    @Override
//    public void onBindViewHolder(@NonNull WallAdapter.MyViewholder holder, int position) {
//
//        //get position
//        DWTestModel dwTestModel = dwTestModelList.get(position);
//        final int pos = position;
//        String title = dwTestModel.getTitle();
//        //String image = dwTestModel.getImage();
//
//        // Get element from your dataset at this position and replace the
//        // contents of the view with that element
//        holder.title.setText(dwTestModelList.get(position).getTitle());
//        //Add glide for picture;
//        /*Glide.with(context)
//                .load(dwTestModelList.get(position).getImage())
//                .into(holder.imageView);*/
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return dwTestModelList.size();
//    }

}
