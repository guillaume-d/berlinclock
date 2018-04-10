package com.catalyst.interviews.berlinclock;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BerlinClockBDDSteps {

    private TimeConverter berlinClock;
    private String time;

    @Given("^the time is (.*)$")
    public void theTimeIs(String time) throws Throwable {
        this.time = time;
    }

    @Then("^the clock should look like:$")
    public void theClockShouldLookLike(String expectedBerlinClockOutput) throws Throwable {
        assertThat(berlinClock.convertTime(time), is(expectedBerlinClockOutput));
    }
}
