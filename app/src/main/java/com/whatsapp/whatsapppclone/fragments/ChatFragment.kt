package com.whatsapp.whatsapppclone.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.whatsapp.whatsapppclone.adapter.ChatAdapter

import com.whatsapp.whatsapppclone.databinding.FragmentChatBinding
import com.whatsapp.whatsapppclone.model.UserModel


class ChatFragment : Fragment() {
    private lateinit var binding: FragmentChatBinding
    private lateinit var firebase :FirebaseDatabase
    private lateinit var userList :ArrayList<UserModel>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        binding = FragmentChatBinding.inflate(layoutInflater)

        firebase = FirebaseDatabase.getInstance()
        userList = ArrayList()

        firebase.reference.child("users")
            .addValueEventListener(object :ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    userList.clear()
                    for (snapshot1 in snapshot.children){
                        val user = snapshot1.getValue(UserModel::class.java)
                        if (user?.uid != FirebaseAuth.getInstance().uid){
                            userList.add(user!!)
                        }
                    }
                    binding.userListRecyclerView.adapter = ChatAdapter(requireContext(),userList)

                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })
        return binding.root

    }
}