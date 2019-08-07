package com.lambdaschool.and3_journal

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_list.*

class ListActivity : AppCompatActivity() {

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
            startActivityForResult(intent, NEW_ENTRY_REQUEST)
        }
    }

    /**
     * Dispatch incoming result to the correct fragment.
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        // S01M03-5 process result from the details activity
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                NEW_ENTRY_REQUEST -> {
                    val date = data?.getStringExtra(DetailsActivity.DATE_KEY) ?: "Jan 1, 1970"
                    val rating = data?.getIntExtra(DetailsActivity.RATING_KEY, 0)
                    // TODO: this line is returning null, is the text too long?
                    val text = data?.getStringExtra(DetailsActivity.TEXT_KEY) ?: "No Text"

                    Toast.makeText(this, "$date - $rating - $text", Toast.LENGTH_SHORT).show()
                }
            }
        }

        super.onActivityResult(requestCode, resultCode, data)
    }
}
