package com.rnk0085.android.memo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.rnk0085.android.memo.databinding.FragmentCreateBinding

class CreateFragment : Fragment(R.layout.fragment_create) {
    private var _binding: FragmentCreateBinding? = null
    private val binding get() = _binding!!

    val args: CreateFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val name = args.name
        binding.textView2.text = name
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
