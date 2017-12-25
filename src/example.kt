import fsm.BaseEvent
import fsm.BaseState
import fsm.StateMachine

// Inherit your custom events and states from provided base classes
class GoUp : BaseEvent()

class Initial : BaseState()
class Input : BaseState()
class Repeat : BaseState()

fun main(args: Array<String>) {

    val m = StateMachine.buildStateMachine(Initial()) {
        state(Initial()) {
            action {
                println("Entered [$it]")
            }
            edge(GoUp(), Input()) {
                action {
                    println("Going to Input")
                }
            }
        }
        state(Input()) {
            action {
                println("Entered [$it]")
            }

            action {
                println("another action is being executed")
            }

            edge(GoUp(), Repeat()) {
                action {
                    println("Going UP")
                }
            }
        }

        state(Repeat()) {
            action {
                println("Entered [$it]")
            }
            action {
                println("Will get bored!")
            }
            edge(GoUp(), Repeat()) {
                action {
                    println("Repeating")
                }
            }
        }
    }

    m.initialize()
    m.acceptEvent(GoUp())
    m.acceptEvent(GoUp())
    m.acceptEvent(GoUp())
    m.acceptEvent(GoUp())
    m.acceptEvent(GoUp())
}