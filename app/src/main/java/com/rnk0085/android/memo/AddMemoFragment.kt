package com.rnk0085.android.memo

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.rnk0085.android.memo.databinding.FragmentAddMemoBinding
import com.rnk0085.android.memo.viewModels.AddMemoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddMemoFragment : Fragment(R.layout.fragment_add_memo) {
    private val viewModel: AddMemoViewModel by viewModels()

    private var _binding: FragmentAddMemoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddMemoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.addMemoButton.setOnClickListener {
            addNewMemo()
        }
    }

    private fun isEntryValid(): Boolean {
        return viewModel.isEntryValid(
            binding.editMemoTitle.editText?.text.toString(),
            binding.editMemoContent.editText?.text.toString()
        )
    }

    private fun addNewMemo() {
        if (isEntryValid()) {
            viewModel.addNewMemo(
                binding.editMemoTitle.editText?.text.toString(),
                binding.editMemoContent.editText?.text.toString()
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
