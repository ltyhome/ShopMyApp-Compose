package com.android.compose.ui.fragment.nav

import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.android.compose.ui.page.nav.HomePage
import com.android.library.Compose

class HomeFragment : Fragment(), Compose {
  override fun compose() =
      ComposeView(requireContext()).apply { setContent { HomePage(findNavController()) } }
}
