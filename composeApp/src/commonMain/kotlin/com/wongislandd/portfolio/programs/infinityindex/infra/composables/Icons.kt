package com.wongislandd.portfolio.programs.infinityindex.infra.composables

import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.materialPath
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

// installed material-icons-extended, copied these in, removed dependency to avoid large app size
val QuestionMark: ImageVector
    get() {
        if (_questionMark != null) {
            return _questionMark!!
        }
        _questionMark = materialIcon(name = "Outlined.QuestionMark") {
            materialPath {
                moveTo(11.07f, 12.85f)
                curveToRelative(0.77f, -1.39f, 2.25f, -2.21f, 3.11f, -3.44f)
                curveToRelative(0.91f, -1.29f, 0.4f, -3.7f, -2.18f, -3.7f)
                curveToRelative(-1.69f, 0.0f, -2.52f, 1.28f, -2.87f, 2.34f)
                lineTo(6.54f, 6.96f)
                curveTo(7.25f, 4.83f, 9.18f, 3.0f, 11.99f, 3.0f)
                curveToRelative(2.35f, 0.0f, 3.96f, 1.07f, 4.78f, 2.41f)
                curveToRelative(0.7f, 1.15f, 1.11f, 3.3f, 0.03f, 4.9f)
                curveToRelative(-1.2f, 1.77f, -2.35f, 2.31f, -2.97f, 3.45f)
                curveToRelative(-0.25f, 0.46f, -0.35f, 0.76f, -0.35f, 2.24f)
                horizontalLineToRelative(-2.89f)
                curveTo(10.58f, 15.22f, 10.46f, 13.95f, 11.07f, 12.85f)
                close()
                moveTo(14.0f, 20.0f)
                curveToRelative(0.0f, 1.1f, -0.9f, 2.0f, -2.0f, 2.0f)
                reflectiveCurveToRelative(-2.0f, -0.9f, -2.0f, -2.0f)
                curveToRelative(0.0f, -1.1f, 0.9f, -2.0f, 2.0f, -2.0f)
                reflectiveCurveTo(14.0f, 18.9f, 14.0f, 20.0f)
                close()
            }
        }
        return _questionMark!!
    }

private var _questionMark: ImageVector? = null

