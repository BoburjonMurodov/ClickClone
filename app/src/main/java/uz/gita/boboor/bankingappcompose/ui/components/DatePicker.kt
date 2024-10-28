package uz.gita.boboor.bankingappcompose.ui.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.calendar.models.CalendarStyle
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Locale


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerDocked(onSelect: (Long) -> Unit) {
    var showDatePicker by remember { mutableStateOf(false) }
    val selectedDate = remember { mutableStateOf(LocalDate.now()) }

    Box(
        modifier = Modifier
            .padding(top = 8.dp)
            .fillMaxWidth()
            .height(45.dp)
            .clickable { showDatePicker = true }
            .border(1.dp, color = Color(0xFF3a3a44), shape = RoundedCornerShape(10.dp))
            .background(Color(0xFF2a2a32), shape = RoundedCornerShape(10.dp))
    ) {


        Text(
            text = "${selectedDate.value.dayOfMonth}/${selectedDate.value.monthValue}/${selectedDate.value.year}",
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 12.dp),
            color = Color.White
        )

        Icon(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 12.dp)

            ,
            imageVector = Icons.Filled.DateRange,
            contentDescription = null,
            tint = Color.White,
        )

        if (showDatePicker) {
            CalendarDialog(
                state = rememberUseCaseState(visible = true, true, onCloseRequest = { }),
                config = CalendarConfig(
                    yearSelection = true,
                    style = CalendarStyle.MONTH,
                ),
                selection = CalendarSelection.Date(
                    selectedDate = selectedDate.value
                ) { newDate ->
                    selectedDate.value = newDate
                    onSelect(convertDateToMillis("${selectedDate.value.dayOfMonth}/${selectedDate.value.monthValue}/${selectedDate.value.year}"))
                    showDatePicker = false
                },
            )
        }

    }
}

fun convertDateToMillis(dateString: String): Long {
    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    dateFormat.isLenient = false // Disable lenient parsing to prevent incorrect dates

    // Parse the string and return the time in milliseconds
    return try {
        val date = dateFormat.parse(dateString)
        date?.time ?: throw IllegalArgumentException("Invalid date format")
    } catch (e: Exception) {
        e.printStackTrace()
        0L // Return 0 if parsing fails
    }
}