package com.android.compose.ui.fragment

import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.android.compose.ui.page.SplashPage
import com.android.library.Compose

class SplashFragment : Fragment(), Compose {
  override fun compose() = ComposeView(requireContext()).apply { setContent { SplashPage() } }
}
