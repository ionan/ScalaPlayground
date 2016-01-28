import scala.annotation.tailrec

object Problems1to10 {
	def problem1(l : List[Int]){
		def last(l : List[Int]) : Option[Int] = l match {
			case h :: Nil  => Some(h)
    		case h :: t => last(t)
			case _ => None
		}	
		println("Last of [" + l.mkString(",") + "]: " + last(l))
	}

	def problem2(l : List[Int]){
		def penultimate(l : List[Int], cand : Option[Int] = None) : Option[Int] = l match {
			case h :: Nil  => cand
    		case h :: t => penultimate(t,Some(h))
			case _ => None
		}	
		println("Penultimate of [" + l.mkString(",") + "]: " + penultimate(l))
	}

	def problem3(l : List[Int], n : Int){
		def nth(l : List[Int], n : Int) : Option[Int] = l match {
			case h :: t => if (n == 0) Some(h) else nth(t,n - 1)
			case _ => None
		}	
		println(n + "th of [" + l.mkString(",") + "]: " + nth(l,n))
	}

	def problem4(l : List[Int]){
		@tailrec def length(l : List[Int], curLen : Int = 0) : Int = l match {
			case h :: t => length(t,curLen + 1)
			case _ => curLen
		}	
		println("Length of [" + l.mkString(",") + "]: " + length(l))
	}

	def problem5(l : List[Int]){
		def reverse(l : List[Int]) : List[Int] = l match {
			case h :: t => reverse(t) ::: List(h)
			case _ => List()
		}
		println("Reverse of [" + l.mkString(",") + "]: " + reverse(l).mkString(","))
	}

	def problem5_bis(l : List[Int]){
		def reverse = l.foldLeft(List[Int]())((x,y) => y :: x)
		println("Reverse of [" + l.mkString(",") + "]: " + reverse.mkString(","))
	}

	def main(args : Array[String]){
		//problem1(List(5, 3, 12))
		//problem1(List())
		//problem2(List(5, 3, 12))
		//problem2(List())
		//problem2(List(5, 12))
		//problem3(List(5, 3, 12),0)
		//problem3(List(5, 3, 12),2)
		//problem3(List(5, 3, 12),7)
		//problem4(List(5, 3, 12))
		//problem4(List())
		//problem4(List(5, 12))
		problem5(List(5, 3, 12))
		problem5(List())
		problem5(List(5, 12))
		problem5_bis(List(5, 3, 12))
		problem5_bis(List())
		problem5_bis(List(5, 12))
	}
}
