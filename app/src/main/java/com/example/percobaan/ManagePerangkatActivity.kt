package com.example.percobaan

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_manage_perangkat.*
import org.json.JSONObject

class ManagePerangkatActivity : AppCompatActivity() {
    lateinit var i: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_perangkat)

        i=intent

        if(i.hasExtra("editmode")) {
            if(i.getStringExtra("editmode").equals("1")) {
                onEditMode()
            }
        }

        btnCreate.setOnClickListener {
            create()
        }
        btnUpdate.setOnClickListener {
            update()
        }

        btnDelete.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Konfirmasi")
                .setMessage("Hapus data ini ?")
                .setPositiveButton("HAPUS", DialogInterface.OnClickListener { dialogInterface, i ->
                    delete()
                })
                .setNegativeButton("BATAL", DialogInterface.OnClickListener { dialogInterface, i ->
                    dialogInterface.dismiss()
                })
                .show()
        }

    }

    private fun onEditMode() {
        txNama.setText(i.getStringExtra("Nama"))
        txEmail.setText(i.getStringExtra("Email"))
        txNoKartu.setText(i.getStringExtra("No Kartu"))
        txNoPerangkat.setText(i.getStringExtra("No Perangkat"))
        txUsername.setText(i.getStringExtra("Username"))
        txPassword.setText(i.getStringExtra("Password"))
        txEmail.isEnabled = false

        btnCreate.visibility = View.GONE
        btnUpdate.visibility = View.VISIBLE
        btnDelete.visibility = View.VISIBLE
    }

    private fun create() {
        val loading = ProgressDialog(this)
        loading.setMessage("Menambahkan data...")
        loading.show()

        AndroidNetworking.post(ApiEndPoint.CREATE)
            .addBodyParameter("Nama", txNama.text.toString())
            .addBodyParameter("Email", txEmail.text.toString())
            .addBodyParameter("No Kartu", txNoKartu.text.toString())
            .addBodyParameter("No Perangkat", txNoPerangkat.text.toString())
            .addBodyParameter("Username", txUsername.text.toString())
            .addBodyParameter("Password", txPassword.text.toString())
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject?) {
                    loading.dismiss()
                    Toast.makeText(applicationContext, response?.getString("message"),
                        Toast.LENGTH_SHORT).show()

                    if (response?.getString("message")?.contains("successfully")!!) {
                        this@ManagePerangkatActivity.finish()
                    }
                }

                override fun onError(anError: ANError?) {
                    loading.dismiss()

                    Log.d("ONERROR", anError?.errorDetail?.toString())
                    Toast.makeText(applicationContext, "Connection Failure",
                        Toast.LENGTH_SHORT).show()
                }

            })
    }

    private fun update(){

        val loading = ProgressDialog(this)
        loading.setMessage("Mengubah data...")
        loading.show()

        AndroidNetworking.post(ApiEndPoint.UPDATE)
            .addBodyParameter("Nama", txNama.text.toString())
            .addBodyParameter("Email", txEmail.text.toString())
            .addBodyParameter("No Kartu", txNoKartu.text.toString())
            .addBodyParameter("No Perangkat", txNoPerangkat.text.toString())
            .addBodyParameter("Username", txUsername.text.toString())
            .addBodyParameter("Password", txPassword.text.toString())
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {

                override fun onResponse(response: JSONObject?) {

                    loading.dismiss()
                    Toast.makeText(applicationContext,response?.getString("message"),Toast.LENGTH_SHORT).show()

                    if(response?.getString("message")?.contains("successfully")!!){
                        this@ManagePerangkatActivity.finish()
                    }

                }

                override fun onError(anError: ANError?) {
                    loading.dismiss()
                    Log.d("ONERROR",anError?.errorDetail?.toString())
                    Toast.makeText(applicationContext,"Connection Failure", Toast.LENGTH_SHORT).show()                    }


            })

    }

    private fun delete(){

        val loading = ProgressDialog(this)
        loading.setMessage("Menghapus data...")
        loading.show()

        AndroidNetworking.get(ApiEndPoint.DELETE+"?Email="+txEmail.text.toString())
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {

                override fun onResponse(response: JSONObject?) {

                    loading.dismiss()
                    Toast.makeText(applicationContext,response?.getString("message"),Toast.LENGTH_SHORT).show()

                    if(response?.getString("message")?.contains("successfully")!!){
                        this@ManagePerangkatActivity.finish()
                    }

                }

                override fun onError(anError: ANError?) {
                    loading.dismiss()
                    Log.d("ONERROR",anError?.errorDetail?.toString())
                    Toast.makeText(applicationContext,"Connection Failure", Toast.LENGTH_SHORT).show()                    }


            })

    }



}
