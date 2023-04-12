package com.example.littlelemon

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.littlelemon.ui.theme.LittleLemonColor

@Composable
fun LowerPanel(navController: NavHostController, dishes: List<Dish> = listOf()) {
    Column {
        WeeklySpecialCard()
        LazyColumn {
            itemsIndexed(dishes) { _, dish ->
                MenuDish(navController, dish)
            }
        }
    }
}

@Composable
fun WeeklySpecialCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(R.string.weekly_special),
            style = MaterialTheme.typography.h1,
            modifier = Modifier
                .padding(8.dp)
        )
    }
}


/*
@Composable
fun WeeklySpecialCard() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(32.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(
                text = stringResource(R.string.weekly_special),
                style = MaterialTheme.typography.h1.copy(fontSize = 25.sp),

            )
        }
    }
}

 */



@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MenuDish(navController: NavHostController? = null, dish: Dish) {
    Card(
        onClick = {
            Log.d("AAA", "Click ${dish.id}")
            navController?.navigate(DishDetails.route + "/${dish.id}")
        },
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(start = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Column {
                Text(
                    text = dish.name,
                    style = MaterialTheme.typography.h2
                )
                Text(
                    text = dish.description,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier
                        .fillMaxWidth(0.75f)
                        .padding(top = 5.dp, bottom = 5.dp)
                )
                Text(
                    text = "$${dish.price}",
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier.padding(bottom = 8.dp
                    )
                )
            }
            Image(
                painter = painterResource(id = dish.imageResource),
                contentDescription = dish.name,
                modifier = Modifier
                    .padding(start = 16.dp)
                    .size(72.dp)
                    .clip(RoundedCornerShape(10.dp))
            )
        }
    }
    Divider(
        modifier = Modifier.padding(start = 8.dp, end = 8.dp),
        thickness = 1.dp,
        color =  LittleLemonColor.yellow
    )
}

