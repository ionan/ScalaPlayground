package simplegame

class Background (var bgX : Int, var bgY : Int) {
    var speedX = 0

		def update() : Unit = {
				bgX += speedX
				if (bgX <= -2160) bgX += 4320
		}
}