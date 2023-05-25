package com.nabeel.studentdatacollection.design

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.nabeel.studentdatacollection.Screen
import com.nabeel.studentdatacollection.database.dao.StudentDao
import com.nabeel.studentdatacollection.database.entity.StudentInfo
import com.nabeel.studentdatacollection.ui.theme.Purple700

@Composable
fun StudentList(navController: NavHostController, studentDao: StudentDao) {
    val scaffoldState = rememberScaffoldState()
    val al = remember {
        mutableListOf<StudentInfo>()
    }
    studentDao.getAllData().observeForever {
        al.clear()
        al.addAll(it)
    }


    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        Column() {
            TopAppBar(title = {
                Text(text = "Form", color = Color.White, textAlign = TextAlign.Center)
            }, modifier = Modifier.align(Alignment.CenterHorizontally),
                backgroundColor = Purple700,
                navigationIcon = {
                    IconButton(onClick = { }) {
                        Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu Btn", tint = Color.White)
                    }
                },
//                backgroundColor = Color.Transparent,
                contentColor = Color.Gray,
                elevation = 2.dp)
            Box(modifier = Modifier.fillMaxSize()) {
                LazyColumn{

                    itemsIndexed(
                        items = al
//                    items = listOf("Student 1","Student 2","Student 3","Student 4")
                    ) { _, String ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 8.dp),
                            shape = RoundedCornerShape(6.dp),
                            elevation = 4.dp
                        ) {
                            Column(modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)) {
                                Text(
                                    text = "Name : ${String.name}",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Start,
                                    modifier = Modifier
                                        .padding(8.dp)
                                )
                                Text(
                                    text = "Class : ${String.in_class}",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Start,
                                    modifier = Modifier
                                        .padding(8.dp)
                                )
                                Text(
                                    text = "Gender : ${String.gender}",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Start,
                                    modifier = Modifier
                                        .padding(8.dp)
                                )
                                Text(
                                    text = "Subjects : ${String.subjects.replace("[","").replace("]","")}",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Start,
                                    modifier = Modifier
                                        .padding(8.dp)
                                )
                            }
                        }

                    }
                }
                FloatingActionButton(modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp),
                    backgroundColor = Color.Blue,
                    onClick = {
                        navController.navigate(Screen.FormScreen.route)
                    }) {
                    Icon(Icons.Filled.Add,"Add Student Data", tint = Color.White)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview2(){
//    StudentList()
}