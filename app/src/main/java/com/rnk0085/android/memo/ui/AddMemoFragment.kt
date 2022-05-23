package com.rnk0085.android.memo.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.rnk0085.android.memo.R
import com.rnk0085.android.memo.databinding.FragmentAddMemoBinding
import com.rnk0085.android.memo.utils.SaveDialogFragment
import com.rnk0085.android.memo.viewModels.AddMemoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddMemoFragment : Fragment(R.layout.fragment_add_memo),
    SaveDialogFragment.SaveDialogListener {
    private val viewModel: AddMemoViewModel by viewModels()

    private var _binding: FragmentAddMemoBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddMemoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

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
            navigation()
        }
    }

    private fun navigation() {
        val action = AddMemoFragmentDirections.actionAddMemoFragmentToHomeFragment()
        findNavController().navigate(action)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_memo_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_add_memo -> {
                addNewMemo()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showDialog() {
        val saveDialogFragment = SaveDialogFragment()
        saveDialogFragment.show(childFragmentManager, SAVE_DIALOG)
    }

    override fun onDialogPositiveClick() {
        // 保存
        addNewMemo()
    }

    override fun onDialogNegativeClick() {
        // 削除
        navigation()
    }

    companion object {
        const val SAVE_DIALOG = "SaveDialogFragment"
    }
}
