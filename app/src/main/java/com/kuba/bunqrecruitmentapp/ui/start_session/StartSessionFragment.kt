package com.kuba.bunqrecruitmentapp.ui.start_session

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.kuba.bunqrecruitmentapp.databinding.FragmentStartSessionBinding
import com.kuba.bunqrecruitmentapp.di.DaggerAppComponent
import javax.inject.Inject

class StartSessionFragment : Fragment() {
    @Inject
    lateinit var vmFactory: ViewModelProvider.Factory
    private lateinit var viewModel: StartSessionViewModel
    private var _binding: FragmentStartSessionBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerAppComponent.create().inject(this)
        viewModel = ViewModelProvider(this, vmFactory).get(StartSessionViewModel::class.java)
        context?.let {
            viewModel.initSession(it)
        }
        requireContext()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStartSessionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        setViewListeners()
    }

    private fun observeViewModel() {
        viewModel.statusLD.observe(viewLifecycleOwner, Observer { status ->
            if (status == ApiContextStatus.SUCCESS) {
                viewModel.userId?.let {
                    NavHostFragment.findNavController(this)
                        .navigate(StartSessionFragmentDirections.actionStartSessionFragmentToPaymentsFragment(it))
                }

            } else {
                binding.statusTV.text = status.toString(binding.statusTV.context)
                binding.progressBar.visibility =
                    if (status == ApiContextStatus.FAIL) View.GONE else View.VISIBLE
                binding.retryTV.visibility =
                    if (status == ApiContextStatus.FAIL) View.VISIBLE else View.GONE
            }
        })
    }

    private fun setViewListeners() {
        binding.retryTV.setOnClickListener {
            context?.let {
                viewModel.initSession(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}