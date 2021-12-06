package com.eit.ipsc

import kotlin.math.pow

class Calcoli(private val ip1Int: Int?, private val ip2Int: Int?, private val ip3Int: Int?, private val ip4Int: Int?, private val mascheraInt: Int?) : MainActivity_Calculate() {

    @Throws(NullPointerException::class)
    fun indirizzoReteArray(): IntArray {

        val indirizzoRete = intArrayOf(0, 0, 0, 0)
        var somma = 0

        val arraybid = arrayOf(intArrayOf(128, 0), intArrayOf(64, 0), intArrayOf(32, 0), intArrayOf(16, 0), intArrayOf(8, 0), intArrayOf(4, 0), intArrayOf(2, 0), intArrayOf(1, 0))

        when {
            mascheraInt!! in 0..8 -> {

                for (i in 0..7)
                    arraybid[i][1] = Character.getNumericValue(ipBinarioArray()[0][i])

                for (y in 0 until mascheraInt)
                    if (arraybid[y][1] == 1)
                        somma += arraybid[y][0]

                indirizzoRete[0] = somma

            }
            mascheraInt in 9..16 -> {

                for (i in 0..7)
                    arraybid[i][1] = Character.getNumericValue(ipBinarioArray()[1][i])

                for (y in 0 until mascheraInt - 8)
                    if (arraybid[y][1] == 1)
                        somma += arraybid[y][0]

                indirizzoRete[0] = ip1Int!!
                indirizzoRete[1] = somma

            }
            mascheraInt in 17..24 -> {

                for (i in 0..7)
                    arraybid[i][1] = Character.getNumericValue(ipBinarioArray()[2][i])

                for (y in 0 until mascheraInt - 16)
                    if (arraybid[y][1] == 1)
                        somma += arraybid[y][0]

                indirizzoRete[0] = ip1Int!!
                indirizzoRete[1] = ip2Int!!
                indirizzoRete[2] = somma

            }
            mascheraInt in 25..32 -> {

                for (i in 0..7)
                    arraybid[i][1] = Character.getNumericValue(ipBinarioArray()[3][i])

                for (y in 0 until mascheraInt - 24)
                    if (arraybid[y][1] == 1)
                        somma += arraybid[y][0]

                indirizzoRete[0] = ip1Int!!
                indirizzoRete[1] = ip2Int!!
                indirizzoRete[2] = ip3Int!!
                indirizzoRete[3] = somma
            }
        }

        return indirizzoRete
    }

    @Throws(NullPointerException::class)
    fun indirizzoRete(): String {
        return indirizzoReteArray()[0].toString() + "." + indirizzoReteArray()[1] + "." + indirizzoReteArray()[2] + "." + indirizzoReteArray()[3] + " /" + mascheraInt
    }

    private fun ipBinarioArray(): Array<String> {

        val ipBinFinal = arrayOf("", "", "", "")

        ipBinFinal[0] = String.format("%8s", Integer.toBinaryString(ip1Int!!)).replace(' ', '0')
        ipBinFinal[1] = String.format("%8s", Integer.toBinaryString(ip2Int!!)).replace(' ', '0')
        ipBinFinal[2] = String.format("%8s", Integer.toBinaryString(ip3Int!!)).replace(' ', '0')
        ipBinFinal[3] = String.format("%8s", Integer.toBinaryString(ip4Int!!)).replace(' ', '0')

        return ipBinFinal
    }

    fun ipBinario(): String {
        return ipBinarioArray()[0] + "." + ipBinarioArray()[1] + "." + ipBinarioArray()[2] + "." + ipBinarioArray()[3]
    }

    private fun mascheraArray(): IntArray {
        val risultato: Int
        var mascConvertita = 0
        val mascheraFinal = intArrayOf(0, 0, 0, 0)
        var tmp = 0

        if (mascheraInt!! >= 0 && mascheraInt <= 8) {

            tmp = 8 - mascheraInt
            risultato = 2.0.pow(tmp.toDouble()).toInt()

            mascConvertita = 255 - risultato + 1

            mascheraFinal[0] = mascConvertita

        } else if (mascheraInt in 9..16) {

            tmp = 16 - mascheraInt
            risultato = 2.0.pow(tmp.toDouble()).toInt()

            mascConvertita = 255 - risultato + 1

            mascheraFinal[0] = 255
            mascheraFinal[1] = mascConvertita

        } else if (mascheraInt in 17..24) {

            tmp = 24 - mascheraInt
            risultato = 2.0.pow(tmp.toDouble()).toInt()

            mascConvertita = 255 - risultato + 1

            mascheraFinal[0] = 255
            mascheraFinal[1] = 255
            mascheraFinal[2] = mascConvertita

        } else if (mascheraInt in 25..32) {

            tmp = 32 - mascheraInt
            risultato = 2.0.pow(tmp.toDouble()).toInt()

            mascConvertita = 255 - risultato + 1

            mascheraFinal[0] = 255
            mascheraFinal[1] = 255
            mascheraFinal[2] = 255
            mascheraFinal[3] = mascConvertita
        }

        return mascheraFinal
    }

