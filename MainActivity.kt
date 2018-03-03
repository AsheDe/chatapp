package cu.bellalogica.chat

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.preference.PreferenceManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SimpleItemAnimator
import android.support.v7.widget.StaggeredGridLayoutManager
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

import java.util.ArrayList

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupToolBar()
        setupRecycler_Lista_Usuarios()
        setupVer_Mensajes_Nuevos()
        findViewById(R.id.archivos_nube)!!.setOnClickListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_mainactivity, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
        /*case android.R.id.home: {
               // onBackPressed();
                break;
            }*/
            R.id.menu_configuracion -> {
            }/// run search /// realizar busqueda entre los mensajes y resaltar la palabra (highlight en amarillo)
        }


        return true

    }

    fun setupToolBar() {
        val toolbar = findViewById(R.id.toolbar) as Toolbar?
        toolbar!!.setTitleTextColor(resources.getColor(R.color.colorBlanco))
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(true)
        supportActionBar!!.setTitle(resources.getString(R.string.app_name))

    }

    fun setupRecycler_Lista_Usuarios() {
        val usuarios = ArrayList<String>()
        usuarios.add("Yanela")
        usuarios.add("Yordi")
        usuarios.add("maikel")
        usuarios.add("abel")
        usuarios.add("Jorgito")
        usuarios.add("Natanael")
        usuarios.add("Debby")
        usuarios.add("Numancia")
        usuarios.add("Ike")

        val adaptador_usuarios = ListaUsuarios_Adapter(usuarios, this, this)
        val list_autores = findViewById(R.id.recycler_list_usuarios) as RecyclerView?
        val columnas = resources.getInteger(R.integer.columnas)
        val mLayoutManager = StaggeredGridLayoutManager(columnas, StaggeredGridLayoutManager.VERTICAL)
        mLayoutManager.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
        //RecyclerView.LayoutManager mangr = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        list_autores!!.layoutManager = mLayoutManager
        list_autores.adapter = adaptador_usuarios

    }

    fun setupVer_Mensajes_Nuevos() {
        findViewById(R.id.imgnewmessage)!!.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.imgnewmessage -> {
                val vermensajesnuevos = Intent(baseContext, Mensajes_nuevos::class.java)
                startActivity(vermensajesnuevos)
            }
            R.id.archivos_nube -> {
                Utiles.AbrirFileManager(this, this, PERMISSION_REQUEST_CODE)
            }
        }
    }

    inner class ListaUsuarios_Adapter(internal var mUsuarios: ArrayList<String>, internal var actividad: AppCompatActivity, internal var contexto: Context) : RecyclerView.Adapter<*>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val v: View
            v = LayoutInflater.from(parent.context).inflate(R.layout.item_usuario_conectado, parent, false)
            val vh = Item_Autor(v)
            return vh
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val vhold = holder as Item_Autor
            vhold.nombre.text = mUsuarios[position]
            vhold.subirfile.setOnClickListener { Utiles.AbrirFileManager(contexto, actividad, PERMISSION_REQUEST_CODE) }
            vhold.itemView.setOnClickListener {
                /// crear libro y enviar intent
                val chatear = Intent(baseContext, Chatear_Ventana::class.java)
                chatear.putExtra("usuario", mUsuarios[position])
                startActivity(chatear)
                overridePendingTransition(R.anim.desplazamiento_lateral, 0)
            }
        }

        override fun getItemCount(): Int {
            return mUsuarios.size
        }


        inner class Item_Autor(itemView: View) : RecyclerView.ViewHolder(itemView) {

            internal var subirfile: ImageView
            internal var nombre: TextView

            init {

                subirfile = itemView.findViewById(R.id.subir_archivo) as ImageView
                nombre = itemView.findViewById(R.id.nombre_usuario) as TextView
            }
        }
    }

    companion object {

        private val PERMISSION_REQUEST_CODE = 234
    }
}
