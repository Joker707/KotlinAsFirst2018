@file:Suppress("Федоров Сергей 13531/4")

package lesson4.task1

import lesson1.task1.discriminant
import lesson3.task1.isPrime
import kotlin.math.sqrt

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
        when {
            y < 0 -> listOf()
            y == 0.0 -> listOf(0.0)
            else -> {
                val root = sqrt(y)
                // Результат!
                listOf(-root, root)
            }
        }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.toLowerCase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double = sqrt(v.fold(0.0) { previousResult, element ->
    previousResult + element * element
})

/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double {
    if (list.isEmpty())
        return 0.0
    else
        return list.sum() / list.size
}

/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    val srednee = mean(list)
    for (i in 0 until list.size) {
        list[i] -= srednee
    }
    return list
}

/**
 * Средняя
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.0.
 */
fun times(a: List<Double>, b: List<Double>): Double =
        a.zip(b) { A, B -> A * B }.fold(0.0) { previouResult, element ->
            previouResult + element
        }

/**
 * Средняя
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0.0 при любом x.
 */
fun power(n: Double, i: Int): Double { /* Дополнительная функция */
    var m = 1.0
    for (j in 1..i) {
        m *= n
    }
    return m
}

fun polynom(p: List<Double>, x: Double): Double {
    var sum = 0.0
    if (p.size == 0) return sum
    for (i in 0 until p.size) {
        sum += p[i] * power(x, i)
    }
    return sum
}

/**
 * Средняя
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Double>): MutableList<Double> {
    var sum = list.sum()
    for (i in list.size - 1 downTo 0) {
        val a = list[i]
        list[i] = sum
        sum -= a
    }
    return list
}

/**
 * Средняя
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {
    var a = n
    var m = 0
    val result = mutableListOf<Int>()
    while (a != 1) {
        for (i in 2..a) {
            if ((isPrime(i)) && (a % i == 0)) {
                result.add(i)
                m = i
                break
            }
        }
        a /= m
    }
    return result
}

/**
 * Сложная
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String = factorize(n).joinToString(separator = "*")

/**
 * Средняя
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    val a = mutableListOf<Int>()
    val b = mutableListOf<Int>()
    var m = n
    if (m == 0) b.add(0)
    if (m == 1) {
        b.add(1)
    } else {
        while (m > 0) {
            if (m >= base) {
                if (m % base == 0) {
                    m /= base
                    a.add(0)
                } else {
                    for (i in 1..base - 1) {
                        if ((m - i) % base == 0) {
                            a.add(i)
                            m -= i
                            break
                        }
                    }
                    m /= base
                }
            } else {
                a.add(m)
                break
            }

        }
    }
    for (i in a.size - 1 downTo 0) {
        b.add(a[i])
    }
    return b
}

/**
 * Сложная
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 */
fun convertToString(n: Int, base: Int): String {
    var a = ""
    var m = n
    if (m < base) {
        if (m > 9) when {
            m == 10 -> a += "a"
            m == 11 -> a += "b"
            m == 12 -> a += "c"
            m == 13 -> a += "d"
            m == 14 -> a += "e"
            m == 15 -> a += "f"
            m == 16 -> a += "g"
            m == 17 -> a += "h"
            m == 18 -> a += "i"
            m == 19 -> a += "j"
            m == 20 -> a += "k"
            m == 21 -> a += "l"
            m == 22 -> a += "m"
            m == 23 -> a += "n"
            m == 24 -> a += "o"
            m == 25 -> a += "p"
            m == 26 -> a += "q"
            m == 27 -> a += "r"
            m == 28 -> a += "s"
            m == 29 -> a += "t"
            m == 30 -> a += "u"
            m == 31 -> a += "v"
            m == 32 -> a += "w"
            m == 33 -> a += "x"
            m == 34 -> a += "y"
            m == 35 -> a += "z"
        } else a += "$m"
    } else {
        while (m > 0) {
            if (m % base == 0) {
                m /= base
                a += "0"
            } else {
                when {
                    m % base == 10 -> a += "a"
                    m % base == 11 -> a += "b"
                    m % base == 12 -> a += "c"
                    m % base == 13 -> a += "d"
                    m % base == 14 -> a += "e"
                    m % base == 15 -> a += "f"
                    m % base == 16 -> a += "g"
                    m % base == 17 -> a += "h"
                    m % base == 18 -> a += "i"
                    m % base == 19 -> a += "j"
                    m % base == 20 -> a += "k"
                    m % base == 21 -> a += "l"
                    m % base == 22 -> a += "m"
                    m % base == 23 -> a += "n"
                    m % base == 24 -> a += "o"
                    m % base == 25 -> a += "p"
                    m % base == 26 -> a += "q"
                    m % base == 27 -> a += "r"
                    m % base == 28 -> a += "s"
                    m % base == 29 -> a += "t"
                    m % base == 30 -> a += "u"
                    m % base == 31 -> a += "v"
                    m % base == 32 -> a += "w"
                    m % base == 33 -> a += "x"
                    m % base == 34 -> a += "y"
                    m % base == 35 -> a += "z"
                    else -> {
                        val i = m % base
                        a += "$i"
                    }
                }
                m /= base
            }
        }
    }
    return a.reversed()
}

