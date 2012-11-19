//素数の時だけjojo（エラトステネスの篩）
//K.A

import scala.math._

object prime {
    def prime(acl : List[Int], sl : List[Int], ms : Int) : List[Int] = sl match {
        case Nil => acl.reverse ::: sl
        case sl => {
            if (sl.head != 2 && (sl.isEmpty || pow(ms, 2) > sl.last)) acl.reverse ::: sl
            else {
                prime(sl.head :: acl, sl.tail.filter(_ % sl.head != 0), sl.head)
            }
        }
    }

    def jojo(pl : List[Int], sl : List[Int]) {
        if (pl != Nil && pl.head == sl.head) {
            println("jojo!")
            jojo(pl.tail, sl.tail)
        } else if (sl != Nil) {
            println(sl.head)
            jojo(pl, sl.tail)
        }
    }

    def main(args : Array[String]) {
        val i = 100 //ここまでの数字内で処理
        jojo(prime(Nil, Range(2, i + 1).toList, 2), Range(1, i + 1).toList)
    }
}