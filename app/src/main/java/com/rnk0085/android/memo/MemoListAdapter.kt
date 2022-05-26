package com.rnk0085.android.memo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rnk0085.android.memo.database.memo.MemoEntity
import com.rnk0085.android.memo.databinding.MemoItemBinding
import java.text.SimpleDateFormat
import java.util.*

class MemoListAdapter(
    private val onItemClicked: (MemoEntity) -> Unit
) : ListAdapter<MemoEntity, MemoListAdapter.MemoViewHolder>(DiffCallback) {

    class MemoViewHolder(
        private var binding: MemoItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(memoEntity: MemoEntity) {
            val sdf = SimpleDateFormat("yyyy/MM/dd(EEE)", Locale.JAPAN)
            sdf.timeZone = TimeZone.getTimeZone("Asia/Tokyo")

            binding.apply {
                memoTitle.text = memoEntity.title
                dateText.text = sdf.format(memoEntity.updatedAt)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): MemoViewHolder {
        return MemoViewHolder(
            MemoItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: MemoViewHolder, position: Int) {
        val current = getItem(position)

        holder.itemView.setOnClickListener {
            onItemClicked(current)
        }

        holder.bind(current)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<MemoEntity>() {
        override fun areItemsTheSame(oldItem: MemoEntity, newItem: MemoEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MemoEntity, newItem: MemoEntity): Boolean {
            return oldItem == newItem
        }

    }
}
