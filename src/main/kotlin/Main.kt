//класс генерации элементов двусвязного списка
class DoubleLinkedList(var value: Int?) {
    var prev: DoubleLinkedList? = null
    var next: DoubleLinkedList? = null
    //var head: DoubleLinkedList? = null

    /*enum class Form {
        head
    }*/

    companion object {

        //получение последнего элемента списка
        //(возвращает ссылку на хвостовой элемент)
        fun getTail(head: DoubleLinkedList): DoubleLinkedList {
            var tail = head
            while (tail.next != null) {
                tail = tail.next!!
            }
            return tail
        }

        //вывод на экран всего списка
        fun printList(head: DoubleLinkedList) {
            var temp = head
            print("${head.value} ")
            while (temp.next != null) {
                print("${temp.next!!.value} ")
                temp = temp.next!!
            }
            println()
        }

        //добавление нового элемента в список
        //(возвращает ссылку на головной элемент)
        fun addElem(head: DoubleLinkedList, value: Int) {
            var newElem = DoubleLinkedList(value)
            var tempTail = getTail(head)
            newElem.prev = tempTail
            tempTail.next = newElem
        }

        //получение элемента списка по индексу
        //(возвращает ссылку элемент по индексу)
        fun getElemOfIndex(head: DoubleLinkedList, index: Int): DoubleLinkedList {
            var tempElem = head
            for (i in 0..<index) {
                tempElem = tempElem.next!!
            }
            return tempElem
        }

        //добавление элемента в конец списка
        //(возвращает ссылку на новый хвостовой элемент)
        fun insertToTail(head: DoubleLinkedList, value: Int): DoubleLinkedList {
            var newTail = getTail(head)
            val oldTail = newTail
            newTail = DoubleLinkedList(value)
            oldTail.next = newTail
            newTail.prev = oldTail

            return newTail
        }

        //добавление элемента в начало списка
        //(возвращает ссылку на новый головной элемент)
        fun insertToHead(head: DoubleLinkedList, value: Int): DoubleLinkedList {
            var newHead = head
            var oldHead = head
            newHead.value = value
            oldHead.prev = newHead
            newHead.next = oldHead

            return newHead
        }

        //удаление последнего элемента
        fun deleteTail(head: DoubleLinkedList) {
            var delElem = getTail(head)
            var newTail = delElem.prev
            newTail?.next = null
        }

        //удаление головного элемента
        //(возвращает ссылку на новый головной элемент)
        fun deleteHead(head: DoubleLinkedList?): DoubleLinkedList? {
            var newHead: DoubleLinkedList? = head
            newHead = newHead?.next
            newHead?.prev = null
            return newHead
        }
    }
}

fun main(args: Array<String>) {
    println("Введите первый элемент списка")
    readln()
    var list1 = DoubleLinkedList(4)

    DoubleLinkedList.addElem(list1, 2)
    DoubleLinkedList.addElem(list1, 3)
    DoubleLinkedList.addElem(list1, 4)
    DoubleLinkedList.addElem(list1, 5)
    DoubleLinkedList.addElem(list1, 6)
    DoubleLinkedList.addElem(list1, 7)

    DoubleLinkedList.printList(list1)

    list1 = DoubleLinkedList.deleteHead(list1)!!

    DoubleLinkedList.printList(list1)

}