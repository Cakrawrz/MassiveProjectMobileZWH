package com.infinite.massiveprojectmobilezwh.beranda

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.infinite.massiveprojectmobilezwh.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BerandaProsesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BerandaProsesFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_beranda_proses, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btn_confrim : Button = view.findViewById(R.id.bt_confirmation)
        val btnPhone : Button = view.findViewById(R.id.bt_phone)
        val btnChat : Button = view.findViewById(R.id.bt_chat)

        btn_confrim.setOnClickListener {
            val intent = Intent(requireActivity(), BerandaOrderSelesaiActivity::class.java)
            startActivity(intent)
        }

        btnPhone.setOnClickListener {
            val phoneNumber = "tel:081234567890"
            val dialIntent = Intent(Intent.ACTION_DIAL, Uri.parse(phoneNumber))
            startActivity(dialIntent)
        }

        btnChat.setOnClickListener {
            val intent = Intent(requireActivity(), BerandaChatActivity::class.java)
            startActivity(intent)
        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BerandaProsesFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BerandaProsesFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}