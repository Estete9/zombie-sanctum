package player

//import punch
import zombies.Zombie
import java.util.*

sealed class Unit(val name: String, var hitPoints: Int = 30, var unitStrength: Int = 5) {

    open fun makeBattleDecision(
        player: Player,
        petZombie: Zombie?,
        zombie: Zombie,
        playerStrength: Int
    ) {
//        when (readLine()!!) {
//            "punch" -> {
//
//                punch(playerStrength, zombie)
//                println("you punched ${zombie.name}")
//                println("inflicted $playerStrength points of damage")
//
//                if (zombie.hitPoints > 0) {
//                    println("${zombie.hitPoints} hit points remaining")
//
//                    zombie.bite(zombie.zombieStrength, player)
//                    println("${zombie.name} bit you! \nouch")
//                    println("Remaining hit points ${player.hitPoints}")
//
//                    println("what would you do?\npunch, run or pet?")
//                    makeBattleDecision(player, petZombie, zombie, playerStrength)
//
//                } else println("${zombie.name} lost all his hit points.\n${zombie.name} is dead")
//
//
//            }
//            "run" -> println("you ran away!\nPhew")
//            "pet" -> {
//
//                tameZombie(zombie)
//                if (!zombie.tamed) {
//                    println("${zombie.name} refused to be pet")
//
//                    zombie.bite(zombie.zombieStrength, player)
//                    println("${zombie.name} bit you! \nouch")
//                    println("Remaining hit points ${player.hitPoints}")
//
//                    println("what would you do?\npunch, run or pet?")
//                    makeBattleDecision(player, petZombie, zombie, playerStrength)
//                } else {
//                    println("Success! ${zombie.name} is now your friend")
//                    addZombie(zombie)
//                    println("${zombie.name} is now in your Zombie Pocket")
//                }
//            }
//            else -> {
//                println("please make a good decision")
//            }
//        }
    }

}

class Player(name: String, hitPoints: Int = 30, playerStrength: Int = 3) : Unit(name, hitPoints, playerStrength) {


    override fun makeBattleDecision(
        player: Player,
        petZombie: Zombie?,
        zombie: Zombie,
        playerStrength: Int
    ) {
        when (readLine()!!) {
            "punch" -> {
                val startingHitPoints = zombie.hitPoints
                punch(playerStrength, zombie)
                zombieMoreTameable(startingHitPoints, zombie)
                println("you punched ${zombie.name}")
                println("inflicted $playerStrength points of damage")

                if (zombie.hitPoints > 0) {
                    println("${zombie.hitPoints} hit points remaining")

                    zombie.bite(zombie.zombieStrength, player)
                    println("${zombie.name} bit you! \nouch")
                    println("Remaining hit points ${player.hitPoints}")


                    println("what would you do?\npunch, run or pet?")
                    makeBattleDecision(player, petZombie, zombie, playerStrength)

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
                    makeBattleDecision(player, petZombie, zombie, playerStrength)
                } else {
                    zombie.tamed = true
                    println("Success! ${zombie.name} is now your friend")
                    addZombie(zombie)
                    println("${zombie.name} is now in your Zombie Pocket")
                }
            }
            "zombie bite" -> {
                println("who should attack?")
                for (element in zombieArrayListPairs) println(
                    "${element.first.petZombieName}, strength ${element.first.petZombieDamage}, hit points ${element
                        .first.petZombieHitPoints}"
                )
                var selectedPetZombiePair = retrieveZombie(readLine()!!)
                println("${selectedPetZombiePair.second.name} has been selected")

                if (zombieArrayListPairs.contains(selectedPetZombiePair)) {
                    petZombieBite(
                        selectedPetZombiePair.second, zombie
                    )
                    if (zombie.hitPoints > 0 && petZombie != null) {
                        petZombie.hitPoints = petZombie.hitPoints - zombie.zombieStrength
                        println("${petZombie.name} was bit \nRemaining hit points ${petZombie.hitPoints}")
                        println("what would you do? \npunch, run, pet or zombie bite?")
//                            decision = readLine()!!
                        makeBattleDecision(player, petZombie, zombie, playerStrength)
                    } else println("${zombie.name} is dead")
                } else {
                    println("you don't have that zombie. Who should attack?")
                    selectedPetZombiePair = retrieveZombie(readLine()!!)
                    petZombieBite(selectedPetZombiePair.second, zombie)
                }
            }
            else -> {
                println("please make a good decision")
                makeBattleDecision(player, petZombie, zombie, playerStrength)
            }
        }
    }

//    fun makeDecision() {
//    when(readLine()!!){
//
//    }
//    }

}

fun zombieMoreTameable(startingHitPoints: Int, zombie: Zombie) {


    when {
        zombie.hitPoints > startingHitPoints * 0.5 && zombie.hitPoints != startingHitPoints -> {

            zombie.changingTamable =
                zombie.changingTamable * 0.75
            println(zombie.hitPoints)
            println("${zombie.changingTamable}")
        }
        zombie.hitPoints <= startingHitPoints * 0.5 && zombie.hitPoints >= startingHitPoints * 0.2 -> {
            zombie.changingTamable = zombie.changingTamable * 0.5
            println(zombie.hitPoints)
            println("${zombie.changingTamable}")
        }

        zombie.hitPoints < startingHitPoints * 0.2 && zombie.hitPoints > startingHitPoints * 0.1 -> {
            zombie.changingTamable = zombie.changingTamable * 0.2
            println(zombie.hitPoints)
            println("${zombie.changingTamable}")
        }
        else -> println("this shouldn't happen")

    }

}

fun punch(playerStrength: Int, zombie: Zombie): Int {
    zombie.hitPoints = zombie.hitPoints - playerStrength

    return zombie.hitPoints
}

fun petZombieBite(selectedPetZombie: Zombie?, zombie: Zombie) {


    if (selectedPetZombie != null) {
        println("${selectedPetZombie.name} attacks")
        zombie.hitPoints = zombie.hitPoints - selectedPetZombie.zombieStrength
        println("${zombie.name} has ${zombie.hitPoints} hit points remaining")
    }
}

private fun tameZombie(zombie: Zombie) {
    var dice = 6
    val tameNumber = Random().nextInt(dice)
    println("$tameNumber dice, ${zombie.changingTamable} tamable number")
    if (tameNumber >= zombie.changingTamable) zombie.tamed = true
}

fun openInventory() {
    for (element in zombieArrayListPairs) println(
        "${element.first.petZombieName}, strength ${element.first.petZombieDamage}, hit points ${element.first.petZombieHitPoints}"
    )
}
