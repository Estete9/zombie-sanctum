package player

import zombies.Zombie

var zombieArrayListPairs = arrayListOf<Pair<ZombiePocket, Zombie>>()

data class ZombiePocket(var zombie: Zombie) {
    val petZombieName = zombie.name
    val petZombieDamage = zombie.zombieStrength
    val petZombieHitPoints = zombie.hitPoints

}

fun addZombie(zombie: Zombie) {
    zombieArrayListPairs.add(Pair(ZombiePocket(zombie), zombie))
}


fun retrieveZombie(zombieName: String): Pair<ZombiePocket, Zombie> {


    var selectedZombieIndex = 0

    for (element in zombieArrayListPairs){
        if(element.first.petZombieName == zombieName){
            selectedZombieIndex = zombieArrayListPairs.indexOf(element)
        }
    }
return zombieArrayListPairs[selectedZombieIndex]
}
