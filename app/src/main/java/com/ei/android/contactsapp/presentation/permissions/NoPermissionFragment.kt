package com.ei.android.contactsapp.presentation.permissions

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ei.android.contactsapp.R
import com.ei.android.contactsapp.databinding.FragmentNoPermissionBinding
import com.ei.android.contactsapp.presentation.MainActivity
import com.ei.android.contactsapp.presentation.contacts.ContactListFragment
import com.ei.android.contactsapp.presentation.contacts.StarredContactListFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class NoPermissionFragment : Fragment() {
    private var _binding: FragmentNoPermissionBinding? = null
    private val binding: FragmentNoPermissionBinding
        get() = _binding ?: throw RuntimeException("NoFragmentBinding = null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoPermissionBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.goToSettingsBtn.setOnClickListener {
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            val uri = Uri.fromParts("package", requireContext().packageName, null)
            intent.data = uri
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        val args = requireArguments()
        if (!args.containsKey(PERMISSION)) {
            throw RuntimeException("No args in NoPermissionFragment")
        }
        val checkPermissionResult = context?.checkSelfPermission(args.getString(PERMISSION, ""))
        if (checkPermissionResult == PackageManager.PERMISSION_GRANTED) {
            if (requireActivity() is MainActivity) {
                val bottomNavigation =
                    requireActivity().findViewById<BottomNavigationView>(R.id.bottom_navigation)
                (requireActivity() as MainActivity).setFragment(
                    (requireActivity() as MainActivity).chooseByNavigationFragment(
                        bottomNavigation
                    )
                )
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        private const val PERMISSION = "permission"
        fun newInstance(permission: Array<out String>) =
            NoPermissionFragment().apply {
                arguments = Bundle().apply {
                    putString(PERMISSION, permission[0])
                }
            }
    }


}