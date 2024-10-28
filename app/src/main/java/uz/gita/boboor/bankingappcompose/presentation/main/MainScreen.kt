package uz.gita.boboor.bankingappcompose.presentation.main

import android.annotation.SuppressLint
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.onEach
import uz.gita.boboor.bankingappcompose.R
import uz.gita.boboor.bankingappcompose.presentation.circleRoundedFont
import uz.gita.boboor.bankingappcompose.ui.components.PaymentInput
import uz.gita.boboor.bankingappcompose.ui.components.Stories
import uz.gita.boboor.bankingappcompose.ui.theme.background
import uz.gita.boboor.bankingappcompose.ui.theme.blue
import uz.gita.boboor.bankingappcompose.utils.ServiceData
import uz.gita.boboor.bankingappcompose.utils.Services

class MainScreen : Screen {
    @Composable
    override fun Content() {
        MainScreenContent()
    }
}

@Composable
@Preview
fun PreviewMainScreen() {
    MainScreenContent()
}


@SuppressLint("RememberReturnType")
@Composable
fun MainScreenContent() {
    val systemUiController = rememberSystemUiController()

    val listState = rememberLazyListState()
    var overlayAlpha by remember { mutableFloatStateOf(1f) }

    val startColor = Color(0xFF03b9f3)
    val finalColor = Color(0xFF252429)

    val maxOffset = remember { mutableFloatStateOf(500f) }

    val rotationAnimatable by rememberInfiniteTransition().animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 10000, easing = LinearEasing), // 4 seconds for one full rotation
            repeatMode = RepeatMode.Restart
        ), label = ""
    )


    val animatedColor = remember {
        derivedStateOf {
            val totalScrollOffset = listState.firstVisibleItemIndex * listState.layoutInfo.viewportEndOffset +
                    listState.firstVisibleItemScrollOffset
            val fraction = (totalScrollOffset / maxOffset.value).coerceIn(0f, 1f)
            lerpColor(startColor, finalColor, fraction)
        }
    }


    LaunchedEffect(listState) {
        snapshotFlow { listState.firstVisibleItemScrollOffset }
            .filter { listState.firstVisibleItemIndex == 0 }
            .collect {
                overlayAlpha = (1 - it / 200f).coerceIn(0f, 1f)
            }
    }


    LaunchedEffect(listState) {
        snapshotFlow { listState.firstVisibleItemScrollOffset }
            .filter { listState.firstVisibleItemIndex == 0 }
            .onEach { scrollOffset ->
                if (scrollOffset < (maxOffset.floatValue / 2)) {
                    listState.animateScrollToItem(0)
                } else {
                    listState.animateScrollToItem(4)
                }
            }
            .collect {}
    }

    systemUiController.setStatusBarColor(animatedColor.value)


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(background)
    ) {

        Box(
            modifier = Modifier
                .aspectRatio(16 / 12f)
                .background(
                    Brush.verticalGradient(
                        listOf(
                            animatedColor.value,
                            background
                        )
                    )
                )
        )

        Icon(
            modifier = Modifier
                .size(700.dp)
                .offset(y = (LocalConfiguration.current.screenHeightDp.dp * -0.4f))
                .offset(x = (LocalConfiguration.current.screenHeightDp.dp * 0.15f))
                .graphicsLayer { rotationZ = rotationAnimatable },

            painter = painterResource(id = R.drawable.ic_click),
            contentDescription = null,
            tint = animatedColor.value
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            Header()
            Box {
//                Column(
//                    modifier = Modifier
//                        .aspectRatio(16 / 10f)
//                ) {
//
//                }
//                Column(
//                    modifier = Modifier
//                        .aspectRatio(16 / 10f)
//                ) {
//
//                }

                Column(
                    modifier = Modifier
                        .aspectRatio(16 / 10f)
                        .graphicsLayer {
                            alpha = overlayAlpha
                        }

                ) {
                    Spacer(modifier = Modifier.height(32.dp))
                    AddCardSection()
                }

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    state = listState,
                ) {

                    item {
                        Column(
                            modifier = Modifier
                                .aspectRatio(16 / 7.5f)
                                .onGloballyPositioned {
                                    maxOffset.floatValue = it.size.height.toFloat()
                                }
                        ) {

                        }
                    }

                    item {
                        Row(
                            modifier = Modifier
                                .background(Color.Transparent)
                                .padding(horizontal = 16.dp, vertical = 16.dp)
                        ) {
                            PremiumSection()
                        }
                    }

                    item {
                        Spacer(modifier = Modifier.height(8.dp))
                        ActionRow()
                    }

                    item {
                        ServicesSection()
                    }

                    item {
                        PaymentsSection()
                    }

                    item {
                        Stories()
                    }

                    item {
                        PaymentOnSpot()
                    }
                }
            }

        }
    }

}

