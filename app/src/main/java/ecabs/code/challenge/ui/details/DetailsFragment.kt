package ecabs.code.challenge.ui.details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import ecabs.code.challenge.R
import ecabs.code.challenge.data.item.Events
import ecabs.code.challenge.databinding.DetailFragmentBinding
import ecabs.code.challenge.ui.main.MainViewModel
import ecabs.code.challenge.common.FragmentDataBindingComponent
import ecabs.code.challenge.common.autoCleared

/**
 * Fragment that displays an Event details.
 */
class DetailsFragment : Fragment() {

    private val args by navArgs<DetailsFragmentArgs>()
    private val mainViewModel by activityViewModels<MainViewModel>()

    private var dataBindingComponent: DataBindingComponent = FragmentDataBindingComponent(this)
    private var binding by autoCleared<DetailFragmentBinding>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.detail_fragment,
            container,
            false,
            dataBindingComponent
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel.setTitle(args.item.actor.display_login)
        binding.apply {
            item = args.item
            buttonRepoUrl.setOnClickListener {
                openUrl(args.item)
            }
            executePendingBindings()
        }
    }

    private fun openUrl(item: Events) {
        try {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(item.repo.url)
                )
            )
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }
}