val MenuBook: ImageVector
    get() {
        if (_menuBook != null) {
            return _menuBook!!
        }
        _menuBook = materialIcon(name = "AutoMirrored.Outlined.MenuBook", autoMirror = true) {
            materialPath {
                moveTo(21.0f, 5.0f)
                curveToRelative(-1.11f, -0.35f, -2.33f, -0.5f, -3.5f, -0.5f)
                curveToRelative(-1.95f, 0.0f, -4.05f, 0.4f, -5.5f, 1.5f)
                curveToRelative(-1.45f, -1.1f, -3.55f, -1.5f, -5.5f, -1.5f)
                reflectiveCurveTo(2.45f, 4.9f, 1.0f, 6.0f)
                verticalLineToRelative(14.65f)
                curveToRelative(0.0f, 0.25f, 0.25f, 0.5f, 0.5f, 0.5f)
                curveToRelative(0.1f, 0.0f, 0.15f, -0.05f, 0.25f, -0.05f)
                curveTo(3.1f, 20.45f, 5.05f, 20.0f, 6.5f, 20.0f)
                curveToRelative(1.95f, 0.0f, 4.05f, 0.4f, 5.5f, 1.5f)
                curveToRelative(1.35f, -0.85f, 3.8f, -1.5f, 5.5f, -1.5f)
                curveToRelative(1.65f, 0.0f, 3.35f, 0.3f, 4.75f, 1.05f)
                curveToRelative(0.1f, 0.05f, 0.15f, 0.05f, 0.25f, 0.05f)
                curveToRelative(0.25f, 0.0f, 0.5f, -0.25f, 0.5f, -0.5f)
                verticalLineTo(6.0f)
                curveTo(22.4f, 5.55f, 21.75f, 5.25f, 21.0f, 5.0f)
                close()
                moveTo(21.0f, 18.5f)
                curveToRelative(-1.1f, -0.35f, -2.3f, -0.5f, -3.5f, -0.5f)
                curveToRelative(-1.7f, 0.0f, -4.15f, 0.65f, -5.5f, 1.5f)
                verticalLineTo(8.0f)
                curveToRelative(1.35f, -0.85f, 3.8f, -1.5f, 5.5f, -1.5f)
                curveToRelative(1.2f, 0.0f, 2.4f, 0.15f, 3.5f, 0.5f)
                verticalLineTo(18.5f)
                close()
            }
            materialPath {
                moveTo(17.5f, 10.5f)
                curveToRelative(0.88f, 0.0f, 1.73f, 0.09f, 2.5f, 0.26f)
                verticalLineTo(9.24f)
                curveTo(19.21f, 9.09f, 18.36f, 9.0f, 17.5f, 9.0f)
                curveToRelative(-1.7f, 0.0f, -3.24f, 0.29f, -4.5f, 0.83f)
                verticalLineToRelative(1.66f)
                curveTo(14.13f, 10.85f, 15.7f, 10.5f, 17.5f, 10.5f)
                close()
            }
            materialPath {
                moveTo(13.0f, 12.49f)
                verticalLineToRelative(1.66f)
                curveToRelative(1.13f, -0.64f, 2.7f, -0.99f, 4.5f, -0.99f)
                curveToRelative(0.88f, 0.0f, 1.73f, 0.09f, 2.5f, 0.26f)
                verticalLineTo(11.9f)
                curveToRelative(-0.79f, -0.15f, -1.64f, -0.24f, -2.5f, -0.24f)
                curveTo(15.8f, 11.66f, 14.26f, 11.96f, 13.0f, 12.49f)
                close()
            }
            materialPath {
                moveTo(17.5f, 14.33f)
                curveToRelative(-1.7f, 0.0f, -3.24f, 0.29f, -4.5f, 0.83f)
                verticalLineToRelative(1.66f)
                curveToRelative(1.13f, -0.64f, 2.7f, -0.99f, 4.5f, -0.99f)
                curveToRelative(0.88f, 0.0f, 1.73f, 0.09f, 2.5f, 0.26f)
                verticalLineToRelative(-1.52f)
                curveTo(19.21f, 14.41f, 18.36f, 14.33f, 17.5f, 14.33f)
                close()
            }
        }
        return _menuBook!!
    }

private var _menuBook: ImageVector? = null


