package simplegame

object Robot {
	val JUMPSPEED = -15
	val MOVESPEED = 5
	val GROUND = 382
}

class Robot (var centerX : Int = 100, var centerY : Int = 382){
	import Robot._
  import simplegame.Projectile
  import scala.collection.mutable.ArrayBuffer

	var jumped = false
	var movingLeft = false
	var movingRight = false
	var ducked = false
	var speedX = 0
	var speedY = 1
  
  val projectiles = new ArrayBuffer[Projectile]

	private val bg1 = Entry.getBg1()
	private val bg2 = Entry.getBg2()

	def update() : Unit = {
			// Moves Character or Scrolls Background accordingly.
			if (speedX < 0) {
				centerX += speedX
			}
			if (speedX == 0 || speedX < 0) {
				bg1.speedX = 0
						bg2.speedX = 0

			}
			if (centerX <= 200 && speedX > 0) {
				centerX += speedX
			}
			if (speedX > 0 && centerX > 200){
				bg1.speedX = -MOVESPEED
						bg2.speedX = -MOVESPEED
			}

			// Updates Y Position
			centerY += speedY
			if (centerY + speedY >= GROUND) {
				centerY = GROUND
			}

			// Handles Jumping
			if (jumped == true) {
				speedY += 1

						if (centerY + speedY >= GROUND) {
							centerY = GROUND
									speedY = 0
									jumped = false
						}

			}

			// Prevents going beyond X coordinate of 0
			if (centerX + speedX <= 60) {
				centerX = 61
			}
	}  

	def moveRight() : Unit = {
			if (!ducked) {
				speedX = MOVESPEED
			}
			movingRight = true
	}

	def moveLeft() : Unit = {
			if (!ducked) {
				speedX = -MOVESPEED
			}
			movingLeft = true
	}

	def stopRight() : Unit = {
			movingRight = false
					stop
	}

	def stopLeft() : Unit = {
			movingLeft = false
					stop
	}

	def stop() : Unit = {
			if (!movingRight && !movingLeft) {
				speedX = 0
			}

			if (!movingRight && movingLeft) {
				moveLeft
			}

			if (movingRight && !movingLeft) {
				moveRight
			}
	}

	def jump() : Unit = {
			if (!jumped) {
				speedY = JUMPSPEED
						jumped = true
			}

	}
  
  def shoot() : Unit = {
    projectiles += new Projectile(centerX + 50, centerY - 25)
  }
}