object qksort {
    def qksort(sl: List[Int]): List[Int] =
        if (sl.length <= 1) sl
        else List.concat(qksort(sl.filter(sl(sl.length / 2) >)), sl.filter(sl(sl.length / 2) ==), qksort(sl.filter(sl(sl.length / 2) <)))

    def main(args: Array[String]) {
        var ls = List(5, 27, 15, 31, 13, 23, 49, 16, 53, 39, 24, 11, 21, 63, 87)
        qksort(ls).foreach{println}
    }
}