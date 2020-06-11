package zombies

import inventory.BaseItem
import player.Player

abstract class Zombie : ZombieBite, UseItem {
    abstract val name: String
    abstract val type: String
    abstract var hitPoints: Int
    abstract val baseHitPoints: Int
    abstract var zombieStrength: Double
    abstract val baseTamable: Double
    abstract var changingTamable: Double
    abstract var condition: String
    var tamed = false

    override fun bite(zDamage: Double, objective: Player) {
        objective.hitPoints = (objective.hitPoints - zombieStrength).toInt()
    }

    override fun useItem(item: BaseItem, decision: String, zombie: Zombie) {
        var turn = 0

        if (decision.isNotEmpty()) {
            when {
                turn < 3 -> when (item.itemName) {
                    "meat" -> zombie.hitPoints += item.healthModifier!!
                    "rabies" -> {
                        condition = item.statusModifier!!
                        while (condition == "angry") zombie.zombieStrength *= 1.5
                        turn += 1
                    }
                }
                turn > 2 -> {
                    println("$condition dissipated")
                    turn = 0
                }

            }
//            if (turn < 3) {
////                when (item) {
////                    "meat" -> zombie.hitPoints = zombie.baseHitPoints
////                    "rabies" -> {
////                        condition = "angry"
////                        while (condition == "angry") zombie.zombieStrength *= 1.5
////                        turn += 1
////                    }
////                }
//            } else {
////                turn = 0
////                println("$condition dissipated")
//            }

        }
    }

}

class BasicZombie : Zombie() {
    override val type = "normal"
    override val name = "$type zombie"
    override var hitPoints = 10
    override var condition = "normal"
    override val baseHitPoints = hitPoints
    override var zombieStrength = 2.0
    override var baseTamable: Double = 3.0
    override var changingTamable = baseTamable

}

class FriendlyZombie : Zombie() {
    override val type = "friendly"
    override val name = "$type zombie"
    override var hitPoints = 8
    override var condition = "normal"
    override var zombieStrength = 4.0
    override var baseTamable: Double = 2.0
    override val baseHitPoints = hitPoints
    override var changingTamable = baseTamable
}

interface ZombieBite {
    fun bite(zDamage: Double, objective: Player) {

    }
}

interface UseItem {
    fun useItem(item: BaseItem, decision: String, zombie: Zombie) {

    }
}