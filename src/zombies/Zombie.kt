package zombies

import player.Player

abstract class Zombie : ZombieBite {
    abstract val name: String
    abstract val type: String
    abstract var hitPoints: Int
    abstract var zombieStrength: Int
    var tamed = false
    abstract val baseTamable: Double
    abstract var changingTamable: Double


    override fun bite(zDamage: Int, objective: Player) {
        objective.hitPoints = objective.hitPoints - zombieStrength
    }

}

class BasicZombie : Zombie() {
    override val type = "normal"
    override val name = "$type zombie"
    override var hitPoints = 10
    override var zombieStrength = 2
    override var baseTamable: Double = 3.0
    override var changingTamable = baseTamable

}

class FriendlyZombie : Zombie() {
    override val type = "friendly"
    override val name = "$type zombie"
    override var hitPoints = 8
    override var zombieStrength = 4
    override var baseTamable: Double = 2.0
    override var changingTamable = baseTamable
}

interface ZombieBite {
    fun bite(zDamage: Int, objective: Player) {

    }
}

