package com.kaloglu.bedavanevar.viewholder.raffle

import android.view.View
import com.kaloglu.bedavanevar.domain.model.Raffle
import com.kaloglu.bedavanevar.utils.adapter.BaseViewHolder
import com.kaloglu.bedavanevar.utils.extensions.toCompactHtml
import com.kaloglu.bedavanevar.utils.extensions.toFormattedDate
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.raffle_list_item.view.*

class RaffleViewHolder(itemView: View) : BaseViewHolder<Raffle>(itemView) {
    override fun bindItem(item: Raffle) {
        itemView.apply {
            Picasso.get().load(item.productInfo?.images?.get(0)?.path).into(rafflePreviewImage)

            raffleName.text = item.title?.toCompactHtml()
            raffleDescription.text = item.description?.toCompactHtml()
            productCount.text = item.productInfo?.count?.toCompactHtml()
            productUnit.text = item.productInfo?.unit?.toCompactHtml()
            productName.text = item.productInfo?.name?.toCompactHtml()

            startDate.text = item.startDate?.toDate()?.time?.toFormattedDate()
            endDate.text = item.endDate?.toDate()?.time?.toFormattedDate()


            raffleMoreButton.setOnClickListener { onViewClick.invoke(item, it) }
        }
    }

}