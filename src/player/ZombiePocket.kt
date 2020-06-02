package player

import zombies.Zombie

var zombieArrayListPairs = arrayListOf<Pair<ZombiePocket, Zombie>>()
//val zombiePocketInventory = mutableListOf<ZombiePocket>()
//val zombieInventory = mutableListOf<Zombie>()

data class ZombiePocket(var zombie: Zombie) {
    val petZombieName = zombie.name
    val petZombieDamage = zombie.zombieStrength
    val petZombieHitPoints = zombie.hitPoints

}

fun addZombie(zombie: Zombie) {
    zombieArrayListPairs.add(Pair(ZombiePocket(zombie), zombie))
//    zombiePocketInventory.add(ZombiePocket(zombie))
//    zombieInventory.add(zombie)
}


fun retrieveZombie(zombieName: String): Pair<ZombiePocket, Zombie> {


    var selectedZombieIndex = 0

    for (element in zombieArrayListPairs){
        if(element.first.petZombieName == zombieName){
            selectedZombieIndex = zombieArrayListPairs.indexOf(element)
        }
    }
return zombieArrayListPairs[selectedZombieIndex]
//    for (element in zombiePocketInventory) {
//        if (element.petZombieName == zombieName) {
//            selectedZombieIndex = zombiePocketInventory.indexOf(element)
//
//        }
//    }
//
//    return zombieInventory[selectedZombieIndex]
}
