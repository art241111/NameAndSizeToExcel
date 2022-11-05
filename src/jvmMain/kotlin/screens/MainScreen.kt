import androidx.compose.foundation.layout.Column
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
import data.FileData
import data.Сondition
import views.DataTable

@Composable
fun MainScreen(
    condition: Сondition,
    onLoadFile: () -> Unit,
    onSaveToExcel: () -> Unit,
    filesData: List<FileData>
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        Text(
            text = "Загрузка имя и размер файла в ексель таблицу",
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.height(20.dp))

        Button(
            onClick = onLoadFile
        ) {
            Text("Загрузить файлы")
        }
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


//        DataTable(filesData)


    }
}