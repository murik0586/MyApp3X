
package ru.netology.nmedia

object DigitalFormat {
    fun format(source: Int) {
        when (source) {
            in 0..999 -> source.toString()
            in 1000..9999 -> {
                val firstPart = (source / 1000).toInt()
                val secondPart = (source % 1000).toString()[0]
                val sb = StringBuilder()
                sb.append(firstPart)
                if (secondPart != '0') {
                    sb.append(".$secondPart")
                }
                sb.append("K")
                sb.toString()
            }
            in 10000..999999 -> {
                    val firstPart2 = (source / 1000).toInt()
                    val secondPart2 = (source % 1000).toString()[0]
                    val sb1 = StringBuilder()
                    sb1.append(firstPart2)
                    if (secondPart2 == '0') {
                        sb1.append(".$secondPart2")
                    }
                    sb1.append("K")
                    sb1.toString()
                }
            else -> {source.toString()
        }

            }


            }


        }



