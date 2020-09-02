package com.example.ckprojectstructure_android.util.extension

import com.layernet.thaidatetimepicker.time.Timepoint
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*


fun Long.toDate(): Date? {
    val unixSeconds = this

    val nixDate = Date(unixSeconds * 1000L)

    val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale("TH", "th-TH"))
    formatter.timeZone = TimeZone.getTimeZone("UTC+7")

    val date = formatter.format(nixDate)

    return try {
        formatter.parse(date)
    } catch (e: ParseException) {
        e.printStackTrace()
        null
    }
}

fun String.toDate(): Date? {
    val formatter = SimpleDateFormat("yyyy-MMM-dd'T'HH:mm:ss", Locale("TH", "th-TH"))
    formatter.timeZone = TimeZone.getTimeZone("UTC+7")
    return try {
        formatter.parse(this)
    } catch (e: ParseException) {
        e.printStackTrace()
        null
    }
}

fun String.toUnixTimeStamp(): Long {
    val thaiPatten = this.split(" ")

    val date = if (thaiPatten.size > 3) {
        val sdf = SimpleDateFormat("dd MMM yyyy HH:mm", Locale("TH", "th-TH"))
        val buddhistPatten =
            "${thaiPatten[0]} ${thaiPatten[1]} ${thaiPatten[2].toInt() - 543} ${thaiPatten[3]}"
        sdf.parse(buddhistPatten)
    } else {
        val sdf = SimpleDateFormat("dd MMM yyyy", Locale("TH", "th-TH"))
        val buddhistPatten = "${thaiPatten[0]} ${thaiPatten[1]} ${thaiPatten[2].toInt() - 543}"
        sdf.parse(buddhistPatten)
    }

    return date.time / 1000
}

fun Date?.toTime(): String {
    if (this == null) return ""

    val simpleDateFormat = SimpleDateFormat("HH:mm aa", Locale("TH", "th-TH"))
    return simpleDateFormat.format(this)
}

fun Date?.toDateTimeThaiPatten(): String {
    if (this == null) return ""

    val formatter = SimpleDateFormat("dd MMM yyyy HH:mm", Locale("TH", "th-TH"))
    val s = formatter.format(this)
    val thaiPatten = s.split(" ")
    return "${thaiPatten[0]} ${thaiPatten[1]} ${thaiPatten[2].toInt() + 543} ${thaiPatten[3]}"
}

fun Date?.toDateThaiPatten(): String {
    if (this == null) return ""

    val formatter = SimpleDateFormat("dd MMM yyyy", Locale("TH", "th-TH"))
    val s = formatter.format(this)
    val thaiPatten = s.split(" ")
    return "${thaiPatten[0]} ${thaiPatten[1]} ${thaiPatten[2].toInt() + 543}"
}

fun LocalDateTime?.toDateThaiPatten(): String {
    if (this == null) return ""

    val formatter = SimpleDateFormat("dd MMM yyyy", Locale("TH", "th-TH"))
    val s = formatter.format(this)
    val thaiPatten = s.split(" ")
    return "${thaiPatten[0]} ${thaiPatten[1]} ${thaiPatten[2].toInt() + 543}"
}

fun Date?.toMonthYearThaiPatten(): String {
    if (this == null) return ""

    val formatter = SimpleDateFormat("dd MMM yyyy", Locale("TH", "th-TH"))
    val s = formatter.format(this)
    val thaiPatten = s.split(" ")
    return "${thaiPatten[1]} ${thaiPatten[2].toInt() + 543}"
}

fun Date?.toFullThaiPatten(): String {
    if (this == null) return ""

    val formatter = SimpleDateFormat("EEEE dd MMM yyyy HH:mm", Locale("TH", "th-TH"))
    val s = formatter.format(this)
    val thaiPatten = s.split(" ")
    return "${thaiPatten[0]} ${thaiPatten[1]} ${thaiPatten[2]} ${thaiPatten[3].toInt() + 543} ${thaiPatten[4]}"
}

fun Date?.toServerPatten(): String {
    if (this == null) return ""

    val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale("TH", "th-TH"))
    return formatter.format(this)
}

