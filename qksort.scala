object qksort {
    def qksort(sl: List[Int]): List[Int] = {
        sl match {
            case List() => sl
            case slh::sll => qksort(sll.filter(slh > _)) ::: List(slh) ::: qksort(sll.filter(slh < _))
        }
    }
    def main(args: Array[String]) {
        val ls = List(5, 27, 15, 31, 13, 23, 49, 16, 53, 39, 24, 11, 21, 63, 87)
        qksort(ls).foreach{println}
    }
}