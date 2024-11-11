package com.dicoding.asclepius.view.history

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.asclepius.R
import com.dicoding.asclepius.databinding.ActivityHistoryBinding

class HistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHistoryBinding

    private val historyViewModel: HistoryViewModel by viewModels {
        HistoryViewModelFactory.getInstance(this)
    }

    private lateinit var historyAdapter: HistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.includeToolbar.toolbar)
        supportActionBar?.title = "History"

        setupRecyclerView()
        observeViewModels()

    }

    private fun observeViewModels() {

        historyViewModel.history.observe(this) { history ->
            historyAdapter.submitList(history)

            val imgEmpty = findViewById<ImageView>(R.id.img_empty)
            val recyclerView = findViewById<RecyclerView>(R.id.rv_history)
            if (history.isNullOrEmpty()) {
                imgEmpty?.visibility = View.VISIBLE
                recyclerView?.visibility = View.GONE
            } else {
                imgEmpty?.visibility = View.GONE
                recyclerView?.visibility = View.VISIBLE
            }
        }

    }

    private fun setupRecyclerView() {

        val recyclerView = findViewById<RecyclerView>(R.id.rv_history)
        recyclerView.layoutManager = LinearLayoutManager(this)

        historyAdapter = HistoryAdapter(onDeleteClick = { item ->
            historyViewModel.deleteHistory(item)
            Toast.makeText(this, "Item deleted", Toast.LENGTH_SHORT).show()
        })

        recyclerView.adapter = historyAdapter

    }


}