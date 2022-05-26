package com.rnk0085.android.memo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rnk0085.android.memo.database.memo.Memo
import com.rnk0085.android.memo.databinding.MemoItemBinding
import java.text.SimpleDateFormat
import java.util.*

class MemoListAdapter(
    private val onItemClicked: (Memo) -> Unit
) : ListAdapter<Memo, MemoListAdapter.MemoViewHolder>(DiffCallback) {

    class MemoViewHolder(
        private var binding: MemoItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(memo: Memo) {
            val sdf = SimpleDateFormat("yyyy/MM/dd(EEE)", Locale.JAPAN)
            sdf.timeZone = TimeZone.getTimeZone("Asia/Tokyo")

            binding.apply {
                memoTitle.text = memo.title
                dateText.text = sdf.format(memo.updatedAt)
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

    companion object DiffCallback : DiffUtil.ItemCallback<Memo>() {
        override fun areItemsTheSame(oldItem: Memo, newItem: Memo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Memo, newItem: Memo): Boolean {
            return oldItem == newItem
        }

    }
}
