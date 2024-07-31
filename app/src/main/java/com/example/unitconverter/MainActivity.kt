package com.example.unitconverter

import android.graphics.drawable.Icon
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconverter.ui.theme.Typography
import com.example.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                   unitConverter()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun unitConverter(){

    var inputValue by remember { mutableStateOf("")}
    var outputValue by remember { mutableStateOf("")}
    var inputUnit by remember { mutableStateOf("Meters")}
    var outputUnit by remember { mutableStateOf("Meters")}
    var iExpanded by remember { mutableStateOf(false)}
    var oExpanded by remember { mutableStateOf(false)}
    val conversionFactor =  remember { mutableStateOf(1.00) }
    val oConversionFactor =  remember { mutableStateOf(1.00) }


   val style = TextStyle(
        color = Color.Red,
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
//        textAlign = TextAlign.Center
    )



    fun convertUnits(){
        // ?: elvis operator
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result = (inputValueDouble * conversionFactor.value * 100.0 / oConversionFactor.value)
            .roundToInt() / 100.0
        outputValue = result.toString()
    }

    Column( modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        )    {
        // Here all the UI element will be stacked below each other

        Text("Unit Converter", style = style )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = inputValue,
            onValueChange = {
            inputValue = it

            // Here goes what should happen, when the value of our outline changes
                convertUnits()
        }, 
        label = { Text(text = "Enter Value")})
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            // Input Box
            Box {
                // Input Box
                Button(onClick = { iExpanded = true }) {
                    Text(text = inputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down ")

                }
                DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded = false  }) {
                    DropdownMenuItem(text = { Text("Centimeters")},
                        onClick = {
                            iExpanded = false
                            inputUnit = "Centimeters"
                            conversionFactor.value = 0.01
                            convertUnits()
                        }
                    )

                    DropdownMenuItem(text = { Text("Meter")},
                        onClick = {
                            iExpanded = false
                            inputUnit = "Meters"
                            conversionFactor.value = 1.00
                            convertUnits()
                        }
                    )

                    DropdownMenuItem(text = { Text("Feet")},
                        onClick = {
                            iExpanded = false
                            inputUnit = "Feet"
                            conversionFactor.value = 0.3048
                            convertUnits()
                        }
                    )


                    DropdownMenuItem(text = { Text("Millimeter")},
                        onClick = {
                            iExpanded = false
                            inputUnit = "Millimeters"
                            conversionFactor.value = 0.001
                            convertUnits()
                        }
                    )

                }
            }
            Spacer(modifier = Modifier.width(16.dp))
                // Output Box
            Box {
               // Output Box
                Button(onClick = { oExpanded = true }) {
                    Text(text = outputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down ")

                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = {oExpanded = false }) {
                    DropdownMenuItem(text = { Text("Centimeters") },
                        onClick = {
                            oExpanded = false
                            outputUnit = "Centimeters"
                            oConversionFactor.value = 0.01
                            convertUnits()
                        }
                    )

                    DropdownMenuItem(text = { Text("Meter")},
                        onClick = {
                            oExpanded = false
                            outputUnit = "Meters"
                            oConversionFactor.value = 1.00
                            convertUnits()
                        }
                    )

                    DropdownMenuItem(text = { Text("Feet")},
                        onClick = {
                            oExpanded = false
                            outputUnit = "Feet"
                            oConversionFactor.value = 0.3048
                            convertUnits()
                        }
                    )


                    DropdownMenuItem(text = { Text("Millimeter")},
                        onClick = {
                            oExpanded = false
                            outputUnit = "Millimeters"
                            oConversionFactor.value = 0.001
                            convertUnits()
                        }
                    )

                }


            }

//            val context = LocalContext.current
//            // Here all the UI element will be stacked next to each other
//            Button(onClick = { Toast.makeText(context,
//                "Thanks for Clicking!", Toast.LENGTH_LONG).show() })
//            {
//                Text(text = "Click Me!")
//
//            }

        }
        Spacer(modifier = Modifier.height(16.dp))
        // result text
        Text(text = "result: $outputValue $outputUnit",
        style = MaterialTheme.typography.headlineMedium
            )

        
    }
}

// The purpose of a preview is to visualize your Composable without running the entire application.
@Preview(showBackground = true)
@Composable
fun unitConverterPreview(){
    unitConverter()
}