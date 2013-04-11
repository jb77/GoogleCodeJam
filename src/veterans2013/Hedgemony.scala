package veterans2013

import scala.io.Source
import java.io.PrintWriter
import java.io.File
import scala.Array.canBuildFrom

object Hedgemony extends App {

  val data = Source.fromFile("c:/temp/gjam/asample.txt")
  val lines=data.getLines.toList
  val numberOfCases=lines.head.toInt
  val caseEntries=lines.tail
  
  val ceIt=caseEntries.iterator
  var n=1;
  
  val pw=new PrintWriter(new File("c:/temp/gjam/aoutput.txt"))
  
  while(ceIt.hasNext)
  {
    val hedgeCount=ceIt.next
    val hedgeHeightsStr=ceIt.next
    val hedgeHeights=hedgeHeightsStr.split(" ").map(_.toFloat).toList
    //println(hedgeHeights)
    val res=calcHeight(hedgeHeights)
    pw.println("Case #" + n+ ": " + res)
    n=n+1
  }
  
  pw.close()
  
  def calcHeight(heights:List[Float]) : Float = {
    /*The rule is -- to start on the left at bush #2 and move to the right. 
    The gardener cuts the top of each bush to make it exactly as tall as the average of the two bushes on either side. 
    If the bush is already as short as the average or shorter, then the gardener does not touch 
    this bush and moves on to the next bush on the right, until the second-to-last bush. 
    The baron is certain that this procedure is the key to success.*/
    
    if(heights.size<3) return heights(0)
    else {
      val avg=( heights(0) + heights(2) ) /2f
      val newHeights=List(avg,heights(1)).min :: heights.tail.tail
      calcHeight(newHeights)
      
      
    }
    
  }
  
}