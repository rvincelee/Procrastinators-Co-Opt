package com.example.retrofitkotlinparsingjsondemo

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import coil.compose.rememberAsyncImagePainter
import com.example.retrofitkotlinparsingjsondemo.ui.theme.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RetrofitKotlinParsingJSONDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    // on below line we are specifying theme as scaffold.
                    Scaffold(

                        // in scaffold we are specifying top bar.
                        topBar = {

                            // inside top bar we are specifying background color.
                            TopAppBar(backgroundColor = greenColor,

                                // along with that we are specifying title
                                // for our top bar.
                                title = {

                                    // in the top bar we are specifying tile as a text
                                    Text(

                                        // on below line we are specifying
                                        // text to display in top app bar.
                                        text = "JSON Parsing in Android",

                                        // on below line we are specifying
                                        // modifier to fill max width.
                                        modifier = Modifier.fillMaxWidth(),

                                        // on below line we are specifying
                                        // text alignment.
                                        textAlign = TextAlign.Center,

                                        // on below line we are specifying
                                        // color for our text.
                                        color = Color.White
                                    )
                                })
                        }) {
                        // on below line we are calling pop window
                        // dialog method to display ui.
                        volleyJSONParsing()
                    }
                }
            }
        }
    }
}

// on below line we are creating a
// pop up window dialog method
@Composable
fun volleyJSONParsing() {
    val ctx = LocalContext.current
    // on below line we are creating variables.
    val courseName = remember {
        mutableStateOf("")
    }
    val courseRequisites = remember {
        mutableStateOf("")
    }
    val courseImg = remember {
        mutableStateOf("")
    }
    val courseDesc = remember {
        mutableStateOf("")
    }
    val courseLink = remember {
        mutableStateOf("")
    }
    val progress = remember {
        mutableStateOf(true)
    }

    jsonParsing(ctx, courseName, courseRequisites, courseImg, courseDesc, courseLink, progress)

    // on the below line we are creating a column.
    Column(
        // in this column we are specifying
        // modifier to add padding and fill
        // max size
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),

        // on below line we are adding horizontal alignment
        // and vertical arrangement
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        // on below line we are creating a column.
        if (progress.value) {
            Column(
                // in this column we are specifying
                // modifier to add padding and fill
                // max size
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp),

                // on below line we are adding horizontal alignment
                // and vertical arrangement
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // below line is use to display a circular progress bar.
                CircularProgressIndicator(
                    // below line is use to add padding to our progress bar.
                    modifier = Modifier.padding(16.dp),

                    // below line is use to add color to our progress bar.
                    color = colorResource(id = R.color.purple_200),

                    // below line is use to add stroke width to our progress bar.
                    strokeWidth = Dp(value = 4F)
                )
            }
        }

        // on the below line we are adding a spacer.
        Spacer(modifier = Modifier.height(4.dp))

        // on below line we are adding image for our image view.
        Image(
            // on below line we are adding the image url
            // from which we will  be loading our image.
            painter = rememberAsyncImagePainter(courseImg.value),

            // on below line we are adding content
            // description for our image.
            contentDescription = "gfg image",

            // on below line we are adding modifier for our
            // image as wrap content for height and width.
            modifier = Modifier
                .wrapContentSize()
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(4.dp),
            alignment = Alignment.Center
        )

        // on the below line we are adding a spacer.
        Spacer(modifier = Modifier.height(10.dp))

        // on below line we are creating
        // text for course name.
        Text(
            text = courseName.value,
            modifier = Modifier.fillMaxWidth(),
            color = Color.Black,
            fontSize = 20.sp,
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Bold, textAlign = TextAlign.Center
        )

        // on below line we are adding spacer
        Spacer(modifier = Modifier.height(20.dp))

        // on below line we are adding
        // text for course prerequisites
        Text(
            text = courseRequisites.value,
            modifier = Modifier.fillMaxWidth(),
            color = Color.Black,
            fontSize = 16.sp,
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal, textAlign = TextAlign.Center
        )

        // on below line we are adding spacer
        Spacer(modifier = Modifier.height(30.dp))

        // on below line we are creating a
        // text for our description.
        Text(
            text = courseDesc.value,
            modifier = Modifier.fillMaxWidth(),
            color = Color.Black,
            fontSize = 15.sp,
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal, textAlign = TextAlign.Start
        )

        // on the below line we are creating a column.
        Column(
            // in this column we are specifying
            // modifier to add padding and fill
            // max size
            modifier = Modifier
                .fillMaxSize(),
            // on below line we are adding horizontal alignment
            // and vertical arrangement
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            // on below line we are creating a button
            Button(
                onClick = {
                    // on below line we are opening
                    // a intent to view the url.
                    val i = Intent(Intent.ACTION_VIEW)
                    i.setData(Uri.parse(courseLink.value))
                    ctx.startActivity(i)
                },
                // on below line we are
                // adding modifier for our button.
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(3.dp)
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth()
            ) {
                // on below line we are setting text as visit course
                Text(text = "Visit Course", color = Color.White)
            }
        }

    }
}

fun jsonParsing(
    ctx: Context,
    name: MutableState<String>,
    requisites: MutableState<String>,
    Img: MutableState<String>,
    Desc: MutableState<String>,
    Link: MutableState<String>,
    progress: MutableState<Boolean>,
) {

    // on below line we are creating a retrofit
    // builder and passing our base url
    // on below line we are creating a retrofit
    // builder and passing our base url
    val retrofit = Retrofit.Builder()
        .baseUrl("https://www.jsonkeeper.com/b/")

        // on below line we are calling add Converter
        // factory as GSON converter factory.
        // at last we are building our retrofit builder.
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    // below line is to create an instance for our retrofit api class.
    // below line is to create an instance for our retrofit api class.
    val retrofitAPI = retrofit.create(RetrofitAPI::class.java)

    val call: Call<CourseDataModal?>? = retrofitAPI.getCourse()

    // on below line we are making a call.
    call!!.enqueue(object : Callback<CourseDataModal?> {
        override fun onResponse(
            call: Call<CourseDataModal?>?,
            response: Response<CourseDataModal?>
        ) {
            if (response.isSuccessful()) {
                // inside the on response method.
                // on below line we are getting data from our response
                // and setting it in variables.
                val courseName: String = response.body()!!.courseName
                val courseLink: String = response.body()!!.courseLink
                val courseImg: String = response.body()!!.courseimg
                val courseDesc: String = response.body()!!.courseDesc
                val coursePreq: String = response.body()!!.Prerequisites

                progress.value = !progress.value
                // on below line we are setting
                // our data to our variables
                name.value = courseName
                Link.value = courseLink
                Img.value = courseImg
                Desc.value = courseDesc
                requisites.value = coursePreq

            }
        }

        override fun onFailure(call: Call<CourseDataModal?>?, t: Throwable?) {
            // displaying an error message in toast
            Toast.makeText(ctx, "Fail to get the data..", Toast.LENGTH_SHORT)
                .show()
        }
    })
}

