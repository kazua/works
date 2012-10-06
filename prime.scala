object prime {
    var plist = List.empty[Int]
    def prime(sl: List[Int]): List[Int] =
        if (sl.head != 2 && (sl.isEmpty || Math.pow(plist.head,2) > sl.last)){
            plist.reverse ::: sl
        }else{
            plist = sl.head :: plist
            prime(sl.tail.filter(_ % sl.head != 0))
        }

    def jojo(pl: List[Int],sl: List[Int]){
        var pls = pl
        var plst = 0
        for(i <- sl){
            if(!pls.isEmpty && plst != pls.head) plst = pls.head
            if(plst == i){
                pls = pls.tail
                println("jojo!")
            }else{
                println(i)
            }
        }
    }

    def main(args: Array[String]) {
        var i = 100//ここまでの数字内で処理
        jojo(prime(Range(2, i + 1).toList),Range(1, i + 1).toList)
    }
}