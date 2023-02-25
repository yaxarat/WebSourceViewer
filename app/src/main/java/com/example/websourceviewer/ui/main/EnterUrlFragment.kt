package com.example.websourceviewer.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.websourceviewer.MainActivity
import com.example.websourceviewer.R
import com.example.websourceviewer.databinding.FragmentEnterUrlBinding
import com.example.websourceviewer.ui.common.CommonViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EnterUrlFragment : Fragment(R.layout.fragment_enter_url) {

    private lateinit var binding: FragmentEnterUrlBinding

    private val viewModel: CommonViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentEnterUrlBinding.bind(view)
        setUpInspectButton()
    }

    private fun setUpInspectButton() {
        with(binding) {
            buttonSubmit.setOnClickListener {
                edittextUrl.text.toString().let { url ->
                    val validUrl = url.isNotEmpty() && url.isNotBlank()
                    if (validUrl) {
                        it.performHapticFeedback(16)
                        viewModel.setUrlToLoad(url)
                        proceedToInspectScreen()
                    } else {
                        it.performHapticFeedback(17)
                    }
                }
            }
        }
    }

    private fun proceedToInspectScreen() {
        (requireActivity() as MainActivity).addFragment()
    }
}