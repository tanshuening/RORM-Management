package com.example.rorm_management.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rorm_management.MenuActivity
import com.example.rorm_management.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

    private lateinit var binding: FragmentDashboardBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuClickListener = View.OnClickListener {
            val intent = Intent(requireContext(), MenuActivity::class.java)
            startActivity(intent)
        }

        binding.menuLayout.setOnClickListener(menuClickListener)
        binding.menuIcon.setOnClickListener(menuClickListener)
        binding.menuText.setOnClickListener(menuClickListener)

/*        val feedbackClickListener = View.OnClickListener {
            val intent = Intent(requireContext(), ReviewActivity::class.java)
            startActivity(intent)
        }

        binding.feedbackLayout.setOnClickListener(feedbackClickListener)
        binding.feedbackIcon.setOnClickListener(feedbackClickListener)
        binding.feedbackText.setOnClickListener(feedbackClickListener)

        val informationClickListener = View.OnClickListener {
            val intent = Intent(requireContext(), ProfileActivity::class.java)
            startActivity(intent)
        }

        binding.informationLayout.setOnClickListener(informationClickListener)
        binding.informationIcon.setOnClickListener(informationClickListener)
        binding.informationText.setOnClickListener(informationClickListener)

        val promotionClickListener = View.OnClickListener {
            val intent = Intent(requireContext(), PromotionActivity::class.java)
            startActivity(intent)
        }

        binding.promotionsLayout.setOnClickListener(promotionClickListener)
        binding.promotionsIcon.setOnClickListener(promotionClickListener)
        binding.promotionsText.setOnClickListener(promotionClickListener)*/
    }

    companion object {
    }
}