package com.example.composecalculator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecalculator.ui.theme.ComposeCalculatorTheme
import com.example.composecalculator.ui.theme.LightGrey
import com.example.composecalculator.ui.theme.Orange

val roundButtonSize = 78.dp
val longButtonWidth = roundButtonSize * 2
val longButtonHeight = roundButtonSize

@Composable
fun Calculator(
    state: CalculatorState,
    modifier: Modifier = Modifier,
    buttonSpacing: Dp = 8.dp,
    onAction: (CalculatorAction) -> Unit,
) {

    Box(modifier = modifier) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            verticalArrangement = Arrangement.spacedBy(buttonSpacing),
        ) {
            item {
                Text(
                    text = state.number1 + (state.operation?.string ?: "") + state.number2,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 32.dp),
                    fontWeight = FontWeight.Light,
                    fontSize = 80.sp,
                    color = Color.White,
                    maxLines = 2,
                )

                Row(
                    modifier=Modifier.fillMaxWidth().padding(bottom = buttonSpacing),
                    horizontalArrangement = Arrangement.spacedBy(buttonSpacing, Alignment.CenterHorizontally),
                )
                {
                    CalculatorButton(
                        symbol = "AC",
                        modifier = Modifier
                            .background(LightGrey)
                            .width(longButtonWidth)
                            .height(longButtonHeight),
                        onClick = {
                            onAction(CalculatorAction.Clear)
                        }
                    )
                    CalculatorButton(
                        symbol = "Del",
                        modifier = Modifier
                            .background(LightGrey)
                            .width(roundButtonSize)
                            .height(roundButtonSize),
                        onClick = {
                            onAction(CalculatorAction.Delete)
                        }
                    )
                    CalculatorButton(
                        symbol = "/",
                        modifier = Modifier
                            .background(Orange)
                            .width(roundButtonSize)
                            .height(roundButtonSize),
                        onClick = {
                            onAction(CalculatorAction.Operation(CalculatorOperation.Divide))
                        }
                    )
                }

                listOf(
                    listOf(1, 2, 3, CalculatorOperation.Add),
                    listOf(4, 5, 6, CalculatorOperation.Subtract),
                    listOf(7, 8, 9, CalculatorOperation.Multiply)
                ).map { subList ->
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(bottom = buttonSpacing),
                        horizontalArrangement = Arrangement.spacedBy(buttonSpacing, Alignment.CenterHorizontally)
                    ) {
                        subList.map {
                            when (it) {
                                is Int -> CalculatorButton(
                                    symbol = it.toString(),
                                    modifier = Modifier
                                        .background(Color.DarkGray)
                                        .width(roundButtonSize)
                                        .height(roundButtonSize),
                                    onClick = {
                                        onAction(CalculatorAction.Number(it))
                                    }
                                )
                                is CalculatorOperation -> CalculatorButton(
                                    symbol = it.string,
                                    modifier = Modifier
                                        .background(Orange)
                                        .width(roundButtonSize)
                                        .height(roundButtonSize),
                                    onClick = {
                                        onAction(CalculatorAction.Operation(it))
                                    }
                                )
                            }
                        }
                    }
                }


                Row(
                    modifier = Modifier.fillMaxWidth().padding(bottom = buttonSpacing),
                    horizontalArrangement = Arrangement.spacedBy(buttonSpacing, Alignment.CenterHorizontally)
                )
                {
                    CalculatorButton(
                        symbol = "0",
                        modifier = Modifier
                            .background(LightGrey)
                            .width(longButtonWidth)
                            .height(longButtonHeight),
                        onClick = {
                            onAction(CalculatorAction.Number(0))
                        }
                    )
                    CalculatorButton(
                        symbol = ".",
                        modifier = Modifier
                            .background(LightGrey)
                            .width(roundButtonSize)
                            .height(roundButtonSize),
                        onClick = {
                            onAction(CalculatorAction.Decimal)
                        }
                    )
                    CalculatorButton(
                        symbol = "=",
                        modifier = Modifier
                            .background(Orange)
                            .width(roundButtonSize)
                            .height(roundButtonSize),
                        onClick = {
                            onAction(CalculatorAction.Calculate)
                        }
                    )
                }

            }
        }
    }
}

@Preview()
@Composable
private fun previewCompose() {
    ComposeCalculatorTheme {
        Calculator(
            state = CalculatorState(
                number1 = "34",
                number2 = "57",
                operation = CalculatorOperation.Add
            ),
            onAction = {}
        )
    }
}