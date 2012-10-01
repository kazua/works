object prime {
    var plist = List.empty[Int]
    def prime(sl: List[Int]): List[Int] =
        if (sl.head != 2 && (sl.isEmpty || Math.pow(plist.head,2)>sl.last)){
            plist = plist.reverse ::: sl
            plist.foreach { println }
            Nil
        }else{
            plist = sl.head :: plist
            sl.head :: prime(sl.tail.filter(_ % sl.head != 0))
        }

    def main(args: Array[String]) {
        var i = 100
        prime(Range(2, i).toList)
    }
}