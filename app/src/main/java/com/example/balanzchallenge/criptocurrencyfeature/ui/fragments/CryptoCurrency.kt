package com.example.balanzchallenge.criptocurrencyfeature.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.balanzchallenge.core.ui.BaseViewState
import com.example.balanzchallenge.core.utils.exhaustive
import com.example.balanzchallenge.criptocurrencyfeature.framework.BinanceApiClient
import com.example.balanzchallenge.criptocurrencyfeature.ui.adapters.CryptoListAdapter
import com.example.balanzchallenge.criptocurrencyfeature.ui.viewmodels.CryptoCurrencyViewModel
import com.example.balanzchallenge.databinding.FragmentCryptoCurrencyBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class CryptoCurrency : Fragment() {

    companion object {
        fun newInstance() = CryptoCurrency()
    }

    private val viewModel: CryptoCurrencyViewModel by viewModels()
    lateinit var binding: FragmentCryptoCurrencyBinding
    private var cryptoAdapter = CryptoListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCryptoCurrencyBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycler()
        setupObserver()
        setupSearchView()
    }

    override fun onStart() {
        super.onStart()
        //viewModel.getData()
    }

    fun setupRecycler(){
        binding.recyclerCryptos.setHasFixedSize(true)
        binding.recyclerCryptos.layoutManager = LinearLayoutManager(context)
        binding.recyclerCryptos.adapter = cryptoAdapter
        //clicklister
    }
    private fun setupObserver(){
        viewModel.viewStateLD.observe(viewLifecycleOwner){ handleViewState(it) }
        viewModel.cryptoListLD.observe(viewLifecycleOwner){ cryptoAdapter.setData(it) }
        lifecycleScope.launchWhenStarted {
            viewModel.getDataFlow.collect{
                cryptoAdapter.setData(it)
            }
        }
    }

    private fun handleViewState(viewState: BaseViewState){
        when(viewState){
            is BaseViewState.Ready -> { }
            is BaseViewState.Loading -> { }
            is BaseViewState.Refreshing -> { }
            is BaseViewState.Failure -> { }
            is BaseViewState.Alert -> { }
        }.exhaustive
    }

    private fun setupSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                binding.searchView.clearFocus()
                searchQuery(query, true)
                return false
            }

            override fun onQueryTextChange(query: String): Boolean {
                searchQuery(query)
                return false
            }
        })
    }

    private fun searchQuery(query: String, submit: Boolean = false) {
        cryptoAdapter.filter(query)
    }
}