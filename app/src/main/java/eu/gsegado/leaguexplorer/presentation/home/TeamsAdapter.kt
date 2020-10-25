package eu.gsegado.leaguexplorer.presentation.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import eu.gsegado.leaguexplorer.R
import eu.gsegado.leaguexplorer.domain.entities.Team
import kotlinx.android.synthetic.main.team_item.view.*

class TeamsAdapter(private val listener: (Team) -> Unit) : RecyclerView.Adapter<TeamsAdapter.ViewHolder>() {

    private var items: List<Team> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.team_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        items[position].let { teamItem ->
            holder.bind(teamItem)
            holder.itemView.setOnClickListener { listener(teamItem) }
        }
    }

    fun setItems(newItems: List<Team>) {
        if (newItems.isNotEmpty()) {
            items = newItems
        }
        notifyDataSetChanged()
    }

    fun clear() {
        items = emptyList()
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Team) {
            Glide
                .with(itemView.context)
                .load(item.badgeUrl)
                .centerInside()
                .placeholder(R.drawable.missing)
                .into(itemView.team_icon)
        }
    }

    interface OnItemClickListener {
        fun onItemClick(item: Team?)
    }

}