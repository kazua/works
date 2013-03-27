//write Kazua

object worldproblem1_5 {
    def ZipStr(tgt : String, zip : String = "", acl : String = "", bktgt : String = "") : String = tgt match {
        case t if t.size == 0 => acl + zip.take(1) + zip.length
        case t => acl match {
            case a if a.length > 0 && a.length >= bktgt.length => bktgt
            case a => zip match {
                case z if z == "" => ZipStr(t.tail.mkString, zip + t.head, a, t.mkString)
                case z if z(0) == t.head => ZipStr(t.tail.mkString, zip + t.head, a, bktgt)
                case z => ZipStr(t.tail.mkString, t.head.toString, a + zip(0) + zip.length, bktgt)
            }
        }
    }
    def main(args : Array[String]) {
        println(ZipStr("aabccccaaa"))
        println(ZipStr("ddrrrtthhhhggf"))
        println(ZipStr("abcdefg"))
    }
}