/**
 * Средняя
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    var result = 0
    if (digits.size < 2) {
        result = digits[0]
    } else {
        for (i in 0 until digits.size - 1) {
            result += digits[i]
            result *= base
        }
        result += digits[digits.size - 1]
    }
    return result
}

/**
 * Сложная
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 */
fun decimalFromString(str: String, base: Int): Int {
    var sum = 0
    var a = ""
    var b = 0
    if (str.length > 1) {
        for (i in 0 until str.length - 1) {
            a += str[i]
            when {
                a == "a" -> b = 10
                a == "b" -> b = 11
                a == "c" -> b = 12
                a == "d" -> b = 13
                a == "e" -> b = 14
                a == "f" -> b = 15
                a == "g" -> b = 16
                a == "h" -> b = 17
                a == "i" -> b = 18
                a == "j" -> b = 19
                a == "k" -> b = 20
                a == "l" -> b = 21
                a == "m" -> b = 22
                a == "n" -> b = 23
                a == "o" -> b = 24
                a == "p" -> b = 25
                a == "q" -> b = 26
                a == "r" -> b = 27
                a == "s" -> b = 28
                a == "t" -> b = 29
                a == "u" -> b = 30
                a == "v" -> b = 31
                a == "w" -> b = 32
                a == "x" -> b = 33
                a == "y" -> b = 34
                a == "z" -> b = 35
                else -> b += a.toInt()
            }
            sum += b
            sum *= base
            a = ""
            b = 0
        }
        a += str[str.length - 1]
        when {
            a == "a" -> b = 10
            a == "b" -> b = 11
            a == "c" -> b = 12
            a == "d" -> b = 13
            a == "e" -> b = 14
            a == "f" -> b = 15
            a == "g" -> b = 16
            a == "h" -> b = 17
            a == "i" -> b = 18
            a == "j" -> b = 19
            a == "k" -> b = 20
            a == "l" -> b = 21
            a == "m" -> b = 22
            a == "n" -> b = 23
            a == "o" -> b = 24
            a == "p" -> b = 25
            a == "q" -> b = 26
            a == "r" -> b = 27
            a == "s" -> b = 28
            a == "t" -> b = 29
            a == "u" -> b = 30
            a == "v" -> b = 31
            a == "w" -> b = 32
            a == "x" -> b = 33
            a == "y" -> b = 34
            a == "z" -> b = 35
            else -> b += a.toInt()
        }
        sum += b
    } else {
        a += str
        when {
            a == "a" -> b = 10
            a == "b" -> b = 11
            a == "c" -> b = 12
            a == "d" -> b = 13
            a == "e" -> b = 14
            a == "f" -> b = 15
            a == "g" -> b = 16
            a == "h" -> b = 17
            a == "i" -> b = 18
            a == "j" -> b = 19
            a == "k" -> b = 20
            a == "l" -> b = 21
            a == "m" -> b = 22
            a == "n" -> b = 23
            a == "o" -> b = 24
            a == "p" -> b = 25
            a == "q" -> b = 26
            a == "r" -> b = 27
            a == "s" -> b = 28
            a == "t" -> b = 29
            a == "u" -> b = 30
            a == "v" -> b = 31
            a == "w" -> b = 32
            a == "x" -> b = 33
            a == "y" -> b = 34
            a == "z" -> b = 35
            else -> b += a.toInt()
        }
        sum += b
    }
    return sum
}

