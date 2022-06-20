package com.example.usermanagement.Adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.usermanagement.Model.Users;
import com.example.usermanagement.R;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ItemUserHolder> {
    private Context context;
    private List<Users> usersList;

    public RecyclerViewAdapter(Context context, List<Users> usersList) {
        this.context = context;
        this.usersList = usersList;
    }

    @NonNull
    @Override
    public ItemUserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemUserHolder(LayoutInflater.from(context).inflate(R.layout.user_items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemUserHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.txtName.setText(usersList.get(position).getName());
        holder.txtPhone.setText(usersList.get(position).getPhone());
        holder.txtAddress.setText(usersList.get(position).getAddress());
        holder.btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setMessage("You want to delete " +"'"+ usersList.get(position).getName() +"'" + " ?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        usersList.remove(position);
                        notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    public void addUser(Users users){
        usersList.add(users);
        notifyDataSetChanged();
    }

    public class ItemUserHolder extends RecyclerView.ViewHolder {
        private TextView txtName, txtPhone, txtAddress;
        private Button btnRemove;
        public ItemUserHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txt_username);
            txtPhone = itemView.findViewById(R.id.txt_phone);
            txtAddress = itemView.findViewById(R.id.txt_address);
            btnRemove = itemView.findViewById(R.id.btn_remove);
        }
    }
}
