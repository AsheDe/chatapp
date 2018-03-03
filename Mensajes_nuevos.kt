package cu.bellalogica.chat

import android.content.Intent
import android.support.v4.app.NavUtils
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import java.util.ArrayList

class Mensajes_nuevos : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actividad_mensajes_nuevos)
        setupToolBar()
        val mensajes = ArrayList<Mensaje>()
        mensajes.add(Mensaje("tati", "mensaje 1 enviado enviado a ti desde mi movilenviado a ti desde mi movila ti desde mi movil"))
        mensajes.add(Mensaje("tati1", "mensaje 2 enviaenviado a ti desde mi movilenviado a ti desde mi movilenviado a ti desde mi movildo a ti desde mi movil"))
        mensajes.add(Mensaje("tati2", "mensaje 3 envienviado a ti desde mi movilenviado a ti desde mi movilenviado a ti desde mi movilado a ti desde mi movil"))
        mensajes.add(Mensaje("tati3", "mensaje 4 envienviado a ti desde mi movilenviado a ti desde mi movilenviado a ti desde mi movilenviado a ti desde mi movilenviado a ti desde mi movilado a ti desde mi movil"))
        mensajes.add(Mensaje("tati4", "mensaje 5 envienviado a ti desde mi movilenviado a ti desde mi movilenviado a ti desde mi movilenviado a ti desde mi movilenviado a ti desde mi movilenviado a ti desde mi movilenviado a ti desde mi movilenviado a ti desde mi movilenviado a ti desde mi movilado a ti desde mi movil"))

        val adaptador_mensajes_nuevos = Lista_Mensajes_Nuevos(mensajes)
        val list_autores = findViewById(R.id.nuevos_mensajes) as RecyclerView?
        val columnas = resources.getInteger(R.integer.columnas)
        // StaggeredGridLayoutManager mLayoutManager = new StaggeredGridLayoutManager(columnas,StaggeredGridLayoutManager.VERTICAL);
        // mLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);
        val mLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        list_autores!!.layoutManager = mLayoutManager
        list_autores.adapter = adaptador_mensajes_nuevos

    }

    fun setupToolBar() {
        val toolbar = findViewById(R.id.toolbar) as Toolbar?
        toolbar!!.setTitleTextColor(resources.getColor(R.color.colorBlanco))
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(true)
        supportActionBar!!.setTitle("Mensajes Nuevos")
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_ventana_chat, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                // onBackPressed();
                NavUtils.navigateUpFromSameTask(this)
            }
            R.id.menu_buscar -> {
            }/// run search /// realizar busqueda entre los mensajes y resaltar la palabra (highlight en amarillo)
        }


        return true

    }


    inner class Lista_Mensajes_Nuevos(internal var mMensajes: ArrayList<Mensaje>) : RecyclerView.Adapter<*>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val v: View
            v = LayoutInflater.from(parent.context).inflate(R.layout.item_mensaje_centro, parent, false)
            val vh = Item_Mensaje(v)
            return vh
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val vhold = holder as Item_Mensaje
            val message = mMensajes[position]
            vhold.nombre_fecha.setText(message.getNombre_sender() + " " + message.FechaFormateada())
            vhold.texto.setText(message.getMensaje_texto())
            vhold.itemView.setOnClickListener {
                /// crear libro y enviar intent
                val chatear = Intent(baseContext, Chatear_Ventana::class.java)
                chatear.putExtra("usuario", message.getNombre_sender())
                startActivity(chatear)
            }
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
