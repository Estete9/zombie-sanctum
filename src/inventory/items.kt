package inventory

import player.ChooseItem
import player.Player
import zombies.Zombie

abstract class BaseItem {
    abstract val itemName: String
    abstract val healthModifier: Int?
    abstract val statusModifier: String?
}

class Meat : BaseItem(), UseItem, ChooseItem {
    override val itemName = "meat"
    override val healthModifier = 3
    override val statusModifier: String? = null

    override fun useItem(item: BaseItem, petZombie: Zombie) {
        if (petZombie.hitPoints < petZombie.baseHitPoints) {
            petZombie.hitPoints += item.healthModifier!!
            println(
                "${petZombie.name} ate the meat and now has ${petZombie.hitPoints}/${petZombie.baseHitPoints} hit " +
                        "points"
            )
        } else {
            println("${petZombie.name} is at full health")
            chooseItem()
        }
    }
}

class Rabies : BaseItem() {
    override val itemName = "rabies"
    override val statusModifier = "angry"
    override val healthModifier: Int? = null
}

interface UseItem {
    fun useItem(item: BaseItem, petZombie: Zombie) {}
}