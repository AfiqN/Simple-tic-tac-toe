package com.afiqn.gameapp.ui.landingpage

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.afiqn.gameapp.R
import com.afiqn.gameapp.ui.components.ListCreditEmail
import com.afiqn.gameapp.ui.components.ListCreditUrl

@Composable
fun CreditScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Credit:",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .padding(top = 16.dp)
        )
        CreditParagraph(
            modifier = Modifier.padding(top = 16.dp)
        )
        CreditInformation(
            modifier = Modifier.padding(top = 16.dp)
        )
        Spacer(Modifier.weight(1f))
        Text(
            text = "©2024 ❤️",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )
    }
}

@Composable
fun CreditParagraph(
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.credit_desc_app),
                modifier = Modifier
            )
        }
    }
}

@Composable
fun CreditInformation(
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Author's information:")

            ListCreditUrl(
                modifier = Modifier.padding(top = 8.dp),
                icon = painterResource(id = R.drawable.ic_linkedin),
                text = "Ahmad Taufiq Nur Rohman",
                uri = "https://www.linkedin.com/in/afiqnur/",
                context = LocalContext.current
            )

            ListCreditUrl(
                modifier = Modifier.padding(top = 8.dp),
                icon = painterResource(id = R.drawable.ic_github),
                text = "AfiqN",
                uri = "https://github.com/AfiqN",
                context = LocalContext.current
            )

            ListCreditEmail(
                modifier = Modifier.padding(top = 8.dp),
                icon = painterResource(id = R.drawable.ic_email),
                text = "afiq20030602@gmail.com",
                email = "afiq20030602@gmail.com",
                context = LocalContext.current
            )
        }
    }
}

@Preview(showBackground = true )
@Composable
fun CreditPagePreview() {
    CreditScreen()
}
