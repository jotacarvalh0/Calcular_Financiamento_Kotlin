package com.example.projeto_samuel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.projeto_samuel.ui.theme.Projeto_SamuelTheme
import kotlin.math.pow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FinanciamentoCalculator()
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FinanciamentoCalculator() {
    var valorPrincipal by remember { mutableStateOf(0.0) }
    var taxaDeJuros by remember { mutableStateOf(0.0) }
    var periodoEmMeses by remember { mutableStateOf(0) }

    var pagamentoMensal by remember { mutableStateOf(0.0) }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = valorPrincipal.toString(),
            onValueChange = { valorPrincipal = it.toDoubleOrNull() ?: 0.0 },
            label = { Text("Valor Principal") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = taxaDeJuros.toString(),
            onValueChange = { taxaDeJuros = it.toDoubleOrNull() ?: 0.0 },
            label = { Text("Taxa de Juros (em decimal)")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = periodoEmMeses.toString(),
            onValueChange = { it.toIntOrNull() ?: 0.0},
            label = { Text("Perido em meses") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(16.dp))

        pagamentoMensal = calcularPagamentoMensal(valorPrincipal, taxaDeJuros, periodoEmMeses)

        Text("Pagamento Mensal: $pagamentoMensal")
    }
}

fun calcularPagamentoMensal (
    valorPrincipal: Double,
    taxaDeJuros: Double,
    periodoEmMeses: Int
): Double{
    if (taxaDeJuros == 0.0) {
        return valorPrincipal / periodoEmMeses
    }
    var r = taxaDeJuros / 12
    var n = periodoEmMeses
    return valorPrincipal * (r * (1 + r ).pow(n)) / ((1 + r).pow(n) - 1)
}
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Projeto_SamuelTheme {
        FinanciamentoCalculator()
    }
}