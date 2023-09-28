package com.monir.marvelapp.ui.customwidgets

import android.content.Context
import android.util.AttributeSet
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.monir.marvelapp.R
import com.monir.marvelapp.base.BaseResource
import com.monir.marvelapp.extensions.hide
import com.monir.marvelapp.extensions.show
import com.monir.marvelapp.ui.details.adapter.ListSubmittingAdapter

class DetailsSectionView @JvmOverloads constructor(mContext: Context,attrs: AttributeSet? = null,defStyleAttr: Int = 0) : ConstraintLayout(mContext, attrs, defStyleAttr) {

    fun interface RetryCallback {
        fun onRetry()
    }

    private val tvTitle: TextView
    private val tvNoData: TextView
    private val rvDetails: RecyclerView
    private val btnRetry: MaterialButton
    private val loadingIndicator: ProgressBar

    var retryCallback: RetryCallback? = null

    @StringRes
    var emptyTextRes : Int? = R.string.empty_string
        set(emptyTextRes) {
            if (emptyTextRes != null)
                tvNoData.text = context.getString(emptyTextRes)
        }

    @StringRes
    var titleRes : Int? = R.string.empty_string
        set(titleRes) {
            if (titleRes != null)
                tvTitle.text = context.getString(titleRes)
        }

    init {
        inflate(context, R.layout.layout_character_section, this)

        btnRetry         = findViewById(R.id.btnRetry)
        rvDetails        = findViewById(R.id.rvDetails)
        tvNoData         = findViewById(R.id.tvNoData)
        tvTitle          = findViewById(R.id.tvSectionTitle)
        loadingIndicator = findViewById(R.id.loadingIndicator)

        btnRetry.setOnClickListener { retryCallback?.onRetry() }

        val attributes = context.obtainStyledAttributes(attrs, R.styleable.DetailsSectionView)
        try {
            emptyTextRes =  attributes.getResourceId(R.styleable.DetailsSectionView_emptyString, R.string.empty_string)
            titleRes =  attributes.getResourceId(R.styleable.DetailsSectionView_sectionTitle, R.string.empty_string)
        } finally {
            attributes.recycle()
        }

    }

    fun <T> manageStates(resource: BaseResource<T>?, adapter: ListSubmittingAdapter<T>) {
        when (resource) {
            is BaseResource.Loading -> {
                btnRetry.hide()
                loadingIndicator.show()
                rvDetails.hide()
                tvNoData.hide()
            }
            is BaseResource.Success -> {
                btnRetry.hide()
                loadingIndicator.hide()
                if (resource.data?.data.isNullOrEmpty()) {
                    rvDetails.hide()
                    tvNoData.show()
                } else {
                    rvDetails.show()
                    tvNoData.hide()
                    adapter.submitList(resource.data!!.data)
                }
            }
            is BaseResource.Error -> {
                btnRetry.show()
                loadingIndicator.hide()
                rvDetails.hide()
                tvNoData.hide()
            }
            null -> {
                btnRetry.show()
                loadingIndicator.hide()
                rvDetails.hide()
                tvNoData.hide()
            }
        }
    }

    fun setAdapter(adapter: RecyclerView.Adapter<*>) {
        rvDetails.adapter = adapter
    }

}