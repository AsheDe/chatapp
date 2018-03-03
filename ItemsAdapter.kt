package cu.bellalogica.chat

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

import java.util.ArrayList

internal class ItemsAdapter(private val itemClickListener: ItemHolder.OnItemClickListener, private val multipleFileSelectionEnabled: Boolean) : RecyclerView.Adapter<ItemHolder>() {
    private var items: List<Item>? = null
    private var selectedItems: MutableList<Item>? = null

    init {
        items = ArrayList<Item>()
        if (multipleFileSelectionEnabled) {
            selectedItems = ArrayList<Item>()
        }
    }

    fun setItems(items: List<Item>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ItemHolder(inflater.inflate(R.layout.list_item, parent, false), itemClickListener)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val item = items!![position]
        if (item.isFile && multipleFileSelectionEnabled) {
            holder.bind(items!![position], selectedItems!!)
        } else {
            holder.bind(items!![position])
        }
    }

    override fun getItemCount(): Int {
        return items!!.size
    }
}
