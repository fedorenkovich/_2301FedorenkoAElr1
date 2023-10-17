import kotlin.math.ceil

var lengthMain : Int = 0
var lengthSecond : Int = 0
var listInserted = false
//класс генерации элементов двусвязного списка
class DoubleLinkedList(var value: Int?) {
    var prev: DoubleLinkedList? = null
    var next: DoubleLinkedList? = null

    companion object {
        //вывод на экран всего списка
        fun printList(start: DoubleLinkedList?, length: Int) {
            println("Список: ")
            var temp = start!!.next
            for (i in 0..<length){
                print("${temp!!.value} ")
                temp = temp.next
            }
            println()
        }

        //добавление элемента в конец списка
        fun insertToTail(finish: DoubleLinkedList, value: Int?) {
            val newTail = DoubleLinkedList(value)
            finish.prev!!.next = newTail
            newTail.next = finish
            newTail.prev = finish.prev
            finish.prev = newTail
        }

        //добавление элемента в начало списка
        fun insertToHead(start: DoubleLinkedList?, value: Int?) {
            val newHead = DoubleLinkedList(value)
            newHead.next = start!!.next
            start.next!!.prev = newHead
            start.next = newHead
            newHead.prev = start
        }

        //добавление элемента на позицию по индексу
        fun addElemOfIndex(head: DoubleLinkedList?, finish: DoubleLinkedList?, index: Int?, length: Int) {
            if (index == 0) {
                print("Введите значение элемента: ")
                val elemValue: Int = readln().toInt()
                insertToHead(head, elemValue)
            } else if ((index!! > 0) && (index < length)) {
                print("Введите значение элемента: ")
                val elemValue: Int = readln().toInt()
                if (index < ceil(length.toDouble() / 2).toInt()) {
                    var tempElem = head!!.next
                    for (i in 1..<index) {
                        tempElem = tempElem!!.next
                    }
                    val newElem = DoubleLinkedList(elemValue)
                    tempElem!!.next!!.prev = newElem
                    newElem.next = tempElem.next
                    tempElem.next = newElem
                    newElem.prev = tempElem
                } else {
                    var tempElem = finish!!.prev
                    for (i in 1..<length - index) {
                        tempElem = tempElem!!.prev
                    }
                    val newElem = DoubleLinkedList(elemValue)
                    tempElem!!.prev!!.next = newElem
                    newElem.prev = tempElem.prev
                    newElem.next = tempElem
                    tempElem.prev = newElem
                }
            } else {
                println("Ошибка, введен несуществующий индекс")
            }
        }

        //удаление последнего элемента списка
        fun deleteLastElem(end: DoubleLinkedList?) {
            val delElem = end!!.prev
            end.prev = delElem!!.prev
            delElem.prev!!.next = end
        }

        //удаление первого элемента списка
        fun deleteFirstElem(start: DoubleLinkedList?) {
            val delElem = start!!.next
            start.next = delElem!!.next
            delElem.next!!.prev = start
        }

        //получение элемента по индексу
        fun getElemOfIndex(start: DoubleLinkedList, end: DoubleLinkedList, length: Int, index: Int): DoubleLinkedList? {
            if (index in 0..<length) {
                println("Значение элемента по индексу “$index”: ")
                return if (index < ceil(length.toDouble() / 2).toInt()) {
                    var elem = start.next
                    for (i in 0..<index) {
                        elem = elem!!.next
                    }
                    elem
                } else {
                    var elem = end.prev
                    for (i in 1..<length - index) {
                        elem = elem!!.prev
                    }
                    elem
                }
            } else {
                println("Ошибка, введен несуществующий индекс")
                return null
            }
        }

        //удаление элемента по индексу
        fun delElemOfIndex(start: DoubleLinkedList, end: DoubleLinkedList, length: Int, index: Int) {
            if (index in 0..<length) {
                if (index < ceil(length.toDouble() / 2).toInt()) {
                    var delElem = start.next
                    for (i in 0..<index) {
                        delElem = delElem!!.next
                    }
                    delElem!!.next!!.prev = delElem.prev
                    delElem.prev!!.next = delElem.next
                } else {
                    var delElem = end.prev
                    for (i in 1..<length - index) {
                        delElem = delElem!!.prev
                    }
                    delElem!!.next!!.prev = delElem.prev
                    delElem.prev!!.next = delElem.next
                }
            } else {
                println("Ошибка, введен несуществующий индекс")
            }
        }

        //вывод длины списка
        fun getLength(length: Int) {
            println("Длина списка: $length")
        }

        //удаление всех элементов списка
        fun clearList(start: DoubleLinkedList, length: Int) {
            var listLength = length
            while (listLength != 0) {
                deleteFirstElem(start)
                listLength--
            }
        }

        //замена значения элемента по индексу на новое
        fun replaceElem(start: DoubleLinkedList, end: DoubleLinkedList, length: Int, index: Int) {
            if (index in 0..<length) {
                print("Введите новое значение элемента: ")
                val elemValue = readln().toInt()
                if (index < ceil(length.toDouble() / 2).toInt()) {
                    var newElem = start.next
                    for (i in 0..<index) {
                        newElem = newElem!!.next
                    }
                    newElem!!.value = elemValue
                } else {
                    var newElem = end.prev
                    for (i in 1..<length - index) {
                        newElem = newElem!!.prev
                    }
                    newElem!!.value = elemValue
                }
            } else {
                println("Ошибка, введен несуществующий индекс")
            }
        }

        //проверка списка на пустоту
        fun checkList(length: Int) {
            if (length == 0)
                println("Список пустой")
            else
                println("Список не пустой")
        }

        //изменение порядка списка на обратный
        fun reverseList(start: DoubleLinkedList, length: Int) {
            var helper = start.next
            val temp = DoubleLinkedList(null)
            for (i in 1..length) {
                temp.next = helper!!.next
                temp.prev = helper.prev
                helper.next = temp.prev
                helper.prev = temp.next
                helper = temp.next
            }
        }

        //добавление один лист в другой по индексу
        fun insertList(startMainList: DoubleLinkedList, endMainList: DoubleLinkedList, startSecondList: DoubleLinkedList, endSecondList: DoubleLinkedList, index: Int) {
            if (index in 0..<lengthMain) {
                println("Значение элемента по индексу: $index: ")
                if (index < ceil(lengthMain.toDouble() / 2).toInt()) {
                    var newElem = startMainList.next
                    for (i in 0..<index-1) {
                        newElem = newElem!!.next
                    }
                    newElem!!.next!!.prev = endSecondList.prev
                    endSecondList.prev!!.next = newElem.next
                    startSecondList.next!!.prev = newElem
                    newElem.next = startSecondList.next
                } else {
                    var newElem = endMainList.prev
                    for (i in 1..<lengthMain - index) {
                        newElem = newElem!!.prev
                    }
                    newElem!!.next!!.prev = endSecondList.prev
                    endSecondList.prev!!.next = newElem.next
                    startSecondList.next!!.prev = newElem
                    newElem.next = startSecondList.next
                }
                lengthMain += lengthSecond
                listInserted = true
            }
        }

        //добавление один список в конец другого
        fun insertListLast(endMainList: DoubleLinkedList, startSecondList: DoubleLinkedList, endSecondList: DoubleLinkedList) {
            startSecondList.next!!.prev = endMainList.prev
            endMainList.prev!!.next = startSecondList.next
            endSecondList.prev!!.next = endMainList
            endMainList.prev = endSecondList.prev
            lengthMain += lengthSecond
            listInserted = true
        }

        //добавление одного списка в начало другого
        fun insertListFirst(startMainList: DoubleLinkedList, startSecondList: DoubleLinkedList, endSecondList: DoubleLinkedList) {
            endSecondList.prev!!.next = startMainList.next
            startMainList.next!!.prev = endSecondList.prev
            startMainList.next = startSecondList.next
            startSecondList.next!!.prev = startMainList
            lengthMain += lengthSecond
            listInserted = true
        }

        //проверка производилась ли вставка одного списка в другой
        fun containsSecondList(inserted: Boolean) {
            if (inserted) {
                println("Вставной список содержится в начальном")
            } else {
                println("Вставной список не содержится в начальном")
            }
        }

        //вывод индекса элемента первого вхождения одного списка в другой список
        fun getFirstEntry(startMainList: DoubleLinkedList, startSecondList: DoubleLinkedList) {
            var newElem: DoubleLinkedList? = startMainList
            var index = 0
            while (newElem!!.next != startSecondList.next) {
                index += 1
                newElem = newElem.next
            }
            println("Индекс первого вхождения списка в список: $index")
        }

        //вывод индекса элемента последнего вхождения одного списка в другой список
        fun getLastEntry(endMainList: DoubleLinkedList, endSecondList: DoubleLinkedList) {
            var newElem: DoubleLinkedList? = endMainList
            var index = 1
            while (newElem!!.prev != endSecondList.prev) {
                index += 1
                newElem = newElem.prev
            }
            index = lengthMain - index
            println("Индекс последнего вхождения списка в список: $index")
        }

        //обмен двух элементов списка по индексам
        fun swap(startMainList: DoubleLinkedList, endMainList: DoubleLinkedList, length: Int, indexFirst: Int, indexSecond: Int) {
            val temp: Int?
            val addFirst = getElemOfIndex(startMainList, endMainList, length, indexFirst)
            val addSecond = getElemOfIndex(startMainList, endMainList, length, indexSecond)
            temp = addFirst!!.value
            addFirst.value = addSecond!!.value
            addSecond.value = temp
        }
    }
}

