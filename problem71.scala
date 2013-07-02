//http://odz.sakura.ne.jp/projecteuler/index.php?cmd=read&page=Problem%2071
//write kazua

object problem71 {
    def problem71(mn : Int) = {
        problem71Proc(mn)._1
    }
    def problem71Proc(mn : Int, kn : (Int, Int) = (3, 7), cn : (Int, Int) = (2, 5)) : (Int, Int) = (cn._2 + kn._2) match {
        case sn if sn > mn => cn
        case sn => problem71Proc(mn, kn, (cn._1 + kn._1, sn))
    }
    def main(args : Array[String]) {
        println(problem71(1000000))
    }
}