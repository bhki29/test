package com.dicoding.asclepius.view.history

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.asclepius.R
import com.dicoding.asclepius.data.local.entity.HistoryEntity
import com.dicoding.asclepius.databinding.ItemHistoryBinding
import com.dicoding.asclepius.util.LoadImage

class HistoryAdapter(private val onDeleteClick: (HistoryEntity) -> Unit) : ListAdapter<HistoryEntity, HistoryAdapter.HistoryViewHolder>(
    DIFF_CALLBACK
) {

    class HistoryViewHolder(private val binding: ItemHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(history: HistoryEntity, onDeleteClick: (HistoryEntity) -> Unit) {
            val title = history.title
            val image = history.mediaCover
            val dateTime = history.date
            val inference = history.inference

            binding.btnDelete.setOnClickListener {
                onDeleteClick(history)
            }

            binding.tvHistory.text = "Hasil : $title"
            binding.tvInferensi.text = "Waktu Inferensi : $inference ms"
            binding.tvTime.text = "Waktu Saat Di Analisis : $dateTime"

            LoadImage.load(
                itemView.context,
                binding.imgPicture,
                image, R.color.placeholder,
            )

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val historyItem = getItem(position)
        if (historyItem != null) {
            holder.bind(historyItem, onDeleteClick)
        }
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<HistoryEntity> =
            object : DiffUtil.ItemCallback<HistoryEntity>() {
                override fun areItemsTheSame(
                    oldItem: HistoryEntity,
                    newItem: HistoryEntity
                ): Boolean {
                    return oldItem.id == newItem.id
                }

                @SuppressLint("DiffUtilEquals")
                override fun areContentsTheSame(
                    oldItem: HistoryEntity,
                    newItem: HistoryEntity
                ): Boolean {
                    return oldItem == newItem
                }
            }
    }
}