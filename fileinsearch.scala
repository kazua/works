import java.io._
import scala.io.Source

object fileinsearch {

    def fileinsearch(path : String, schwds : String, schext : String){
        try{
            new File(path).listFiles.map{
                case d if d.isDirectory() => fileinsearch(d.getPath(), schwds, schext)
                case f if f.isFile() && f.canRead() && f.getName().endsWith(schext) => {
                    var source = Source.fromFile(f.getPath())
                    try{
                        var lines = source.getLines.toList
                        for (i <- 0 until lines.length if lines(i).contains(schwds)) println("%s:%4d:%s".format(f.getPath(), i + 1, lines(i)))
                    }catch{
                        case ex : java.nio.charset.MalformedInputException => println("%s:%s".format(f.getPath(), "検索対象外ファイルです"))
                    }
                }
                case e => e
            }
        }catch{
            case ex : java.lang.NullPointerException => println("%s:%s".format(path, "検索不可ディレクトリです"))
            case ex2 : java.io.IOException => println("%s:%s".format(path, "ファイルにアクセスできません"))
        }
    }

    def main(args: Array[String]) {
        fileinsearch(args(0), args(1), args(2))
    }
}