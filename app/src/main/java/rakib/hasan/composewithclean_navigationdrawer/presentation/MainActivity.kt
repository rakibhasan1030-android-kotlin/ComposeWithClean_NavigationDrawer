package rakib.hasan.composewithclean_navigationdrawer.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import rakib.hasan.composewithclean_navigationdrawer.R
import rakib.hasan.composewithclean_navigationdrawer.presentation.components.AppBar
import rakib.hasan.composewithclean_navigationdrawer.presentation.components.DrawerItem
import rakib.hasan.composewithclean_navigationdrawer.ui.theme.ComposeWithClean_NavigationDrawerTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeWithClean_NavigationDrawerTheme {
                val drawerState = rememberDrawerState(DrawerValue.Closed)
                val scope = rememberCoroutineScope()
                val items = listOf(Icons.Default.Favorite, Icons.Default.Face, Icons.Default.Email)

                val drawerItems = listOf<DrawerItem>(
                    DrawerItem(
                        "home",
                        "Home",
                        "Home",
                        ImageVector.vectorResource(id = R.drawable.ic_home)
                    ),
                    DrawerItem(
                        "about",
                        "About",
                        "About",
                        ImageVector.vectorResource(id = R.drawable.ic_about)
                    ),
                )
                val selectedItem = remember { mutableStateOf(drawerItems[0]) }
                ModalNavigationDrawer(
                    drawerState = drawerState,
                    drawerContent = {
                        ModalDrawerSheet {
                            Spacer(Modifier.height(12.dp))
                            drawerItems.forEach { item ->
                                NavigationDrawerItem(
                                    icon = { Icon(item.icon, contentDescription = null) },
                                    label = { Text(item.title) },
                                    selected = item == selectedItem.value,
                                    onClick = {
                                        scope.launch { drawerState.close() }
                                        selectedItem.value = item
                                    },
                                    modifier = Modifier
                                        .requiredWidth(250.dp)
                                        .background(
                                            Color(Color.Transparent.value),
                                            RoundedCornerShape(topEnd = 16.dp, bottomEnd = 16.dp)
                                        )
                                        .padding(NavigationDrawerItemDefaults.ItemPadding),
                                )
                            }
                        }
                    },
                    content = {
                        Scaffold(
                            topBar = {
                                AppBar {
                                    scope.launch {
                                        if (drawerState.isClosed) drawerState.open()
                                        else drawerState.close()
                                    }
                                }
                            },
                        ) { paddingValues ->
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(paddingValues),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(text = if (drawerState.isClosed) "This is main text" else "")
                            }
                        }
                    }
                )
            }
        }
    }
}