import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import data.Сondition

@Composable
fun MainScreen (
    condition : Сondition,
    onLoadFile: () -> Unit,
    onSaveToExcel: () -> Unit
) {
    LazyColumn (horizontalAlignment = Alignment.CenterHorizontally) {
        item {
            Text(
                text = "Загрузка имя и размер файла в ексель таблицу",
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
        item {
            Spacer(Modifier.height(20.dp))
        }
        item {
            Button(
                onClick = onLoadFile
            ) {
                Text("Загрузить файлы")
            }
        }
        item {
            if (condition != Сondition.SAVING) {
                Button(
                    enabled = condition == Сondition.HAVE_FILES_AT_MEMORY,
                    onClick = onSaveToExcel
                ) {
                    Text("Загрузить в екселе")
                }
            } else {
                CircularProgressIndicator()
            }
        }


    }
}