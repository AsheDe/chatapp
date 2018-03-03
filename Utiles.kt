package cu.bellalogica.chat

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.preference.PreferenceManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.widget.Toast

/**
 * Created by Administrador on 08/02/2018.
 */
object Utiles {

    fun AbrirFileManager(contexto: Context, actividad: AppCompatActivity, PERMISSION_REQUEST_CODE: Int) {
        val permissionCheck = ContextCompat.checkSelfPermission(contexto, Manifest.permission.READ_EXTERNAL_STORAGE)
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(actividad, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), PERMISSION_REQUEST_CODE)
        } else {
            // Your app already has the permission to access files and folders
            // so you can simply open FileChooser here.
            try {
                val dialog = FileChooserDialog.Builder(FileChooserDialog.ChooserType.FILE_CHOOSER, object : FileChooserDialog.ChooserListener {
                    override fun onSelect(path: String) {
                        //basedatos = new Manejo_DB(contexto,path);
                        //PreferenceManager.getDefaultSharedPreferences(contexto).edit().putString("dir_base_datos",path).apply();
                        Toast.makeText(contexto, "Has seleccionado" + path, Toast.LENGTH_SHORT).show()
                        //Cursor cur = null;
                        /*  if(basedatos.basededatosOK)
                                    cur = basedatos.SugerenciasdeCodigo(busqueda.getQuery().toString());

                                CargadorSugerencias  cargadorsugerencias = new CargadorSugerencias(contexto,basedatos, cur);
                                busqueda.setSuggestionsAdapter(cargadorsugerencias);*/

                    }
                }).build()
                dialog.show(actividad.supportFragmentManager, null)
                //return true;
            } catch (e: Exception) {
                Toast.makeText(contexto, e.message, Toast.LENGTH_LONG).show()
                //return false;
            }

        }
    }
}
