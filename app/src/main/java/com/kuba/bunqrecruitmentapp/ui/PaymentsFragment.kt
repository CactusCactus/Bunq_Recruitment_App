package com.kuba.bunqrecruitmentapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.kuba.bunqrecruitmentapp.R
import com.kuba.bunqrecruitmentapp.databinding.FragmentPaymentsBinding

class PaymentsFragment : Fragment() {
    lateinit var viewModel: PaymentsViewModel
    lateinit var binding: FragmentPaymentsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PaymentsViewModel::class.java)
        viewModel.fetchPayments()
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
    }

    private fun observeViewModel() {
        viewModel.paymentListLD.observe(viewLifecycleOwner, Observer { paymentList ->
            paymentList.let {
                Log.d("list", paymentList.size.toString())
            }
        })
    }
}