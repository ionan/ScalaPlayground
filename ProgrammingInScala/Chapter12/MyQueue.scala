object MyQueue {
    def main(args: Array[String]) = {
        val queue = new BasicIntQueue with Doubling
        queue.put(10)
        println(queue.get)

        val queue2 = (new BasicIntQueue with Incrementing with Filtering)
        queue2.put(-1)
        queue2.put(0)
        queue2.put(1)
        println(queue2.get)
        println(queue2.get)
    }
}
