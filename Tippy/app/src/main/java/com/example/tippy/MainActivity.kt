package com.example.tippy

import android.animation.ArgbEvaluator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_main.*
import android.util.Log
import androidx.core.content.ContextCompat

private const val TAG = "MainActivity"
private const val INITIAL_TIP_PERCENT = 15
private const val INITIAL_SPLIT = 1
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        seekBarTip.progress = INITIAL_TIP_PERCENT
        tvTipPercent.text = "$INITIAL_TIP_PERCENT%"
        updateTipDescription(INITIAL_TIP_PERCENT)
        seekBarSplit.progress = INITIAL_SPLIT
        updateSplitNumber(INITIAL_SPLIT)

        seekBarTip.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
//                Log.i(TAG, "onProgressChanged $progress")
                tvTipPercent.text = "$progress%"
                computeTipAndTotalAndSplit()
                updateTipDescription(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        etBase.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
//                Log.i(TAG, "afterTextChanged $s")
                computeTipAndTotalAndSplit()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        seekBarSplit.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                Log.i(TAG, "onProgressChanged $progress")
                computeTipAndTotalAndSplit()
                updateSplitNumber(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }

    private fun updateSplitNumber(splitNumber: Int) {
        tvNumPeople.text = if (splitNumber == 1) "By 1 person" else "By $splitNumber people"
    }

    private fun updateTipDescription(tipPercent: Int) {
        val tipDescription: String
        when (tipPercent) {
            in 0..9 -> tipDescription = "Poor\uD83D\uDE44"
            in 10..14 -> tipDescription = "Acceptable\uD83D\uDE10"
            in 15..20 -> tipDescription = "Good\uD83D\uDE0A"
            in 20..24 -> tipDescription = "Great\uD83D\uDE04"
            else -> tipDescription = "Amazing\uD83D\uDE0D"
        }
        tvTipDescription.text = tipDescription
        val color = ArgbEvaluator().evaluate(
            tipPercent.toFloat() / seekBarTip.max,
            ContextCompat.getColor(this, R.color.colorWorstTip),
            ContextCompat.getColor(this, R.color.colorBestTip)
        ) as Int
        tvTipDescription.setTextColor(color)
    }

    private fun computeTipAndTotalAndSplit() {
        // Get the value of the base and tip percent
        if (etBase.text.isEmpty()){
            tvTipAmount.text = ""
            tvTotalAmount.text = ""
            tvPerPerson.text= ""
            return
        }
        val baseAmount = etBase.text.toString().toDouble()
        val tipPercent = seekBarTip.progress
        val tipAmount = baseAmount * tipPercent / 100
        val totalAmount = baseAmount + tipAmount
        val perPersonAmount = totalAmount / seekBarSplit.progress
        tvTipAmount.text = "%.2f".format(tipAmount)
        tvTotalAmount.text = "%.2f".format(totalAmount)
        tvPerPerson.text = "%.2f".format(perPersonAmount)
    }
}
