package com.kxmillx.focusmate.presentation.pomodoro

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBarDefaults.centerAlignedTopAppBarColors
import androidx.compose.material3.TopAppBarDefaults.pinnedScrollBehavior
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.cos
import kotlin.math.sin

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PomodoroScreen() {
    val scrollBehavior = pinnedScrollBehavior(rememberTopAppBarState())
    val scope = rememberCoroutineScope()

    var focusSessionsCount by remember { mutableIntStateOf(0) }
    var secondsRemaining by remember { mutableIntStateOf(25 * 60) }
    var isTimerRunning by remember { mutableStateOf(false) }
    var timerJob by remember { mutableStateOf<Job?>(null) }

    val totalTime = 25 * 60

    val formattedTime = remember(secondsRemaining) {
        val minutes = secondsRemaining / 60
        val seconds = secondsRemaining % 60
        String.format("%02d:%02d", minutes, seconds)
    }

    LaunchedEffect(isTimerRunning) {
        if (isTimerRunning) {
            timerJob?.cancel()
            timerJob = scope.launch {
                while (secondsRemaining > 0) {
                    delay(1000L)
                    secondsRemaining--
                }
                isTimerRunning = false
                focusSessionsCount++
            }
        } else {
            timerJob?.cancel()
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Pomodoro",
                        maxLines = 1,
                        fontSize = 24.sp
                    )
                },
                scrollBehavior = scrollBehavior,
                colors = centerAlignedTopAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color(0xFF222222)
                )
            )
        },
        containerColor = Color.White
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            color = Color.White
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(260.dp)
                        .padding(top = 32.dp)
                ) {
                    val progress = 1f - (secondsRemaining.toFloat() / totalTime)

                    Canvas(modifier = Modifier.size(240.dp)) {

                        val strokeWidth = 8.dp.toPx()
                        val radius = size.minDimension / 2 - strokeWidth / 2
                        val center = Offset(size.width / 2, size.height / 2)

                        drawArc(
                            color = Color(0xFFE0E0E0),
                            startAngle = -90f,
                            sweepAngle = 360f,
                            useCenter = false,
                            style = Stroke(width = strokeWidth)
                        )

                        drawArc(
                            color = Color.Blue,
                            startAngle = -90f,
                            sweepAngle = progress * 360f,
                            useCenter = false,
                            style = Stroke(width = strokeWidth)
                        )


                        if (progress > 0f) {
                            val angleRad = Math.toRadians((progress * 360 - 90).toDouble())
                            val x = center.x + radius * cos(angleRad).toFloat()
                            val y = center.y + radius * sin(angleRad).toFloat() - 16

                            drawCircle(
                                color = Color.Blue,
                                radius = 12.dp.toPx(),
                                center = Offset(x, y)
                            )
                        }
                    }

                    Text(
                        text = formattedTime,
                        fontSize = 42.sp,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )
                }

                Text(
                    text = "Completed focus sessions: $focusSessionsCount",
                    fontSize = 18.sp,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Row {
                    Button(
                        onClick = {
                            if (!isTimerRunning) {
                                isTimerRunning = true
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = Color.Blue
                        ),
                        border = BorderStroke(1.dp, Color.Blue),
                        shape = RoundedCornerShape(12.dp),
                        enabled = !isTimerRunning
                    ) {
                        Text("Start")
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Button(
                        onClick = {
                            isTimerRunning = false
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = Color.Blue
                        ),
                        border = BorderStroke(1.dp, Color.Blue),
                        shape = RoundedCornerShape(12.dp),
                        enabled = isTimerRunning
                    ) {
                        Text("Pause")
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Button(
                        onClick = {
                            isTimerRunning = false
                            secondsRemaining = totalTime
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = Color.Blue
                        ),
                        border = BorderStroke(1.dp, Color.Blue),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Reset")
                    }
                }
            }
        }
    }
}
