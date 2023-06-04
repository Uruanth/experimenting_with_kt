package Flows.utils

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf


class ColorDBMock {

    companion object {
        fun getListColors(): List<ColorModel> {
            return listOf(
                ColorModel(1, "Blue", "#0000FF", true, "0.0.255"),
                ColorModel(2, "green", "#00FF00", false, "0.255.0"),
                ColorModel(3, "red", "#FF0000", true, "255.0.0"),
                ColorModel(4, "yellow", "#FFFF00", true, "255.255.0"),
                ColorModel(5, "purple", "#00FFFF", false, "0.255.255"),
                ColorModel(6, "orange", "#FF00FF", false, "255.0.255")
            );
        }
    }

    suspend fun getFlow(): Flow<ColorModel> =
        flow {
            for (color in getListColors()) {
                delay(200);
                emit(color);
            }
        }


    fun getOtherFlow(): Flow<Int> {
        return flowOf(1, 2, 3, 4, 5, 6, 7)
    }

    suspend fun getFlowDelay(color: ColorModel): Flow<String> = flow {
        emit("Color in hex: ${color.hexa}")
        delay(100)
        emit("Color in rgb ${color.rgb}")
        delay(100)
        emit("Color name ${color.name}")
        delay(100)
        emit("Color id ${color.id}")
        emit("-------")
    }

    suspend fun getFlowWithError(): Flow<ColorModel> = flow {
        for (c in getListColors()) {
            delay(100)
            if (c.name == "Blue") {
                throw RuntimeException("Color ${c.name} papu")
            }
            emit(c)
        }
    }

}