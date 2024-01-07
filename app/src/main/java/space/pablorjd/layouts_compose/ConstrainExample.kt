package space.pablorjd.layouts_compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout

// @Preview
@Composable
fun ConstrainExampleOne() {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {

        val (boxRed, boxBlue, boxYellow, boxMagenta, boxGray) = createRefs() // creando ids para cada una de las cajas

        Box(modifier = Modifier
            .size(125.dp)
            .background(Color.Red)
            .constrainAs(boxRed) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            })
        Box(modifier = Modifier
            .size(125.dp)
            .background(Color.Blue)
            .constrainAs(boxBlue) {
                top.linkTo(boxRed.bottom)
                start.linkTo(boxRed.end)
            })
        Box(modifier = Modifier
            .size(125.dp)
            .background(Color.Yellow)
            .constrainAs(boxYellow) {
                bottom.linkTo(boxRed.top)
                end.linkTo(boxRed.start)
            })
        Box(modifier = Modifier
            .size(125.dp)
            .background(Color.Magenta)
            .constrainAs(boxMagenta) {
                bottom.linkTo(boxRed.top)
                start.linkTo(boxRed.end)
            })
        Box(modifier = Modifier
            .size(125.dp)
            .background(Color.Gray)
            .constrainAs(boxGray) {
                top.linkTo(boxRed.bottom)
                end.linkTo(boxRed.start)
            })
    }
}


@Composable
fun ConstrainExampleGuide() {

    ConstraintLayout(Modifier.fillMaxSize()) {
        val boxRed = createRef() //creando referencia
        val topGuide =
            createGuidelineFromTop(0.1f) // defieniendo el porcentaje de la guia que es un float, tambien se pueden usar DP
        val startGuide = createGuidelineFromStart(0.25f)

        Box(modifier = Modifier
            .size(125.dp)
            .background(Color.Red)
            .constrainAs(boxRed) {
                top.linkTo(topGuide)
                start.linkTo(startGuide)
            }) {
        }
    }
}


@Composable
fun ConstrainBarrier() {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (boxRed, boxYellow, boxGreen) = createRefs()
        val barrier = createEndBarrier(
            boxRed,
            boxGreen
        ) // un barrier o barrera sirve para definir limites dentro del constrainelayout
        Box(modifier = Modifier
            .size(125.dp)
            .background(Color.Green)
            .constrainAs(boxGreen) {
                start.linkTo(parent.start, margin = 16.dp)
            })
        Box(modifier = Modifier
            .size(235.dp)
            .background(Color.Red)
            .constrainAs(boxRed) {
                top.linkTo(boxGreen.bottom)
                start.linkTo(parent.start, margin = 32.dp)
            })
        Box(
            Modifier
                .size(50.dp)
                .background(Color.Yellow)
                .constrainAs(boxYellow) {
                    start.linkTo(barrier)
                })
    }
}

@Preview
@Composable
fun ConstraineChainExample() {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (boxRed, boxYellow, boxGreen) = createRefs()

        Box(modifier = Modifier
            .size(75.dp)
            .background(Color.Green)
            .constrainAs(boxGreen) {
                start.linkTo(parent.start)
                end.linkTo(boxRed.start)
            })
        Box(modifier = Modifier
            .size(75.dp)
            .background(Color.Red)
            .constrainAs(boxRed) {
                start.linkTo(boxGreen.end)
                end.linkTo(boxYellow.start)
            })
        Box(Modifier
                .size(75.dp)
                .background(Color.Yellow)
                .constrainAs(boxYellow) {
                    start.linkTo(boxRed.end)
                    end.linkTo(parent.end)
                })
        createHorizontalChain(boxRed,boxGreen,boxYellow, chainStyle = ChainStyle.SpreadInside)
    // asi se genera una cadena y se enlazan con los ids de cada constraine y se le debe de asignar un estilo personalizado
    }
}