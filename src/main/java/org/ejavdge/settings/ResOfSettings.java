package org.ejavdge.settings;

import org.ejavdge.app.setup.PresetDriver;
import org.ejavdge.auth.Session;
import org.ejavdge.contest.ContestResource;
import org.ejavdge.web.driver.WebDriver;

public final class ResOfSettings {
    private final WebDriver driver;
    private final EjState state;

    public ResOfSettings(final EjState s) {
        this(new PresetDriver(), s);
    }

    public ResOfSettings(final WebDriver d, final EjState s) {
        this.driver = d;
        this.state = s;
    }

    public ContestResource resource() {
        return new ContestResource(
            this.driver,
            new LocOfSettings(this.state).location(),
            new Session(
                this.driver,
                new LocOfSettings(this.state).location(),
                new CredOfSettings(this.state).credentials()
            )
        );
    }
}
