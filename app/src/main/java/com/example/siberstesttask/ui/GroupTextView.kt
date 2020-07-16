package com.example.siberstesttask.ui

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.example.siberstesttask.R
import com.example.siberstesttask.databinding.LayoutGroupTextviewBinding


class GroupTextView(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {
    private var binding: LayoutGroupTextviewBinding
    init {
        View.inflate(context,
            R.layout.layout_group_textview, this).also {
            binding = LayoutGroupTextviewBinding.bind(it)
        }
        val arr = context.obtainStyledAttributes(attrs,
            R.styleable.GroupTextView
        )
        arr.getString(R.styleable.GroupTextView_labelText)?.let { binding.labelTextView.text = it }
        arr.getString(R.styleable.GroupTextView_valueText)?.let { binding.valueTextView.text = it }
        arr.recycle()
    }

    fun setValueText(text: String) {
        binding.valueTextView.text = text
    }
}