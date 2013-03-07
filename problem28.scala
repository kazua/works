//http://odz.sakura.ne.jp/projecteuler/index.php?cmd=read&page=Problem%2028
//K.A

object problem28 {
    def CornerNumSum(len : Int, acl : List[Int]) : List[Int] = len match {
        case i if i <= 1 =>
            acl ::: List(i)
        case l =>
            val mn = math.pow(l, 2).toInt
            val cn = l - 1
            CornerNumSum(l - 2, acl ::: mn :: mn - cn :: mn - 2 * cn :: mn - 3 * cn :: Nil)
    }
    def main(args : Array[String]) {
        val len = 1001
        println(CornerNumSum(len, Nil).sum)
    }
}