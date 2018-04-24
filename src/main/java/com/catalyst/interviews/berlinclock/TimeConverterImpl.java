package com.catalyst.interviews.berlinclock;

import static com.catalyst.interviews.berlinclock.TimeConverterImpl.LampState.*;
import static com.catalyst.interviews.commons.Logging.logged;
import static com.catalyst.interviews.commons.Streams.join;

import com.catalyst.interviews.berlinclock.TimeConverterImpl.LampState;
import java.util.Arrays;

/**
 * TimeConverter implementation optimized for readability only.
 */
public class TimeConverterImpl implements TimeConverter {

    @Override
    public String convertTime(String time) {
        String[] time_parts = time.split(":");

        int hours = logged(Integer.parseInt(time_parts[0]), this, "hours");
        assert hours <= 24 && hours >= 0;
        int minutes = logged(Integer.parseInt(time_parts[1]), this, "minutes");
        assert minutes < 60 && minutes >= 0;
        int seconds = logged(Integer.parseInt(time_parts[2]), this, "seconds");
        assert seconds < 60 && seconds >= 0;

        return join("\n",
            new Lamps(1, YELLOW).counting((seconds + 1) % 2),

            new Lamps(4, RED).counting(hours / 5),
            new Lamps(4, RED).counting(hours % 5),

            new Lamps(11, YELLOW).counting(minutes / 5).changed_to_if_on_at(RED, 3, 6, 9),
            new Lamps(4, YELLOW).counting(minutes % 5)
        );
    }

    /**
     * States a lamp can be in.
     *
     * NOTE: Nested only so as to allow static imports in the nesting class.
     */
    enum LampState {
        OFF("O"),
        YELLOW("Y"),
        RED("R");

        /** The text associated to the state. */
        private final String text;

        LampState(String text) {
            assert text.length() >= 1;
            this.text = text;
        }

        public String toString() {
            return text;
        }
    }

}

/**
 * One or more lamps that can each be in a LampState state,
 *
 * NOTE: This is an implementation detail so we keep it out of sight here.
 */
class Lamps {

    private LampState[] states;

    /** The default state the lamps are in to signal that 1 has to be added. */
    private LampState on_default_state;

    public Lamps(int count, LampState on_default_state) {
        assert count >= 1;
        assert on_default_state != OFF;
        states = new LampState[count];
        Arrays.fill(states, OFF);
        this.on_default_state = on_default_state;
    }

    /**
     * Returns new lamps whose states represent the given number.
     */
    public Lamps counting(int n) {
        assert n >= 0;
        Lamps counting_lamps = new Lamps(this.states.length, this.on_default_state);
        Arrays.fill(counting_lamps.states, 0, n, this.on_default_state);
        return counting_lamps;
    }

    /**
     * Returns new lamps whose states at the given positions (1-based)
     *  are changed to the given on_state if on.
     */
    public Lamps changed_to_if_on_at(LampState on_state, int... ordinals) {
        assert ordinals.length >= 1;
        Lamps changed_lamps = new Lamps(this.states.length, this.on_default_state);
        changed_lamps.states = Arrays.copyOf(this.states, this.states.length);
        for (int ordinal : ordinals) {
            int i = ordinal - 1;
            changed_lamps.states[i] = this.states[i] == this.on_default_state ?
                on_state :
                this.states[i];
        }
        return changed_lamps;
    }

    /** Returns the textual represenation of the lamps. */
    public String toString() {
        return logged(join("", states), this, "toString()");
    }
}
