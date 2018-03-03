package cu.bellalogica.chat

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.GregorianCalendar

/**
 * Created by dexter on 03/02/2018.
 */
class Mensaje {
    var nombre_sender: String
    var fecha: Date
    var mensaje_texto: String

    constructor(nombre_sender: String, mensaje_texto: String) {
        this.nombre_sender = nombre_sender
        this.mensaje_texto = mensaje_texto
        this.fecha = Date()

    }

    constructor(nombre_sender: String, fecha: Date, mensaje_texto: String) {
        this.nombre_sender = nombre_sender
        this.fecha = fecha
        this.mensaje_texto = mensaje_texto
    }

    fun FechaFormateada(): String {
        val dateformat = "dd-MM-yyyy"
        val f = SimpleDateFormat(dateformat)
        return f.format(fecha)
    }


}