fun Date?.toDayTH(): String {
    if (this == null) return ""

    val formatter = SimpleDateFormat("dd", Locale("TH", "th-TH"))
    return formatter.format(this)
}

fun Date?.toMonthTH(): String {
    if (this == null) return ""

    val formatter = SimpleDateFormat("MMM", Locale("TH", "th-TH"))
    return formatter.format(this)
}

fun Date?.toYearTH(): String {
    if (this == null) return ""

    val formatter = SimpleDateFormat("yyyy", Locale("TH", "th-TH"))
    val s = formatter.format(this)
    return "${s.toInt() + 543}"
}

fun Date?.toDay(): Int {
    if (this == null) return -1

    val formatter = SimpleDateFormat("dd", Locale("TH", "th-TH"))
    return formatter.format(this).toInt()
}

fun Date?.toMonth(): Int {
    if (this == null) return -1

    val formatter = SimpleDateFormat("MM", Locale("TH", "th-TH"))
    return formatter.format(this).toInt() - 1
}

fun Date?.toYear(): Int {
    if (this == null) return -1

    val formatter = SimpleDateFormat("yyyy", Locale("TH", "th-TH"))
    return formatter.format(this).toInt()
}

fun Date?.toHour(): Int {
    if (this == null) return -1

    val formatter = SimpleDateFormat("HH", Locale("TH", "th-TH"))
    return formatter.format(this).toInt()
}

fun Date?.toMinute(): Int {
    if (this == null) return -1

    val formatter = SimpleDateFormat("mm", Locale("TH", "th-TH"))
    return formatter.format(this).toInt()
}

fun Date?.toTimeTH(): String {
    if (this == null) return ""

    val formatter = SimpleDateFormat("HH:mm", Locale("TH", "th-TH"))
    return formatter.format(this).toString()
}

fun Any.get30MinuteTimePointArray(): Array<Timepoint> {

    val timePoints = Array(48) {
        return@Array Timepoint(0, 0)
    }

    var start = 0
    for (hour in 0..23) {
        for (minute in 0..1) {
            if (start < timePoints.size) {
                timePoints[start] = if (minute == 0) {
                    Timepoint(hour, 0)
                } else {
                    Timepoint(hour, 30)
                }
            }
            start += 1
        }
    }

    return timePoints
}

fun Any.getAllMinuteTimePointArray(): Array<Timepoint> {
    val timePoints = Array(1440) {
        return@Array Timepoint(0, 0)
    }

    var start = 0
    for (hour in 0..23) {
        for (minute in 0..59) {
            if (start < timePoints.size) {
                timePoints[start] = Timepoint(hour, minute)
            }
            start += 1
        }
    }

    return timePoints
}

