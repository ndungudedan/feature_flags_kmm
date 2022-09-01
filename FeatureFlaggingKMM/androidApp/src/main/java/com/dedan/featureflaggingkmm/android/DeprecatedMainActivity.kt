package com.dedan.featureflaggingkmm.android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.dedan.featureflaggingkmm.SeasonsResponse

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DeprecatedMainScreenNavigation()
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
@Preview
fun DeprecatedMainScreen() {
    val viewModel = remember { MainViewModel() }
    val navController= rememberNavController()
    var appBarTitle by remember { mutableStateOf(BottomNavigationScreens.Winter.resourceId) }

    val bottomNavigationItems = listOf(
        BottomNavigationScreens.Winter,
        BottomNavigationScreens.Summer,
        BottomNavigationScreens.Autumn,
        BottomNavigationScreens.Spring
    )
    Scaffold(
        topBar = {
            TopAppBar() {
                Text(text = stringResource(id = appBarTitle))
            }
        },
        bottomBar = {
            AppBottomNavigation(navController, bottomNavigationItems, onTap = {
                appBarTitle=it
            })
        },
    ) {
        NavHost(navController = navController, startDestination = BottomNavigationScreens.Winter.route) {
            composable(BottomNavigationScreens.Winter.route) {
                if(viewModel.mainState.isLoading){
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        CircularProgressIndicator()
                    }
                }
                else if(viewModel.mainState.loadError!=null){
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(
                            text = viewModel.mainState.loadError!!,
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 2.sp
                            ),
                            fontSize = 30.sp
                        )
                    }
                }
                else{
                    LazyVerticalGrid(
                        cells = GridCells.Adaptive(minSize = 128.dp)
                    ) {
                        items(viewModel.mainState.winterPhotos) { photo ->
                            PhotoView(photo.url)
                        }
                    }
                }

            }
            composable(BottomNavigationScreens.Summer.route) {
                if(viewModel.mainState.isLoading){
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        CircularProgressIndicator()
                    }
                }else if(viewModel.mainState.loadError!=null){
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(
                            text = viewModel.mainState.loadError!!,
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 2.sp
                            ),
                            fontSize = 30.sp
                        )
                    }
                }else {
                    LazyVerticalGrid(
                        cells = GridCells.Adaptive(minSize = 128.dp)
                    ) {
                        items(viewModel.mainState.summerPhotos) { photo ->
                            PhotoView(photo.url)
                        }
                    }
                }
            }
            composable(BottomNavigationScreens.Autumn.route) {
                if(viewModel.mainState.isLoading){
                    CircularProgressIndicator()
                }else if(viewModel.mainState.loadError!=null){
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(
                            text = viewModel.mainState.loadError!!,
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 2.sp
                            ),
                            fontSize = 30.sp
                        )
                    }
                }else {
                    LazyVerticalGrid(
                        cells = GridCells.Adaptive(minSize = 128.dp)
                    ) {
                        items(viewModel.mainState.autumnPhotos) { photo ->
                            PhotoView(photo.url)
                        }
                    }
                }
            }
            composable(BottomNavigationScreens.Spring.route) {
                if(viewModel.mainState.isLoading){
                    CircularProgressIndicator()
                }else if(viewModel.mainState.loadError!=null){
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(
                            text = viewModel.mainState.loadError!!,
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 2.sp
                            ),
                            fontSize = 30.sp
                        )
                    }
                }else {
                    LazyVerticalGrid(
                        cells = GridCells.Adaptive(minSize = 128.dp)
                    ) {
                        items(viewModel.mainState.springPhotos) { photo ->
                            PhotoView(photo.url)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun PhotoView(photo:String){
    Box(
        modifier = Modifier
            .height(150.dp)
            .width(150.dp)
            .padding(10.dp)
            .background(Color.White), contentAlignment = Alignment.Center
    ) {

        AsyncImage(
            model = photo,
            contentScale = ContentScale.Fit,
            contentDescription = null,
            placeholder = painterResource(R.drawable.placeholder),
        )
    }
}

@Composable
private fun AppBottomNavigation(
    navController: NavController,
    items: List<BottomNavigationScreens>,
    onTap: (name: Int) -> Unit,
) {
    BottomNavigation {
        val currentRoute = currentRoute(navController)
        items.forEach { screen ->
            BottomNavigationItem(
                icon = { Icon(screen.icon,null) },
                label = { Text(stringResource(id = screen.resourceId)) },
                selected = currentRoute?.hierarchy?.any { it.route == screen.route } == true,
                alwaysShowLabel = false, // This hides the title for the unselected items
                onClick = {
                    navController.navigate(screen.route){
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                    onTap(screen.resourceId)

                }
            )
        }
    }
}

@Composable
private fun currentRoute(navController: NavController): NavDestination? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination
}

@Composable
private fun DeprecatedMainScreenNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Index.route) {
        composable(Screen.Index.route, content = { DeprecatedMainScreen() })

    }
}
