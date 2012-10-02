object prime {
    var plist = List.empty[Int]
    def prime(sl: List[Int]): List[Int] =
        if (sl.head != 2 && (sl.isEmpty || Math.pow(plist.head,2)>sl.last)){
            plist = plist.reverse ::: sl
            plist
        }else{
            plist = sl.head :: plist
            sl.head :: prime(sl.tail.filter(_ % sl.head != 0))
            plist
        }

    def jojo(pl: List[Int],sl: List[Int]){
        for(i <- sl)
            if(pl.exists(_ == i))
                println("jojo!")
            else
                println(i)
    }

    def main(args: Array[String]) {
        var i = 100
        jojo(prime(Range(2, i).toList),Range(1, i).toList)
    }
}