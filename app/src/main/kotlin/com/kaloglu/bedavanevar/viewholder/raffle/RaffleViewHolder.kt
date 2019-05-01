package com.kaloglu.bedavanevar.viewholder.raffle

import android.view.View
import com.kaloglu.bedavanevar.domain.model.Raffle
import com.kaloglu.bedavanevar.utils.adapter.BaseViewHolder
import kotlinx.android.synthetic.main.raffle_list_item.view.*

class RaffleViewHolder(itemView: View) : BaseViewHolder<Raffle>(itemView) {
    override fun bindItem(item: Raffle) {
        itemView.apply {
            raffleName.text = item.title
            raffleMoreButton.setOnClickListener { onViewClick.invoke(item, it) }
        }
    }

}