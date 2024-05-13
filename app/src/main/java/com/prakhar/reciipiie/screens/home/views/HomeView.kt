package com.prakhar.reciipiie.screens.home.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.prakhar.reciipiie.R

@Composable
fun HomeView() {

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(end = 15.dp, start = 15.dp)
    ) {
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            Text(
                "\uD83D\uDC4B Hey <user first name>", fontSize = 20.sp
            )
            Text("Discover tasty and healthy receipt", fontSize = 15.sp)
            ReciipiieSearchBar()
        }
    }
}

@Composable
fun ReciipiieSearchBar(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 10.dp, bottom = 20.dp)
            .background(Color(0xFFF2F7FD), MaterialTheme.shapes.medium),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = "Search",
            modifier = Modifier.padding(start = 16.dp)
        )

        Text(
            text = stringResource(id = R.string.search_recipe),
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
