package fsm

/**
 * Represents an event name. This class will be referred to as "event name" in
 * docs. To create an event just subclass [BaseEvent] e.g
 *
 * class MyEvent: fsm.BaseEvent()
 *
 *  You shouldn't add body to the subclasses as it doesn't make much sense
 */
abstract class BaseEvent : Base()

/**
 * Represents a state name. This class will be referred to as "state name" in
 * docs. Serves the same purpose of [BaseEvent]
 */
abstract class BaseState : Base()

abstract class Base {
    final override fun equals(other: Any?): Boolean {
        val e = other as Base
        return this.javaClass == e.javaClass
    }

    final override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}

class DFAError(e: String) : Error(e)