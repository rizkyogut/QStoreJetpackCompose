package com.q.qstorejetpackcompose.ui.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.q.qstorejetpackcompose.R
import com.q.qstorejetpackcompose.ui.components.ProfileItem
import com.q.qstorejetpackcompose.ui.theme.QStoreJetpackComposeTheme

@ExperimentalMaterial3Api
@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit,
) {
    Column(
        modifier = modifier
            .padding(16.dp)
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Back",
            modifier = Modifier
                .padding(16.dp)
                .clickable { navigateBack() }
        )
        Image(
            modifier = modifier
                .fillMaxWidth()
                .height(360.dp)
                .padding(16.dp)
                .clip(CircleShape),
            painter = painterResource(R.drawable.profile),
            contentScale = ContentScale.FillWidth,
            contentDescription = "me"
        )
        Spacer(modifier = modifier.height(8.dp))
        ProfileItem(title = "Name", field = "Muhammad Rizky Maulana")
        ProfileItem(title = "Email", field = "mrizky.maul99@gmail.com")
        ProfileItem(title = "Kota", field = "Jakarta")
    }
}

@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    QStoreJetpackComposeTheme {
        ProfileScreen(navigateBack = {})
    }
}