package com.rnk0085.android.memo.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.rnk0085.android.memo.MemoListAdapter
import com.rnk0085.android.memo.R
import com.rnk0085.android.memo.databinding.FragmentHomeBinding
import com.rnk0085.android.memo.ui.common.dialog.ErrorDialogFragment
import com.rnk0085.android.memo.viewModels.HomeUiState
import com.rnk0085.android.memo.viewModels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home),
        ErrorDialogFragment.ErrorDialogListener
{
    private val viewModel: HomeViewModel by viewModels()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = MemoListAdapter {
            // TODO: 詳細ページへの遷移
        }

        val dividerItemDecoration = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)

        binding.apply {
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            recyclerView.addItemDecoration(dividerItemDecoration)
        }

        lifecycleScope.launch {
            viewModel.uiState.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect { uiState ->
                    when (uiState) {
                        is HomeUiState.Initial -> {
                            binding.progressBar.isGone = true
                        }
                        is HomeUiState.Loading -> {
                            binding.progressBar.isVisible = true
                        }
                        is HomeUiState.Success -> {
                            binding.progressBar.isGone = true
                            adapter.submitList(uiState.memoEntities)
                        }
                        is HomeUiState.Error -> {
                            binding.progressBar.isGone = true
                            showErrorDialog()
                        }
                    }
                }
        }

        binding.floatingActionButton.setOnClickListener { onCreateButton() }
    }

    private fun onCreateButton() {
        val action = HomeFragmentDirections
            .actionHomeFragmentToCreateFragment()
        view?.findNavController()?.navigate(action)
    }

    private fun showErrorDialog() {
        ErrorDialogFragment.newInstance()
            .show(childFragmentManager, ErrorDialogFragment.TAG)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onErrorDialogPositiveClick() {
        // TODO：「再読み込み」処理を作成
        viewModel.dialogShown()
    }

    override fun onErrorDialogNegativeClick() {
        viewModel.dialogShown()
    }

    override fun onErrorDialogCancel() {
        viewModel.dialogShown()
    }
}
