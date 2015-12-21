package simplegame

class Projectile (var x : Int, var y : Int) {
	var speedX = 7
	var visible = true

	def update() : Unit = {
			x += speedX
			if (x > 800) {
				visible = false
			}
	}
}