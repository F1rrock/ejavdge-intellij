package org.ejavdge.settings;
import org.ejavdge.scalar.num.Num;
import org.ejavdge.scalar.text.Text;
import org.ejavdge.web.context.Location;

public final class LocOfSettings {
    private final EjState state;

    public LocOfSettings(final EjState s) {
        this.state = s;
    }

    public Location location() {
        return new Location(
            new Text.Of(this.state.clientPath),
            new Text.Of(this.state.baseUrl),
            new Num.Of(this.state.port)
        );
    }
}
