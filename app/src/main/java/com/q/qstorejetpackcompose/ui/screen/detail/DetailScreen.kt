package com.q.qstorejetpackcompose.ui.screen.detail

import androidx.annotation.DrawableRes
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.q.qstorejetpackcompose.R
import com.q.qstorejetpackcompose.di.Injection
import com.q.qstorejetpackcompose.ui.common.UiState
import com.q.qstorejetpackcompose.ui.screen.ViewModelFactory
import com.q.qstorejetpackcompose.ui.theme.QStoreJetpackComposeTheme

@Composable
fun DetailScreen(
    phoneId: Long,
    viewModel: DetailPhoneViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository()
        )
    ),
    navigateBack: () -> Unit,
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getPhoneById(phoneId)
            }
            is UiState.Success -> {
                val data = uiState.data
                DetailContent(
                    image = data.phone.photoUrl,
                    name = data.phone.name,
                    brand = data.phone.brand,
                    price = data.phone.price,
                    description = data.phone.description,
                    releaseDate = data.phone.releaseDate,
                    year = data.phone.year,
                    onBackClick = navigateBack
                )
            }
            is UiState.Error -> {}
        }
    }
}


@Composable
fun DetailContent(
    @DrawableRes image: Int,
    name: String,
    brand: String,
    price: String,
    description: String,
    releaseDate: String,
    year: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Icon(
        imageVector = Icons.Default.ArrowBack,
        contentDescription = "Back",
        modifier = Modifier
            .padding(16.dp)
            .clickable { onBackClick() }
    )
    Column(
        modifier = modifier
    ) {
        ImagesDetail(image = image, modifier = modifier)
        Spacer(modifier = modifier.width(24.dp))
        ContentDescriptionDetail(name, brand, price, description, releaseDate, year)
    }
}

@Composable
fun ImagesDetail(
    image: Int,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .padding(48.dp)
            .clip(CircleShape)
    ) {
        Card(
            modifier = modifier
                .fillMaxWidth()
        ) {
            Image(
                modifier = modifier.padding(24.dp),
                painter = painterResource(image),
                contentDescription = null,
                contentScale = ContentScale.Fit
            )
        }
    }
}

@Composable
fun ContentDescriptionDetail(
    name: String,
    brand: String,
    price: String,
    description: String,
    releaseDate: String,
    year: String,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp)
                .verticalScroll(rememberScrollState())
                .weight(weight =1f, fill = false)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = modifier.padding(
                        start = 18.dp,
                        top = 18.dp,
                        end = 18.dp,
                    ),
                    text = name,
                    style = MaterialTheme.typography.displaySmall
                )
                YearPhone(year = year, modifier = modifier)
            }
            Text(
                modifier = modifier.padding(
                    start = 18.dp,
                    bottom = 12.dp
                ),
                text = releaseDate,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                modifier = modifier.padding(
                    start = 18.dp,
                    bottom = 12.dp
                ),
                text = brand,
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                modifier = modifier.padding(start = 18.dp),
                text = price,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                modifier = modifier.padding(18.dp),
                text = description,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Composable
fun YearPhone(
    year: String,
    modifier: Modifier = Modifier,
) {
    Box {
        Card(
            modifier = modifier
                .padding(8.dp)
                .width(44.dp)
                .height(22.dp)
        ) {
            Text(
                modifier = modifier
                    .fillMaxWidth()
                    .background(Color.LightGray)
                    .padding(4.dp),
                text = year,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailContentPreview() {
    QStoreJetpackComposeTheme {
        DetailContent(
            image = R.drawable.phone_1,
            name = "Note 5",
            brand = "Xiaomi",
            price = "$100",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
            releaseDate = "10 May",
            year = "2022",
            onBackClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ImagesDetailPreview() {
    QStoreJetpackComposeTheme {
        ImagesDetail(R.drawable.phone_1)
    }
}

@Preview(showBackground = true)
@Composable
fun ContentDescriptionPreview() {
    QStoreJetpackComposeTheme {
        ContentDescriptionDetail(
            "Reno4 Pro",
            "Oppo",
            "$450",
            "This product presents the latest innovations in charging. OPPO Reno4 Pro features 65W SuperVOOC 2.0 charging. 65 W of power allows the battery to be fully charged 100% in just 35 minutes. The right choice for those of you who can't get away from your cellphone every day.",
            "12 June",
            "2020"
        )
    }
}