val DominoMask: ImageVector
    get() {
        if (_dominoMask != null) {
            return _dominoMask!!
        }
        _dominoMask = ImageVector.Builder(
            name = "Domino_mask_24dp_E8EAED_FILL0_wght400_GRAD0_opsz24",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 960f,
            viewportHeight = 960f
        ).apply {
            materialPath {
                moveTo(312f, 720f)
                quadToRelative(-51f, 0f, -97.5f, -18f)
                reflectiveQuadTo(131f, 649f)
                quadToRelative(-48f, -45f, -69.5f, -106.5f)
                reflectiveQuadTo(40f, 415f)
                quadToRelative(0f, -78f, 38f, -126.5f)
                reflectiveQuadTo(189f, 240f)
                quadToRelative(14f, 0f, 26.5f, 2.5f)
                reflectiveQuadTo(241f, 250f)
                lineToRelative(239f, 89f)
                lineToRelative(239f, -89f)
                quadToRelative(13f, -5f, 25.5f, -7.5f)
                reflectiveQuadTo(771f, 240f)
                quadToRelative(73f, 0f, 111f, 48.5f)
                reflectiveQuadTo(920f, 415f)
                quadToRelative(0f, 66f, -21.5f, 127.5f)
                reflectiveQuadTo(829f, 649f)
                quadToRelative(-37f, 35f, -83.5f, 53f)
                reflectiveQuadTo(648f, 720f)
                quadToRelative(-66f, 0f, -112f, -30f)
                lineToRelative(-46f, -30f)
                horizontalLineToRelative(-20f)
                lineToRelative(-46f, 30f)
                quadToRelative(-46f, 30f, -112f, 30f)
                close()
                moveToRelative(0f, -80f)
                quadToRelative(37f, 0f, 69f, -17.5f)
                reflectiveQuadToRelative(59f, -42.5f)
                horizontalLineToRelative(80f)
                quadToRelative(27f, 25f, 59f, 42.5f)
                reflectiveQuadToRelative(69f, 17.5f)
                quadToRelative(36f, 0f, 69.5f, -12.5f)
                reflectiveQuadTo(777f, 589f)
                quadToRelative(34f, -34f, 48.5f, -80f)
                reflectiveQuadToRelative(14.5f, -94f)
                quadToRelative(0f, -41f, -17f, -68.5f)
                reflectiveQuadTo(769f, 320f)
                quadToRelative(-3f, 0f, -22f, 4f)
                lineTo(480f, 424f)
                lineTo(213f, 324f)
                quadToRelative(-5f, -2f, -10.5f, -3f)
                reflectiveQuadToRelative(-11.5f, -1f)
                quadToRelative(-37f, 0f, -54f, 27f)
                reflectiveQuadToRelative(-17f, 68f)
                quadToRelative(0f, 49f, 14.5f, 95f)
                reflectiveQuadToRelative(49.5f, 80f)
                quadToRelative(26f, 25f, 59f, 37.5f)
                reflectiveQuadToRelative(69f, 12.5f)
                close()
                moveToRelative(49f, -60f)
                quadToRelative(37f, 0f, 58f, -16.5f)
                reflectiveQuadToRelative(21f, -45.5f)
                quadToRelative(0f, -49f, -64.5f, -93.5f)
                reflectiveQuadTo(239f, 380f)
                quadToRelative(-37f, 0f, -58f, 16.5f)
                reflectiveQuadTo(160f, 442f)
                quadToRelative(0f, 49f, 64.5f, 93.5f)
                reflectiveQuadTo(361f, 580f)
                close()
                moveToRelative(-6f, -60f)
                quadToRelative(-38f, 0f, -82.5f, -25f)
                reflectiveQuadTo(220f, 444f)
                quadToRelative(5f, -2f, 11.5f, -3.5f)
                reflectiveQuadTo(245f, 439f)
                quadToRelative(38f, 0f, 82.5f, 25.5f)
                reflectiveQuadTo(380f, 516f)
                quadToRelative(-5f, 2f, -11.5f, 3f)
                reflectiveQuadToRelative(-13.5f, 1f)
                close()
                moveToRelative(244f, 61f)
                quadToRelative(72f, 0f, 136.5f, -45f)
                reflectiveQuadToRelative(64.5f, -94f)
                quadToRelative(0f, -29f, -20.5f, -46f)
                reflectiveQuadTo(721f, 379f)
                quadToRelative(-72f, 0f, -136.5f, 45f)
                reflectiveQuadTo(520f, 518f)
                quadToRelative(0f, 29f, 21f, 46f)
                reflectiveQuadToRelative(58f, 17f)
                close()
                moveToRelative(6f, -61f)
                quadToRelative(-7f, 0f, -13f, -1f)
                reflectiveQuadToRelative(-11f, -3f)
                quadToRelative(8f, -26f, 52.5f, -51f)
                reflectiveQuadToRelative(82.5f, -25f)
                quadToRelative(7f, 0f, 13f, 1f)
                reflectiveQuadToRelative(11f, 3f)
                quadToRelative(-8f, 26f, -52.5f, 51f)
                reflectiveQuadTo(605f, 520f)
                close()
                moveToRelative(-125f, -40f)
                close()
            }
        }.build()
        return _dominoMask!!
    }

