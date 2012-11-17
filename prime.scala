import scala.math._

object prime {
    def prime(sl : List[Int], ms : Int) : List[Int] = sl match {
        case Nil => Nil
        case sl => {
        if (sl.head != 2 && (sl.isEmpty || pow(ms, 2) > sl.last)) sl
        else {
            println(sl.head)
            println(sl.tail.filter(_ % sl.head != 0))
            sl.head :: prime(sl.tail.filter(_ % sl.head != 0),sl.head)
        }
        }
    }

    def jojo(pl: List[Int],sl: List[Int]){
        var pls = pl
        var plst = 0
        for(i <- sl){
            if(!pls.isEmpty && plst != pls.head) plst = pls.head
            if(plst == i){
                pls = pls.tail
                println("jojo!")
            }else println(i)
        }
    }

    def main(args: Array[String]) {
        val i = 100//ここまでの数字内で処理
        jojo(prime(Range(2, i + 1).toList,2),Range(1, i + 1).toList)
    }
}