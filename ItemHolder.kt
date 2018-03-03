package cu.bellalogica.chat

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView

internal class ItemHolder(itemView: View, private val itemClickListener: ItemHolder.OnItemClickListener) : RecyclerView.ViewHolder(itemView) {
    private val ivItemIcon: ImageView
    private val tvItemName: TextView
    private val cbFile: CheckBox

    internal interface OnItemClickListener {
        fun onItemClick(item: Item)
    }

    init {

        ivItemIcon = itemView.findViewById(R.id.item_icon_imageview) as ImageView
        tvItemName = itemView.findViewById(R.id.item_name_textview) as TextView
        cbFile = itemView.findViewById(R.id.file_checkbox) as CheckBox
    }

    fun bind(item: Item) {
        tvItemName.text = item.name
        ivItemIcon.setImageDrawable(item.icon)
        itemView.setOnClickListener { itemClickListener.onItemClick(item) }
    }

    fun bind(item: Item, selectedItems: MutableList<Item>) {
        tvItemName.text = item.name
        ivItemIcon.setImageDrawable(item.icon)
        cbFile.visibility = View.VISIBLE
        val onClickListener = View.OnClickListener {
            if (cbFile.isChecked) {
                selectedItems.remove(item)
                cbFile.isChecked = false
            } else {
                selectedItems.add(item)
                cbFile.isChecked = true
            }
        }
        cbFile.setOnClickListener(onClickListener)
        itemView.setOnClickListener(onClickListener)
    }
}
