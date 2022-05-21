package com.rnk0085.android.memo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rnk0085.android.memo.databinding.FragmentAddMemoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddMemoFragment : Fragment(R.layout.fragment_add_memo) {
    private var _binding: FragmentAddMemoBinding? = null
    private val binding get() = _binding!!

    // val args: CreateFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddMemoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        val name = args.name
//        binding.textView2.text = name
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
