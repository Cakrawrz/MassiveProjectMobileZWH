package com.infinite.massiveprojectmobilezwh.beranda

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
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
        // Konfirmasi order selesai
        val btnConfrim : Button = view.findViewById(R.id.bt_confirmation)
        btnConfrim.setOnClickListener {
            Intent(requireActivity(), BerandaOrderSelesaiActivity::class.java).also {
                startActivity(it)
            }
        }
        // Lanjut ke dial
        val btnPhone : Button = view.findViewById(R.id.bt_phone)
        btnPhone.setOnClickListener {
            val phoneNumber = "tel:081234567890"
            Intent(Intent.ACTION_DIAL, Uri.parse(phoneNumber)).also {
                startActivity(it)
            }
        }
        // Intent ke Chat
        val btnChat : Button = view.findViewById(R.id.bt_chat)
        btnChat.setOnClickListener {
            Intent(requireActivity(), BerandaChatActivity::class.java).also {
                startActivity(it)
            }
        }

        // Membaca alamat dari SharedPreferences dan menggantikan teks pada tv_maps_detail
        val savedAddress = getAddressFromSharedPreferences()
        if (savedAddress != null) {
            val tvMapsDetail: TextView = view.findViewById(R.id.tv_maps_detail)
            tvMapsDetail.text = savedAddress
        }

    }

    private fun getAddressFromSharedPreferences(): String? {
        val sharedPreferences = requireContext().getSharedPreferences("Order", AppCompatActivity.MODE_PRIVATE)
        return sharedPreferences.getString("ADDRESS", null)
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