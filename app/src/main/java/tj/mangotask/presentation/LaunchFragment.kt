package tj.mangotask.presentation

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import tj.mangotask.R
import tj.mangotask.databinding.FragmentLaunchBinding
import tj.mangotask.databinding.FragmentProfileBinding

@AndroidEntryPoint
class LaunchFragment : BaseFragment<FragmentLaunchBinding>() {

    private val viewModel: UserViewModel by viewModels()

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentLaunchBinding {
        return FragmentLaunchBinding.inflate(inflater, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getUserToken()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.userTokenState.observe(viewLifecycleOwner) {
            it.userToken?.let { aToken ->
                if (aToken.accessToken.isNotBlank()) {
                    findNavController().popBackStack(
                        findNavController().graph.startDestinationId,
                        true
                    )
                    findNavController().navigate(R.id.ProfileFragment)
                } else {
                    findNavController().navigate(R.id.action_LaunchFragment_to_PhoneFragment)
                }
            }
        }
    }

}