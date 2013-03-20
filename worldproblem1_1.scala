//K.A
import scala.collection.mutable._

object worldproblem1_1 {

    def UniqueCheck(chkstr : String) : Boolean = {
        if (chkstr.length > 256) return false

        val chrmp = Map[Char, Boolean]()
        for (i <- 0 to chkstr.length - 1) {
            val ch = chkstr.charAt(i)

            if (chrmp.contains(ch)) return chrmp(ch)
            else chrmp(ch) = false
        }
        true
    }
    def main(args : Array[String]) {
        println(UniqueCheck("abcgdjwp"))
        println(UniqueCheck("abcadjwp"))
    }
}