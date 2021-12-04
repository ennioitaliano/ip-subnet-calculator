package com.eit.ipsc

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.support.design.widget.CollapsingToolbarLayout
import android.support.design.widget.Snackbar
import android.support.design.widget.TextInputEditText
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_main_calculate.*

import java.util.ArrayList
import java.util.Arrays
import java.util.Objects

open class MainActivity_Calculate : AppCompatActivity() {

    private var ip1: TextInputEditText? = null
    private var ip2: TextInputEditText? = null
    private var ip3: TextInputEditText? = null
    private var ip4: TextInputEditText? = null
    private var maschera: TextInputEditText? = null
    private var ip1Int: Int? = null
    private var ip2Int: Int? = null
    private var ip3Int: Int? = null
    private var ip4Int: Int? = null
    private var mascheraInt: Int? = null
    private var titles: ArrayList<String>? = null
    private var results: ArrayList<String>? = null
    private var arrayTitles: Array<String>? = null
    private var arrayResults: Array<String>? = null
    private var adapter: Adapter? = null
    private var recyclerView: RecyclerView? = null
    private var ctl: CollapsingToolbarLayout? = null
    private var toolbar: Toolbar? = null
    private val PREFS_NAME = "MyPrefsFile"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_calculate)
        setTheme(R.style.AppTheme)

        val settings = getSharedPreferences(PREFS_NAME, 0)

        if (settings.getBoolean("my_first_time", true)) {
            //the app is being launched for first time, do something
            settings.edit().putBoolean("my_first_time", false).commit()
        }

        toolbar = findViewById(R.id.tool_bar)
        setSupportActionBar(toolbar)
        //toolbar.setTitleTextColor(Color.WHITE);
        //Objects.requireNonNull(getSupportActionBar()).setTitle(null);

        val tf = Typeface.createFromAsset(applicationContext.assets, "fonts/googlesansregular.ttf") //!!!!

        ctl = this.findViewById(R.id.ctl)
        //Objects.requireNonNull(ctl).setTitle(null);
        ctl!!.setCollapsedTitleTextAppearance(R.style.ToolbarWhiteCOL)
        ctl!!.setExpandedTitleTextAppearance(R.style.ToolbarWhiteEX)
        ctl!!.setExpandedTitleTypeface(tf)
        ctl!!.setCollapsedTitleTypeface(tf)
        ctl!!.expandedTitleMarginStart = 96
        ctl!!.expandedTitleMarginTop = 200

        //RECYCLER
        recyclerView = findViewById(R.id.recycler)
        recyclerView!!.layoutManager = LinearLayoutManager(this)

        arrayTitles = arrayOf(getString(R.string.indirizzo_di_rete), getString(R.string.indirizzo_ip_inserito), getString(R.string.maschera_di_sottorete_inserita), getString(R.string.primo_indirizzo), getString(R.string.ultimo_indirizzo), getString(R.string.indirizzi_disponibili), getString(R.string.broadcast), getString(R.string.wildcard))

        arrayResults = arrayOf("", "", "", "", "", "", "", "", "")

        for (i in 0..7)
            arrayResults!![i] = getString(R.string.risultati_vuoti)

        titles = ArrayList(Arrays.asList(*arrayTitles!!))
        results = ArrayList(Arrays.asList(*arrayResults!!))

        adapter = Adapter(applicationContext, titles!!, results!!, recyclerView!!)

        recyclerView!!.adapter = adapter
        recyclerView!!.setHasFixedSize(true)

        ip1 = findViewById(R.id.insIp1)
        ip2 = findViewById(R.id.insIp2)
        ip3 = findViewById(R.id.insIp3)
        ip4 = findViewById(R.id.insIp4)
        maschera = findViewById(R.id.insMaschera)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_scrolling, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId

        if (id == R.id.action_info) {
            val intent = Intent(this@MainActivity_Calculate, About::class.java)
            startActivity(intent)
        }

        return super.onOptionsItemSelected(item)
    }

    fun buttonCalcola(v: View) {

        try {
            ip1Int = Integer.parseInt(ip1!!.editableText.toString())
            ip2Int = Integer.parseInt(ip2!!.editableText.toString())
            ip3Int = Integer.parseInt(ip3!!.editableText.toString())
            ip4Int = Integer.parseInt(ip4!!.editableText.toString())
            mascheraInt = Integer.parseInt(maschera!!.editableText.toString())

            val calc = Calcoli(ip1Int, ip2Int, ip3Int, ip4Int, mascheraInt)

            if (ip1Int!! > 255 || ip2Int!! > 255 || ip3Int!! > 255 || ip4Int!! > 255 || mascheraInt!! > 32) {

                val sberr1 = Snackbar.make(currentFocus!!, getString(R.string.errVal), Snackbar.LENGTH_LONG)
                sberr1.setAction(getString(R.string.cancella)) {
                    ip1!!.setText("")
                    ip2!!.setText("")
                    ip3!!.setText("")
                    ip4!!.setText("")
                    maschera!!.setText("")
                    Objects.requireNonNull<CollapsingToolbarLayout>(ctl).title = "IP Subnet Calculator"
                }
                sberr1.show()

            } else {
                Objects.requireNonNull<CollapsingToolbarLayout>(ctl).title = ip1Int.toString() + "." + ip2Int + "." + ip3Int + "." + ip4Int + "/" + mascheraInt

                arrayResults?.set(0, calc.indirizzoRete())
                arrayResults?.set(1, calc.ipBinario())
                arrayResults?.set(2, calc.maschera())
                arrayResults?.set(3, calc.primoIndirizzo())
                arrayResults?.set(4, calc.ultimoInd())
                arrayResults?.set(5, calc.numIndDisp())
                arrayResults?.set(6, calc.broadcast())
                arrayResults?.set(7, calc.wildcard())

                results = ArrayList(Arrays.asList(*arrayResults!!))
                adapter = Adapter(applicationContext, titles!!, results!!, recyclerView!!)
                recyclerView!!.adapter = adapter
            }

        } catch (e: NumberFormatException) {

            val sberr2 = Snackbar.make(app_bar, getString(R.string.errNul), Snackbar.LENGTH_LONG)
            sberr2.setAction(getString(R.string.esempio)) {
                ip1!!.setText("192")
                ip2!!.setText("168")
                ip3!!.setText("1")
                ip4!!.setText("0")
                maschera!!.setText("24")
                Objects.requireNonNull<CollapsingToolbarLayout>(ctl).title = "IP Subnet Calculator"
            }
            sberr2.show()
        }

    }
}