@Composable
fun Header() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp)
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(32.dp),
            imageVector = Icons.Default.Menu,
            contentDescription = "Menu",
            tint = Color.White
        )
        Spacer(modifier = Modifier.width(8.dp))

        Row(
            modifier = Modifier
                .weight(1f)
                .height(45.dp)
                .padding(start = 8.dp, end = 16.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color(0x80252429))
                .padding(horizontal = 16.dp),

            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_main_search),
                contentDescription = null,
                tint = Color.White
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "Qidiruv",
                color = Color.White,
                fontFamily = circleRoundedFont,
            )
        }
    }
}

@Composable
fun AddCardSection() {
    Column(
        modifier = Modifier
            .aspectRatio(12 / 3.5f)
            .padding(horizontal = 32.dp)
            .background(background, RoundedCornerShape(16.dp))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Karta qo'shish",
            color = Color.White,
            fontSize = 20.sp,
        )

        Spacer(modifier = Modifier.height(16.dp))

        Icon(
            painter = painterResource(id = R.drawable.ic_add_cricle),
            contentDescription = null,
            tint = blue
        )

    }
}


@Composable
fun ActionRow() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            ActionItem(resId = R.drawable.ic_click_pass, label = "Click Pass")
        }
        Spacer(modifier = Modifier.width(8.dp))

        Column(modifier = Modifier.weight(1f)) {
            ActionItem(resId = R.drawable.ic_question, label = "Click Boom")
        }
        Spacer(modifier = Modifier.width(8.dp))

        Column(modifier = Modifier.weight(1f)) {
            ActionItem(resId = R.drawable.ic_card, label = "Kartalar")
        }
        Spacer(modifier = Modifier.width(8.dp))

        Column(modifier = Modifier.weight(1f)) {
            ActionItem(resId = R.drawable.ic_qrcode_logo, label = "QR skaner")
        }
        Spacer(modifier = Modifier.width(16.dp))
    }
}



@Composable
fun ActionItem(resId: Int, label: String) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(12))
            .background(Color(0xFF35353f))
            .aspectRatio(1f),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painterResource(id = resId),
            contentDescription = null,
            tint = blue,
            modifier = Modifier.size(32.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = label,
            color = Color.White,
            fontSize = 14.sp,
            fontFamily = circleRoundedFont
        )
    }
}

@Composable
fun PremiumSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .clip(RoundedCornerShape(12.dp))
            .background(
                brush = Brush.verticalGradient(
                    listOf(
                        Color(0xFF35353f),
                        Color(0xFF35353f)
                    )
                )

            )
            .padding(12.dp),
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(id = R.drawable.ic_click_cashback),
                    contentDescription = null,
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    "0 so'm",
                    style = TextStyle(
                        brush = Brush.verticalGradient(
                            listOf(
                                Color(0xFF02a7fc),
                                Color(0xFF0157AD)
                            )
                        ),
                    ),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text("Bu oyda cashback", color = Color.Gray, fontSize = 14.sp)
        }

        Spacer(
            modifier = Modifier
                .fillMaxHeight()
                .width(1.dp)
                .background(Color(0xFF47485a))
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp)
        )
        {

            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    modifier = Modifier
                        .size(24.dp),
                    painter = painterResource(id = R.drawable.ic_premium),
                    contentDescription = null,
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    "Premium",
                    color = Color(0xFFffbf00),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text("Obunani ulash", color = Color.Gray, fontSize = 14.sp)
        }
    }
}