private var _dominoMask: ImageVector? = null

val Bolt: ImageVector
    get() {
        if (_bolt != null) {
            return _bolt!!
        }
        _bolt = ImageVector.Builder(
            name = "Bolt_24dp_E8EAED_FILL0_wght400_GRAD0_opsz24",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 960f,
            viewportHeight = 960f
        ).apply {
            materialPath {
                moveTo(422f, 728f)
                lineToRelative(207f, -248f)
                horizontalLineTo(469f)
                lineToRelative(29f, -227f)
                lineToRelative(-185f, 267f)
                horizontalLineToRelative(139f)
                lineToRelative(-30f, 208f)
                close()
                moveTo(320f, 880f)
                lineToRelative(40f, -280f)
                horizontalLineTo(160f)
                lineToRelative(360f, -520f)
                horizontalLineToRelative(80f)
                lineToRelative(-40f, 320f)
                horizontalLineToRelative(240f)
                lineTo(400f, 880f)
                horizontalLineToRelative(-80f)
                close()
                moveToRelative(151f, -390f)
                close()
            }
        }.build()
        return _bolt!!
    }

private var _bolt: ImageVector? = null


val Rocket: ImageVector
    get() {
        if (_rocket != null) {
            return _rocket!!
        }
        _rocket = ImageVector.Builder(
            name = "Rocket_24dp_E8EAED_FILL0_wght400_GRAD0_opsz24",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 960f,
            viewportHeight = 960f
        ).apply {
            materialPath {
                moveTo(240f, 762f)
                lineToRelative(79f, -32f)
                quadToRelative(-10f, -29f, -18.5f, -59f)
                reflectiveQuadTo(287f, 611f)
                lineToRelative(-47f, 32f)
                verticalLineToRelative(119f)
                close()
                moveToRelative(160f, -42f)
                horizontalLineToRelative(160f)
                quadToRelative(18f, -40f, 29f, -97.5f)
                reflectiveQuadTo(600f, 505f)
                quadToRelative(0f, -99f, -33f, -187.5f)
                reflectiveQuadTo(480f, 181f)
                quadToRelative(-54f, 48f, -87f, 136.5f)
                reflectiveQuadTo(360f, 505f)
                quadToRelative(0f, 60f, 11f, 117.5f)
                reflectiveQuadToRelative(29f, 97.5f)
                close()
                moveToRelative(80f, -200f)
                quadToRelative(-33f, 0f, -56.5f, -23.5f)
                reflectiveQuadTo(400f, 440f)
                quadToRelative(0f, -33f, 23.5f, -56.5f)
                reflectiveQuadTo(480f, 360f)
                quadToRelative(33f, 0f, 56.5f, 23.5f)
                reflectiveQuadTo(560f, 440f)
                quadToRelative(0f, 33f, -23.5f, 56.5f)
                reflectiveQuadTo(480f, 520f)
                close()
                moveToRelative(240f, 242f)
                verticalLineToRelative(-119f)
                lineToRelative(-47f, -32f)
                quadToRelative(-5f, 30f, -13.5f, 60f)
                reflectiveQuadTo(641f, 730f)
                lineToRelative(79f, 32f)
                close()
                moveTo(480f, 79f)
                quadToRelative(99f, 72f, 149.5f, 183f)
                reflectiveQuadTo(680f, 520f)
                lineToRelative(84f, 56f)
                quadToRelative(17f, 11f, 26.5f, 29f)
                reflectiveQuadToRelative(9.5f, 38f)
                verticalLineToRelative(237f)
                lineToRelative(-199f, -80f)
                horizontalLineTo(359f)
                lineTo(160f, 880f)
                verticalLineToRelative(-237f)
                quadToRelative(0f, -20f, 9.5f, -38f)
                reflectiveQuadToRelative(26.5f, -29f)
                lineToRelative(84f, -56f)
                quadToRelative(0f, -147f, 50.5f, -258f)
                reflectiveQuadTo(480f, 79f)
                close()
            }
        }.build()
        return _rocket!!
    }

