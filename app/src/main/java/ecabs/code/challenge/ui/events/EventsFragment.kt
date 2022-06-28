package ecabs.code.challenge.ui.events

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingComponent
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import ecabs.code.challenge.R
import ecabs.code.challenge.common.FragmentDataBindingComponent
import ecabs.code.challenge.common.autoCleared
import ecabs.code.challenge.data.item.Status
import ecabs.code.challenge.databinding.EventFragmentBinding
import ecabs.code.challenge.ui.main.MainViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Fragment that displays a list of clickable cards,
 * each representing an Event details.
 */
@AndroidEntryPoint
class EventsFragment : Fragment() {

    private val refreshTime = 10000L
    private val viewModel by viewModels<EventsViewModel>()
    private val mainViewModel by activityViewModels<MainViewModel>()

    private var dataBindingComponent: DataBindingComponent = FragmentDataBindingComponent(this)
    private var binding by autoCleared<EventFragmentBinding>()
    private var adapter by autoCleared<EventsAdapter>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = EventFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initViewModel()
    }

    private fun initRecyclerView() {
        val eventsAdapter = EventsAdapter(
            dataBindingComponent = dataBindingComponent
        ) { item ->
            findNavController().navigate(EventsFragmentDirections.showDetails(item))
        }
        binding.recyclerView.adapter = eventsAdapter
        adapter = eventsAdapter
    }

    private fun initViewModel() {
        val job = viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                // this block is automatically executed when moving into
                // the started state, and cancelled when stopping.
                while (true) {
                    delay(refreshTime)
                    viewModel.refresh()
                }
            }
        }
        viewModel.request.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    adapter.submitList(it.data)
                    mainViewModel.setTitle(getString(R.string.app_name) + " (${it.data?.size})")
                }
                Status.LOADING -> Toast.makeText(
                    context,
                    getString(R.string.loading),
                    Toast.LENGTH_SHORT
                ).show()
                Status.ERROR -> {
                    job.cancel()
                    Snackbar.make(
                        binding.root,
                        R.string.an_error_occurred,
                        Snackbar.LENGTH_INDEFINITE
                    ).setAction(R.string.try_again) {
                        job.start()
                        viewModel.refresh()
                    }.show()
                }
            }
        }
    }
}