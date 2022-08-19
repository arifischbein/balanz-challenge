package com.example.balanzchallenge.criptocurrencyfeature.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.balanzchallenge.criptocurrencyfeature.ui.viewmodels.CryptoCurrencyViewModel
import com.example.balanzchallenge.databinding.FragmentCryptoCurrencyBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CryptoCurrency : Fragment() {

    companion object {
        fun newInstance() = CryptoCurrency()
    }

    private val viewModel: CryptoCurrencyViewModel by viewModels()
    lateinit var binding: FragmentCryptoCurrencyBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCryptoCurrencyBinding.inflate(layoutInflater)
        return binding.root
    }
}