@Composable
fun ServicesSection() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            "Xizmatlar",
            color = Color(0xFFb2b7cd),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        val list = listOf(
            listOf(
                ServiceData(
                    name = "Mening oilam",
                    id = R.drawable.ic_my_family,
                    color = Color(0xFFff6602),
                    type = Services.MY_FAMILY
                ),

                ServiceData(
                    name = "Mening uyim",
                    id = R.drawable.ic_home,
                    color = Color(0xFF7a2cff),
                    type = Services.MY_HOME
                ),

                ServiceData(
                    name = "Mening avto",
                    id = R.drawable.ic_car_compact,
                    color = Color(0xFF1da8f1),
                    type = Services.MY_AUTO
                ),

                ServiceData(
                    name = "Xayriya",
                    id = R.drawable.ic_charity,
                    color = Color(0xFFfe3c00),
                    type = Services.CHARITY
                ),
            ),
            listOf(
                ServiceData(
                    name = "Ovqat yetkazish BRINGO",
                    id = R.drawable.ic_food_delivery,
                    color = Color(0xFFfe3c00),
                    type = Services.FOOD_DELIVERY
                ),

                ServiceData(
                    name = "Gullar Oygul",
                    id = R.drawable.ic_flower,
                    color = Color(0xFFfe3c00),
                    type = Services.FOOD_DELIVERY,
                    isNew = true
                ),

                ServiceData(
                    name = "Uzbekistan Airways",
                    id = R.drawable.ic_flight,
                    color = Color(0xFF0ad087),
                    type = Services.UZB_AIRWAYS,
                ),
                ServiceData(
                    name = "Kinoteatrga chiptalar",
                    id = R.drawable.ic_cinema,
                    color = Color(0xFF0ad087),
                    type = Services.CINEMA,
                ),
            ),
            listOf(
                ServiceData(
                    name = "Powerbanklar q.watt",
                    id = R.drawable.ic_qwatt,
                    color = Color(0xFF8bde12),
                    type = Services.POWER_BANK,
                ),

                ServiceData(
                    name = "Click TV",
                    id = R.drawable.ic_click_tv,
                    color = Color(0xFF008cff),
                    type = Services.CLICK_TV,
                    isNew = true
                ),


                ServiceData(
                    name = "Davlat Xizmatlari",
                    id = R.drawable.ic_government_services,
                    color = Color(0xFF06b0ad),
                    type = Services.GOVERNMENT_SERVICES,
                    isNew = true
                ),


                ServiceData(
                    name = "Tanlangan to'lovlar",
                    id = R.drawable.ic_star,
                    color = Color(0xFFcc22e1),
                    type = Services.SELECTED_PAYMENTS,
                ),

                )
        )


        for (i in list.indices) {
            Row {
                for (j in 0 until list[i].size) {
                    ServiceItem(
                        modifier = Modifier.weight(1f),
                        serviceData = list[i][j]
                    )
                }
            }
        }

    }
}


@Composable
fun ServiceItem(modifier: Modifier = Modifier, serviceData: ServiceData) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(8.dp)
    ) {
        Box() {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                Spacer(modifier = Modifier.height(8.dp))

                Icon(
                    painter = painterResource(id = serviceData.id),
                    contentDescription = serviceData.name,
                    tint = serviceData.color,
                    modifier = Modifier.size(32.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = serviceData.name,
                    color = Color.White,
                    fontSize = 14.sp,
                    fontFamily = circleRoundedFont,
                    textAlign = TextAlign.Center
                )
            }
            if (serviceData.isNew) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(50))
                        .background(Brush.verticalGradient(listOf(Color(0xFFfe8685), Color(0xFFfd7373))))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                        .align(Alignment.TopEnd)
                ) {
                    Text(
                        text = "NEW",
                        color = Color.White,
                        fontSize = 14.sp,
                        fontFamily = circleRoundedFont,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }

        }

    }

}


@Composable
fun PaymentsSection() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            "Mobil aloqa uchun to'lov",
            color = Color(0xFFb2b7cd),
            fontSize = 18.sp, fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        PaymentInput()
    }
}


@Composable
fun LocationNotification() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.DarkGray)
            .padding(16.dp)
    ) {
        Text(
            "Xizmatning to'g'ri ishlashi uchun geolokatsiya yoqilishi lozim",
            color = Color.White
        )
    }
}

fun lerpColor(startColor: Color, endColor: Color, fraction: Float): Color {
    val red = (startColor.red + fraction * (endColor.red - startColor.red)).coerceIn(0f, 1f)
    val green = (startColor.green + fraction * (endColor.green - startColor.green)).coerceIn(0f, 1f)
    val blue = (startColor.blue + fraction * (endColor.blue - startColor.blue)).coerceIn(0f, 1f)
    return Color(red, green, blue)
}


@Preview
@Composable
fun PaymentOnSpot() {
    Column(modifier = Modifier.padding(16.dp)) {

        Row(verticalAlignment = Alignment.CenterVertically) {

            Text(
                "Mobil aloqa uchun to'lov",
                color = Color(0xFFb2b7cd),
                fontSize = 18.sp, fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                "Hammasi",
                color = blue,
                fontSize = 16.sp, fontWeight = FontWeight.Bold
            )

            Icon(
                painter = painterResource(id = R.drawable.ic_next),
                contentDescription = null,
                tint = blue
            )

        }
        Spacer(modifier = Modifier.height(8.dp))

        for (i in 0 until 2) {
            PaymentOnSpotItem()
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
@Preview
fun PaymentOnSpotItem(
    name: String = "Adamari Family"
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFF35353f))
            .padding(8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .aspectRatio(1f)
                .clip(RoundedCornerShape(8.dp))
        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = R.drawable.adamari),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }

        Column(modifier = Modifier.padding(horizontal = 8.dp)) {
            Text(
                text = name,
                fontSize = 18.sp,
                fontFamily = circleRoundedFont,
                fontWeight = FontWeight.Bold,
                color = Color.White,
            )

            Row(
                modifier = Modifier.padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically

            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_turn_right),
                    contentDescription = null,
                    tint = Color(0xFFb2b7cd)
                )
                Text(
                    text = "26 m",
                    fontSize = 14.sp,
                    color = Color(0xFFb2b7cd),

                    )

            }

        }

    }
}