/**
 * Сложная
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String {
    var str = ""
    when {
        n / 1000 == 1 -> str += "M"
        n / 1000 == 2 -> str += "MM"
        n / 1000 == 3 -> str += "MMM"
    }
    when {
        n / 100 % 10 == 1 -> str += "C"
        n / 100 % 10 == 2 -> str += "CC"
        n / 100 % 10 == 3 -> str += "CCC"
        n / 100 % 10 == 4 -> str += "CD"
        n / 100 % 10 == 5 -> str += "D"
        n / 100 % 10 == 6 -> str += "DC"
        n / 100 % 10 == 7 -> str += "DCC"
        n / 100 % 10 == 8 -> str += "DCCC"
        n / 100 % 10 == 9 -> str += "CM"
    }
    when {
        n / 10 % 10 == 1 -> str += "X"
        n / 10 % 10 == 2 -> str += "XX"
        n / 10 % 10 == 3 -> str += "XXX"
        n / 10 % 10 == 4 -> str += "XL"
        n / 10 % 10 == 5 -> str += "L"
        n / 10 % 10 == 6 -> str += "LX"
        n / 10 % 10 == 7 -> str += "LXX"
        n / 10 % 10 == 8 -> str += "LXXX"
        n / 10 % 10 == 9 -> str += "XC"
    }
    when {
        n % 10 == 1 -> str += "I"
        n % 10 == 2 -> str += "II"
        n % 10 == 3 -> str += "III"
        n % 10 == 4 -> str += "IV"
        n % 10 == 5 -> str += "V"
        n % 10 == 6 -> str += "VI"
        n % 10 == 7 -> str += "VII"
        n % 10 == 8 -> str += "VIII"
        n % 10 == 9 -> str += "IX"
    }
    return str
}

/**
 * Очень сложная
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun russian(n: Int): String {
    var str1 = ""
    var str2 = ""
    val n1 = n / 1000
    val n2 = n % 1000
    when {
        n1 / 100 == 1 -> str1 += "сто "
        n1 / 100 == 2 -> str1 += "двести "
        n1 / 100 == 3 -> str1 += "триста "
        n1 / 100 == 4 -> str1 += "четыреста "
        n1 / 100 == 5 -> str1 += "пятьсот "
        n1 / 100 == 6 -> str1 += "шестьсот "
        n1 / 100 == 7 -> str1 += "семьсот "
        n1 / 100 == 8 -> str1 += "восемьсот "
        n1 / 100 == 9 -> str1 += "девятьсот "
    }
    when {
        n1 % 100 == 10 -> str1 += "десять "
        n1 % 100 == 11 -> str1 += "одиннадцать "
        n1 % 100 == 12 -> str1 += "двенадцать "
        n1 % 100 == 13 -> str1 += "тринадцать "
        n1 % 100 == 14 -> str1 += "четырнадцать "
        n1 % 100 == 15 -> str1 += "пятнадцать "
        n1 % 100 == 16 -> str1 += "шестнадцать "
        n1 % 100 == 17 -> str1 += "семнадцать "
        n1 % 100 == 18 -> str1 += "восемнадцать "
        n1 % 100 == 19 -> str1 += "девятнадцать "
        n1 % 100 / 10 == 2 -> str1 += "двадцать "
        n1 % 100 / 10 == 3 -> str1 += "тридцать "
        n1 % 100 / 10 == 4 -> str1 += "сорок "
        n1 % 100 / 10 == 5 -> str1 += "пятьдесят "
        n1 % 100 / 10 == 6 -> str1 += "шестьдесят "
        n1 % 100 / 10 == 7 -> str1 += "семьдесят "
        n1 % 100 / 10 == 8 -> str1 += "восемьдесят "
        n1 % 100 / 10 == 9 -> str1 += "девяносто "
    }
    when {
        (n1 % 10 == 1) && (n1 % 100 != 11) -> str1 += "одна "
        (n1 % 10 == 2) && (n1 % 100 != 12) -> str1 += "две "
        (n1 % 10 == 3) && (n1 % 100 != 13) -> str1 += "три "
        (n1 % 10 == 4) && (n1 % 100 != 14) -> str1 += "четыре "
        (n1 % 10 == 5) && (n1 % 100 != 15) -> str1 += "пять "
        (n1 % 10 == 6) && (n1 % 100 != 16) -> str1 += "шесть "
        (n1 % 10 == 7) && (n1 % 100 != 17) -> str1 += "семь "
        (n1 % 10 == 8) && (n1 % 100 != 18) -> str1 += "восемь "
        (n1 % 10 == 9) && (n1 % 100 != 19) -> str1 += "девять "
    }
    if ((n1 % 10 == 1) && (n1 % 100 !in 10..19)) str1 += "тысяча "
    if ((n1 % 10 in 2..4) && (n1 % 100 !in 10..19)) str1 += "тысячи "
    else {
        if ((n1 > 0) && ((n1 % 10 !in 1..4) || (n1 % 100 in 10..19))) str1 += "тысяч "
    }
    when {
        n2 / 100 == 1 -> str2 += "сто "
        n2 / 100 == 2 -> str2 += "двести "
        n2 / 100 == 3 -> str2 += "триста "
        n2 / 100 == 4 -> str2 += "четыреста "
        n2 / 100 == 5 -> str2 += "пятьсот "
        n2 / 100 == 6 -> str2 += "шестьсот "
        n2 / 100 == 7 -> str2 += "семьсот "
        n2 / 100 == 8 -> str2 += "восемьсот "
        n2 / 100 == 9 -> str2 += "девятьсот "
    }
    when {
        n2 % 100 == 10 -> str2 += "десять "
        n2 % 100 == 11 -> str2 += "одиннадцать "
        n2 % 100 == 12 -> str2 += "двенадцать "
        n2 % 100 == 13 -> str2 += "тринадцать "
        n2 % 100 == 14 -> str2 += "четырнадцать "
        n2 % 100 == 15 -> str2 += "пятнадцать "
        n2 % 100 == 16 -> str2 += "шестнадцать "
        n2 % 100 == 17 -> str2 += "семнадцать "
        n2 % 100 == 18 -> str2 += "восемнадцать "
        n2 % 100 == 19 -> str2 += "девятнадцать "
        n2 % 100 / 10 == 2 -> str2 += "двадцать "
        n2 % 100 / 10 == 3 -> str2 += "тридцать "
        n2 % 100 / 10 == 4 -> str2 += "сорок "
        n2 % 100 / 10 == 5 -> str2 += "пятьдесят "
        n2 % 100 / 10 == 6 -> str2 += "шестьдесят "
        n2 % 100 / 10 == 7 -> str2 += "семьдесят "
        n2 % 100 / 10 == 8 -> str2 += "восемьдесят "
        n2 % 100 / 10 == 9 -> str2 += "девяносто "
    }
    when {
        (n2 % 10 == 1) && (n2 % 100 != 11) -> str2 += "один "
        (n2 % 10 == 2) && (n2 % 100 != 12) -> str2 += "два "
        (n2 % 10 == 3) && (n2 % 100 != 13) -> str2 += "три "
        (n2 % 10 == 4) && (n2 % 100 != 14) -> str2 += "четыре "
        (n2 % 10 == 5) && (n2 % 100 != 15) -> str2 += "пять "
        (n2 % 10 == 6) && (n2 % 100 != 16) -> str2 += "шесть "
        (n2 % 10 == 7) && (n2 % 100 != 17) -> str2 += "семь "
        (n2 % 10 == 8) && (n2 % 100 != 18) -> str2 += "восемь "
        (n2 % 10 == 9) && (n2 % 100 != 19) -> str2 += "девять "
    }
    if (n2 > 0) {
        str2 = str2.substring(0, str2.length - 1)
    } else {
        str1 = str1.substring(0, str1.length - 1)
    }
    return str1 + str2
}