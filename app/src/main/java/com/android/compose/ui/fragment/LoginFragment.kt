package com.android.compose.ui.fragment

import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.android.compose.ui.page.LoginPage
import com.android.library.Compose

class LoginFragment : Fragment(), Compose {
  override fun compose() = ComposeView(requireContext()).apply { setContent { LoginPage() } }
}
