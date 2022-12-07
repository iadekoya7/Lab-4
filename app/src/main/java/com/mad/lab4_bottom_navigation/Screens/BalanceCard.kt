package com.mad.lab4_bottom_navigation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp

@Composable
fun BalanceCard() {
    var balance by remember { mutableStateOf(0.0) }
    var availableBalance by remember { mutableStateOf(0.0) }
    var loading by remember { mutableStateOf(false) }
    var visible by remember { mutableStateOf(false) }
    val balanceFormatted = remember(availableBalance, visible) {
        if (visible) availableBalance.toCurrencyFormat()
        else "XXX,XXX.XX"
    }
    val ledgerBalanceFormatted = remember(balance, visible) {
        if (visible) balance.toCurrencyFormat()
        else "XX,XXX.XX"
    }

    Card(
        modifier = Modifier
            .heightIn(100.dp, 170.dp)
            .fillMaxWidth()
            .padding(6.dp),
        elevation = 2.dp,
        backgroundColor = colorResource(R.color.white),
        contentColor = MaterialTheme.colors.onSecondary,
        shape = RoundedCornerShape(20.dp),
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(16.dp),
        ) {
            Text(
                text = balanceFormatted,
                style = MaterialTheme.typography.h4,
                color = MaterialTheme.colors.onSecondary,
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .weight(1f),
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "LEDGER BALANCE: $ledgerBalanceFormatted",
                    style = MaterialTheme.typography.button,
                    color = MaterialTheme.colors.onSecondary,
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .weight(1f),
                )
                if (loading) {
                    CircularProgressIndicator(
                        strokeWidth = 2.dp,
                        color = MaterialTheme.colors.onSecondary,
                        modifier = Modifier
                            .size(20.dp)
                    )
                } else {
                    IconButton(
                        onClick = {
                            if (visible) visible = false
                        },
                    ) {
                        Icon(
                            imageVector = if (visible) Icons.Outlined.Visibility else Icons.Outlined.VisibilityOff,
                            contentDescription = null
                        )
                    }
                }
            }
        }
    }

}