private var _rocket: ImageVector? = null

val Storm: ImageVector
    get() {
        if (_storm != null) {
            return _storm!!
        }
        _storm = ImageVector.Builder(
            name = "Storm_24dp_E8EAED_FILL0_wght400_GRAD0_opsz24",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 960f,
            viewportHeight = 960f
        ).apply {
            materialPath {
                moveTo(726f, 880f)
                quadToRelative(25f, -62f, 35f, -127f)
                reflectiveQuadToRelative(5f, -131f)
                quadToRelative(-39f, 83f, -116.5f, 130.5f)
                reflectiveQuadTo(480f, 800f)
                quadToRelative(-84f, 0f, -152f, -39.5f)
                reflectiveQuadTo(212f, 655f)
                quadToRelative(-48f, -66f, -74f, -151.5f)
                reflectiveQuadTo(112f, 326f)
                quadToRelative(0f, -63f, 8.5f, -124.5f)
                reflectiveQuadTo(150f, 80f)
                horizontalLineToRelative(84f)
                quadToRelative(-24f, 62f, -34.5f, 127f)
                reflectiveQuadTo(194f, 338f)
                quadToRelative(39f, -83f, 116.5f, -130.5f)
                reflectiveQuadTo(480f, 160f)
                quadToRelative(84f, 0f, 152f, 39.5f)
                reflectiveQuadTo(748f, 305f)
                quadToRelative(48f, 66f, 74f, 151.5f)
                reflectiveQuadTo(848f, 634f)
                quadToRelative(0f, 63f, -8.5f, 124.5f)
                reflectiveQuadTo(810f, 880f)
                horizontalLineToRelative(-84f)
                close()
                moveTo(480f, 720f)
                quadToRelative(100f, 0f, 170f, -70f)
                reflectiveQuadToRelative(70f, -170f)
                quadToRelative(0f, -100f, -70f, -170f)
                reflectiveQuadToRelative(-170f, -70f)
                quadToRelative(-100f, 0f, -170f, 70f)
                reflectiveQuadToRelative(-70f, 170f)
                quadToRelative(0f, 100f, 70f, 170f)
                reflectiveQuadToRelative(170f, 70f)
                close()
                moveToRelative(0f, -80f)
                quadToRelative(66f, 0f, 113f, -47f)
                reflectiveQuadToRelative(47f, -113f)
                quadToRelative(0f, -66f, -47f, -113f)
                reflectiveQuadToRelative(-113f, -47f)
                quadToRelative(-66f, 0f, -113f, 47f)
                reflectiveQuadToRelative(-47f, 113f)
                quadToRelative(0f, 66f, 47f, 113f)
                reflectiveQuadToRelative(113f, 47f)
                close()
                moveToRelative(0f, -80f)
                quadToRelative(-33f, 0f, -56.5f, -23.5f)
                reflectiveQuadTo(400f, 480f)
                quadToRelative(0f, -33f, 23.5f, -56.5f)
                reflectiveQuadTo(480f, 400f)
                quadToRelative(33f, 0f, 56.5f, 23.5f)
                reflectiveQuadTo(560f, 480f)
                quadToRelative(0f, 33f, -23.5f, 56.5f)
                reflectiveQuadTo(480f, 560f)
                close()
                moveToRelative(0f, -80f)
                close()
            }
        }.build()
        return _storm!!
    }

private var _storm: ImageVector? = null

