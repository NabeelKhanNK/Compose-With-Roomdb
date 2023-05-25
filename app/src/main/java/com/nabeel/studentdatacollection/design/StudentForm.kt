package com.nabeel.studentdatacollection.design

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.nabeel.studentdatacollection.database.dao.StudentDao
import com.nabeel.studentdatacollection.database.entity.StudentInfo
import com.nabeel.studentdatacollection.ui.theme.Purple700
import com.nabeel.studentdatacollection.widgets.CheckBoxUI
import com.nabeel.studentdatacollection.widgets.RadioButtonUi
import com.nabeel.studentdatacollection.widgets.SpacerUI
import com.nabeel.studentdatacollection.widgets.SpinnerUI
import kotlinx.coroutines.launch

@Composable
fun StudentForm(studentDao: StudentDao, navController: NavHostController) {
    val scaffoldState = rememberScaffoldState()
    val mContext = LocalContext.current
    var name by remember {
        mutableStateOf("")
    }

    val scope = rememberCoroutineScope()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState
    ) {
        Column() {
            TopAppBar(title = {
                Text(text = "Form", color = Color.White, textAlign = TextAlign.Center)
            }, modifier = Modifier.align(CenterHorizontally),
                backgroundColor = Purple700,
                navigationIcon = {
                    IconButton(onClick = { }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Menu Btn", tint = Color.White)
                    }
                },
//                backgroundColor = Color.Transparent,
                contentColor = Color.Gray,
                elevation = 2.dp)
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                TextField(
                    value = name,
                    label = {
                        Text(text = "Name")
                    },
                    onValueChange = {
                        name = it
                    },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                SpacerUI()
                Text(text = "Gender")
                SpacerUI()
                val gender = RadioButtonUi(scaffoldState)
                SpacerUI()
                Text(text = "Class")
                SpacerUI()
                val inClass = SpinnerUI()
                SpacerUI()
                Text(text = "Subjects")
                SpacerUI()
                val subjects = CheckBoxUI(scaffoldState = scaffoldState)
                SpacerUI()
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center) {
                    Button(
                        modifier = Modifier.padding(8.dp),
                        onClick = {
                            val studentData = StudentInfo(0,name,gender,inClass,subjects)
                            scope.launch {
                                val id = studentDao.insertStudentInfo(studentData)
                                var mssg: String  = ""
                                mssg = if (id>0) "Add Successfully" else "Failed"
                                Toast.makeText(mContext, mssg, Toast.LENGTH_LONG).show()
                                navController.popBackStack()
                            }
                        }) {
                        Text(text = "Submit")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview(){
//    StudentForm(studentDao)
}