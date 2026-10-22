package com.avinash.crimealertpendant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

private val Background = Color(0xFFF4F5F7)
private val Ink = Color(0xFF17243A)
private val Muted = Color(0xFF667085)
private val Accent = Color(0xFFD92D3A)
private val BorderColor = Color(0xFFE2E6EB)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                SafetyHome()
            }
        }
    }
}

@Composable
fun SafetyHome() {
    var seconds by remember { mutableIntStateOf(0) }
    var alertActive by remember { mutableStateOf(false) }
    var selectedTab by remember { mutableIntStateOf(0) }

    LaunchedEffect(alertActive) {
        while (alertActive && seconds > 0) {
            delay(1000)
            seconds--
        }
        if (seconds == 0) alertActive = false
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding()
                .padding(horizontal = 22.dp)
        ) {
            Spacer(Modifier.height(20.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(Modifier.weight(1f)) {
                    Text(
                        "PERSONAL SAFETY",
                        color = Muted,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        letterSpacing = 1.5.sp
                    )
                    Spacer(Modifier.height(6.dp))
                    Text(
                        "Crime Alert",
                        color = Ink,
                        fontSize = 27.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        "Pendant",
                        color = Ink,
                        fontSize = 27.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = Color.White,
                    border = androidx.compose.foundation.BorderStroke(
                        1.dp, BorderColor
                    )
                ) {
                    Text(
                        "CA",
                        modifier = Modifier.padding(
                            horizontal = 16.dp, vertical = 14.dp
                        ),
                        color = Ink,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(Modifier.height(24.dp))

            Surface(
                shape = RoundedCornerShape(16.dp),
                color = Color.White,
                border = androidx.compose.foundation.BorderStroke(
                    1.dp, BorderColor
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(Modifier.weight(1f)) {
                        Text(
                            "Pendant status",
                            color = Ink,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 15.sp
                        )
                        Spacer(Modifier.height(4.dp))
                        Text(
                            "Bluetooth not connected",
                            color = Muted,
                            fontSize = 13.sp
                        )
                    }
                    Surface(
                        color = Color(0xFFFFF1D6),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            "DEMO",
                            color = Color(0xFF805600),
                            modifier = Modifier.padding(
                                horizontal = 10.dp, vertical = 6.dp
                            ),
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Spacer(Modifier.height(28.dp))

            Text(
                if (alertActive) "DEMO ALERT IN PROGRESS"
                else "Need help?",
                modifier = Modifier.fillMaxWidth(),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                color = Ink,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(Modifier.height(20.dp))

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    onClick = {
                        if (!alertActive) {
                            seconds = 60
                            alertActive = true
                        }
                    },
                    modifier = Modifier.size(184.dp),
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Accent
                    ),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 2.dp,
                        pressedElevation = 0.dp
                    )
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            "SOS",
                            fontSize = 42.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            if (alertActive) "$seconds SECONDS"
                            else "TAP TO START",
                            fontSize = 12.sp,
                            letterSpacing = 0.5.sp
                        )
                    }
                }
            }

            Spacer(Modifier.height(12.dp))

            Text(
                if (alertActive)
                    "Demo countdown. Cancel before it ends."
                else
                    "Start a demo countdown. No alert is sent.",
                modifier = Modifier.fillMaxWidth(),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                color = Muted,
                fontSize = 13.sp
            )

            if (alertActive) {
                Spacer(Modifier.height(12.dp))
                OutlinedButton(
                    onClick = {
                        alertActive = false
                        seconds = 0
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Cancel demo alert", color = Accent)
                }
            }

            Spacer(Modifier.height(24.dp))

            Text(
                "QUICK ACCESS",
                color = Muted,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.3.sp
            )

            Spacer(Modifier.height(10.dp))

            QuickAccessCard(
                title = "Emergency contacts",
                subtitle = "Manage up to 6 trusted people",
                symbol = "01",
                onClick = { selectedTab = 1 }
            )

            Spacer(Modifier.height(10.dp))

            QuickAccessCard(
                title = "My location",
                subtitle = "Location sharing not configured",
                symbol = "02",
                onClick = { selectedTab = 2 }
            )

            Spacer(Modifier.weight(1f))

            HorizontalDivider(color = BorderColor)

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(66.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                listOf("Home", "Contacts", "Settings").forEachIndexed {
                        index, label ->
                    TextButton(
                        onClick = { selectedTab = index }
                    ) {
                        Text(
                            label,
                            color = if (selectedTab == index) Ink else Muted,
                            fontWeight = if (selectedTab == index)
                                FontWeight.Bold else FontWeight.Normal
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun QuickAccessCard(
    title: String,
    subtitle: String,
    symbol: String,
    onClick: () -> Unit
) {
    Surface(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(15.dp),
        color = Color.White,
        border = androidx.compose.foundation.BorderStroke(
            1.dp, BorderColor
        )
    ) {
        Row(
            modifier = Modifier.padding(15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                symbol,
                color = Muted,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.width(14.dp))
            Column(Modifier.weight(1f)) {
                Text(
                    title,
                    color = Ink,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(Modifier.height(4.dp))
                Text(subtitle, color = Muted, fontSize = 12.sp)
            }
            Text("›", color = Muted, fontSize = 25.sp)
        }
    }
}