val Planet: ImageVector
    get() {
        if (_planet != null) {
            return _planet!!
        }
        _planet = ImageVector.Builder(
            name = "Planet_24dp_E8EAED_FILL0_wght400_GRAD0_opsz24",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 960f,
            viewportHeight = 960f
        ).apply {
            materialPath {
                moveTo(449f, 421f)
                quadToRelative(21f, 0f, 35.5f, -14.5f)
                reflectiveQuadTo(499f, 371f)
                quadToRelative(0f, -21f, -14.5f, -35.5f)
                reflectiveQuadTo(449f, 321f)
                quadToRelative(-21f, 0f, -35.5f, 14.5f)
                reflectiveQuadTo(399f, 371f)
                quadToRelative(0f, 21f, 14.5f, 35.5f)
                reflectiveQuadTo(449f, 421f)
                close()
                moveTo(822f, 880f)
                quadToRelative(-42f, 0f, -113f, -35f)
                reflectiveQuadToRelative(-152f, -95f)
                quadToRelative(-19f, 5f, -38.5f, 7.5f)
                reflectiveQuadTo(479f, 760f)
                quadToRelative(-117f, 0f, -198f, -81f)
                reflectiveQuadToRelative(-81f, -198f)
                quadToRelative(0f, -20f, 3f, -40f)
                reflectiveQuadToRelative(8f, -39f)
                quadToRelative(-59f, -81f, -94.5f, -151.5f)
                reflectiveQuadTo(81f, 138f)
                quadToRelative(0f, -27f, 15f, -42.5f)
                reflectiveQuadToRelative(41f, -15.5f)
                quadToRelative(26f, 0f, 67.5f, 18f)
                reflectiveQuadTo(319f, 159f)
                quadToRelative(-21f, 11f, -39f, 23f)
                reflectiveQuadToRelative(-35f, 26f)
                quadToRelative(-19f, -11f, -37f, -19f)
                reflectiveQuadToRelative(-38f, -17f)
                quadToRelative(18f, 38f, 38.5f, 74f)
                reflectiveQuadToRelative(43.5f, 71f)
                quadToRelative(38f, -54f, 97f, -85f)
                reflectiveQuadToRelative(130f, -31f)
                quadToRelative(117f, 0f, 198.5f, 81.5f)
                reflectiveQuadTo(759f, 481f)
                quadToRelative(0f, 71f, -31.5f, 130f)
                reflectiveQuadTo(642f, 708f)
                quadToRelative(35f, 23f, 71.5f, 44f)
                reflectiveQuadToRelative(74.5f, 38f)
                quadToRelative(-8f, -19f, -16.5f, -37f)
                reflectiveQuadTo(752f, 716f)
                quadToRelative(15f, -17f, 27f, -36f)
                reflectiveQuadToRelative(22f, -39f)
                quadToRelative(46f, 78f, 62.5f, 116.5f)
                reflectiveQuadTo(880f, 822f)
                quadToRelative(0f, 29f, -16f, 43.5f)
                reflectiveQuadTo(822f, 880f)
                close()
                moveTo(549f, 601f)
                quadToRelative(17f, 0f, 28.5f, -11.5f)
                reflectiveQuadTo(589f, 561f)
                quadToRelative(0f, -17f, -11.5f, -28.5f)
                reflectiveQuadTo(549f, 521f)
                quadToRelative(-17f, 0f, -28.5f, 11.5f)
                reflectiveQuadTo(509f, 561f)
                quadToRelative(0f, 17f, 11.5f, 28.5f)
                reflectiveQuadTo(549f, 601f)
                close()
                moveToRelative(50f, -140f)
                quadToRelative(13f, 0f, 21.5f, -8.5f)
                reflectiveQuadTo(629f, 431f)
                quadToRelative(0f, -13f, -8.5f, -21.5f)
                reflectiveQuadTo(599f, 401f)
                quadToRelative(-13f, 0f, -21.5f, 8.5f)
                reflectiveQuadTo(569f, 431f)
                quadToRelative(0f, 13f, 8.5f, 21.5f)
                reflectiveQuadTo(599f, 461f)
                close()
                moveTo(468f, 679f)
                quadToRelative(-51f, -44f, -98f, -91f)
                reflectiveQuadToRelative(-90f, -98f)
                quadToRelative(2f, 38f, 17f, 71.5f)
                reflectiveQuadToRelative(41f, 59.5f)
                quadToRelative(26f, 26f, 59f, 41f)
                reflectiveQuadToRelative(71f, 17f)
                close()
                moveToRelative(103f, -21f)
                quadToRelative(48f, -25f, 78f, -72.5f)
                reflectiveQuadTo(679f, 480f)
                quadToRelative(0f, -83f, -58.5f, -141f)
                reflectiveQuadTo(479f, 281f)
                quadToRelative(-58f, 0f, -105f, 30f)
                reflectiveQuadToRelative(-72f, 78f)
                quadToRelative(57f, 76f, 125f, 144f)
                reflectiveQuadToRelative(144f, 125f)
                close()
                moveToRelative(-197f, -73f)
                close()
                moveToRelative(117f, -116f)
                close()
            }
        }.build()
        return _planet!!
    }

