object Queues {
	class SlowAppendQueue[T](elems: List[T]) {
		def head = elems.head
		def tail = new SlowAppendQueue(elems.tail)
		def append(x: T) = new SlowAppendQueue(elems ::: List(x))
		override def toString = elems mkString  ("SlowAppendQueue(", ", ", ")")
	}

	class SlowHeadQueue[T](smele: List[T]) {
	    def head = smele.last
	    def tail = new SlowHeadQueue(smele.init)
	    def append(x: T) = new SlowHeadQueue(x :: smele)
		override def toString = smele.reverse mkString  ("SlowHeadQueue(", ", ", ")")
	}

	class Queue1[T](private val leading: List[T], private val trailing: List[T] ) {
		private def mirror = 
			if (leading.isEmpty)
				new Queue1(trailing.reverse, Nil)
			else
				this

		def head = mirror.leading.head

		def tail = { 
			val q = mirror 
			new Queue1(q.leading.tail, q.trailing) 
		}

		def append(x: T) = new Queue1(leading, x :: trailing)
		
		override def toString = leading ::: trailing.reverse mkString ("Queue1(", ", ", ")")
	}

	object Queue1 {
	    def apply[T](xs: T*) = new Queue1[T](xs.toList, Nil)
	}

	trait Queue[T] {
		def head: T
		def tail: Queue[T]
		def append(x: T): Queue[T]
	}

	object Queue {

		def apply[T](xs: T*): Queue[T] = new QueueImpl[T](xs.toList, Nil)

		private class QueueImpl[T](private val leading: List[T], private val trailing: List[T]) extends Queue[T] {

			def mirror = 
				if (leading.isEmpty)
					new QueueImpl(trailing.reverse, Nil)
				else 
					this

			def head: T = mirror.leading.head

			def tail: QueueImpl[T] = {
				val q = mirror
				new QueueImpl(q.leading.tail, q.trailing)
			}

			def append(x: T) = new QueueImpl(leading, x :: trailing)
			
			override def toString() = (leading ::: trailing.reverse) mkString ("Queue(", ", ", ")")
		}
	}

	def main(args: Array[String]) {
	  	// 1. SlowAppendQueue
	  	val q = new SlowAppendQueue(Nil) append 1 append 2
	  	println(q)

	    // 2. SlowHeadQueue
	    val q2 = new SlowHeadQueue(Nil) append 1 append 2
	    println(q2)

	    // 3. Queue1
	    val q3 = Queue1[Int]() append 1 append 2
		println(q3)

		// 4.Queue
		val q4 = Queue[Int]() append 1 append 2
		println(q4)
	}
}