fun main() {
    var headMain = DoubleLinkedList(null)
    var tailMain = DoubleLinkedList(null)

    val headSecond = DoubleLinkedList(null)
    val tailSecond = DoubleLinkedList(null)

    val add: DoubleLinkedList?

    println("Заполните второй список (для дальнейшей работы с двумя списками)\n")
    println("Введите первый элемент для добавления в список: ")
    val firstElem: Int = readln().toInt()
    add = DoubleLinkedList(firstElem)
    add.next = tailSecond
    add.prev = headSecond
    headSecond.next = add
    tailSecond.prev = add
    lengthSecond++
    DoubleLinkedList.printList(headSecond, lengthSecond)

    do {
        println("===================================================")
        println("Выберите: ")
        println("1. Добавить элемент в конец")
        println("2. Добавить элемент в начало")
        println("3. Добавить элемент по индексу")
        println("0. Закончить ввод и перейти к основному списку")
        println("===================================================")
        val choiceBegin = readln().toInt()
        when (choiceBegin) {
            1 -> {
                print("Введите элемент для добавления: ")
                val elemValue = readln().toInt()
                DoubleLinkedList.insertToTail(tailSecond, elemValue)
                lengthSecond++
                DoubleLinkedList.printList(headSecond, lengthSecond)
            }
            2 -> {
                print("Введите элемент для добавления: ")
                val elemValue = readln().toInt()
                DoubleLinkedList.insertToHead(headSecond, elemValue)
                lengthSecond++
                DoubleLinkedList.printList(headSecond, lengthSecond)
            }
            3 -> {
                print("Введите индекс для добавления: ")
                val elemIndex = readln().toInt()
                DoubleLinkedList.addElemOfIndex(headSecond, tailSecond, elemIndex, lengthSecond)
                lengthSecond++
                DoubleLinkedList.printList(headSecond, lengthSecond)
            }
        }
    } while (choiceBegin != 0)
    println()
    println("Основной список")
    println()

    do {
        if (lengthMain == 0){
            println("Введите первый элемент для добавления в список: ")
            val firstElemSecondListValue = readln().toInt()
            val firstElemSecondList = DoubleLinkedList(firstElemSecondListValue)
            firstElemSecondList.next = tailMain
            firstElemSecondList.prev = headMain
            headMain.next = firstElemSecondList
            tailMain.prev = firstElemSecondList
            lengthMain++
            DoubleLinkedList.printList(headMain, lengthMain)
        }

        println("===================================================")
        println("Выберите действие: ")
        println("1. Добавить элемент в конец списка")
        println("2. Добавить элемент в начало списка")
        println("3. Удалить последний элемент списка")
        println("4. Удалить первый элемент списка")
        println("5. Добавить элемент по индексу")
        println("6. Получить элемент по индексу")
        println("7. Удалить элемент по индексу")
        println("8. Получить размер списка")
        println("9. Удалить все элементы списка")
        println("10. Заменить элемент по индексу")
        println("11. Проверка на пустоту списка")
        println("12. Поменять порядок элементов на обратный")
        println("13. Вставить список в список по индексу")
        println("14. Вставить другой список в конец")
        println("15. Вставить другой список в начало")
        println("16. Проверить на содержание одного списка в другом")
        println("17. Поиск первого вхождения списка в список")
        println("18. Поиск последнего вхождения списка")
        println("19. Обмен двух элементов списка по индексам")
        println("0. Завершить работу")
        println("===================================================")
        val choice = readln().toInt()

        when (choice) {
            1 -> {
                print("Введите элемент для добавления: ")
                val elemValue = readln().toInt()
                DoubleLinkedList.insertToTail(tailMain, elemValue)
                lengthMain++
                DoubleLinkedList.printList(headMain, lengthMain)
            }
            2 -> {
                print("Введите элемент для добавления: ")
                val elemValue = readln().toInt()
                DoubleLinkedList.insertToHead(headMain, elemValue)
                lengthMain++
                DoubleLinkedList.printList(headMain, lengthMain)
            }
            3 -> {
                DoubleLinkedList.deleteLastElem(tailMain)
                lengthMain--
                DoubleLinkedList.printList(headMain, lengthMain)
            }
            4 -> {
                DoubleLinkedList.deleteFirstElem(headMain)
                lengthMain--
                DoubleLinkedList.printList(headMain, lengthMain)
            }
            5 -> {
                print("Введите индекс для добавления: ")
                val index = readln().toInt()
                DoubleLinkedList.addElemOfIndex(headMain, tailMain, index, lengthMain)
                lengthMain++
                DoubleLinkedList.printList(headMain, lengthMain)
            }
            6 -> {
                print("Введите индекс элемента для поиска: ")
                val findIndex: Int = readln().toInt()
                val newElem = DoubleLinkedList.getElemOfIndex(headMain, tailMain, lengthMain, findIndex)
                println(newElem!!.value)
            }
            7 -> {
                print("Введите индекс для удаления: ")
                val delIndex: Int = readln().toInt()
                DoubleLinkedList.delElemOfIndex(headMain, tailMain, lengthMain, delIndex)
                lengthMain--
                DoubleLinkedList.printList(headMain, lengthMain)
            }
            8 -> {
                DoubleLinkedList.getLength(lengthMain)
            }
            9 -> {
                DoubleLinkedList.clearList(headMain, lengthMain)
                lengthMain = 0
                println("Все элементы списка были удалены.")
            }
            10 -> {
                print("Введите индекс элемента: ")
                val findIndex: Int = readln().toInt()
                DoubleLinkedList.replaceElem(headMain, tailMain, lengthMain, findIndex)
                DoubleLinkedList.printList(headMain, lengthMain)
            }

            11 -> DoubleLinkedList.checkList(lengthMain)
            12 -> {
                DoubleLinkedList.reverseList(headMain, lengthMain)
                val temp2 = headMain
                headMain = tailMain
                tailMain = temp2
                headMain.next = headMain.prev
                headMain.prev = null
                tailMain.prev = tailMain.next
                tailMain.next = null
                DoubleLinkedList.printList(headMain, lengthMain)
            }
            13 -> {
                print("Введите индекс элемента, после которого вставить список: ")
                val insertIndex = readln().toInt()
                DoubleLinkedList.insertList(headMain, tailMain, headSecond, tailSecond, insertIndex)
                DoubleLinkedList.printList(headMain, lengthMain)
                DoubleLinkedList.printList(headSecond, lengthSecond)
            }
            14 -> {
                DoubleLinkedList.insertListLast(tailMain, headSecond, tailSecond)
                DoubleLinkedList.printList(headMain, lengthMain)
            }
            15 -> {
                DoubleLinkedList.insertListFirst(headMain, headSecond, tailSecond)
                DoubleLinkedList.printList(headMain, lengthMain)
            }
            16 -> DoubleLinkedList.containsSecondList(listInserted)
            17 -> {
                if (listInserted) {
                    DoubleLinkedList.getFirstEntry(headMain, headSecond)
                } else {
                    println("Вставной список не содержится в начальном")
                }
            }
            18 -> {
                if (listInserted) {
                    DoubleLinkedList.getLastEntry(tailMain, tailSecond)
                } else {
                    println("Вставной список не содержится в начальном")
                }
            }
            19 -> {
                print("Введите индексы элементов для перестановки: ")
                val index1 = readln().toInt()
                val index2 = readln().toInt()
                DoubleLinkedList.swap(headMain, tailMain, lengthMain, index1, index2)
                DoubleLinkedList.printList(headMain, lengthMain)
            }
        }
    } while (choice != 0)
}