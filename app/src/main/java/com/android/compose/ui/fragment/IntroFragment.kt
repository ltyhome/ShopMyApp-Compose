package com.android.compose.ui.fragment

import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.android.compose.ui.page.IntroPage
import com.android.library.Compose

class IntroFragment : Fragment(), Compose {
  override fun compose() =
      ComposeView(requireContext()).apply { setContent { IntroPage(findNavController()) } }
}
