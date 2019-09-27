package pl.mbak.robolectric_alertdialog_issue

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_main.*

class MainPassingFragment : Fragment() {

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_main, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    textView.setOnClickListener {
      showLogoutDialog()
    }
  }

  private fun showLogoutDialog() {
    context?.let {
      AlertDialog.Builder(it)
          .setMessage(R.string.message_label)
          .setTitle(R.string.title_label)
          .setNegativeButton(R.string.cancel_label) { dialog, _ -> dialog.dismiss() }
          .setPositiveButton(R.string.ok_label) { dialog, _ -> dialog.dismiss() }
          .show()
    }
  }
}
