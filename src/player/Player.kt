package player

//import punch
import zombies.Zombie
import java.util.*

sealed class Unit(val name: String, var hitPoints: Int = 30, var playerStrength: Int = 5) {

    open fun makeDecision(
        player: Player,
        petZombie: Zombie?,
        zombie: Zombie,
        playerStrength: Int
    ) {
        when (readLine()!!) {
            "punch" -> {

                punch(playerStrength, zombie)
                println("you punched ${zombie.name}")
                println("inflicted $playerStrength points of damage")

                if (zombie.hitPoints > 0) {
                    println("${zombie.hitPoints} hit points remaining")

                    zombie.bite(zombie.zombieStrength, player)
                    println("${zombie.name} bit you! \nouch")
                    println("Remaining hit points ${player.hitPoints}")

                    println("what would you do?\npunch, run or pet?")
                    makeDecision(player, petZombie, zombie, playerStrength)

                } else println("${zombie.name} lost all his hit points.\n${zombie.name} is dead")


            }
            "run" -> println("you ran away!\nPhew")
            "pet" -> {

                tameZombie(zombie)
                if (!zombie.tamed) {
                    println("${zombie.name} refused to be pet")

                    zombie.bite(zombie.zombieStrength, player)
                    println("${zombie.name} bit you! \nouch")
                    println("Remaining hit points ${player.hitPoints}")

                    println("what would you do?\npunch, run or pet?")
                    makeDecision(player, petZombie, zombie, playerStrength)
                } else {
                    println("Success! ${zombie.name} is now your friend")
                    addZombie(zombie)
                    println("${zombie.name} is now in your Zombie Pocket")
                }
            }
            else -> {
                println("please make a good decision")
            }
        }
    }

}

class Player(name: String, hitPoints: Int = 30, strength: Int = 5) : Unit(name, hitPoints, strength) {


    override fun makeDecision(
        player: Player,
        petZombie: Zombie?,
        zombie: Zombie,
        playerStrength: Int
    ) {
        var decision = readLine()!!
        when (decision) {
            "punch" -> {

                punch(playerStrength, zombie)
                println("you punched ${zombie.name}")
                println("inflicted $playerStrength points of damage")

                if (zombie.hitPoints > 0) {
                    println("${zombie.hitPoints} hit points remaining")

                    zombie.bite(zombie.zombieStrength, player)
                    println("${zombie.name} bit you! \nouch")
                    println("Remaining hit points ${player.hitPoints}")

                    println("what would you do?\npunch, run or pet?")
                    makeDecision(player, petZombie, zombie, playerStrength)

                } else println("${zombie.name} lost all his hit points.\n${zombie.name} is dead")


            }
            "run" -> println("you ran away!\nPhew")
            "pet" -> {

                tameZombie(zombie)
                if (!zombie.tamed) {
                    println("${zombie.name} refused to be pet")

                    zombie.bite(zombie.zombieStrength, player)
                    println("${zombie.name} bit you! \nouch")
                    println("Remaining hit points ${player.hitPoints}")

                    println("what would you do?\npunch, run or pet?")
                    makeDecision(player, petZombie, zombie, playerStrength)
                } else {
                    println("Success! ${zombie.name} is now your friend")
                    addZombie(zombie)
                    println("${zombie.name} is now in your Zombie Pocket")
                }
            }
            "zombie bite" -> {
                println("who should attack?")
                for (element in zombiePocketInventory) println(
                    "${element.petZombieName}, strength ${element.petZombieDamage}, hit points ${element.petZombieHitPoints}"
                )
                var selectedPetZombie = retrieveZombie(readLine()!!)
                println("${selectedPetZombie.name} has been selected")

                when (selectedPetZombie) {
                    in zombieInventory -> {
                        petZombieBite(
                            selectedPetZombie, zombie
                        )
                        if (zombie.hitPoints > 0 && petZombie != null) {
                            petZombie.hitPoints = petZombie.hitPoints - zombie.zombieStrength
                            println("${petZombie.name} was bit \nRemaining hit points ${petZombie.hitPoints}")
                            println("what would you do? \npunch, run, pet or zombie bite?")
//                            decision = readLine()!!
                            makeDecision(player, petZombie, zombie, playerStrength)
                        } else println("${zombie.name} is dead")
                    }
                    !in zombieInventory -> {
                        println("you don't have that zombie. Who should attack?")
                        selectedPetZombie = retrieveZombie(readLine()!!)
                        petZombieBite(selectedPetZombie, zombie)
                    }

                    //                    selectedPetZombie.hitPoints <= 0 -> println("${zombie.name} is dead")
                }

//                if (zombie.hitPoints == 0) println("${zombie.name} is dead") else zombieBite(selectedPetZombie, zombie)
//                println("who should attack?")
//                for (element in zombiePocketInventory) println(
//                    "${element.petZombieName}, strength ${element.petZombieDamage}, hit points ${element.petZombieHitPoints}"
//                )
//                val selectedPetZombie = retrieveZombie(readLine()!!)
//                println("${selectedPetZombie.name} has been selected")
//                if (zombieInventory.contains<Zombie>(selectedPetZombie) && petZombie != null) {
//                    println("${petZombie.name} attacks")
//                    zombie.hitPoints = zombie.hitPoints - petZombie.zombieStrength
//                    println("${zombie.name} has ${zombie.hitPoints} hit points remaining")
//                } else {
//                    println("you don't have that zombie")
//                }
            }
            else -> {
                println("please make a good decision")
                makeDecision(player, petZombie, zombie, playerStrength)
            }
        }
    }


}

fun punch(playerStrength: Int, zombie: Zombie): Int {
    zombie.hitPoints = zombie.hitPoints - playerStrength
    return zombie.hitPoints
}

fun petZombieBite(selectedPetZombie: Zombie?, zombie: Zombie) {


//    when {

//        zombieInventory.contains(selectedPetZombie) && selectedPetZombie != null -> {
    if (selectedPetZombie != null) {
        println("${selectedPetZombie.name} attacks")
        zombie.hitPoints = zombie.hitPoints - selectedPetZombie.zombieStrength
        println("${zombie.name} has ${zombie.hitPoints} hit points remaining")
    }
//            if(zombie.hitPoints == 0) println("${zombie.name} is dead") else zombieBite(selectedPetZombie, zombie)
//        }
//        else -> {
//
//        }
//    }

//    if (zombieInventory.contains<Zombie>(selectedPetZombie) && selectedPetZombie != null) {
//        println("${selectedPetZombie.name} attacks")
//        zombie.hitPoints = zombie.hitPoints - selectedPetZombie.zombieStrength
//        println("${zombie.name} has ${zombie.hitPoints} hit points remaining")
//    } else {
//        println("you don't have that zombie")
//        zombieBite(selectedPetZombie, zombie)
//    }
}
//interface MakeDecision {
//
//}

private fun tameZombie(zombie: Zombie) {
    val tameNumber = Random().nextInt(6)
    println(tameNumber)
    if (tameNumber >= zombie.tamable) zombie.tamed = true
}

fun openInventory() {
    for (element in zombiePocketInventory) println(
        "${element.petZombieName}, strength ${element.petZombieDamage}, hit points ${element.petZombieHitPoints}"
    )
}