    fun maschera(): String {
        return mascheraArray()[0].toString() + "." + mascheraArray()[1] + "." + mascheraArray()[2] + "." + mascheraArray()[3]
    }

    fun primoIndirizzo(): String {

        return if (mascheraInt == 32)
            indirizzoReteArray()[0].toString() + "." + indirizzoReteArray()[1] + "." + indirizzoReteArray()[2] + "." + indirizzoReteArray()[3]
        else
            indirizzoReteArray()[0].toString() + "." + indirizzoReteArray()[1] + "." + indirizzoReteArray()[2] + "." + (indirizzoReteArray()[3] + 1)
    }

    fun ultimoInd(): String {

        return if (mascheraInt == 32)
            broadcastArray()[0].toString() + "." + broadcastArray()[1] + "." + broadcastArray()[2] + "." + broadcastArray()[3]
        else
            broadcastArray()[0].toString() + "." + broadcastArray()[1] + "." + broadcastArray()[2] + "." + (broadcastArray()[3] - 1)
    }

    fun numIndDisp(): String {

        val num: Long
        val potenza3 = 256.0.pow(3.0).toLong()
        val potenza2 = 256.0.pow(2.0).toLong()
        val potenza1 = 256.0.pow(1.0).toLong()
        val potenza0 = 256.0.pow(0.0).toLong()

        val broadcastArray0 = broadcastArray()[0]
        val broadcastArray1 = broadcastArray()[1]
        val broadcastArray2 = broadcastArray()[2]
        val broadcastArray3 = broadcastArray()[3]

        val indReteArray0 = indirizzoReteArray()[0]
        val indReteArray1 = indirizzoReteArray()[1]
        val indReteArray2 = indirizzoReteArray()[2]
        val indReteArray3 = indirizzoReteArray()[3]

        val calcolo1 = broadcastArray0 * potenza3 + broadcastArray1 * potenza2 + broadcastArray2 * potenza1 + (broadcastArray3 - 1) * potenza0
        val calcolo2 = indReteArray0 * potenza3 + indReteArray1 * potenza2 + indReteArray2 * potenza1 + (indReteArray3 + 1) * potenza0

        num = if (mascheraInt == 32)
            1
        else
            calcolo1 - calcolo2 + 1

        return num.toString()
    }

    private fun broadcastArray(): IntArray {

        val broadcastFinal = intArrayOf(255, 255, 255, 255)

        var somma = 0

        val arraybid = arrayOf(intArrayOf(128, 0), intArrayOf(64, 0), intArrayOf(32, 0), intArrayOf(16, 0), intArrayOf(8, 0), intArrayOf(4, 0), intArrayOf(2, 0), intArrayOf(1, 0))

        when {
            mascheraInt!! in 0..8 -> {

                for (i in 0..7)
                    arraybid[i][1] = Character.getNumericValue(ipBinarioArray()[0][i])

                for (y in 0 until mascheraInt)
                    if (arraybid[y][1] == 0)
                        somma += arraybid[y][0]

                broadcastFinal[0] = 255 - somma

            }
            mascheraInt in 9..16 -> {

                for (i in 0..7)
                    arraybid[i][1] = Character.getNumericValue(ipBinarioArray()[1][i])

                for (y in 0 until mascheraInt - 8)
                    if (arraybid[y][1] == 0)
                        somma += arraybid[y][0]

                broadcastFinal[0] = ip1Int!!
                broadcastFinal[1] = 255 - somma

            }
            mascheraInt in 17..24 -> {

                for (i in 0..7)
                    arraybid[i][1] = Character.getNumericValue(ipBinarioArray()[2][i])

                for (y in 0 until mascheraInt - 16)
                    if (arraybid[y][1] == 0)
                        somma += arraybid[y][0]

                broadcastFinal[0] = ip1Int!!
                broadcastFinal[1] = ip2Int!!
                broadcastFinal[2] = 255 - somma

            }
            mascheraInt in 25..31 -> {

                for (i in 0..7)
                    arraybid[i][1] = Character.getNumericValue(ipBinarioArray()[3][i])

                for (y in 0 until mascheraInt - 24)
                    if (arraybid[y][1] == 0)
                        somma += arraybid[y][0]

                broadcastFinal[0] = ip1Int!!
                broadcastFinal[1] = ip2Int!!
                broadcastFinal[2] = ip3Int!!
                broadcastFinal[3] = 255 - somma

            }
            mascheraInt == 32 -> {
                broadcastFinal[0] = ip1Int!!
                broadcastFinal[1] = ip2Int!!
                broadcastFinal[2] = ip3Int!!
                broadcastFinal[3] = ip4Int!!
            }
        }

        return broadcastFinal
    }

    fun broadcast(): String {
        return broadcastArray()[0].toString() + "." + broadcastArray()[1] + "." + broadcastArray()[2] + "." + broadcastArray()[3]
    }

    fun wildcard(): String {
        return (255 - mascheraArray()[0]).toString() + "." + (255 - mascheraArray()[1]) + "." + (255 - mascheraArray()[2]) + "." + (255 - mascheraArray()[3])
    }
}
