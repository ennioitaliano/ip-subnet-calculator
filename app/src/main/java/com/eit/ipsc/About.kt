package com.eit.ipsc

import android.annotation.TargetApi
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.support.design.widget.CollapsingToolbarLayout
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.TextView

import android.text.Layout.JUSTIFICATION_MODE_INTER_WORD

class About : AppCompatActivity() {

    private var ctlAb: CollapsingToolbarLayout? = null
    @TargetApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.about)

        val toolbar = findViewById<Toolbar>(R.id.tool_bar)
        setSupportActionBar(toolbar)
        toolbar.setTitleTextColor(Color.WHITE)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        ctlAb = findViewById(R.id.ctlAb)
        ctlAb!!.title = getString(R.string.informazioni) //TITOLO WHERE U AT?

        val testo = findViewById<TextView>(R.id.testo)
        testo.justificationMode = JUSTIFICATION_MODE_INTER_WORD
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    fun email(view: View) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/ennioitaliano/IPSC")))
    }

    fun playDev(view: View) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/ennioitaliano")))
    }
}
