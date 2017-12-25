package fsm

/**
 * Builds and operates state machines
 */
class StateMachine private constructor(private val initialState: BaseState) {
    private lateinit var currentState: State
    private val states = mutableListOf<State>()

    fun state(stateName: BaseState, init: State.() -> Unit) {
        val state = State(stateName)
        state.init()

        states.add(state)
    }

    /**
     * Translates state name to an object
     */
    private fun getState(stateType: BaseState): State {
        return states.firstOrNull { stateType.javaClass == it.name.javaClass } ?:
                throw NoSuchElementException(stateType.javaClass.canonicalName)
    }

    /**
     * Initializes the [StateMachine] and puts it on the first state
     */
    fun initialize() {
        currentState = getState(initialState)
        currentState.enter()
    }

    /**
     * Gives the FSM an event to act upon, state is then changed and actions are performed
     */
    fun acceptEvent(e: BaseEvent) {
        try {
            val edge = currentState.getEdgeForEvent(e)

            // Indirectly get the state stored in edge
            // The syntax is weird to guarantee that the states are changed
            // once the actions are performed
            // This line just queries the next state name (Class) from the
            // state list and retrieves the corresponding state object.
            val state = edge.applyTransition { getState(it) }
            state.enter()

            currentState = state
        } catch (exc: NoSuchElementException) {
            throw IllegalStateException("This state doesn't support " +
                    "transition on ${e.javaClass.simpleName}")
        }
    }

    companion object {
        fun buildStateMachine(initialStateName: BaseState, init: StateMachine.() -> Unit): StateMachine {
            val stateMachine = StateMachine(initialStateName)
            stateMachine.init()
            return stateMachine
        }
    }
}
