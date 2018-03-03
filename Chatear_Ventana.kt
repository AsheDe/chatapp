package cu.bellalogica.chat

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import java.util.ArrayList

class Chatear_Ventana : AppCompatActivity() {

    internal var mListamensajes: ArrayList<Mensaje>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actividad_chatear__ventana)
        val mChat_with = intent.getStringExtra("usuario")
        setupToolBar(mChat_with)
        AdquirirMensajes(mChat_with)
        Visualizar_Mensajes()
        val contexto = this
        findViewById(R.id.eliminar_historial)!!.setOnClickListener {
            val dialogo = AlertDialog.Builder(contexto)
            dialogo.setPositiveButton("BORRAR") { dialog, which -> dialog.dismiss() }.setNegativeButton("CANCELAR") { dialog, which -> dialog.dismiss() }.setMessage("Está seguro que desea BORRAR los mensajes en el historial de conversaciones con este usuario?")
            dialogo.create().show()
        }
    }

    fun Visualizar_Mensajes() {
        val adaptador_mensajes_nuevos = Lista_Mensajes_EntreUsers(mListamensajes)
        val list_mensajes = findViewById(R.id.recycler_mensajes_entre_usuarios) as RecyclerView?
        val mLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        list_mensajes!!.layoutManager = mLayoutManager
        list_mensajes.adapter = adaptador_mensajes_nuevos
        list_mensajes.layoutManager.smoothScrollToPosition(list_mensajes, null, mListamensajes.size - 1)
    }

    fun AdquirirMensajes(mChat_with: String) {
        //// coger los mensajes de la B.D  y ponerlos en lista de mensajes
        mListamensajes = ArrayList<Mensaje>()
        mListamensajes.add(Mensaje(mChat_with, "este es el mensaje que se escribio"))
        mListamensajes.add(Mensaje(mChat_with, "este es el mensaje que se escribio"))
        mListamensajes.add(Mensaje(mChat_with, "este es el mensaje que se escribio"))
        mListamensajes.add(Mensaje("minombre", "este es el mensaje que se escribio"))
        mListamensajes.add(Mensaje("minombre", "este es el mensaje que se escribio"))
        mListamensajes.add(Mensaje(mChat_with, "este es el mensaje que se escribio"))
        mListamensajes.add(Mensaje(mChat_with, "este es el mensaje que se escribio"))
        mListamensajes.add(Mensaje(mChat_with, "este es el mensaje que se escribio"))
        mListamensajes.add(Mensaje("minombre", "este es el mensaje que se escribio"))
        mListamensajes.add(Mensaje("minombre", "este es el mensaje que se escribio"))
        mListamensajes.add(Mensaje("minombre", "este es el mensaje que se escribio"))
        mListamensajes.add(Mensaje(mChat_with, "este es el mensaje que se escribio"))
        mListamensajes.add(Mensaje(mChat_with, "este es el mensaje que se escribio"))
        mListamensajes.add(Mensaje("minombre", "este es el mensaje que se escribio"))
        mListamensajes.add(Mensaje(mChat_with, "este es el mensaje que se escribio"))
        mListamensajes.add(Mensaje(mChat_with, "este es el mensaje que se escribio"))
        mListamensajes.add(Mensaje(mChat_with, "este es el mensaje que se escribio"))
        mListamensajes.add(Mensaje(mChat_with, "este es el mensaje que se escribio"))
        mListamensajes.add(Mensaje("minombre", "este es el mensaje que se escribio"))
        mListamensajes.add(Mensaje("minombre", "este es el mensaje que se escribio"))
        mListamensajes.add(Mensaje("minombre", "este es el mensaje que se escribio"))
        mListamensajes.add(Mensaje(mChat_with, "este es el mensaje que se escribio"))
        mListamensajes.add(Mensaje(mChat_with, "este es el mensaje que se escribio"))
        mListamensajes.add(Mensaje("minombre", "este es el mensaje que se escribio"))
        mListamensajes.add(Mensaje(mChat_with, "este es el mensaje que se escribio"))
        mListamensajes.add(Mensaje(mChat_with, "este es el mensaje que se escribio"))
        mListamensajes.add(Mensaje(mChat_with, "este es el mensaje que se escribio"))
        mListamensajes.add(Mensaje(mChat_with, "este es el mensaje que se escribio"))
        mListamensajes.add(Mensaje("minombre", "este es el mensaje que se escribio"))
        mListamensajes.add(Mensaje("minombre", "este es el mensaje que se escribio"))
        mListamensajes.add(Mensaje(mChat_with, "este es el mensaje que se escribio"))
        mListamensajes.add(Mensaje(mChat_with, "este es el mensaje que se escribio"))
        mListamensajes.add(Mensaje(mChat_with, "este es el mensaje que se escribio"))
        mListamensajes.add(Mensaje("minombre", "este es el mensaje que se escribio"))
        mListamensajes.add(Mensaje("minombre", "este es el mensaje que se escribio"))
        mListamensajes.add(Mensaje("minombre", "este es el mensaje que se escribio"))
        mListamensajes.add(Mensaje(mChat_with, "este es el mensaje que se escribio"))
        mListamensajes.add(Mensaje(mChat_with, "este es el mensaje que se escribio"))
        mListamensajes.add(Mensaje("minombre", "este es el mensaje que se escribio"))
        mListamensajes.add(Mensaje(mChat_with, "este es el mensaje que se escribio"))
        mListamensajes.add(Mensaje(mChat_with, "este es el mensaje que se escribio"))
        mListamensajes.add(Mensaje(mChat_with, "este es el mensaje que se escribio"))
        mListamensajes.add(Mensaje(mChat_with, "este es el mensaje que se escribio"))
        mListamensajes.add(Mensaje("minombre", "este es el mensaje que se escribio"))
        mListamensajes.add(Mensaje("minombre", "este es el mensaje que se escribio"))
        mListamensajes.add(Mensaje("minombre", "este es el mensaje que se escribio"))
        mListamensajes.add(Mensaje(mChat_with, "este es el mensaje que se escribio"))
        mListamensajes.add(Mensaje(mChat_with, "este es el mensaje que se escribio"))
        mListamensajes.add(Mensaje("minombre", "este es el mensaje que se escribio"))
        mListamensajes.add(Mensaje(mChat_with, "este es el mensaje que se escribio"))
        mListamensajes.add(Mensaje(mChat_with, "este es el mensaje que se escribio"))
        mListamensajes.add(Mensaje(mChat_with, "este es el mensaje que se escribio"))
        mListamensajes.add(Mensaje(mChat_with, "este es el mensaje que se escribio"))
        mListamensajes.add(Mensaje("minombre", "este es el mensaje que se escribio"))
        mListamensajes.add(Mensaje("minombre", "este es el mensaje que se escribio"))
        mListamensajes.add(Mensaje(mChat_with, "este es el mensaje que se escribio"))
        mListamensajes.add(Mensaje(mChat_with, "este es el mensaje que se escribio"))
        mListamensajes.add(Mensaje(mChat_with, "este es el mensaje que se escribio"))
        mListamensajes.add(Mensaje("minombre", "este es el mensaje que se escribio"))
        mListamensajes.add(Mensaje("minombre", "este es el mensaje que se escribio"))
        mListamensajes.add(Mensaje("minombre", "este es el mensaje que se escribio"))
        mListamensajes.add(Mensaje(mChat_with, "este es el mensaje que se escribio"))
        mListamensajes.add(Mensaje(mChat_with, "este es el mensaje que se escribio"))
        mListamensajes.add(Mensaje("minombre", "este es el mensaje que se escribio"))
        mListamensajes.add(Mensaje(mChat_with, "este es el mensaje que se escribio"))
        mListamensajes.add(Mensaje(mChat_with, "este es el mensaje que se escribio"))
        mListamensajes.add(Mensaje(mChat_with, "este es el mensaje que se escribio"))
        mListamensajes.add(Mensaje(mChat_with, "este es el mensaje que se escribio"))
        mListamensajes.add(Mensaje("minombre", "este es el mensaje que se escribio"))
        mListamensajes.add(Mensaje("minombre", "este es el mensaje que se escribio"))
        mListamensajes.add(Mensaje("minombre", "este es el mensaje que se escribio"))
        mListamensajes.add(Mensaje(mChat_with, "este es el mensaje que se escribio"))
        mListamensajes.add(Mensaje(mChat_with, "este es el mensaje que se escribio"))
        mListamensajes.add(Mensaje("minombre", "este es el mensaje que se escribio"))
        mListamensajes.add(Mensaje(mChat_with, "este es el mensaje que se escribio"))
    }

    fun setupToolBar(mChat_with: String) {
        val toolbar = findViewById(R.id.toolbar) as Toolbar?
        toolbar!!.setTitleTextColor(resources.getColor(R.color.colorBlanco))
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(true)
        supportActionBar!!.setTitle(mChat_with)
        supportActionBar!!.setIcon(resources.getDrawable(R.drawable.message))

    }

    inner class Lista_Mensajes_EntreUsers(internal var mMensajes: ArrayList<Mensaje>) : RecyclerView.Adapter<*>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val vi: View
            if (viewType == 0) {
                vi = LayoutInflater.from(parent.context).inflate(R.layout.item_mensaje_derecho, parent, false)
            } else {
                vi = LayoutInflater.from(parent.context).inflate(R.layout.item_mensaje_izquierdo, parent, false)
            }
            return Item_Mensaje(vi)
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val vhold = holder as Item_Mensaje
            val message = mMensajes[position]
            vhold.nombre_fecha.text = message.nombre_sender + " " + message.FechaFormateada()
            vhold.texto.text = message.mensaje_texto
            vhold.itemView.setOnClickListener {
                /// crear libro y enviar intent
                val chatear = Intent(baseContext, Chatear_Ventana::class.java)
                chatear.putExtra("usuario", message.nombre_sender)
                startActivity(chatear)
            }
        }

        override fun getItemViewType(position: Int): Int {

            return mMensajes[position].nombre_sender.compareTo("minombre")

        }


        override fun getItemCount(): Int {
            return mMensajes.size
        }


        inner class Item_Mensaje(itemView: View) : RecyclerView.ViewHolder(itemView) {

            internal var nombre_fecha: TextView
            internal var texto: TextView

            init {
                nombre_fecha = itemView.findViewById(R.id.mensajedeycuando) as TextView
                texto = itemView.findViewById(R.id.contenido_mensaje) as TextView
            }
        }
    }


}
