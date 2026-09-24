package org.ejavdge.settings;

import org.ejavdge.scalar.IntellijScalar;
import org.ejavdge.app.setup.PresetDriver;
import org.ejavdge.auth.Session;
import org.ejavdge.contest.ContestForm;
import org.ejavdge.error.IntellijError;
import org.ejavdge.web.driver.WebDriver;

public final class FormOfSettings implements IntellijScalar<ContestForm> {
    private final WebDriver driver;
    private final EjState state;

    public FormOfSettings(final EjState s) {
        this(new PresetDriver(), s);
    }

    public FormOfSettings(final WebDriver d, final EjState s) {
        this.driver = d;
        this.state = s;
    }

    @Override
    public ContestForm value() throws IntellijError {
        final var location = new LocOfSettings(this.state).value();
        return new ContestForm(
            this.driver,
            location,
            new Session(
                this.driver,
                location,
                new CredOfSettings(this.state).value()
            )
        );
    }
}