fun convertDateForRocketChat(date: Date, time: Boolean): String? {
    val simpleDateInThisWeek =
        SimpleDateFormat("kk:mm", Locale.getDefault()) // the format of your date
    simpleDateInThisWeek.timeZone = TimeZone.getTimeZone("GMT+7")
    val simpleDateMoreThanOneWeek =
        SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()) // the format of your date
    simpleDateMoreThanOneWeek.timeZone = TimeZone.getTimeZone("GMT+7")
    val simpleDateMoreThanOneWeekWithTime =
        SimpleDateFormat("dd-MM-yyyy kk:mm", Locale.getDefault()) // the format of your date
    simpleDateMoreThanOneWeekWithTime.timeZone = TimeZone.getTimeZone("GMT+7")
    val timeInMilliseconds = date.time
    val checkDate: Long = diff(
        timeInMilliseconds,
        Calendar.DAY_OF_YEAR
    ) // 0 - today, 1 - tomorrow, -1 - yesterday
    diff(
        timeInMilliseconds,
        Calendar.WEEK_OF_YEAR
    ) // 0 - this week, -1 - last week etc.
    var weekDay = ""
    return when (checkDate.toInt()) {
        0 -> simpleDateInThisWeek.format(date)
        -1 -> "เมื่อวาน"
        -2, -3, -4, -5, -6 -> {
            val c = Calendar.getInstance()
            val dayOfWeek = c[Calendar.DAY_OF_WEEK]
            when (dayOfWeek) {
                Calendar.MONDAY -> {
                    if (diff(
                            timeInMilliseconds,
                            Calendar.DAY_OF_YEAR
                        ).toInt() == -2
                    ) {
                        weekDay = "วันเสาร์"
                    } else if (diff(
                            timeInMilliseconds,
                            Calendar.DAY_OF_YEAR
                        ).toInt() == -3
                    ) {
                        weekDay = "วันศุกร์"
                    } else if (diff(
                            timeInMilliseconds,
                            Calendar.DAY_OF_YEAR
                        ).toInt() == -4
                    ) {
                        weekDay = "วันพฤหัส"
                    } else if (diff(
                            timeInMilliseconds,
                            Calendar.DAY_OF_YEAR
                        ).toInt() == -5
                    ) {
                        weekDay = "วันพุธ"
                    } else if (diff(
                            timeInMilliseconds,
                            Calendar.DAY_OF_YEAR
                        ).toInt() == -6
                    ) {
                        weekDay = "วันอังคาร"
                    }
                    weekDay
                }
                Calendar.TUESDAY -> {
                    if (diff(
                            timeInMilliseconds,
                            Calendar.DAY_OF_YEAR
                        ).toInt() == -2
                    ) {
                        weekDay = "วันอาทิตย์"
                    } else if (diff(
                            timeInMilliseconds,
                            Calendar.DAY_OF_YEAR
                        ).toInt() == -3
                    ) {
                        weekDay = "วันเสาร์"
                    } else if (diff(
                            timeInMilliseconds,
                            Calendar.DAY_OF_YEAR
                        ).toInt() == -4
                    ) {
                        weekDay = "วันศุกร์"
                    } else if (diff(
                            timeInMilliseconds,
                            Calendar.DAY_OF_YEAR
                        ).toInt() == -5
                    ) {
                        weekDay = "วันพฤหัส"
                    } else if (diff(
                            timeInMilliseconds,
                            Calendar.DAY_OF_YEAR
                        ).toInt() == -6
                    ) {
                        weekDay = "วันพุธ"
                    }
                    weekDay
                }
                Calendar.WEDNESDAY -> {
                    if (diff(
                            timeInMilliseconds,
                            Calendar.DAY_OF_YEAR
                        ).toInt() == -2
                    ) {
                        weekDay = "วันจันทร์"
                    } else if (diff(
                            timeInMilliseconds,
                            Calendar.DAY_OF_YEAR
                        ).toInt() == -3
                    ) {
                        weekDay = "วันอาทิตย์"
                    } else if (diff(
                            timeInMilliseconds,
                            Calendar.DAY_OF_YEAR
                        ).toInt() == -4
                    ) {
                        weekDay = "วันเสาร์"
                    } else if (diff(
                            timeInMilliseconds,
                            Calendar.DAY_OF_YEAR
                        ).toInt() == -5
                    ) {
                        weekDay = "วันศุกร์"
                    } else if (diff(
                            timeInMilliseconds,
                            Calendar.DAY_OF_YEAR
                        ).toInt() == -6
                    ) {
                        weekDay = "วันพฤหัส"
                    }
                    weekDay
                }
                Calendar.THURSDAY -> {
                    if (diff(
                            timeInMilliseconds,
                            Calendar.DAY_OF_YEAR
                        ).toInt() == -2
                    ) {
                        weekDay = "วันอังคาร"
                    } else if (diff(
                            timeInMilliseconds,
                            Calendar.DAY_OF_YEAR
                        ).toInt() == -3
                    ) {
                        weekDay = "วันจันทร์"
                    } else if (diff(
                            timeInMilliseconds,
                            Calendar.DAY_OF_YEAR
                        ).toInt() == -4
                    ) {
                        weekDay = "วันอาทิตย์"
                    } else if (diff(
                            timeInMilliseconds,
                            Calendar.DAY_OF_YEAR
                        ).toInt() == -5
                    ) {
                        weekDay = "วันเสาร์"
                    } else if (diff(
                            timeInMilliseconds,
                            Calendar.DAY_OF_YEAR
                        ).toInt() == -6
                    ) {
                        weekDay = "วันศุกร์"
                    }
                    weekDay
                }
                Calendar.FRIDAY -> {
                    if (diff(
                            timeInMilliseconds,
                            Calendar.DAY_OF_YEAR
                        ).toInt() == -2
                    ) {
                        weekDay = "วันพุธ"
                    } else if (diff(
                            timeInMilliseconds,
                            Calendar.DAY_OF_YEAR
                        ).toInt() == -3
                    ) {
                        weekDay = "วันอังคาร"
                    } else if (diff(
                            timeInMilliseconds,
                            Calendar.DAY_OF_YEAR
                        ).toInt() == -4
                    ) {
                        weekDay = "วันจันทร์"
                    } else if (diff(
                            timeInMilliseconds,
                            Calendar.DAY_OF_YEAR
                        ).toInt() == -5
                    ) {
                        weekDay = "วันอาทิตย์"
                    } else if (diff(
                            timeInMilliseconds,
                            Calendar.DAY_OF_YEAR
                        ).toInt() == -6
                    ) {
                        weekDay = "วันเสาร์"
                    }
                    weekDay
                }
                Calendar.SATURDAY -> {
                    if (diff(
                            timeInMilliseconds,
                            Calendar.DAY_OF_YEAR
                        ).toInt() == -2
                    ) {
                        weekDay = "วันพฤหัส"
                    } else if (diff(
                            timeInMilliseconds,
                            Calendar.DAY_OF_YEAR
                        ).toInt() == -3
                    ) {
                        weekDay = "วันพุธ"
                    } else if (diff(
                            timeInMilliseconds,
                            Calendar.DAY_OF_YEAR
                        ).toInt() == -4
                    ) {
                        weekDay = "วันอังคาร"
                    } else if (diff(
                            timeInMilliseconds,
                            Calendar.DAY_OF_YEAR
                        ).toInt() == -5
                    ) {
                        weekDay = "วันจันทร์"
                    } else if (diff(
                            timeInMilliseconds,
                            Calendar.DAY_OF_YEAR
                        ).toInt() == -6
                    ) {
                        weekDay = "วันอาทิตย์"
                    }
                    weekDay
                }
                Calendar.SUNDAY -> {
                    if (diff(
                            timeInMilliseconds,
                            Calendar.DAY_OF_YEAR
                        ).toInt() == -2
                    ) {
                        weekDay = "วันศุกร์"
                    } else if (diff(
                            timeInMilliseconds,
                            Calendar.DAY_OF_YEAR
                        ).toInt() == -3
                    ) {
                        weekDay = "วันพฤหัส"
                    } else if (diff(
                            timeInMilliseconds,
                            Calendar.DAY_OF_YEAR
                        ).toInt() == -4
                    ) {
                        weekDay = "วันพุธ"
                    } else if (diff(
                            timeInMilliseconds,
                            Calendar.DAY_OF_YEAR
                        ).toInt() == -5
                    ) {
                        weekDay = "วันอังคาร"
                    } else if (diff(
                            timeInMilliseconds,
                            Calendar.DAY_OF_YEAR
                        ).toInt() == -6
                    ) {
                        weekDay = "วันจันทร์"
                    }
                    weekDay
                }
                else -> {
                    if (time) {
                        simpleDateMoreThanOneWeekWithTime.format(date)
                    } else {
                        simpleDateMoreThanOneWeek.format(date)
                    }
                }
            }
        }
        else -> {
            if (time) {
                simpleDateMoreThanOneWeekWithTime.format(date)
            } else {
                simpleDateMoreThanOneWeek.format(date)
            }
        }
    }
}

private fun diff(time: Long, field: Int): Long {
    val fieldTime: Long = getFieldInMillis(field)
    val cal = Calendar.getInstance()
    val now = cal.timeInMillis
    return time / fieldTime - now / fieldTime
}

private fun getFieldInMillis(field: Int): Long {
    val cal = Calendar.getInstance()
    val now = cal.timeInMillis
    cal.add(field, 1)
    val after = cal.timeInMillis
    return after - now
}
