package uz.jkamoliddinov0303.firebaserealtimedatabase

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row_item.view.*

class RvAdapter(val list: ArrayList<User>) : RecyclerView.Adapter<RvAdapter.UsersViewHolder>() {
    inner class UsersViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_item, null, false)
        return UsersViewHolder(view)
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
       holder.itemView.tv_password.text = list[position].password
       holder.itemView.tv_username.text = list[position].username
    }

    override fun getItemCount(): Int {
        return list.size
    }
}