private var _planet: ImageVector? = null

val Freeze: ImageVector
    get() {
        if (_freeze != null) {
            return _freeze!!
        }
        _freeze = ImageVector.Builder(
            name = "Ac_unit_24dp_E8EAED_FILL0_wght400_GRAD0_opsz24",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 960f,
            viewportHeight = 960f
        ).apply {
            materialPath {
                moveTo(440f, 880f)
                verticalLineToRelative(-166f)
                lineTo(310f, 842f)
                lineToRelative(-56f, -56f)
                lineToRelative(186f, -186f)
                verticalLineToRelative(-80f)
                horizontalLineToRelative(-80f)
                lineTo(174f, 706f)
                lineToRelative(-56f, -56f)
                lineToRelative(128f, -130f)
                horizontalLineTo(80f)
                verticalLineToRelative(-80f)
                horizontalLineToRelative(166f)
                lineTo(118f, 310f)
                lineToRelative(56f, -56f)
                lineToRelative(186f, 186f)
                horizontalLineToRelative(80f)
                verticalLineToRelative(-80f)
                lineTo(254f, 174f)
                lineToRelative(56f, -56f)
                lineToRelative(130f, 128f)
                verticalLineToRelative(-166f)
                horizontalLineToRelative(80f)
                verticalLineToRelative(166f)
                lineToRelative(130f, -128f)
                lineToRelative(56f, 56f)
                lineToRelative(-186f, 186f)
                verticalLineToRelative(80f)
                horizontalLineToRelative(80f)
                lineToRelative(186f, -186f)
                lineToRelative(56f, 56f)
                lineToRelative(-128f, 130f)
                horizontalLineToRelative(166f)
                verticalLineToRelative(80f)
                horizontalLineTo(714f)
                lineToRelative(128f, 130f)
                lineToRelative(-56f, 56f)
                lineToRelative(-186f, -186f)
                horizontalLineToRelative(-80f)
                verticalLineToRelative(80f)
                lineToRelative(186f, 186f)
                lineToRelative(-56f, 56f)
                lineToRelative(-130f, -128f)
                verticalLineToRelative(166f)
                horizontalLineToRelative(-80f)
                close()
            }
        }.build()
        return _freeze!!
    }

private var _freeze: ImageVector? = null

