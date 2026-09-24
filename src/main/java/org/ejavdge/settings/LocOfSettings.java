package org.ejavdge.settings;
import org.ejavdge.scalar.IntellijScalar;
import org.ejavdge.web.context.Location;

public final class LocOfSettings implements IntellijScalar<Location> {
    private final EjState state;

    public LocOfSettings(final EjState s) {
        this.state = s;
    }

    @Override
    public Location value() {
        return new EjConfiguration(this.state, "").location();
    }
}
