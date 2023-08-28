package com.assignment.assessment.ui

//import android.content.Intent
//import android.os.Bundle
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import com.assignment.assessment.R
//import com.assignment.assessment.databinding.FragmentSummaryBinding
//
//
//class SummaryFragment : Fragment() {
//    private  var binding :FragmentSummaryBinding?= null
////    private val binding get() = _binding
//
//
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        binding = FragmentSummaryBinding.inflate(inflater, container , false)
//        return binding?.root
//
//    }
//
//    override fun onResume() {
//        super.onResume()
//       binding?.fabAddBill?.setOnClickListener {
//           startActivity(Intent(requireContext(), AddBillActivity::class.java))
//
//       }
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        binding = null
//    }
//
//}

//

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.assignment.assessment.R
import com.assignment.assessment.databinding.FragmentSummaryBinding
import com.assignment.assessment.viewmodel.BillsViewModel
class SummaryFragment : Fragment() {
    private var binding: FragmentSummaryBinding? = null
    //    private val binding get() = binding!!
    private lateinit var billsViewModel: BillsViewModel
    private lateinit var adapter:SaveBillAdapter// Initialize your BillAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        billsViewModel = ViewModelProvider(requireActivity()).get(BillsViewModel::class.java)
        adapter = SaveBillAdapter(requireContext(), R.layout.item_bill, mutableListOf())
        // Set the adapter to your ListView or RecyclerView
        binding?.listViewBills?.adapter = adapter
        // Observe the list of bills from the ViewModel and update the adapter
        billsViewModel.getAllBills().observe(viewLifecycleOwner, Observer { bills ->
            adapter.clear()
            adapter.addAll(bills)
            adapter.notifyDataSetChanged()
        })

        binding?.fabAddBill?.setOnClickListener {
            startActivity(Intent(requireContext(),AddBillActivity::class.java))
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSummaryBinding.inflate(layoutInflater,container,false)
        return  binding?.root

    }
    override fun onResume() {
        super.onResume()
        binding?.fabAddBill?.setOnClickListener {
            startActivity(Intent(requireContext(),AddBillActivity::class.java))
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        binding=null
    }
}