val Infinity: ImageVector
    get() {
        if (_infinity != null) {
            return _infinity!!
        }
        _infinity = ImageVector.Builder(
            name = "All_inclusive_24dp_E8EAED_FILL0_wght400_GRAD0_opsz24",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 960f,
            viewportHeight = 960f
        ).apply {
            materialPath {
                moveTo(220f, 700f)
                quadToRelative(-92f, 0f, -156f, -64f)
                reflectiveQuadTo(0f, 480f)
                quadToRelative(0f, -92f, 64f, -156f)
                reflectiveQuadToRelative(156f, -64f)
                quadToRelative(37f, 0f, 71f, 13f)
                reflectiveQuadToRelative(61f, 37f)
                lineToRelative(68f, 62f)
                lineToRelative(-60f, 54f)
                lineToRelative(-62f, -56f)
                quadToRelative(-16f, -14f, -36f, -22f)
                reflectiveQuadToRelative(-42f, -8f)
                quadToRelative(-58f, 0f, -99f, 41f)
                reflectiveQuadToRelative(-41f, 99f)
                quadToRelative(0f, 58f, 41f, 99f)
                reflectiveQuadToRelative(99f, 41f)
                quadToRelative(22f, 0f, 42f, -8f)
                reflectiveQuadToRelative(36f, -22f)
                lineToRelative(310f, -280f)
                quadToRelative(27f, -24f, 61f, -37f)
                reflectiveQuadToRelative(71f, -13f)
                quadToRelative(92f, 0f, 156f, 64f)
                reflectiveQuadToRelative(64f, 156f)
                quadToRelative(0f, 92f, -64f, 156f)
                reflectiveQuadToRelative(-156f, 64f)
                quadToRelative(-37f, 0f, -71f, -13f)
                reflectiveQuadToRelative(-61f, -37f)
                lineToRelative(-68f, -62f)
                lineToRelative(60f, -54f)
                lineToRelative(62f, 56f)
                quadToRelative(16f, 14f, 36f, 22f)
                reflectiveQuadToRelative(42f, 8f)
                quadToRelative(58f, 0f, 99f, -41f)
                reflectiveQuadToRelative(41f, -99f)
                quadToRelative(0f, -58f, -41f, -99f)
                reflectiveQuadToRelative(-99f, -41f)
                quadToRelative(-22f, 0f, -42f, 8f)
                reflectiveQuadToRelative(-36f, 22f)
                lineTo(352f, 650f)
                quadToRelative(-27f, 24f, -61f, 37f)
                reflectiveQuadToRelative(-71f, 13f)
                close()
            }
        }.build()
        return _infinity!!
    }

private var _infinity: ImageVector? = null


val Sort: ImageVector
    get() {
        if (_sort != null) {
            return _sort!!
        }
        _sort = ImageVector.Builder(
            name = "Sort_24dp_E8EAED_FILL0_wght400_GRAD0_opsz24",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 960f,
            viewportHeight = 960f
        ).apply {
            materialPath {
                moveTo(120f, 720f)
                verticalLineToRelative(-80f)
                horizontalLineToRelative(240f)
                verticalLineToRelative(80f)
                horizontalLineTo(120f)
                close()
                moveToRelative(0f, -200f)
                verticalLineToRelative(-80f)
                horizontalLineToRelative(480f)
                verticalLineToRelative(80f)
                horizontalLineTo(120f)
                close()
                moveToRelative(0f, -200f)
                verticalLineToRelative(-80f)
                horizontalLineToRelative(720f)
                verticalLineToRelative(80f)
                horizontalLineTo(120f)
                close()
            }
        }.build()
        return _sort!!
    }

private var _sort: ImageVector? = null

val Filter: ImageVector
    get() {
        if (_filter != null) {
            return _filter!!
        }
        _filter = ImageVector.Builder(
            name = "Filter_list_24dp_E8EAED_FILL0_wght400_GRAD0_opsz24",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 960f,
            viewportHeight = 960f
        ).apply {
            materialPath {
                moveTo(400f, 720f)
                verticalLineToRelative(-80f)
                horizontalLineToRelative(160f)
                verticalLineToRelative(80f)
                horizontalLineTo(400f)
                close()
                moveTo(240f, 520f)
                verticalLineToRelative(-80f)
                horizontalLineToRelative(480f)
                verticalLineToRelative(80f)
                horizontalLineTo(240f)
                close()
                moveTo(120f, 320f)
                verticalLineToRelative(-80f)
                horizontalLineToRelative(720f)
                verticalLineToRelative(80f)
                horizontalLineTo(120f)
                close()
            }
        }.build()
        return _filter!!
    }

private var _filter: ImageVector? = null

