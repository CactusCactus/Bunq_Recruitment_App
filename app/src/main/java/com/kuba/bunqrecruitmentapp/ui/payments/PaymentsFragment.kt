package com.kuba.bunqrecruitmentapp.ui.payments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import com.kuba.bunqrecruitmentapp.databinding.FragmentPaymentsBinding
import com.kuba.bunqrecruitmentapp.di.DaggerAppComponent
import javax.inject.Inject

class PaymentsFragment : Fragment() {
    @Inject
    lateinit var vmFactory: ViewModelProvider.Factory
    private lateinit var viewModel: PaymentsViewModel
    private var _binding: FragmentPaymentsBinding? = null
    private val binding get() = _binding!!
    private val paymentsAdapter = PaymentsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerAppComponent.create().inject(this)
        viewModel = ViewModelProvider(this, vmFactory).get(PaymentsViewModel::class.java)
        arguments?.let { args ->
            viewModel.userId = PaymentsFragmentArgs.fromBundle(args).userId
            paymentsAdapter.clear()
            viewModel.fetchPayments(requireContext())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPaymentsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        observeViewModel()
        setUpClickListeners()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.recyclerView.adapter = null
        _binding = null
    }

    private fun observeViewModel() {
        viewModel.apply {
            paymentListLD.observe(viewLifecycleOwner, Observer { paymentList ->
                paymentList.let {
                    paymentsAdapter.setData(paymentList)
                    paymentsAdapter.fetchedAll = nextPage == null
                }
            })
            paymentListNextPageLD.observe(viewLifecycleOwner, Observer { paymentList ->
                paymentList.let {
                    paymentsAdapter.addData(paymentList)
                    paymentsAdapter.fetchedAll = nextPage == null
                }

            })
        }
    }

    private fun setUpRecyclerView() {
        binding.recyclerView.apply {
            adapter = paymentsAdapter
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    if (!recyclerView.canScrollVertically(1) && viewModel.nextPage != null) {
                        viewModel.userId?.let { viewModel.fetchNextPage(recyclerView.context) }
                    }
                }
            })
            paymentsAdapter.registerAdapterDataObserver(object :
                RecyclerView.AdapterDataObserver() {
                override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                    if (positionStart == 0) {
                        layoutManager?.scrollToPosition(0)
                    }
                }
            })
            paymentsAdapter.clear()
        }
    }

    private fun setUpClickListeners() {
        binding.addFab.setOnClickListener {
            val userId = viewModel.userId
            val accountId = viewModel.accountId
            if (userId != null && accountId != null) {
                NavHostFragment.findNavController(this).navigate(
                    PaymentsFragmentDirections.actionPaymentsFragmentToNewPaymentFragment(
                        userId,
                        accountId
                    )
                )
            }
        }
    }

}