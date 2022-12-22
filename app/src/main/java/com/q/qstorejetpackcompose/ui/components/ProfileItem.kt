package com.q.qstorejetpackcompose.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.q.qstorejetpackcompose.ui.theme.QStoreJetpackComposeTheme

@ExperimentalMaterial3Api
@Composable
fun ProfileItem(
    title: String,
    field: String,
    modifier: Modifier = Modifier,
) {
    ElevatedCard(
        modifier = modifier.fillMaxWidth()
            .padding(8.dp),
    ) {
        Column(modifier.padding(
            start = 16.dp,
            top = 8.dp,
            end = 8.dp,
            bottom = 8.dp
        )) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = modifier.height(8.dp))
            Text(
                modifier = modifier.padding(bottom = 2.dp),
                text = field,
                style = MaterialTheme.typography.titleMedium
            )
            Divider(color = MaterialTheme.colorScheme.onPrimary, thickness = 2.dp)
        }
    }

}

@ExperimentalMaterial3Api
@Composable
@Preview(showBackground = true)
fun ProfileItemReview() {
    QStoreJetpackComposeTheme {
        ProfileItem(title = "Email", field = "test@gmail.com")
    }
}
