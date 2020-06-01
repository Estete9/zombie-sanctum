package player

import zombies.Zombie


val zombiePocketInventory = mutableListOf<ZombiePocket>()
val zombieInventory = mutableListOf<Zombie>()

data class ZombiePocket(var zombie: Zombie) {
    val petZombieName = zombie.name
    val petZombieDamage = zombie.zombieStrength
    val petZombieHitPoints = zombie.hitPoints

}

fun addZombie(zombie: Zombie) {
    zombiePocketInventory.add(ZombiePocket(zombie))
    zombieInventory.add(zombie)
}


fun retrieveZombie(zombieName: String): Zombie {
    var selectedZombieIndex = 0
    for (element in zombiePocketInventory) {
        if (element.petZombieName == zombieName) {
            selectedZombieIndex = zombiePocketInventory.indexOf(element)

        }
    }

    return zombieInventory[selectedZombieIndex]
}
