//http://odz.sakura.ne.jp/projecteuler/index.php?cmd=read&page=Problem%2022
//K.A

import java.io._
import scala.io.Source

object problem22 {
    def problem22(path : String) {
        try {
            new File(path) match {
                case d if d.isDirectory() => println("%s:%s".format(path, "指定パスはディレクトリです"))
                case f if f.isFile() && f.canRead() && f.getName() == "names.txt" => {
                    try {
                        var score = 0
                        Source.fromFile(f.getPath).getLines.toList.mkString.replace("\"", "").split(",").toList.sortWith(_.compareToIgnoreCase(_) < 0).zipWithIndex.foreach {
                            ite =>
                                {
                                    var alpidx = 0
                                    ite._1.foreach {
                                        c => alpidx += (('a' to 'z').mkString.indexOf(c.toLower) + 1)
                                    }
                                    score += ((ite._2 + 1) * alpidx)
                                }
                        }
                        println(score)
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