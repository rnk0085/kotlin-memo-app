package com.rnk0085.android.memo.ui

import android.os.Bundle
import android.view.*
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.rnk0085.android.memo.R
import com.rnk0085.android.memo.databinding.FragmentAddMemoBinding
import com.rnk0085.android.memo.utils.ErrorDialogFragment
import com.rnk0085.android.memo.utils.SaveDialogFragment
import com.rnk0085.android.memo.viewModels.AddMemoUiState
import com.rnk0085.android.memo.viewModels.AddMemoViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AddMemoFragment : Fragment(R.layout.fragment_add_memo),
    SaveDialogFragment.SaveDialogListener,
    ErrorDialogFragment.ErrorDialogListener
{
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

        requireActivity().onBackPressedDispatcher.addCallback(this) {
            onBackButtonClick()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        lifecycleScope.launch {
            viewModel.uiState.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect { uiState ->
                    when (uiState) {
                        is AddMemoUiState.Initial -> {}
                        is AddMemoUiState.Success -> {
                            navigation()
                        }
                        is AddMemoUiState.Failure -> {
                            showErrorDialog()
                        }
                    }
                }
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

    private fun navigation() {
        val action = AddMemoFragmentDirections.actionAddMemoFragmentToHomeFragment()
        findNavController().navigate(action)
    }

    private fun onBackButtonClick() {
        if (isEntryValid()) showSaveDialog()
        else navigation()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_memo_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                // Upボタンクリック時
                onBackButtonClick()
                true
            }
            R.id.action_add_memo -> {
                // 保存ボタンクリック時
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

    private fun showSaveDialog() {
        val saveDialogFragment = SaveDialogFragment()
        saveDialogFragment.show(childFragmentManager, SAVE_DIALOG)
    }

    override fun onSaveDialogPositiveClick() {
        // 保存
        addNewMemo()
    }

    override fun onSaveDialogNegativeClick() {
        // 削除
        navigation()
    }

    private fun showErrorDialog() {
        ErrorDialogFragment.newInstance()
            .show(childFragmentManager, ErrorDialogFragment.TAG)
    }

    override fun onErrorDialogPositiveClick() {
        // 再試行
        viewModel.errorCancelled()
        addNewMemo()
    }

    override fun onErrorDialogNegativeClick() {
        // エラーダイアログを閉じる
        viewModel.errorCancelled()
    }

    override fun onErrorDialogCancel() {
        // エラーダイアログを閉じる
        viewModel.errorCancelled()
    }

    companion object {
        const val SAVE_DIALOG = "SaveDialogFragment"
    }
}
