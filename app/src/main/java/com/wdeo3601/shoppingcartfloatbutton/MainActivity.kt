package com.wdeo3601.shoppingcartfloatbutton

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val shopping = findViewById<ShoppingCartFloatButton>(R.id.view)
        val seekBar = findViewById<SeekBar>(R.id.seekBar)
        seekBar.progress = shopping.getCommodityCount()
        seekBar.setOnSeekBarChangeListener(object :SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                shopping.setCommodityCount(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })

        val seekBar2 = findViewById<SeekBar>(R.id.seekBar2)
        seekBar2.progress = shopping.getDotMarginStart()
        seekBar2.setOnSeekBarChangeListener(object :SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                shopping.setDotMarginStart(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })

        val seekBar3 = findViewById<SeekBar>(R.id.seekBar3)
        seekBar3.progress = shopping.getDotBottomMargin()
        seekBar3.setOnSeekBarChangeListener(object :SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                shopping.setDotBottomMargin(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })
    }
}
