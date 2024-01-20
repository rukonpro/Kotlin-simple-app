package com.example.codelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {


               val windowSizeClass = calculateWindowSizeClass(this)
               MySootheApp(windowSizeClass)


            // Set up NavHost with start destination


        }
    }

}



@Composable
fun MySootheApp(windowSize: WindowSizeClass){
    when (windowSize.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            MySootheAppPortrait()
        }
        WindowWidthSizeClass.Expanded -> {
            MySootheAppLandscape()
        }
    }
}



@Composable
fun MySootheAppLandscape() {
    Surface(color = MaterialTheme.colorScheme.background) {
        Row {
            SootheNavigationRail()
            HomeScreen()
        }
    }
}

@Composable
fun MySootheAppPortrait() {
    Surface {
        Scaffold(
            bottomBar = { SootheBottomNavigation() }
        ) { padding ->


            HomeScreen(Modifier.padding(padding))
        }
    }
}




@Composable
fun HomeScreen(padding:Modifier=Modifier) {
    Column(Modifier.padding()) {
        Spacer(modifier = Modifier.height(16.dp))
        SearchBar(modifier = Modifier.padding(horizontal = 16.dp))
        HomeSection(padding) {
            Text(
                text = "Align your body",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .paddingFromBaseline(top = 40.dp, bottom = 16.dp)
                    .padding(horizontal = 16.dp)
            )
            AlignYourBodyRow(padding)
        }

        HomeSection(padding) {
            Text(
                text = "Favorite your collections",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .paddingFromBaseline(top = 40.dp, bottom = 16.dp)
                    .padding(horizontal = 16.dp)
            )

            FavoriteCollectionsGrid(padding)
        }
        Spacer(modifier = Modifier.height(16.dp))

    }

}

@Composable
fun HomeSection(
    modifier: Modifier = Modifier, content: @Composable () -> Unit

) {
    Column(modifier) {
        content()

    }

}



val itemsList = (0..100).toList()


@Composable
fun AlignYourBodyRow(modifier: Modifier = Modifier) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier
    ) {

        items(itemsList) {
            AlignYourBodyElement()
        }

    }
}


@Composable
fun FavoriteCollectionsGrid(
    modifier: Modifier = Modifier
) {
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.height(168.dp)

    ) {
        items(itemsList) {
            FavoriteCollectionCard()
        }
    }
}


@Composable
fun SearchBar(modifier: Modifier = Modifier) {
    TextField(value = "", onValueChange = {}, leadingIcon = {
        Icon(
            imageVector = Icons.Default.Search, contentDescription = null
        )
    },

        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            focusedContainerColor = MaterialTheme.colorScheme.surface
        ), placeholder = {
            Text(text = "Search")
        }, modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp)


    )
}

@Composable
fun AlignYourBodyElement(
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(top = 10.dp)
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(R.drawable.layouts15_856),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(88.dp)
                .clip(CircleShape)

        )

        Text(text = "Inversion")
    }
}


@Composable
fun FavoriteCollectionCard(modifier: Modifier = Modifier) {
    Surface(
        shape = MaterialTheme.shapes.medium, modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically, modifier = Modifier.width(260.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.nature_meditations),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(80.dp)
            )
            Text(
                text = "Nature Meditations", modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }

}


@Composable
private fun SootheBottomNavigation(
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier=modifier,
        containerColor = MaterialTheme.colorScheme.surfaceVariant
    ) {
        NavigationBarItem(selected = true, onClick = { /*TODO*/ }, label = {
            Text("Home")
        },

            icon = {
                Icon(imageVector = Icons.Default.Home, contentDescription = null)
            })


        NavigationBarItem(selected = false, onClick = { /*TODO*/ }, label = {
            Text("Profile")
        },

            icon = {
                Icon(imageVector = Icons.Default.Person, contentDescription = null)
            })
    }
}



@Composable
private fun SootheNavigationRail(modifier: Modifier = Modifier) {
    NavigationRail(
        modifier = modifier.padding(start = 8.dp, end = 8.dp),
        containerColor = MaterialTheme.colorScheme.background,

        ) {
        Column(
            modifier = modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            NavigationRailItem(
                icon = {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = null
                    )
                },
                label = {
                    Text("Home")
                },
                selected = true,
                onClick = {}
            )
            Spacer(modifier = Modifier.height(8.dp))

            NavigationRailItem(
                icon = {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null
                    )
                },
                label = {
                    Text("Profile")
                },
                selected = false,
                onClick = {}
            )
        }
    }
}




