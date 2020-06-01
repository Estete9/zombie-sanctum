package zombies

import player.Player

abstract class Zombie: ZombieBite{
    abstract val name: String
    abstract val type: String
    abstract var hitPoints: Int
    abstract var zombieStrength: Int
    var tamed = false
    abstract var tamable: Int

    override fun bite(zDamage: Int, objective: Player) {
        objective.hitPoints = objective.hitPoints - zombieStrength
    }


}

class BasicZombie : Zombie()  {
    override val type = "normal"
    override val name = "$type zombie"
    override var hitPoints = 10
    override var zombieStrength = 2
    override var tamable = 3


}

class FriendlyZombie : Zombie() {
    override val type = "friendly"
    override val name = "$type zombie"
    override var hitPoints = 8
    override var zombieStrength = 4
    override var tamable = 2

}

interface ZombieBite {
    fun bite(zDamage: Int, objective: Player) {

    }
}