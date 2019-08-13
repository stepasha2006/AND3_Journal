package com.lambdaschool.and3_journal.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lambdaschool.and3_journal.R
import com.lambdaschool.and3_journal.model.Entry
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import android.widget.Toast
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener



class DetailsActivity : AppCompatActivity() {

    val entry = Entry()

    companion object {
        const val ENTRY_KEY = "ENTRY_KEY"
        const val DATE_KEY = "ENTRY_DATE_KEY"
        const val RATING_KEY = "ENTRY_RATING_NUMBER"
        const val TEXT_KEY = "ENTRY_TEXT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // S01M03-1 - prep views
//        date_text_view.text = Date().toString()
        day_rating_bar.max = 4
        day_rating_bar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                // TODO Auto-generated method stub
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // TODO Auto-generated method stub
            }

            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                entry.rating = progress
            }
        });
    }

    /**
     * Dispatch onResume() to fragments.  Note that for better inter-operation
     * with older versions of the platform, at the point of this call the
     * fragments attached to the activity are *not* resumed.  This means
     * that in some cases the previous state may still be saved, not allowing
     * fragment transactions that modify the state.  To correctly interact
     * with fragments in their proper state, you should instead override
     * [.onResumeFragments].
     */
    override fun onResume() {
        super.onResume()
        date_text_view.text = entry.dateStamp.toString()
        day_rating_bar.progress = entry.rating
        text_entry.setText(entry.text)
    }

    // S01M03-2 override back button
    override fun onBackPressed() {
        /*val date = date_text_view.text
        val rating = day_rating_bar.progress
        val text = text_entry.text

        // S01M03-3 create intent for result
        val resultIntent = Intent()

        resultIntent.putExtra(DATE_KEY, date)
        resultIntent.putExtra(RATING_KEY, rating)
        resultIntent.putExtra(TEXT_KEY, text)

        setResult(Activity.RESULT_OK, resultIntent)
        finish() // destroy the activity*/

        entry.rating = day_rating_bar.progress
        entry.text = text_entry.text.toString()

        // S01M03-3 create intent for result
        val resultIntent = Intent()

        resultIntent.putExtra(ENTRY_KEY, entry)

        setResult(Activity.RESULT_OK, resultIntent)
        finish() // destroy the activity
    }
}
