package com.kaloglu.bedavanevar.viewholder.raffle

import android.view.View
import com.kaloglu.bedavanevar.domain.model.Raffle
import com.kaloglu.bedavanevar.utils.adapter.BaseViewHolder
import com.kaloglu.bedavanevar.utils.extensions.isNotNullOrEmpty
import com.kaloglu.bedavanevar.utils.extensions.toCompactHtml
import com.kaloglu.bedavanevar.utils.extensions.toFormattedDate
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.raffle_list_item.view.*

class RaffleViewHolder(itemView: View) : BaseViewHolder<Raffle>(itemView) {
    override fun bindItem(item: Raffle) {
        itemView.apply {
            item.run {

                raffleName.text = title?.toCompactHtml()
                raffleDescription.text = description?.toCompactHtml()

                textViewStartDate.text = startDate?.toFormattedDate()
                textViewEndDate.text = endDate?.toFormattedDate()

                productInfo?.run {
                    if (images.isNotNullOrEmpty())
                        Picasso.get().load(images?.get(0)?.path).into(rafflePreviewImage)

                    productCount.text = count?.toCompactHtml()
                    productUnit.text = unit?.toCompactHtml()
                    productName.text = name?.toCompactHtml()
                }


            }
            raffleMoreButton.setOnClickListener { onViewClick.invoke(item, it) }
        }
    }

}