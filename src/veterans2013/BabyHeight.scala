package veterans2013

import scala.io.Source
import java.io.PrintWriter
import java.io.File

object BabyHeight extends App {
  
  val data = Source.fromFile("c:/temp/gjam/bsample.txt")
  val lines=data.getLines.toList
  val numberOfCases=lines.head.toInt
  val caseEntries=lines.tail
  
  var n=0;
  
  val pw=new PrintWriter(new File("c:/temp/gjam/boutput.txt"))
  
  val res=caseEntries.map(_.split(" ").toList)    
  val processed=res.map(list=>process(list(0),list(1),list(2)))
  processed.foreach(pw.println(_))  
  pw.close()
  
  def process(gender:String, mHgt:String, fHgt:String) : String ={
    n=n+1
    val mIn=convertToIn(mHgt)
    val fIn=convertToIn(fHgt)
    val newHgt=gender match {
      case "B" => (mIn+fIn+5)/2f
      case "G" => (mIn+fIn-5)/2f
      case _ => throw new IllegalArgumentException("Gender must be B or G")
    }
    
    "Case #" +n+": " + convertToFtAndIn(newHgt-4,true) + " to " + convertToFtAndIn(newHgt+4,false)    
  }
  
  def convertToFtAndIn(inches:Float, rndUp:Boolean) : String = {
    var ft=scala.math.floor(inches/12).toInt
    var in= rndUp match {
      case true => scala.math.ceil(inches%12).toInt
      case false => scala.math.floor(inches%12).toInt
    }
    
    if(in==12) {in=0;ft=ft+1}
    
    ft + "'" + in +"\""
  }
  
  def convertToIn(ftAndIn:String) : Int = {
    val ftAndInArr=ftAndIn.dropRight(1).split("'")
    (ftAndInArr(0).toInt*12) + ftAndInArr(1).toInt    
  }

}