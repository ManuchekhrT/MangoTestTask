package tj.mangotask.presentation

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavGraph
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import tj.mangotask.R
import tj.mangotask.databinding.FragmentRegisterBinding
import tj.mangotask.domain.model.State
import tj.mangotask.domain.usecases.RegisterParam
import tj.mangotask.extensions.hideKeyboard
import tj.mangotask.extensions.isValidUsername
import tj.mangotask.extensions.makeEnabled
import tj.mangotask.extensions.showToast

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = checkNotNull(_binding)

    private var phone: String = ""
    private val viewModel: AuthViewModel by viewModels()

    private var loadingDialog: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            phone = it.getString(ARG_PHONE, "")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
        setupObservers()
    }

    private fun setupView() = with(binding) {
        textInputEdtPhone.setText(phone)
        textInputEdtPhone.makeEnabled()

        textInputEdtUsername.doAfterTextChanged { s ->
            val username = s.toString()

            if (isValidUsername(username)) {
                // Valid username, no error
                binding.textInputEdtUsername.error = null
            } else {
                // Invalid username, show error
                binding.textInputEdtUsername.error = "Invalid format username"
            }
        }
        btnActionRegister.setOnClickListener {
            val phone = textInputEdtPhone.text.toString().trim()
            val username = textInputEdtUsername.text.toString()
            val name = textInputEdtName.text.toString()

            if (isValidUsername(username)) {
                viewModel.registerParam =
                    RegisterParam(username = username, name = name, phone = phone)
                viewModel.register()
            }
        }
    }

    private fun setupObservers() = with(viewModel) {
        registerState.observe(viewLifecycleOwner) {
            when (it) {
                is State.Loading -> {
                    showLoadingDialog()
                }
                is State.Error -> {
                    dismissLoadingDialog()
                    requireContext().showToast(it.message)
                }
                is State.Success -> {
                    requireActivity().hideKeyboard()
                    Handler().postDelayed({
                        dismissLoadingDialog()
                        findNavController().popBackStack(R.id.RegisterFragment, true)
                        findNavController().navigate(R.id.ProfileFragment)
                    }, 1000)
                }
            }
        }
    }

    private fun showLoadingDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setView(R.layout.dialog_loading) // Replace with your custom layout for the loading dialog
        builder.setCancelable(false) // Prevent dialog dismissal on outside touch or back press

        loadingDialog = builder.create()
        loadingDialog?.show()
    }

    private fun dismissLoadingDialog() {
        loadingDialog?.dismiss()
    }

    companion object {
        const val ARG_PHONE = "arg_phone"
    }
}