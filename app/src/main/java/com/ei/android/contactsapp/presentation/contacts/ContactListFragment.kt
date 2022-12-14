package com.ei.android.contactsapp.presentation.contacts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.SimpleItemAnimator
import com.ei.android.contactsapp.core.ContactsApp
import com.ei.android.contactsapp.databinding.FragmentContactListBinding
import com.ei.android.contactsapp.presentation.contacts.adapter.ContactAdapter
import java.lang.RuntimeException


class ContactListFragment : Fragment() {

    private lateinit var viewModel: ContactsViewModel

    private var _binding:FragmentContactListBinding? = null
    private val binding:FragmentContactListBinding
        get() = _binding?:throw RuntimeException("FragmentBinding = null")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = (requireActivity().application as ContactsApp).viewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContactListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ContactAdapter (object : ContactAdapter.OnStarredClickListener{
            override fun onClickListener(id: String, starred: Boolean) {
                viewModel.changeStar(id,starred)
                viewModel.fetchContacts()
            }


        })
        binding.contactList.adapter = adapter
        (binding.contactList.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false

        viewModel.observeContacts(this){
            adapter.submitList(it)
        }
        viewModel.fetchContacts()

    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {

        fun newInstance() =
            ContactListFragment()
    }
}