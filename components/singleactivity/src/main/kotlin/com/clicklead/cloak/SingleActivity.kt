package com.clicklead.cloak

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.clicklead.cloak.composable.screen.WebViewScreen
import com.clicklead.cloak.ui.theme.CloakTheme

class SingleActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CloakTheme {
                WebViewScreen()
            }
        }
    }
}



