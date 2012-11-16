//http://odz.sakura.ne.jp/projecteuler/index.php?cmd=read&page=Problem%2022
//K.A

import java.io._
import scala.io.Source

object problem22 {
    def sum(s : String) : Int = s match {
        case null => 0
        case "" => 0
        case s => (('a' to 'z').mkString.indexOf(s.charAt(0).toLower) + 1) + sum(s.substring(1))
    }
    def score(ls : List[(String, Int)]) : Int = ls match {
        case Nil => 0
        case x::xs => ((x._2 + 1) * sum(x._1)) + score(xs)
    }
    def problem22(path : String) {
        try {
            new File(path) match {
                case d if d.isDirectory() => println("%s:%s".format(path, "指定パスはディレクトリです"))
                case f if f.isFile() && f.canRead() && f.getName() == "names.txt" => {
                    try {
                        val scp = score(Source.fromFile(f.getPath).getLines.toList.mkString.replace("\"", "").split(",").toList.sortWith(_.compareToIgnoreCase(_) < 0).zipWithIndex)
                        println(scp)
                    } catch {
                        case ex : java.nio.charset.MalformedInputException => println("%s:%s".format(f.getPath(), "検索対象外ファイルです"))
                    }
                }
                case e => println("指定ファイルは異常です")
            }
        } catch {
            case ex : java.lang.NullPointerException => println("%s:%s".format(path, "指定パスは検索不可です"))
            case ex2 : java.io.IOException => println("%s:%s".format(path, "指定パスにアクセスできません"))
        }
    }
    def main(args : Array[String]) {
        problem22("e:/names.txt")
    }
}