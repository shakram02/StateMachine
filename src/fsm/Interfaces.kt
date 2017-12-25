package fsm

/**
 * Represents an event name. This class will be referred to as "event name" in
 * docs. To create an event just subclass [BaseEvent] e.g
 *
 * class MyEvent: fsm.BaseEvent()
 *
 *  You shouldn't add body to the subclasses as it doesn't make much sense
 */
interface BaseEvent

/**
 * Represents a state name. This class will be referred to as "state name" in
 * docs. Serves the same purpose of [BaseEvent]
 */
interface  BaseState
