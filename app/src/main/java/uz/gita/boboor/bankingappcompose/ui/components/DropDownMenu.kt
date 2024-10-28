package uz.gita.boboor.bankingappcompose.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.gita.boboor.bankingappcompose.presentation.register.Gender


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyDropDownMenu(onSelect: (Gender) -> Unit) {
    val list = listOf(Gender.ERKAK, Gender.AYOL)

    var selectedText = remember {
        mutableStateOf(list.first())
    }

    var isExpanded = remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .padding(top = 8.dp)
            .fillMaxWidth()
            .height(45.dp)
        ,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        ExposedDropdownMenuBox(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, color = Color(0xFF3a3a44), shape = RoundedCornerShape(10.dp))
                .background(Color(0xFF2a2a32), shape = RoundedCornerShape(10.dp)),
            expanded = isExpanded.value,
            onExpandedChange = { isExpanded.value = !isExpanded.value }

        ) {

            TextField(
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                ),

                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
                    .border(1.dp, color = Color(0xFF3a3a44), shape = RoundedCornerShape(10.dp)),
                value = selectedText.value.name,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded.value) }
            )

            ExposedDropdownMenu(
                modifier = Modifier
                    .background(Color(0xFF2a2a32)),
                expanded = isExpanded.value,
                onDismissRequest = {
                    isExpanded.value = false
                }) {

                list.forEachIndexed() { index, text ->
                    DropdownMenuItem(
                        text = { Text(text = text.name) },
                        colors = MenuDefaults.itemColors(textColor = Color.White),
                        onClick = {
                            selectedText.value = list[index]
                            onSelect(selectedText.value)
                            isExpanded.value = false
                        }, contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                    )
                }
            }

        }

        Text(
            modifier = Modifier.padding(4.dp),
            text = "",
            color = Color.Red,
            fontSize = 12.sp,
        )
    }
}
