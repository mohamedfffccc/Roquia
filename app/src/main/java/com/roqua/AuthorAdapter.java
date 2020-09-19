package com.roqua;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;


public class AuthorAdapter extends RecyclerView.Adapter<AuthorAdapter.ProductViewHolder> {


    private Context context;
    private List<AuthorItem> articleslist = new ArrayList<>();
    private Dialog dialog;
    HomeActivity activity;


    public AuthorAdapter(List<AuthorItem> articleslist, Context context , HomeActivity activity) {
        this.articleslist = articleslist;
        this.context = context;
        this.activity=activity;

    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.author_item, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.authorItemTvName.setText(articleslist.get(position).getName());
        holder.authorItemIvImage.setImageResource(articleslist.get(position).getImage());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialoge(position , context);
            }
        });


    }

    //TODO logout
    private void showDialoge(int i , Context c) {
        dialog = new Dialog(context, R.style.customDialogTheme);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.dialog_select_from, null);
        dialog.setContentView(v);
        TextView roqia = (TextView) v.findViewById(R.id.dialog_roqia_tv);
        TextView harq = (TextView) v.findViewById(R.id.dialog_harq_tv);

        roqia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayerFragment fragment = new PlayerFragment();
                fragment.path=articleslist.get(i).getRoqia_aya();
                fragment.name=articleslist.get(i).getName();
                ReplaceFragment(activity.getSupportFragmentManager() , fragment , R.id.container);
                dialog.dismiss();





            }
        });
        harq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PlayerFragment fragment = new PlayerFragment();
                fragment.path=articleslist.get(i).getHarq_aya();
                fragment.name=articleslist.get(i).getName();
                ReplaceFragment(activity.getSupportFragmentManager() , fragment , R.id.container);
                dialog.dismiss();


            }
        });


        dialog.show();

    }
    //TODO player
    public  void ReplaceFragment(FragmentManager supportFragmentManager, Fragment fragment, int container_id
            ) {

        FragmentTransaction transaction = supportFragmentManager.beginTransaction();

        transaction.replace(container_id, fragment);
        transaction.addToBackStack(null);
        transaction.commit();



    }
    @Override
    public int getItemCount() {
        return articleslist.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.author_item_iv_image)
        ImageView authorItemIvImage;
        @BindView(R.id.author_item_tv_name)
        TextView authorItemTvName;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
