
package com.assignment.assessment.ui
import com.assignment.assessment.ui.Login
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.content.Intent
import android.widget.Button
import com.assignment.assessment.R


class SettingsBillsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_settings_bills, container, false)


        val logoutButton = view.findViewById<Button>(R.id.logoutButton)


        logoutButton.setOnClickListener {
            logout()
        }

        return view
    }


    private fun logout() {

        val intent = Intent(activity, Login::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        activity?.finish()
    }
}

