package com.kuba.bunqrecruitmentapp.ui.payments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.kuba.bunqrecruitmentapp.databinding.FragmentPaymentsBinding
import com.kuba.bunqrecruitmentapp.di.DaggerAppComponent
import javax.inject.Inject

class PaymentsFragment : Fragment() {
    @Inject
    lateinit var vmFactory: ViewModelProvider.Factory
    private lateinit var viewModel: PaymentsViewModel
    private lateinit var binding: FragmentPaymentsBinding
    private val paymentsAdapter = PaymentsAdapter()
    private var userId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerAppComponent.create().inject(this)
        viewModel = ViewModelProvider(this, vmFactory).get(PaymentsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPaymentsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        arguments?.let { args ->
            userId = PaymentsFragmentArgs.fromBundle(args).userId
            userId?.let {
                viewModel.fetchPayments(view.context, it)
            }
        }
        binding.recyclerView.apply {
            adapter = paymentsAdapter
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    if (!recyclerView.canScrollVertically(1) && viewModel.nextPageLD.value != null) {
                        userId?.let { viewModel.fetchPayments(recyclerView.context, it) }
                    }
                }
            })
            (adapter as PaymentsAdapter).registerAdapterDataObserver(object :
                RecyclerView.AdapterDataObserver() {
                override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                    if (positionStart == 0) {
                        layoutManager?.scrollToPosition(0)
                    }
                }
            })
        }
    }

    private fun observeViewModel() {
        viewModel.apply {
            paymentListLD.observe(viewLifecycleOwner, Observer { paymentList ->
                paymentList.let {
                    paymentsAdapter.setData(it)
                }
            })
            nextPageLD.observe(viewLifecycleOwner, Observer { nextPage ->
                paymentsAdapter.fetchedAll = nextPage == null
            })
        }
    }


}