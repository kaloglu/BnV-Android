package com.kaloglu.bedavanevar.adapter.raffle

import android.view.ViewGroup
import com.kaloglu.bedavanevar.R
import com.kaloglu.bedavanevar.domain.model.Raffle
import com.kaloglu.bedavanevar.utils.adapter.BaseRecyclerAdapter
import com.kaloglu.bedavanevar.utils.extensions.inflate
import com.kaloglu.bedavanevar.viewholder.raffle.RaffleViewHolder

class RaffleListAdapter : BaseRecyclerAdapter<Raffle, RaffleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            RaffleViewHolder(parent.inflate(R.layout.raffle_list_item, false))

}
