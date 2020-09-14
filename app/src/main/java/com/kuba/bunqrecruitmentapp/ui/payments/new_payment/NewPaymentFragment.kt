package com.kuba.bunqrecruitmentapp.ui.payments.new_payment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.kuba.bunqrecruitmentapp.R
import com.kuba.bunqrecruitmentapp.api.models.AliasShort
import com.kuba.bunqrecruitmentapp.api.models.Amount
import com.kuba.bunqrecruitmentapp.api.models.ContactType
import com.kuba.bunqrecruitmentapp.api.models.payment.PaymentSendBody
import com.kuba.bunqrecruitmentapp.databinding.FragmentNewPaymentBinding
import com.kuba.bunqrecruitmentapp.di.DaggerAppComponent
import com.kuba.bunqrecruitmentapp.hideKeyboard
import javax.inject.Inject

class NewPaymentFragment : Fragment() {
    @Inject
    lateinit var vmFactory: ViewModelProvider.Factory
    private lateinit var viewModel: NewPaymentViewModel
    private var _binding: FragmentNewPaymentBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerAppComponent.create().inject(this)
        viewModel = ViewModelProvider(this, vmFactory).get(NewPaymentViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewPaymentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            viewModel._userId = NewPaymentFragmentArgs.fromBundle(it).userId
            viewModel._accountId = NewPaymentFragmentArgs.fromBundle(it).accountId
        }
        setClickListeners()
        observeViewModel()
    }

    private fun setClickListeners() {
        binding.sendBtn.setOnClickListener {
            context?.hideKeyboard(it)
            if (isAnyFieldBlank()) {
                Toast.makeText(context, getString(R.string.fill_all_field), Toast.LENGTH_SHORT)
                    .show()
            } else {
                binding.viewBlocker.visibility = View.VISIBLE
                val amount = Amount(binding.amountET.text.toString(), "EUR")
                val alias = AliasShort(
                    ContactType.EMAIL,
                    binding.emailET.text.toString(),
                    binding.nameET.text.toString()
                )
                val description = binding.descET.text.toString()
                val paymentSendBody = PaymentSendBody(amount, alias, description)
                viewModel.sendPayment(it.context, paymentSendBody)
            }
        }
    }

    private fun observeViewModel() {
        viewModel.sendStatusLD.observe(viewLifecycleOwner, Observer {
            binding.viewBlocker.visibility =
                if (it == SendStatus.SENDING) View.VISIBLE else View.GONE
            if (it == SendStatus.SUCCESS) {
                Toast.makeText(
                    context,
                    getString(R.string.transaction_complate),
                    Toast.LENGTH_SHORT
                ).show()
                NavHostFragment.findNavController(this).popBackStack()
            } else if (it == SendStatus.FAIL) {
                Toast.makeText(
                    context,
                    getString(R.string.transacion_failed),
                    Toast.LENGTH_SHORT
                ).show()
                NavHostFragment.findNavController(this).popBackStack()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun isAnyFieldBlank() =
        binding.emailET.text.isBlank() || binding.amountET.text.isBlank() ||
                binding.nameET.text.isBlank() || binding.descET.text.isBlank()
}