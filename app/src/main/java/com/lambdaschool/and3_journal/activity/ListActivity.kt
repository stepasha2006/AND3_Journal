package com.lambdaschool.and3_journal.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.lambdaschool.and3_journal.EntryListAdapter
import com.lambdaschool.and3_journal.R
import com.lambdaschool.and3_journal.model.Entry

import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.android.synthetic.main.content_list.*

class ListActivity : AppCompatActivity() {

    private val entries = mutableListOf<Entry>()
    private val adapter = EntryListAdapter(entries)

    companion object {
        const val NEW_ENTRY_REQUEST = 65498
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        setSupportActionBar(toolbar)

        // S01M03-4 create intent to start details activity and wait for result
        fab.setOnClickListener { view ->
            val intent = Intent(this, DetailsActivity::class.java)
            startActivityForResult(intent,
                NEW_ENTRY_REQUEST
            )
        }

        list_layout.setHasFixedSize(false)
//        val manager = StaggeredGridLayoutManager(3, RecyclerView.VERTICAL)
        val manager = LinearLayoutManager(this)

        list_layout.layoutManager = manager
        list_layout.adapter = adapter

        addSampleEntries()
    }

    fun addSampleEntries() {
        for(i in 0..20) {
            entries.add(Entry())
        }
        adapter.notifyDataSetChanged()
    }

    /**
     * Dispatch incoming result to the correct fragment.
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        // S01M03-5 process result from the details activity
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                NEW_ENTRY_REQUEST -> {
                    /*val date = data?.getStringExtra(DetailsActivity.DATE_KEY) ?: "Jan 1, 1970"
                    val rating = data?.getIntExtra(DetailsActivity.RATING_KEY, 0)
                    // TODO: this line is returning null, is the text too long?
                    val text = data?.getStringExtra(DetailsActivity.TEXT_KEY) ?: "No Text"

                    Toast.makeText(this, "$date - $rating - $text", Toast.LENGTH_SHORT).show()*/

                    val entry = data?.getSerializableExtra(DetailsActivity.ENTRY_KEY) as Entry
                    Toast.makeText(this, "$entry", Toast.LENGTH_SHORT).show()
                    entries.add(entry)
                    adapter.notifyDataSetChanged()
                }
            }
        }

        super.onActivityResult(requestCode, resultCode, data)
    }
}
