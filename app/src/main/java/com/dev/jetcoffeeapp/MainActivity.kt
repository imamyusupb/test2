package com.dev.jetcoffeeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.jetcoffeeapp.component.*
import com.dev.jetcoffeeapp.model.*
import com.dev.jetcoffeeapp.ui.theme.JetCoffeeAPPTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetCoffeeAPPTheme() {
                JetCoffeeApp()
            }
        }
    }
}

@Composable
fun JetCoffeeApp(modifier: Modifier = Modifier) {
    Column(modifier = modifier.verticalScroll(rememberScrollState())) {
        Banner()
        HomeSection(
            title = stringResource(R.string.section_category),
            content = { CategoryRow() }
        )
        HomeSection(stringResource(R.string.section_best_seller_menu), Modifier) {
            MenuRow(dummyMenu)
        }
        HomeSection(stringResource(R.string.section_best_seller_menu)) {
            MenuRow(dummyBestSellerMenu)
        }
    }

}

@Composable
fun Banner(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Image(
            painter = painterResource(id = R.drawable.banner),
            contentDescription = "Banner",
            contentScale = ContentScale.Crop,
            modifier = modifier.height(150.dp)
        )
        SearchBar()
    }
}

@Composable
fun CategoryRow(
    modifier: Modifier = Modifier
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(dummyCategory, key = { it.textCategory }) { category ->
            CategoryItem(category = category)
        }
    }
}

@Composable
fun MenuRow(
    listMenu: List<Menu>,
    modifier: Modifier = Modifier
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier
    ) {
        items(listMenu, key = { it.title }) { menu ->
            MenuItem(menu)
        }
    }
}

@Composable
fun FormInputan() {
    val name = remember { mutableStateOf("") }
    OutlinedTextField(
        value = name.value,
        onValueChange = { newName ->
            name.value = newName
        },
        label = Text(text = "Name"),
        modifier = Modifier.padding(8.dp)
    )
}

@Composable
fun Timerrr(modifier: Modifier = Modifier) {
   var count by remember { mutableStateOf(0) }
    TimerrStateless(count = count, onClick = {count++})
}

@Composable
fun TimerrStateless(count:Int,onClick:()->Unit) {
    Column() {
        Text(text = "Button clicked $count")
        Button(onClick = {onClick}) {
            Text(text = "click")
        }
    }
}

@Composable
fun BottomBar(modifier: Modifier = Modifier) {
    BottomNavigation(modifier = modifier) {
        val navigationItem = listOf(
            BottomBarItem(
                title = stringResource(id = R.string.menu_home),
                icon = Icons.Default.Home
            ),
            BottomBarItem(
                title = stringResource(R.string.menu_favorite),
                icon = Icons.Default.Favorite
            ),
            BottomBarItem(
                title = stringResource(R.string.menu_profile),
                icon = Icons.Default.AccountCircle
            ),
        )
        navigationItem.map {
            BottomNavigationItem(
                icon = Icon(imgv =, contentDescription =), onClick = { /*TODO*/ }) {

            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CategoryRowPreview() {
    JetCoffeeAPPTheme {
        CategoryRow()
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun JetCoffeeAppPreview() {
    JetCoffeeAPPTheme() {
        JetCoffeeApp()
    }
}