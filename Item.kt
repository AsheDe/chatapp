package cu.bellalogica.chat

import android.graphics.drawable.Drawable

import java.io.File

internal class Item(path: String, val icon: Drawable) : File(path), Comparable<File> {

    // We want our list to show directories first and then show files below them
    // so we need to sort our items. Since Collections.sort() sorts items in ascending order
    // we should assume that directories are smaller than files.
    override fun compareTo(pathname: File): Int {
        if (isDirectory && pathname.isFile) {
            return -1
        }
        if (isFile && pathname.isDirectory) {
            return 1
        }
        return 0
    }
}
