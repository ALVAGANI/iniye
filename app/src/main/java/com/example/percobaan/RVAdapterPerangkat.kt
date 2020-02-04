package com.example.percobaan

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.perangkat_list.view.*

class RVAdapterPerangkat (private val context: Context, private val arrayList: ArrayList<Perangkat>) :
    RecyclerView.Adapter<RVAdapterPerangkat.Holder> () {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.perangkat_list, parent, false))
    }

    override fun getItemCount(): Int = arrayList!!.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.view.lbEmailList.text = arrayList?.get(position)?.Email
        holder.view.lbEmailList.text = "Email : " + arrayList?.get(position)?.Email
        holder.view.lbNamaList.text = "Nama : " + arrayList?.get(position)?.Nama
        holder.view.lbNoKartuList.text = "No Kartu : " + arrayList?.get(position)?.No_Kartu
        holder.view.lbNoPerangkatList.text = "No Perangkat : " + arrayList?.get(position)?.No_Perangkat
        holder.view.lbUsernameList.text = "Username : " + arrayList?.get(position)?.Username
        holder.view.lbPasswordList.text = "Password : " + arrayList?.get(position)?.Password

        holder.view.cv_list.setOnClickListener {
            val i = Intent(context, ManagePerangkatActivity::class.java)
            i.putExtra("editmode", "1")
            i.putExtra("Nama", arrayList?.get(position)?.Nama)
            i.putExtra("Email", arrayList?.get(position)?.Email)
            i.putExtra("No Kartu", arrayList?.get(position)?.No_Kartu)
            i.putExtra("No Perangkat", arrayList?.get(position)?.No_Perangkat)
            i.putExtra("Username", arrayList?.get(position)?.Username)
            i.putExtra("Password", arrayList?.get(position)?.Password)
            context.startActivity(i)

        }
    }

    class Holder(val view: View) : RecyclerView.ViewHolder(view)
}